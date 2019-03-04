package com.entor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entor.entity.PublicCondition;
import com.entor.service.BaseService;


@Controller
@RequestMapping("/baseController")
public class BaseController<T> {
	@Resource(name="baseService")
	private BaseService<T> baseService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@RequestMapping("/add")
	public void add(T t) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			//Oracle数据库sql语句传的字段值不能为NULL值,且数据库字段名要与实体类字段相同才能在页面显示
			baseService.add(t);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/deleteMore")
	public void deleteMore(T t,String ids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			baseService.deleteMore(t.getClass(),ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/update")
	public void update(T t) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			baseService.update(t);
			jo.put("state", 0);
			jo.put("msg", "成功修改记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "修改记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/queryByPage")
	public void queryByPage(T t,PublicCondition pc) throws Exception {
		
		//获取每页显示记录数
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
	
		String condition = getCondition(pc);
		
		//当前页
		int sp = 1;
		//总记录数
		int totals = baseService.getTotals(t.getClass());
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
		List<T> list = baseService.queryByPageCondition(t.getClass(),sp, pageSize,condition);
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
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<T> queryAll(Class<?> cls,T t) {
		List<T> list = baseService.queryAll(t.getClass(), t);
		return list;
	}
	

	
	
	
	/**
	 * 处理参数为日期格式
	 * @param binder
	 */
	@InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
	
	public static String getCondition(PublicCondition pc) {
		
		String condition = " 1=1 ";
		
		String id = pc.getId();
		String username = pc.getUsername();
		String name = pc.getName();
		String sex = pc.getSex();
		String publicName = pc.getPublicName();
		String publicId = pc.getPublicId();
		String publicType = pc.getPublicType();
		String u_id = pc.getU_id();
		String pid = pc.getPid();
		//Integer state = pc.getState();
		String tid = pc.getTid();
		
		
		/*Field[] fields = pc.getClass().getDeclaredFields();
		for(Field field : fields) {
			
		}
		Field f = pc.getClass().getDeclaredField(name);
		*/
		
		if(name!=null&&!name.equals("")&&!name.equalsIgnoreCase("null")){
			condition += " and name like '%"+name+"%'";
		}
		if(username!=null&&!username.equals("")&&!username.equalsIgnoreCase("null")){
			condition += " and username like '%"+username+"%'";
		}
		if(sex!=null&&!sex.equals("")&&!sex.equals("-1")&&!sex.equalsIgnoreCase("null")){
			condition += " and sex = "+sex;
		}
		if(id!=null&&!id.equals("")&&!id.equalsIgnoreCase("null")){
			condition += " and id like '%"+id+"%'";
		}
		if(publicName!=null&&!publicName.equals("")&&!publicName.equalsIgnoreCase("null")){
			condition += " and publicName like '%"+publicName+"%'";
		}
		if(publicId!=null&&!publicId.equals("")&&!publicId.equalsIgnoreCase("null")){
			condition += " and publicId like '%"+publicId+"%'";
		}
		if(publicType!=null&&!publicType.equals("")&&!publicType.equals("-1")){
			condition += " and publicType like '%"+publicType+"%'";
		}
		if(u_id!=null&&!u_id.equals("")&&!u_id.equalsIgnoreCase("null")){
			condition += " and u_id = "+"'"+u_id+"'";
		}
		if(pid!=null&&!pid.equals("")&&!pid.equalsIgnoreCase("null")){
			condition += " and pid = "+"'"+pid+"'";
		}
		/*if(state!=null&&!state.equals("")&&!state.equals("-1")){
			condition += " and state = "+state;
		}*/
		if(tid!=null&&!tid.equals("")&&!tid.equalsIgnoreCase("null")){
			condition += " and tid = "+"'"+tid+"'";
		}
		
		return condition;
	}
	
}
