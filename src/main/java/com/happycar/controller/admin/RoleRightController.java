package com.happycar.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysRightDao;
import com.happycar.dao.SysRoleDao;
import com.happycar.dao.SysRoleMenuDao;
import com.happycar.dao.SysRoleRightDao;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysRole;
import com.happycar.model.HcSysRoleMenu;
import com.happycar.model.HcSysRoleRight;
import com.happycar.service.SysMenuService;
import com.happycar.service.SysRightService;
import com.happycar.utils.MessageUtil;
import com.happycar.vo.ZTreeVO;


@Controller
@RequestMapping("/admin/roleRight")
public class RoleRightController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRightDao rightDao;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysRoleRightDao roleRightDao;
	@Resource
	private SysRoleMenuDao roleMenuDao;
	@Resource
	private SysMenuService menuService;
	@Resource
	private SysRightService rightService;
	
	@RequestMapping("/index")
	public void index(String roleId,Model model,Pageable pageable){
		List<HcSysRole> roles = roleDao.findAllByIsDeleted( 0);
		if(roles.size()==0) return;
		if(roleId==null){
			roleId=roles.get(0).getId().toString();
		}
		HcSysRole role = roleDao.findOne(Integer.parseInt(roleId));
		model.addAttribute("roleId", roleId);
		model.addAttribute("role", role);
		model.addAttribute("roles", roles);
	}
	
	@RequestMapping("/menuTree")
	public void menuTree(String type,String roleId,Model model,Pageable pageable){
		List<ZTreeVO> treeVOs = menuService.getTree(Integer.parseInt(type));
		List<HcSysRoleMenu> roleMenus = roleMenuDao.findByRoleId(Integer.parseInt(roleId));
		for (ZTreeVO treeVO : treeVOs) {
			treeVO.setParent(true);
			treeVO.setOpen(true);
			for (HcSysRoleMenu roleMenu : roleMenus) {
				if(roleMenu.getMenuId().intValue()==Integer.parseInt(treeVO.getId())){
					treeVO.setChecked(true);
					break;
				}
			}
		}
		
		model.addAttribute("tree", treeVOs);
	}
	
	@RequestMapping("/rightTree")
	public void rightTree(String roleId,Model model,Pageable pageable){
		List<ZTreeVO> treeVOs = rightService.getTree();
		List<HcSysRoleRight> roleRights = roleRightDao.findByRoleId(Integer.parseInt(roleId));
		for (ZTreeVO treeVO : treeVOs) {
			treeVO.setParent(true);
			treeVO.setOpen(true);
			for (HcSysRoleRight roleRight : roleRights) {
				if(roleRight.getRightId().intValue()==Integer.parseInt(treeVO.getId())){
					treeVO.setChecked(true);
					break;
				}
			}
		}
		model.addAttribute("tree", treeVOs);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(@RequestParam(value="menuIds[]")Integer[] menuIds,@RequestParam(value="rightIds[]")Integer[] rightIds,Integer roleId,HttpSession session, Model model) {
		//删除旧数据
		roleMenuDao.deleteByRoleId(roleId);
		roleRightDao.deleteByRoleId(roleId);
		//插入数据
		for (Integer menuId : menuIds) {
			if(menuId.intValue()==0) continue;
			HcSysRoleMenu roleMenu = new HcSysRoleMenu();
			roleMenu.setMenuId(menuId);
			roleMenu.setRoleId(roleId);
			roleMenuDao.save(roleMenu);
		}
		for (Integer rightId : rightIds) {
			if(rightId.intValue()==0) continue;
			HcSysRoleRight roleRight = new HcSysRoleRight();
			roleRight.setRightId(rightId);
			roleRight.setRoleId(roleId);
			roleRightDao.save(roleRight);
		}
		MessageUtil.success("更新成功", model);
	}

	
}
