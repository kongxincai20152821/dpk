package com.entor.controller;


import java.io.PrintWriter;
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
import com.entor.entity.PublicNumberCheck;
import com.entor.entity.UserPublicNumber;
import com.entor.service.PublicNumberCheckService;
@Controller
@RequestMapping("/publicNumberCheck")
public class PublicNumberCheckController extends BaseController<PublicNumberCheck> {
	@Resource
	private PublicNumberCheckService publicNumberCheckService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@RequestMapping("/savePublicNumberCheck")
	public void savePublicNumberCheck(String uids,String pids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			List<PublicNumberCheck> listPNC = new ArrayList<>();
			String[] uidPNC = uids.split(",");
			String[] pidPNC = pids.split(",");
			PublicNumberCheck pnc = null;
			for(int i=0;i<uidPNC.length;i++) {
				for(int j=0;j<pidPNC.length;j++) {
				pnc = new PublicNumberCheck();
				pnc.setU_id(uidPNC[i]);
				pnc.setPid(pidPNC[j]);
				pnc.setApplyTime(new Date());
				pnc.setAdvise("");
				pnc.setState(1);
				listPNC.add(pnc);
				}
				
				
			}
			publicNumberCheckService.addMore(listPNC);
		
			jo.put("state", 0);
			jo.put("msg", "审批录入成功");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "审批录入失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	
	@ModelAttribute
	public void setRequestAndResponseUserPNC(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}

	
}
