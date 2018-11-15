package com.yali.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yali.dao.RoleDao;
import com.yali.dao.UserDao;
import com.yali.model.User;
import com.yali.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}


	@Override
	public JSONArray findAllUserByPageAndSearch(Map<String, Object> map) {
		List<User> userList=userDao.findAllUserByPageAndSearch(map);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<userList.size();i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("userId", userList.get(i).getUserId());
			jsonObject.put("userName", userList.get(i).getUserName());
			jsonObject.put("password", userList.get(i).getPassword());
			jsonObject.put("userType", userList.get(i).getUserType());
			jsonObject.put("roleId", userList.get(i).getRoleId());
			String roleName=roleDao.getRoleNameById(userList.get(i).getRoleId());
			jsonObject.put("roleName",roleName);
			jsonObject.put("userDescription", userList.get(i).getUserDescription());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}


	@Override
	public int addUser(User user) {
		if(this.isExistUserName(user.getUserName())) {
			return -1;
		}else {
			return userDao.addUser(user);
		}
	}


	@Override
	public List<Integer> addListUser(ArrayList<ArrayList<String>> uList) {
		List<Integer> resultList=new ArrayList<Integer>();
		for(int i=0;i<uList.size();i++) {
			ArrayList<String> list=uList.get(i);
				User user =new User();
				/*user.setUserName(list.get(0));
				user.setPassword(list.get(1));
				user.setUserType(Integer.parseInt(list.get(2)));
				user.setRoleId(Integer.parseInt(list.get(3)));
				user.setUserDescription(list.get(4));*/
				for(int j=0;j<list.size();j++) {
					if(user.getUserName()==null) {
						user.setUserName(list.get(j));
					}else if (user.getPassword()==null) {
						user.setPassword(list.get(j));
					}else if (user.getUserType()==0&&user.getRoleId()==-1) {
						user.setUserType(Integer.parseInt(list.get(j)));
					}else if (user.getRoleId()==-1) {
						user.setRoleId(Integer.parseInt(list.get(j)));
					}else {
						user.setUserDescription(list.get(j));
					}
				}
				if(this.isExistUserName(user.getUserName())) {
					resultList.add(-1);
				}else {
					int result=userDao.addUser(user);
					resultList.add(result);
				}
		}
		return resultList;
	}


	@Override
	public int deleteListUser(List<Integer> list) {
		int success=0;
		int error=0;
		for(int i=0;i<list.size();i++) {
			int deleteResult=userDao.deleteUser(list.get(i));
			if(deleteResult>0) {
				success++;
			}else {
				error++;
			}
		}
		if(success==list.size()) {
			return 1;
		}else{
			return error;
		}
	}


	@Override
	public boolean isExistUserName(String userName) {
		int exist=userDao.isExistUserName(userName);
		if(exist>0) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public ArrayList<ArrayList<String>> findUserListByIdList(List<Integer> userIds) {
		ArrayList<User> userList=new ArrayList<User>();
		ArrayList<ArrayList<String>> userListList=new ArrayList<ArrayList<String>>();
		for(int i=0;i<userIds.size();i++) {
			userList.add(userDao.findUserById(userIds.get(i)));
		}
		for(int i=0;i<userList.size();i++) {
			ArrayList<String> list=new ArrayList<String>();
			list.add(userList.get(i).getUserId()+"");
			list.add(userList.get(i).getUserName());
			list.add(userList.get(i).getPassword());
			list.add(userList.get(i).getRoleName());
			list.add(userList.get(i).getUserDescription());
			userListList.add(list);
		}
		return userListList;
	}
	
}
