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
import com.happycar.dao.TheoryDao;
import com.happycar.dao.MemberDao;
import com.happycar.dao.SchoolDao;
import com.happycar.model.HcSchool;
import com.happycar.model.HcTheory;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/theory")
public class TheoryController extends BaseController{
	@Resource
	private TheoryDao theoryDao;
	@Resource
	private SchoolDao schoolDao;
	@Resource
	private MemberDao memberDao;
	
	@RequestMapping("/index")
	public void index(final String title,
			final Integer subjectType,
			final Integer questionType,
			Model model,Pageable pageable){
		Page<HcTheory> page = theoryDao.findAll(new Specification<HcTheory>() {
			
			@Override
			public Predicate toPredicate(Root<HcTheory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(title)){  
		            list.add(cb.like(root.get("title").as(String.class), "%"+title+"%"));  
		        }
		        if(subjectType!=null){  
		            list.add(cb.equal(root.get("subjectType").as(Integer.class), subjectType));  
		        }
		        if(questionType!=null){  
		            list.add(cb.equal(root.get("questionType").as(Integer.class), questionType));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("subjectType",subjectType);
		model.addAttribute("questionType",questionType);
	}
	
	@RequestMapping("/add")
	public void add(Integer questionType,Model model){
		model.addAttribute("questionType", questionType);
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcTheory theory,Model model){
		if(theory.getTitle()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(theory.getAnswer()==null){
			MessageUtil.fail("答案不能为空", model);
			return;
		}
		theory.setAddTime(new Date());
		theory.setIsDeleted(0);
		theoryDao.save(theory);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		HcTheory theory = theoryDao.findOne(id);
		model.addAttribute("theory", theory);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcTheory theory,Model model){
		if(theory.getTitle()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(theory.getAnswer()==null){
			MessageUtil.fail("答案不能为空", model);
			return;
		}
		HcTheory oriTheory = theoryDao.findOne(theory.getId());
		BeanUtil.copyProperty(oriTheory, theory);
		oriTheory.setUpdateTime(new Date());
		theoryDao.save(oriTheory);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		theoryDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
	
	
	@RequestMapping("/detail")
	@Transactional
	public void detail(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空", model);
			return;
		}
		HcTheory theory = theoryDao.findOne(id);
		model.addAttribute("theory",theory);
		MessageUtil.success("操作成功", model);
	}

}
