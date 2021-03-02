package com.findit.config;
import com.findit.entity.User;
import com.findit.user.controller.UserController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class AppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
		me.setBaseUploadPath("/img");
	}

	@Override
	public void configRoute(Routes me) {
		// 设置控制器映射路径
		me.add("user",UserController.class);
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/findit_db?characterEncoding=utf8", "root", "");
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
	    me.add(arp);
	    arp.addMapping("user_info","user_id",User.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
