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
import com.happycar.dao.SysParamDao;
import com.happycar.dao.SysRightDao;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysParam;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;
import com.happycar.vo.ZTreeVO;


@Controller
@RequestMapping("/admin/sysParam")
public class SysParamController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRightDao rightDao;
	@Resource
	private SysParamDao sysParamDao;
	
	
	@RequestMapping("/index")
	public void index(){
	}
	
	@RequestMapping("/ztree")
	public void ztree(String type,String roleId,Model model,Pageable pageable){
		List<HcSysParam> sysParams = sysParamDao.findAll();
		List<ZTreeVO> treeVOs = new ArrayList<ZTreeVO>();
		//最上层根节点
		ZTreeVO rootVO = new ZTreeVO();
		rootVO.setId("0");
		rootVO.setpId("-1");
		rootVO.setName("顶层");
		rootVO.setParent(true);
		rootVO.setOpen(true);
		rootVO.setExt(type+"");
		treeVOs.add(rootVO);
		for (HcSysParam param : sysParams) {
			ZTreeVO nodeVO = new ZTreeVO();
			nodeVO.setId(param.getId()+"");
			nodeVO.setpId(param.getPid()+"");
			nodeVO.setName(param.getName()); 
			nodeVO.setParent(true);
			nodeVO.setOpen(true);
			treeVOs.add(nodeVO);
		}
		model.addAttribute("tree", treeVOs);
	}
	
	@RequestMapping("/list")
	public void list(final String key,final String name,final String pid,Model model,Pageable pageable){
		Page<HcSysParam> page = sysParamDao.findAll(new Specification<HcSysParam>() {
			
			@Override
			public Predicate toPredicate(Root<HcSysParam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(key)){  
		            list.add(cb.like(root.get("key").as(String.class), "%"+key+"%"));  
		        }
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(StringUtils.isNotEmpty(pid)){  
		            list.add(cb.equal(root.get("pid").as(String.class),pid));  
		        }
				//过滤已删除的数据
//		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("key", key);
		model.addAttribute("pid", pid);
		model.addAttribute("name", name);
	}
	
	@RequestMapping("/add")
	public void add(String pid,Model model){
		model.addAttribute("pid", pid);
	}
	
	@RequestMapping("/save")
	public void save(HcSysParam sysParam,Model model){
		if(StringUtils.isEmpty(sysParam.getKey())){
			MessageUtil.fail("参数键不能为空！", model);
			return;
		}
		if(StringUtils.isEmpty(sysParam.getName())){
			MessageUtil.fail("参数名不能为空！", model);
			return;
		}
		if(StringUtils.isEmpty(sysParam.getValue())){
			MessageUtil.fail("参数值不能为空！", model);
			return;
		}
		if(sysParamDao.findByKey(sysParam.getKey())!=null){
			MessageUtil.fail("参数键已存在！", model);
			return;
		}
		sysParam.setAddTime(new Date());
		sysParamDao.save(sysParam);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcSysParam sysParam = sysParamDao.findOne(id);
		model.addAttribute("sysParam", sysParam);
	}
	
	@RequestMapping("/update")
	public void update(HcSysParam sysParam,Model model){
		if(StringUtils.isEmpty(sysParam.getKey())){
			MessageUtil.fail("参数键不能为空！", model);
			return;
		}
		if(StringUtils.isEmpty(sysParam.getName())){
			MessageUtil.fail("参数名不能为空！", model);
			return;
		}
		if(StringUtils.isEmpty(sysParam.getValue())){
			MessageUtil.fail("参数值不能为空！", model);
			return;
		}
		
		HcSysParam oriRole = sysParamDao.findOne(sysParam.getId());
		//有更改名称
		if(!oriRole.getKey().equals(sysParam.getKey())){
			if(sysParamDao.findByKey(sysParam.getKey())!=null){
				MessageUtil.fail("参数键已存在！", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriRole, sysParam);
		oriRole.setUpdateTime(new Date());
		sysParamDao.save(oriRole);
		MessageUtil.success("操作成功", model);
	}
	
	
	@RequestMapping("/del")
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		sysParamDao.delete(id);
		MessageUtil.success("删除成功", model);
	}
}
