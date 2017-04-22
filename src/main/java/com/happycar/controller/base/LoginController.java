package com.happycar.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happycar.Constant;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysUser;
import com.happycar.service.QiniuService;
import com.happycar.utils.MD5Util;
import com.happycar.utils.MessageUtil;

@Controller("login")
@RequestMapping("/")
public class LoginController extends BaseController {
	
	@Resource
	private SysUserDao userDao;
	@Resource
	private QiniuService qiniuService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String index(){
		return "/login/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String userName,String pwd,Model model,HttpSession session){
		HcSysUser user = userDao.findByUserNameAndIsDeleted(userName, 0);
		if(user==null){
			MessageUtil.fail("帐号不存在", model);
			return "/login/index";
		}
		if(!user.getPwd().equals(MD5Util.md5(pwd))){
			MessageUtil.fail("帐号或密码不对", model);
			return "/login/index";
		}
		session.setAttribute(Constant.SESSION_LOGIN_USER, user);
		return "redirect:/admin/index.do";
	}

	
	@RequestMapping(value="/logout")
	public String logout(Model model,HttpSession session){
		session.removeAttribute(Constant.SESSION_LOGIN_USER);
		session.removeAttribute(Constant.SESSION_LOGIN_COMPANY);
		return "redirect:/login.do";
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(){
		return qiniuService.getUploadToken();
	}
}