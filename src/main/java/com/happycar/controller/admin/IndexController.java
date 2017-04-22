package com.happycar.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happycar.Constant;
import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysMenuDao;
import com.happycar.model.HcSysMenu;
import com.happycar.model.HcSysUser;
import com.happycar.vo.MenuVO;

@Controller("manageIndex")
@RequestMapping("/admin")
public class IndexController extends BaseController {
	
	@Resource
	private SysMenuDao menuDao;
	
	@RequestMapping("/index")
	public void index(HttpSession session,Model model){
		
	}
	
	@RequestMapping("/left")
	public void left(HttpSession session,Model model){
		HcSysUser user = (HcSysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
//		List<HcSysMenu> menus = menuDao.getMenusByTypeAndUserId(0, user.getId());
		List<HcSysMenu> firstMenus = menuDao.getMenusByTypeAndLevelOrderBySeqAsc(0,1);
		List<HcSysMenu> secMenus = menuDao.getMenusByTypeAndLevelOrderBySeqAsc(0,2);
		List<MenuVO> menus = new ArrayList<MenuVO>();
		for (HcSysMenu sysMenu : firstMenus) {
			MenuVO menu = new MenuVO();
			menu.setName(sysMenu.getName());
			menu.setUrl(sysMenu.getUrl());
			for (HcSysMenu sysMenu1 : secMenus) {
				if(sysMenu1.getPid().intValue()==sysMenu.getId().intValue()){
					MenuVO menu1 = new MenuVO();
					menu1.setName(sysMenu1.getName());
					menu1.setUrl(sysMenu1.getUrl());
					menu.getChilds().add(menu1);
				}
			}
			menus.add(menu);
		}
		model.addAttribute("menus", menus);
	}
	
	@RequestMapping("/top")
	public void top(HttpSession session,Model model){
		HcSysUser user = (HcSysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		model.addAttribute("user", user);
	}
}
