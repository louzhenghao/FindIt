package com.findit.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.findit.user.server.UserServer;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	
	
	public void register() {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String info = getPara("info");
			String ans = new UserServer().register(info);
			out.print(ans);
			out.close();
			renderNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login() {
		System.out.println("user login");
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream out = response.getOutputStream();
			String phone = getPara("phone");
			String pwd = getPara("pwd");
			String ans = new UserServer().login(phone, pwd);
			out.print(ans);
			out.close();
			renderNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String info = getPara("info");
			String ans = new UserServer().update(info);
			out.print(ans);
			out.close();
			renderNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePwd() {
		try {
			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String phone = getPara("phone");
			String pwd = getPara("pwd");
			String ans = new UserServer().updatePwd(phone,pwd);
			out.print(ans);
			out.close();
			renderNull();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
