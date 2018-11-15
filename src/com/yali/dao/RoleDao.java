package com.yali.dao;

import java.util.List;
import java.util.Map;

import com.yali.model.Role;

public interface RoleDao {
	public List<Role> findAllRole();
	public int addRole(Role role);
	public int updateRole(Role role);
	public int deleteRole(Integer roleId);
	public Role findRoleById(Integer roleId);
	public int updateDel(Role role);
	public String getRoleNameById(Integer roleId);
	public String getAuthIdsById(Integer roleId);
	
	public List<Role> findRolesByPageAndSearch(Map<String, Object> map);
	public int roleAuthIdsUpdate(Map<String, Object> map);
	public List<Role> roleList();
}
