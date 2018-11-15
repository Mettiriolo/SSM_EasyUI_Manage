package com.yali.service;

import java.util.List;

import com.yali.model.Auth;

import net.sf.json.JSONArray;

public interface AuthService {
	public JSONArray findAllAuth(String parentId);
	public JSONArray getAuthsByParentId(String parentId,List<Integer> authIdsList);
	public int addAuth(Auth auth);
	public int updateAuth(Auth auth);
	public int updateState(String state,String parentId);
	public boolean isLeaf(String authId);
	public boolean isOneParent(String parentId);
	public int deleteAuth(String authId);
	public JSONArray getAuthsByParentIdAndAuthIds(String parentId,String[] authIdsList);
}
