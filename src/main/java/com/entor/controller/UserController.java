package com.entor.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entor.dao.UserDao;
import com.entor.entity.PublicNumber;
import com.entor.entity.User;
import com.entor.service.PublicNumberService;
import com.entor.service.UserService;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	@Resource
	private UserService userService;
	@Resource
	private PublicNumberService publicNumberService;
	@Resource
	private UserDao userDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	// 查询已有公众号
	@RequestMapping("queryExistPid")
	@ResponseBody
	public List<PublicNumber> queryExistPid(String uids) {

		List<PublicNumber> list = publicNumberService.queryExistPid(PublicNumber.class, uids);
		return list;
	}


	// 上传
	@RequestMapping("/uploadFile")
	public void uploadFile(@RequestParam("file") MultipartFile file) {// 多文件改为数组file[]，循环遍历即可

		String path = request.getServletContext().getRealPath("/upload/");
		System.out.println(path);
		if (!file.isEmpty()) {
			System.out.println("文件名称" + file.getOriginalFilename());
			try {
				file.transferTo(new File(path, file.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File file1 = new File(path, file.getOriginalFilename());
		List<User> list = null;
		try {
			// 获取工作簿
			Workbook wb = Workbook.getWorkbook(file1);
			// 获取工作表
			Sheet sheet = wb.getSheet(0);
			// 获取表行
			int rows = sheet.getRows();
			list = new ArrayList<>();
			for (int i = 1; i < rows; i++) {
				User user = new User();
				user.setId(sheet.getCell(0, i).getContents());
				user.setUsername(sheet.getCell(1, i).getContents());
				user.setPassword(sheet.getCell(2, i).getContents());
				user.setName(sheet.getCell(3, i).getContents());
				user.setSex(Integer.parseInt(sheet.getCell(4, i).getContents()));
				user.setPhone(sheet.getCell(5, i).getContents());
				user.setAddress(sheet.getCell(6, i).getContents());
				user.setEmail(sheet.getCell(7, i).getContents());
				user.setPhoto(sheet.getCell(8, i).getContents());
				user.setRole(Integer.parseInt(sheet.getCell(9, i).getContents()));
				user.setCreateTime(new Timestamp(System.currentTimeMillis()));
				list.add(user);
			}
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userDao.addMore(list);
	}

	// 下载
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String fileName) throws IOException {

		fileName = request.getParameter("fileName");
		File file1 = new File(fileName);
		try {
			// 工作簿
			WritableWorkbook wwb = Workbook.createWorkbook(file1);
			// 工作表
			WritableSheet ws = wwb.createSheet("用户信息", 0);
			// 单元格
			Label cell1 = new Label(0, 0, "编号");
			Label cell2 = new Label(1, 0, "账号");
			Label cell3 = new Label(2, 0, "密码");
			Label cell4 = new Label(3, 0, "姓名");
			Label cell5 = new Label(4, 0, "性别");
			Label cell6 = new Label(5, 0, "联系方式");
			Label cell7 = new Label(6, 0, "联系地址");
			Label cell8 = new Label(7, 0, "邮箱");
			Label cell9 = new Label(8, 0, "用户头像");
			Label cell10 = new Label(9, 0, "所属角色");
			Label cell11 = new Label(10, 0, "创建时间");
			ws.addCell(cell1);
			ws.addCell(cell2);
			ws.addCell(cell3);
			ws.addCell(cell4);
			ws.addCell(cell5);
			ws.addCell(cell6);
			ws.addCell(cell7);
			ws.addCell(cell8);
			ws.addCell(cell9);
			ws.addCell(cell10);
			ws.addCell(cell11);
			
			List<User> list = userDao.queryAll(User.class,new User());
			for (int i = 1; i < list.size(); i++) {
				User user = list.get(i);
				Label cell01 = new Label(0, i, user.getId());
				Label cell02 = new Label(1, i, user.getUsername());
				Label cell03 = new Label(2, i, user.getPassword());
				Label cell04 = new Label(3, i, user.getName());
				Label cell05 = new Label(4, i, user.getSex()+"");
				Label cell06 = new Label(5, i, user.getPhone());
				Label cell07 = new Label(6, i, user.getAddress());
				Label cell08 = new Label(7, i, user.getEmail());
				Label cell09 = new Label(8, i, user.getPhoto());
				Label cell010 = new Label(9, i, user.getRole()+"");
				Label cell011 = new Label(10, i, user.getCreateTime()+"");
				ws.addCell(cell01);
				ws.addCell(cell02);
				ws.addCell(cell03);
				ws.addCell(cell04);
				ws.addCell(cell05);
				ws.addCell(cell06);
				ws.addCell(cell07);
				ws.addCell(cell08);
				ws.addCell(cell09);
				ws.addCell(cell010);
				ws.addCell(cell011);
			}

			// 写入工作簿
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File(fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

	}
	
	
	@ModelAttribute
	public void setRequestAndResponseUser(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}

}
