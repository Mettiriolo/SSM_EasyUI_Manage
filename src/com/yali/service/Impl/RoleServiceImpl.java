package com.yali.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yali.dao.RoleDao;
import com.yali.model.Role;
import com.yali.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}
	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}
	@Override
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}
	@Override
	public int deleteRole(Integer roleId) {
		return roleDao.deleteRole(roleId);
	}
	@Override
	public Role findRoleById(Integer roleId) {
		return roleDao.findRoleById(roleId);
	}
	@Override
	public int updateDel(Role role) {
		return roleDao.updateDel(role);
	}
	@Override
	public String getAuthIdsById(Integer roleId) {
		return roleDao.getAuthIdsById(roleId);
	}
	@Override
	public String getRoleNameById(Integer roleId) {
		return roleDao.getRoleNameById(roleId);
	}
	@Override
	public JSONArray findRolesByPageAndSearch(Map<String, Object> map) {
		JSONArray jsonArray=new JSONArray();
		List<Role> roleList=roleDao.findRolesByPageAndSearch(map);
		for(int i=0;i<roleList.size();i++){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("roleId", roleList.get(i).getRoleId());
			jsonObject.put("roleName", roleList.get(i).getRoleName());
			jsonObject.put("authIds", roleList.get(i).getAuthIds());
			jsonObject.put("roleDescription", roleList.get(i).getRoleDescription());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	@Override
	public int roleAuthIdsUpdate(Map<String, Object> map) {
		return roleDao.roleAuthIdsUpdate(map);
	}
	@Override
	public JSONArray roleList() {
		JSONArray jsonArray=new JSONArray();
		List<Role> roleList=roleDao.roleList();
		for(int i=0;i<roleList.size();i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("roleId", roleList.get(i).getRoleId());
			jsonObject.put("roleName", roleList.get(i).getRoleName());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

}
