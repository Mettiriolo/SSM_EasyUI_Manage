package com.yali.dao;

import java.util.List;
import java.util.Map;

import com.yali.model.Auth;


public interface AuthDao {
	public List<Auth> findAllAuthByParentId(String ParentId);
	public Auth findAuthById();
	public List<Auth> getAuthsByParentId(Map<String, Object> map);
	public Integer hasChildren(Map<String, Object> map);
	public int addAuth(Auth auth);
	public int updateAuth(Auth auth);
	public int updateState(String state,String parentId);
	public int isLeaf(String authId);
	public int isOneParent(String parentId);
	public int deleteAuth(String authId);
}
