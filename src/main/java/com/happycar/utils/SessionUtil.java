package com.happycar.utils;

import javax.servlet.http.HttpSession;

import com.happycar.Constant;
import com.happycar.model.HcSysUser;

public class SessionUtil {
	
	public static HcSysUser getLoginUser(HttpSession session){
		HcSysUser user = (HcSysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		return user;
	}
	
}
