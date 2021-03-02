package com.findit.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.findit.entity.User;
import com.findit.util.DbUtil;
import com.google.gson.Gson;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserDao {
	
	public String findUserById(Integer id) {
		User u = User.dao.findById(id);
		return JsonKit.toJson(u);
	}
	
	public String login(String phone, String pwd) {
		List<Record> user = Db.find("select * from user_info where user_phonenumber = ?",phone);
		for(int i = 0 ; i < user.size() ; i++) {
			if(user.get(i).get("user_password").equals(pwd)){
				return "true";
			}
		}
		return "false";
	}
	
	public String findByPhoneNumber(String phone) {
		List<User> u = User.dao.find("select * from user_info where user_phonenumber = ?", phone);
		if(u.size() == 0)
			return "false";
		return JsonKit.toJson(u.get(0));
	}
	
	public boolean update(User u) {
		boolean flag = u.
				set("user_id", u.getUser_id()).
				set("user_nickname", u.getUser_nickname()).
				set("user_sex", u.getUser_sex()).
				set("user_age", u.getUser_age()).
				set("user_email", u.getUser_email()).
				set("user_age", u.getUser_age()).update();
		return flag;
	}
	
	public boolean register(User u) {
		List<User> list = User.dao.find("select * from user_info where user_phonenumber = ?",u.getUser_phonenumber());
		if(list.size() != 0)
			return false;
		return u.set("user_phonenumber", u.getUser_phonenumber())
				.set("user_password", u.getUser_password())
				.set("user_age", 0)
				.set("user_sex", "不告诉你")
				.set("user_nickname", "取个名字吧")
				.set("user_email", "@lff.com")
				.set("user_address", "可能是地球吧")
				.set("user_headportrait", "img/default.png")
				.save();
	}
	
	public boolean updatePwd(String phone, String pwd) {
		System.out.println("pwd = " + pwd + " phone =  " + phone);
		int x = DbUtil.executeUpdate("update user_info set user_password = ? where user_phonenumber = ?",
				new Object[] {pwd,phone});
		if(x != -1)
			return true;
		else
			return false;
	}
	

	
}
