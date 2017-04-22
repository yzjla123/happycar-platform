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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happycar.controller.base.BaseController;
import com.happycar.dao.SysRightDao;
import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysRight;
import com.happycar.service.SysRightService;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;
import com.happycar.vo.ZTreeVO;


@Controller
@RequestMapping("/admin/right")
public class RightController extends BaseController{
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRightDao rightDao;
	@Resource
	private SysRightService rightService;

	@RequestMapping(value="/ztree", method=RequestMethod.GET)
	public void ztree(String type, Model model) {
		List<ZTreeVO> treeVOs = rightService.getTree();
		model.addAttribute("tree", treeVOs);
	}
	
	@RequestMapping("/index")
	public void index(Model model,Pageable pageable){
	}
	
	@RequestMapping("/list")
	public void list(final String name,
			final String level,
			final String pid,
			Model model,@PageableDefault(sort="seq",direction=Direction.ASC)Pageable pageable){
		Page<HcSysRight> page = rightDao.findAll(new Specification<HcSysRight>() {
			
			@Override
			public Predicate toPredicate(Root<HcSysRight> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
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
		model.addAttribute("level", level);
		model.addAttribute("pid", pid);
	}
	
	@RequestMapping("/add")
	public void add(Integer pid,Model model){
		model.addAttribute("pid", pid);
		int maxSeq = rightDao.findMaxSeqByPid(pid);
		model.addAttribute("seq", maxSeq+1);
	}
	
	@RequestMapping("/save")
	public void save(HcSysRight right,Model model){
		if(StringUtils.isEmpty(right.getName())){
			MessageUtil.fail("权限名不能为空！", model);
			return;
		}
		if(rightDao.findByCode(right.getCode())!=null){
			MessageUtil.fail("代码已存在！", model);
			return;
		}
		right.setAddTime(new Date());
		if(right.getPid()==0){
			right.setLevel(1);
		}else{
			HcSysRight pright = rightDao.findOne(right.getPid());
			right.setLevel(pright.getLevel()+1);
		}
		rightDao.save(right);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcSysRight right = rightDao.findOne(id);
		model.addAttribute("right", right);
	}
	
	@RequestMapping("/update")
	public void update(HcSysRight right,Model model){
		if(StringUtils.isEmpty(right.getName())){
			MessageUtil.fail("权限名不能为空", model);
			return;
		}
		
		HcSysRight oriright = rightDao.findOne(right.getId());
		if(!oriright.getCode().equals(right.getCode())){
			if(rightDao.findByCode(right.getCode())!=null){
				MessageUtil.fail("代码已存在！", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriright, right);
		oriright.setUpdateTime(new Date());
		rightDao.save(oriright);
		MessageUtil.success("操作成功", model);
	}
	
	
	@RequestMapping("/del")
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		rightDao.delete(id);
		MessageUtil.success("删除成功", model);
	}
}
