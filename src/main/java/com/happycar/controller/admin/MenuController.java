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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysMenuDao;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysMenu;
import com.happycar.service.SysMenuService;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;
import com.happycar.vo.ZTreeVO;


@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysMenuDao menuDao;
	@Resource
	private SysMenuService menuService;
	

	@RequestMapping(value="/ztree", method=RequestMethod.GET)
	public void ztree(Integer type, Model model) {
		List<ZTreeVO> treeVOs = menuService.getTree(type);
		model.addAttribute("tree", treeVOs);
	}
	
	@RequestMapping("/index")
	public void index(String type,Model model,Pageable pageable){
		model.addAttribute("type", type);
	}
	
	@RequestMapping("/list")
	public void list(final String name,
					 final String type,
					 final String level,
					 final String pid,
			Model model,@PageableDefault(sort="seq",direction=Direction.ASC)Pageable pageable){
		Page<HcSysMenu> page = menuDao.findAll(new Specification<HcSysMenu>() {
			
			@Override
			public Predicate toPredicate(Root<HcSysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(StringUtils.isNotEmpty(type)){
		            list.add(cb.equal(root.get("type").as(String.class), type));  
		        }
		        if(StringUtils.isNotEmpty(level)){
		            list.add(cb.equal(root.get("level").as(Integer.class), level));  
		        }
		        if(StringUtils.isNotEmpty(pid)){
		            list.add(cb.equal(root.get("pid").as(Integer.class), pid));  
		        }
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		model.addAttribute("level", level);
		model.addAttribute("pid", pid);
	}
	
	@RequestMapping("/add")
	public void add(Integer type,Integer pid,Model model){
		model.addAttribute("type", type);
		model.addAttribute("pid", pid);
		int maxSeq = menuDao.findMaxSeqByPidAndType(pid,type);
		model.addAttribute("seq", maxSeq+1);
	}
	
	@RequestMapping("/save")
	public void save(HcSysMenu menu,Model model){
		if(StringUtils.isEmpty(menu.getName())){
			MessageUtil.fail("菜单名不能为空！", model);
			return;
		}
		if(menu.getType()==null){
			MessageUtil.fail("类型不能为空！", model);
			return;
		}
		menu.setAddTime(new Date());
		if(menu.getPid()==0){
			menu.setLevel(1);
		}else{
			HcSysMenu pmenu = menuDao.findOne(menu.getPid());
			menu.setLevel(pmenu.getLevel()+1);
		}
		menuDao.save(menu);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcSysMenu menu = menuDao.findOne(id);
		model.addAttribute("menu", menu);
	}
	
	@RequestMapping("/update")
	public void update(HcSysMenu menu,Model model){
		if(StringUtils.isEmpty(menu.getName())){
			MessageUtil.fail("菜单名不能为空", model);
			return;
		}
		HcSysMenu orimenu = menuDao.findOne(menu.getId());
		BeanUtil.copyProperty(orimenu, menu);
		orimenu.setUpdateTime(new Date());
		menuDao.save(orimenu);
		MessageUtil.success("操作成功", model);
	}
	
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		menuDao.delete(id);
		menuDao.deleteByPid(id);
		MessageUtil.success("删除成功", model);
	}
}
