package com.yali.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yali.model.Auth;
import com.yali.model.User;
import com.yali.service.AuthService;
import com.yali.service.RoleService;
import com.yali.util.ResponseUtil;
import com.yali.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Resource
	private AuthService authService;
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/findAllAuth")
	public void findAllAuth(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String parentId="-1";
		JSONArray jsonArray=authService.findAllAuth(parentId);
		ResponseUtil.write(response, jsonArray);
	}
	
	
	//添加菜单
	@RequestMapping("/addAuth")
	public void addAuth(Auth auth,HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		auth.setState("open");
		int addResult=authService.addAuth(auth);
		if(addResult>0) {
			authService.updateState("closed", auth.getParentId()+"");
			result.put("success", true);
		}else {
			result.put("errorMsg", "保存失败!");
		}
		ResponseUtil.write(response, result);
	}
	
	//修改菜单
	@RequestMapping("/updateAuth")
	public void updateAuth(Auth auth,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		int updateResult=authService.updateAuth(auth);
		if(updateResult>0) {
			result.put("success", true);
		}else {
			result.put("errorMsg", "保存失败!");
		}
		ResponseUtil.write(response, result);
	}
	
	@RequestMapping("/deleteAuth")
	public void deleteAuth(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		String authId=request.getParameter("authId");
		String parentId=request.getParameter("parentId");
		if(authService.isLeaf(authId)) {
			result.put("errorMsg", "该菜单无法删除!");
		}else if (authService.isOneParent(parentId)) {
			authService.updateState("open", parentId);
			int deleteResult=authService.deleteAuth(authId);
			if(deleteResult>0) {
				result.put("success", true);
			}else {
				result.put("errorMsg", "删除失败!");
			}
		}
		ResponseUtil.write(response, result);
	}
	
	//获取菜单列表
	@RequestMapping("/menu")
	public void menu(User user,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String parentId="-1";
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		String authIds=roleService.getAuthIdsById(currentUser.getUserId());
		List<Integer> authIdsList=StringUtil.stringToArry(authIds);
		JSONArray jsonArray=authService.getAuthsByParentId(parentId, authIdsList);
		ResponseUtil.write(response, jsonArray);
	}
	
	@RequestMapping("/authMenu")
	public void authMenu(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String parentId="-1";
		String roleId=request.getParameter("roleId");
		String authIds=roleService.getAuthIdsById(Integer.parseInt(roleId));
		String[] authIdsList=authIds.split(",");
		JSONArray jsonArray=authService.getAuthsByParentIdAndAuthIds(parentId, authIdsList);
		ResponseUtil.write(response, jsonArray);
	}
}
