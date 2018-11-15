package com.yali.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yali.model.User;

import net.sf.json.JSONArray;

public interface UserService {
	
	public User login(User user);
	public JSONArray findAllUserByPageAndSearch(Map<String, Object> map);
	public int addUser(User user);
	public List<Integer> addListUser(ArrayList<ArrayList<String>> arrayList);
	public int deleteListUser(List<Integer> list);
	public boolean isExistUserName(String userName);
	public ArrayList<ArrayList<String>> findUserListByIdList(List<Integer> userIds);
}
