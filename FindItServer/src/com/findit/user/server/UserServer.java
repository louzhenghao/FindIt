package com.findit.user.server;

import java.util.List;

import com.findit.entity.User;
import com.findit.user.dao.UserDao;
import com.google.gson.Gson;
import com.jfinal.kit.JsonKit;

public class UserServer {

	public String login(String phone, String pwd) {
		return new UserDao().login(phone, pwd);
	}
	
	public String findByPhoneNumber(String phone) {
		return new UserDao().findByPhoneNumber(phone);
	}

	public String update(String info) {
		User u = new Gson().fromJson(info, User.class);
		if(new UserDao().update(u))
			return "true";
		else
			return "false";
	}

	public String register(String info) {
		User u = new Gson().fromJson(info,User.class);
		if(new UserDao().register(u))
			return "true";
		else
			return "false";
	}
	
	public String updatePwd(String phone, String pwd) {
		if(new UserDao().updatePwd(phone, pwd))
			return "true";
		else
			return "false";
	}

}
