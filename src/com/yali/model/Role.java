package com.yali.model;


public class Role {
	
	private Integer roleId;
	private String roleName;
	private String authIds;
	private String roleDescription;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthIds() {
		return authIds;
	}
	public void setAuthIds(String authIds) {
		this.authIds = authIds;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", authIds=" + authIds + ", roleDescription="
				+ roleDescription + "]";
	}
	
}
