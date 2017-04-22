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
import com.happycar.dao.CoachDao;
import com.happycar.dao.SchoolDao;
import com.happycar.model.HcSchool;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/school")
public class SchoolController extends BaseController{
	@Resource
	private SchoolDao schoolDao;
	@Resource
	private CoachDao coachDao;
	
	@RequestMapping("/index")
	public void index(final String name,
			Model model,Pageable pageable){
		Page<HcSchool> page = schoolDao.findAll(new Specification<HcSchool>() {
			
			@Override
			public Predicate toPredicate(Root<HcSchool> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
				//过滤已删除的数据
		        //list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		for (HcSchool hcSchool : page.getContent()) {
			hcSchool.setcoachNum(coachDao.findBySchoolId(hcSchool.getId()).size());
		}
		model.addAttribute("page", page);
		model.addAttribute("name", name);
	}
	
	@RequestMapping("/add")
	public void add(Model model){
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcSchool school,Model model){
		if(school.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		school.setAddTime(new Date());
		schoolDao.save(school);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcSchool school = schoolDao.findOne(id);
		model.addAttribute("school", school);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcSchool school,Model model){
		if(school.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		HcSchool oriSchool = schoolDao.findOne(school.getId());
		BeanUtil.copyProperty(oriSchool, school);
		oriSchool.setUpdateTime(new Date());
		schoolDao.save(oriSchool);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		if(coachDao.findBySchoolId(id).size()>0){
			MessageUtil.fail("请先删除这个学校的教练", model);
			return;
		}
		schoolDao.delete(id);
		MessageUtil.success("删除成功", model);
	}
}
