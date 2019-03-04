package com.entor.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entor.entity.PublicCondition;
import com.entor.entity.PublicNumber;
import com.entor.entity.PublicNumberCheck;
import com.entor.entity.User;
import com.entor.entity.UserPublicNumber;
import com.entor.entity.UserPublicNumberVO;
import com.entor.service.PublicNumberCheckService;
import com.entor.service.PublicNumberService;
import com.entor.service.UserPublicNumberService;
import com.entor.service.UserService;

@Controller
@RequestMapping("/userPublicNumber")
public class UserPublicNumberController extends BaseController<UserPublicNumber> {
	@Resource
	private UserPublicNumberService userPublicNumberService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Resource
	private UserService userService;
	@Resource
	private PublicNumberService publicNumberService;
	@Resource
	private PublicNumberCheckService publicNumberCheckService;

	
	
	@Override
	public void queryByPage(UserPublicNumber t, PublicCondition pc) throws Exception {
				queryUid();
				//获取每页显示记录数
				String rows = request.getParameter("rows");
				String page = request.getParameter("page");
			
				String condition = getCondition(pc);
				
				//当前页
				int sp = 1;
				//总记录数
				int totals = userPublicNumberService.getTotals(t.getClass());
				//每页记录数
				int pageSize = Integer.parseInt(rows);
				//总页数
				int pageCounts = totals/pageSize;
				if(totals%pageSize!=0){
					pageCounts++;
				}
				try{
					sp = Integer.parseInt(page);
				}catch(Exception e){
					sp = 1;
				}
				if(sp>pageCounts){
					sp = pageCounts;
				}
				if(sp<1){
					sp = 1;
				}
				List<UserPublicNumberVO> list = userPublicNumberService.queryVO(UserPublicNumberVO.class, sp, pageSize, condition);
				try {
					PrintWriter out = response.getWriter();
					JSONObject jo = new JSONObject();
					jo.put("total", totals);
					jo.put("rows", list);
					String json = JSON.toJSONString(jo);
					System.out.println(json);
					out.write(json);
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//查询所有UserPublicNumberVO的id,name,publicId字段返回前台界面
	@RequestMapping("/queryAllVO")
	@ResponseBody
	public List<UserPublicNumberVO> queryAllVO() {
		List<UserPublicNumberVO> list = userPublicNumberService.queryVOAll(UserPublicNumberVO.class);
		return list;
	}
	
	//通过审核成功绑定公众号
	@RequestMapping("/savePublicNumber")
	public void savePublicNumber(String uid,String pid) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
				//先删除用户已绑定的所有公众号
				userPublicNumberService.deleteUserPublicNumByUid(UserPublicNumber.class, uid);

			//添加用户公众号绑定
			List<UserPublicNumber> list = new ArrayList<>();
			//for(String uid:uids.split(",")) {
			//	for(String pid:pids.split(",")) {
					UserPublicNumber userPublicNumber = new UserPublicNumber();
					userPublicNumber.setU_id(uid);
					userPublicNumber.setPid(pid);
					list.add(userPublicNumber);
			//	}
			//}
			userPublicNumberService.addMore(list);
			
			
			jo.put("state", 0);
			jo.put("msg", "公众号分配成功");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "公众号分配失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	
	

	//页面显示数据
	@RequestMapping("/queryUid")
	@ResponseBody
	public List<User> queryUid() {
		User user = new User();
		List<User> list = userService.queryAll(user.getClass(), user);
		System.out.println(list);
		return list;
	}
	
	//页面显示数据
	@RequestMapping("/queryPublicId")
	@ResponseBody
	public List<PublicNumber> queryPublicId() {
		PublicNumber publicNumber = new PublicNumber();
		List<PublicNumber> list = publicNumberService.queryAll(publicNumber.getClass(), publicNumber);
		System.out.println(list);
		return list;
	}
	
	
	
	@ModelAttribute
	public void setRequestAndResponseUserPubNum(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}

	
	
	

}
