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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysRightDao;
import com.happycar.dao.SysRoleDao;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysRole;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRightDao rightDao;
	@Resource
	private SysRoleDao roleDao;
	
	@RequestMapping("/index")
	public void index(final String name,final String companyId,final String type,Model model,Pageable pageable){
		Page<HcSysRole> page = roleDao.findAll(new Specification<HcSysRole>() {
			
			@Override
			public Predicate toPredicate(Root<HcSysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(StringUtils.isNotEmpty(companyId)){  
		            list.add(cb.equal(root.get("companyId").as(String.class), companyId));  
		        }
		        if(StringUtils.isNotEmpty(type)){
		            list.add(cb.equal(root.get("type").as(String.class),type));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("name", name);
	}
	
	@RequestMapping("/add")
	public void add(Model model){
		List<HcSysRole> manageRoles = roleDao.findAllByIsDeleted(0);
		model.addAttribute("manageRoles", manageRoles);
	}
	
	@RequestMapping("/save")
	public void save(HcSysRole role,Model model){
		if(StringUtils.isEmpty(role.getName())){
			MessageUtil.fail("角色名不能为空！", model);
			return;
		}
		if(roleDao.findByNameAndIsDeleted(role.getName(),0)!=null){
			MessageUtil.fail("角色名已存在！", model);
			return;
		}
		role.setAddTime(new Date());
		role.setIsDeleted(0);
		roleDao.save(role);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcSysRole role = roleDao.findOne(id);
		model.addAttribute("role", role);
	}
	
	@RequestMapping("/update")
	public void update(HcSysRole role,Model model){
		if(StringUtils.isEmpty(role.getName())){
			MessageUtil.fail("角色名不能为空", model);
			return;
		}
		HcSysRole oriRole = roleDao.findOne(role.getId());
		//有更改名称
		if(!oriRole.getName().equals(role.getName())){
			if(roleDao.findByNameAndIsDeleted(role.getName(),0)!=null){
				MessageUtil.fail("角色名已存在", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriRole, role);
		oriRole.setUpdateTime(new Date());
		roleDao.save(oriRole);
		MessageUtil.success("操作成功", model);
	}
	
	
	@RequestMapping("/del")
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		roleDao.delete(id);
		MessageUtil.success("删除成功", model);
	}
}
