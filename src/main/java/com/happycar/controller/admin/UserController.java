package com.happycar.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysRightDao;
import com.happycar.dao.SysRoleDao;
import com.happycar.dao.SysUserDao;
import com.happycar.dao.SysUserRoleDao;
import com.happycar.model.HcSysRole;
import com.happycar.model.HcSysUser;
import com.happycar.model.HcSysUserRole;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MD5Util;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRightDao rightDao;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysUserRoleDao userRoleDao;
	
	@RequestMapping("/index")
	public void index(final String userName,
			final String status,
			Model model,Pageable pageable){
		Page<HcSysUser> page = userDao.findAll(new Specification<HcSysUser>() {
			
			@Override
			public Predicate toPredicate(Root<HcSysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(userName)){  
		            list.add(cb.like(root.get("userName").as(String.class), "%"+userName+"%"));  
		        }
		        if(StringUtils.isNotEmpty(status)){  
		            list.add(cb.equal(root.get("status").as(String.class),status));  
		        }  
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("userName", userName);
		model.addAttribute("status1", status);
	}
	
	@RequestMapping("/add")
	public void add(Model model){
		List<HcSysRole> manageRoles = roleDao.findAllByIsDeleted(0);
		model.addAttribute("manageRoles", manageRoles);
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcSysUser user,@RequestParam("roleId")Integer[] roleId,Model model){
		if(user.getUserName()==null){
			MessageUtil.fail("用户名不能为空", model);
			return;
		}
		if(user.getPwd()==null){
			MessageUtil.fail("密码不能为空", model);
			return;
		}
		if(user.getPwd().length()<6){
			MessageUtil.fail("密码不能小于6位", model);
			return;
		}
		if(userDao.findByUserNameAndIsDeleted(user.getUserName(),0)!=null){
			MessageUtil.fail("用户名已存在", model);
			return;
		}
		user.setPwd(MD5Util.md5(user.getPwd()));
		user.setAddTime(new Date());
		user.setIsSuper(0);
		user.setIsDeleted(0);
		user.setNickName(user.getUserName());
		user.setStatus(0);
		userDao.save(user);
		for(Integer id : roleId){
			HcSysUserRole userRole = new HcSysUserRole();
			userRole.setRoleId(id);
			userRole.setUserId(user.getId());
			userRole.setAddTime(new Date());
			userRoleDao.save(userRole);
		}
		MessageUtil.success("添加用户成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		List<HcSysRole> manageRoles = roleDao.findAllByIsDeleted(0);
		List<HcSysUserRole> userRoles = userRoleDao.findByUserId(id);
		List<Integer> roleIds = new ArrayList();
		for(HcSysUserRole userRole : userRoles){
			roleIds.add(userRole.getRoleId());
		}
		model.addAttribute("manageRoles", manageRoles);
		model.addAttribute("roleIds", roleIds);
		HcSysUser user = userDao.findOne(id);
		user.setPwd(null);
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcSysUser user,@RequestParam("roleId")Integer[] roleId,Model model){
		if(user.getUserName()==null){
			MessageUtil.fail("用户名不能为空", model);
			return;
		}
//		if(user.getPwd()==null){
//			MessageUtil.fail("密码不能为空", model);
//			return;
//		}
		if(StringUtils.isNotEmpty(user.getPwd())&&user.getPwd().length()<6){
			MessageUtil.fail("密码不能小于6位", model);
			return;
		}
		HcSysUser oriUser = userDao.findOne(user.getId());
		//有更改用户名
		if(!oriUser.getUserName().equals(user.getUserName())){
			if(userDao.findByUserNameAndIsDeleted(user.getUserName(),0)!=null){
				MessageUtil.fail("用户名已存在", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriUser, user);
		if(StringUtils.isNotEmpty(user.getPwd())){
			oriUser.setPwd(MD5Util.md5(user.getPwd()));
		}
		oriUser.setUpdateTime(new Date());
		userDao.save(oriUser);
		//删除原来的角色
		userRoleDao.deleteByUserId(oriUser.getId());
		for(Integer id : roleId){
			HcSysUserRole userRole = new HcSysUserRole();
			userRole.setRoleId(id);
			userRole.setUserId(oriUser.getId());
			userRole.setAddTime(new Date());
			userRoleDao.save(userRole);
		}
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		userDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
	
	@RequestMapping("/disableUser")
	@Transactional
	public void disableUser(int id,Model model){
		userDao.disableUserById(id);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/enableUser")
	@Transactional
	public void enableUser(int id,Model model){
		userDao.enableUserById(id);
		MessageUtil.success("操作成功", model);
	}
}
