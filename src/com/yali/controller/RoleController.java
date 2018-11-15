package com.yali.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yali.model.PageBean;
import com.yali.model.Role;
import com.yali.service.RoleService;
import com.yali.service.UserService;
import com.yali.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/findAllRole")
	public void findAllRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Role role=new Role();
		String roleName=request.getParameter("s_roleName");
		role.setRoleName(roleName);
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean();
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setPageSize(Integer.parseInt(rows));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("role", role);
		map.put("pageBean", pageBean);
		JSONArray jsonArray=roleService.findRolesByPageAndSearch(map);
		ResponseUtil.write(response, jsonArray);
	}
	@RequestMapping("/roleList")
	public void roleList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONArray roleList=roleService.roleList();
		ResponseUtil.write(response, roleList);
	}
	@RequestMapping("/roleAuthIdsUpdate")
	public void roleAuthIdsUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject result=new JSONObject();
		String roleId=request.getParameter("roleId");
		String authIds=request.getParameter("authIds");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("authIds", authIds);
		map.put("roleId", Integer.parseInt(roleId));
		int updateResult=roleService.roleAuthIdsUpdate(map);
		if(updateResult>0) {
			result.put("success", true);
		}else {
			result.put("errorMsg", "授权失败!");
		}
		ResponseUtil.write(response, result);
	}
	
}
