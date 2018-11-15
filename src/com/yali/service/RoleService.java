package com.yali.service;

import java.util.List;
import java.util.Map;

import com.yali.model.Role;

import net.sf.json.JSONArray;

public interface RoleService {
	public List<Role> findAllRole();
	public int addRole(Role role);
	public int updateRole(Role role);
	public int deleteRole(Integer roleId);
	public Role findRoleById(Integer roleId);
	public String getRoleNameById(Integer roleId);
	public int updateDel(Role role);
	public String getAuthIdsById(Integer roleId);
	
	public JSONArray findRolesByPageAndSearch(Map<String, Object> map);
	public int roleAuthIdsUpdate(Map<String, Object> map);
	public JSONArray roleList();
}
