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

import com.happycar.controller.base.BaseController;
import com.happycar.dao.TuitionDao;
import com.happycar.model.HcTuition;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/tuition")
public class TuitionController extends BaseController{
	@Resource
	private TuitionDao tuitionDao;
	
	@RequestMapping("/index")
	public void index(final String name,
			Model model,Pageable pageable){
		Page<HcTuition> page = tuitionDao.findAll(new Specification<HcTuition>() {
			
			@Override
			public Predicate toPredicate(Root<HcTuition> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
	}
	
	@RequestMapping("/add")
	public void add(Model model){
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcTuition tuition,Model model){
		if(tuition.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(tuition.getAmount()==null){
			MessageUtil.fail("金额不能为空", model);
			return;
		}
		if(tuition.getAmount()<=0){
			MessageUtil.fail("金额不能小于0", model);
			return;
		}
		tuition.setAddTime(new Date());
		tuition.setIsDeleted(0);
		tuitionDao.save(tuition);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcTuition tuition = tuitionDao.findOne(id);
		model.addAttribute("tuition", tuition);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcTuition tuition,Model model){
		if(tuition.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(tuition.getAmount()==null){
			MessageUtil.fail("金额不能为空", model);
			return;
		}
		if(tuition.getAmount()<=0){
			MessageUtil.fail("金额不能小于0", model);
			return;
		}
		HcTuition oriTuition = tuitionDao.findOne(tuition.getId());
		BeanUtil.copyProperty(oriTuition, tuition);
		oriTuition.setUpdateTime(new Date());
		tuitionDao.save(oriTuition);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		tuitionDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
}
