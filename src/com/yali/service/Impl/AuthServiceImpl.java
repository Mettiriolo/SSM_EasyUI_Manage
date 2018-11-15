package com.yali.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yali.dao.AuthDao;
import com.yali.model.Auth;
import com.yali.service.AuthService;
import com.yali.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Resource
	private AuthDao authDao;
	
	@Override
	public JSONArray findAllAuth(String parentId) {
		JSONArray jsonArray=this.findAllAuths(parentId);
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))) {
				continue;
			}else {
				jsonObject.put("children", findAllAuth(jsonObject.getString("id")));
			}
		}
		return jsonArray;
	}
	public JSONArray findAllAuths(String parentId) {
		List<Auth> authList=authDao.findAllAuthByParentId(parentId);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<authList.size();i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", authList.get(i).getAuthId());
			jsonObject.put("text", authList.get(i).getAuthName());
			jsonObject.put("authPath", authList.get(i).getAuthPath());
			jsonObject.put("parentId", authList.get(i).getParentId());
			jsonObject.put("authDescription", authList.get(i).getAuthDescription());
			jsonObject.put("state", authList.get(i).getState());
			jsonObject.put("iconCls", authList.get(i).getIconCls());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getAuthsByParentId(String parentId, List<Integer> authIdsList) {
		JSONArray jsonArray=this.getAuthByParentId(parentId, authIdsList);
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))) {
				continue;
			}else {
				jsonObject.put("children", getAuthsByParentId(jsonObject.getString("id"), authIdsList));
			}
		}
		return jsonArray;
	}
	
	public JSONArray getAuthByParentId(String parentId,List<Integer> authIdsList) {
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("parentId", parentId);
		mapParam.put("authIdsList", authIdsList);
		List<Auth> authList=authDao.getAuthsByParentId(mapParam);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<authList.size();i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", authList.get(i).getAuthId());
			jsonObject.put("text", authList.get(i).getAuthName());
			if(!(this.hasChildren(authList.get(i).getAuthId()+"", authIdsList))) {
				jsonObject.put("state", "open");
			}else {
				jsonObject.put("state", authList.get(i).getState());
			}
			jsonObject.put("iconCls", authList.get(i).getIconCls());
			JSONObject attributhObject=new JSONObject();
			attributhObject.put("authPath", authList.get(i).getAuthPath());
			jsonObject.put("attributes", attributhObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	private boolean hasChildren(String parentId,List<Integer> authIdsList) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("parentId", parentId);
		map.put("authIdsList", authIdsList);
		int authList=authDao.hasChildren(map);
		if(authList>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int addAuth(Auth auth) {
		return authDao.addAuth(auth);
	}
	@Override
	public int updateState(String state, String parentId) {
		return authDao.updateState(state, parentId);
	}
	@Override
	public int updateAuth(Auth auth) {
		return authDao.updateAuth(auth);
	}
	@Override
	public boolean isLeaf(String authId) {
		int resultCount=authDao.isLeaf(authId);
		if(resultCount==1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean isOneParent(String parentId) {
		int resultCount=authDao.isOneParent(parentId);
		if(resultCount==1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int deleteAuth(String authId) {
		return authDao.deleteAuth(authId);
	}
	@Override
	public JSONArray getAuthsByParentIdAndAuthIds(String parentId, String[] authIdsList) {
		JSONArray jsonArray=this.getAuthByParentIdAndAuthIds(parentId, authIdsList);
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))) {
				continue;
			}else {
				jsonObject.put("children", getAuthsByParentIdAndAuthIds(jsonObject.getString("id"),authIdsList));
			}
		}
		return jsonArray;
		
	}
	
	public JSONArray getAuthByParentIdAndAuthIds(String parentId,String[] authIdsList) {
		List<Auth> authList=authDao.findAllAuthByParentId(parentId);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<authList.size();i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", authList.get(i).getAuthId());
			jsonObject.put("text", authList.get(i).getAuthName());
			jsonObject.put("authPath", authList.get(i).getAuthPath());
			jsonObject.put("parentId", authList.get(i).getParentId());
			if(StringUtil.existStrArr(authList.get(i).getParentId()+"", authIdsList)) {
				jsonObject.put("checked", true);
			}
			jsonObject.put("authDescription", authList.get(i).getAuthDescription());
			jsonObject.put("state", authList.get(i).getState());
			jsonObject.put("iconCls", authList.get(i).getIconCls());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	

}
