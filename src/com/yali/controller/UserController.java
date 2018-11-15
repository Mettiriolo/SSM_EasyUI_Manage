package com.yali.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yali.model.PageBean;
import com.yali.model.User;
import com.yali.service.RoleService;
import com.yali.service.UserService;
import com.yali.util.FileUtil;
import com.yali.util.ResponseUtil;
import com.yali.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	

	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		User resultUser=userService.login(user);
		if(resultUser==null) {
			return "redirect:/login.jsp?error=1&userName="+user.getUserName()+"&password="+user.getPassword();
		}else {
			String roleName=roleService.getRoleNameById(resultUser.getRoleId());
			resultUser.setRoleName(roleName);
			session.setAttribute("currentUser", resultUser);
			return "main";
		}
	}
	
	@RequestMapping("/findAllUser")
	public void findAllUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String roleId=request.getParameter("s_roleId");
		String userName=request.getParameter("s_userName");
		User user=new User();
		if(StringUtil.isNotEmpty(roleId)) {
			user.setRoleId(Integer.parseInt(roleId));
		}
		if(StringUtil.isNotEmpty(userName)) {
			user.setUserName(userName);
		}
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean();
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setPageSize(Integer.parseInt(rows));
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pageBean", pageBean);
		map.put("user", user);
		JSONArray jsonArray=userService.findAllUserByPageAndSearch(map);
		ResponseUtil.write(response, jsonArray);
	}
	
	@RequestMapping("/addUser")
	public void addUser(User user,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result=new JSONObject();
		int addResult=userService.addUser(user);
		if(addResult>0) {
			result.put("success", true);
		}else if(addResult==-1){
			result.put("errorMsg", "此用户已存在");
		}else {
			result.put("errorMsg", "添加失败");
		}
	}
	//deleteUser
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		String userIds=request.getParameter("userIds");
		String[] userStr=userIds.split(",");
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<userStr.length;i++) {
			list.add(Integer.parseInt(userStr[i]));
		}
		int deleteResult=userService.deleteListUser(list);
		
		if(deleteResult==1) {
			result.put("success", true);
		}else {
			result.put("errorMsg", "删除失败:"+deleteResult);
		}
		ResponseUtil.write(response, result);
	}
	
	@RequestMapping("/uploadFile")
	public void uploadFile(@RequestParam("file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		result.put("success", true);
		String filePath=request.getServletContext().getRealPath("/")+file.getOriginalFilename();
		file.transferTo(new File(filePath));
		ArrayList<ArrayList<String>> arrayList=new ArrayList<ArrayList<String>> ();
		if(filePath.endsWith(".xls")) {
			arrayList=FileUtil.importExcelAndXls(filePath);
		}
		if(filePath.endsWith(".xlsx")) {
			arrayList=FileUtil.importExcelAndxlsx(filePath);
		}
		List<Integer> resultList=userService.addListUser(arrayList);
		int success=0;
		int exist=0;
		int error=0;
		for(int i=0;i<resultList.size();i++) {
			if(resultList.get(i)>0) {
				success++;
			}else if(resultList.get(i)==-1){
				exist++;
			}else {
				error++;
			}
		}
		if(success==arrayList.size()) {
			result.put("success", true);
		}else if(exist!=0){
			result.put("errorMsg", "数据重复:"+exist+""+";添加失败:"+error);
		}
		ResponseUtil.write(response, result);
	}
	
	//批量下载用户信息
	@RequestMapping("/downloadUser")
	public void downloadUser(HttpServletRequest request,HttpServletResponse response) {
		String userIdsArray=request.getParameter("userIds");
		String[] userIdArr=userIdsArray.split(",");
		List<Integer> userIds=new ArrayList<Integer>();
		for(int i=0;i<userIdsArray.length();i++) {
			userIds.add(Integer.parseInt(userIdArr[i]));
		}
		/*ArrayList<ArrayList<String>> userList=userService.findUserListByIdList(userIds);
		String filePath="";*/
		
	}
	
	@RequestMapping("/isExistName")
	public void isExistName(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userName=request.getParameter("userName");
		if(userService.isExistUserName(userName)) {
		    	response.getWriter().write("true");
			}else {
			    response.getWriter().write("false");
			}
		}
	
}
