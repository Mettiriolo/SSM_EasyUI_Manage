package com.yali.dao;


import java.util.List;
import java.util.Map;

import com.yali.model.User;

public interface UserDao {
	public User login(User user);
	public List<User> findAllUserByPageAndSearch(Map<String, Object> map);
	public int addUser(User user);
	public int deleteUser(int userId);
	public int isExistUserName(String userName);
	public User findUserById(Integer userId);
}
