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
import com.happycar.dao.MemberDao;
import com.happycar.dao.SchoolDao;
import com.happycar.model.HcCoach;
import com.happycar.model.HcMember;
import com.happycar.model.HcSchool;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/member")
public class MemberController extends BaseController{
	@Resource
	private MemberDao memberDao;
	@Resource
	private SchoolDao schoolDao;
	@Resource
	private CoachDao coachDao;
	
	@RequestMapping("/index")
	public void index(final String name,
			final Integer coachId,
			final Integer schoolId,
			Model model,Pageable pageable){
		Page<HcMember> page = memberDao.findAll(new Specification<HcMember>() {
			
			@Override
			public Predicate toPredicate(Root<HcMember> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(coachId!=null){  
		            list.add(cb.equal(root.join("coach").get("id").as(Integer.class), coachId));  
		        }
		        if(schoolId!=null){  
		            list.add(cb.equal(root.join("coach").join("school").get("id").as(Integer.class), schoolId));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
		
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("coachId",coachId);
	}
	
	@RequestMapping("/add")
	public void add(Model model){
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcMember member,Model model){
		if(member.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(member.getPhone()==null){
			MessageUtil.fail("手机号不能为空", model);
			return;
		}
		if(member.getCoachId()==null){
			MessageUtil.fail("教练不能为空", model);
			return;
		}
		if(memberDao.findByPhone(member.getPhone()).size()>0){
			MessageUtil.fail("手机号已存在", model);
			return;
		}
		member.setAddTime(new Date());
		member.setIsDeleted(0);
		memberDao.save(member);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
		HcMember member = memberDao.findOne(id);
		model.addAttribute("member", member);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcMember member,Model model){
		if(member.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(member.getPhone()==null){
			MessageUtil.fail("手机号不能为空", model);
			return;
		}
		if(member.getCoachId()==null){
			MessageUtil.fail("教练不能为空", model);
			return;
		}
		HcMember oriMember = memberDao.findOne(member.getId());
		if(!oriMember.getPhone().equals(member.getPhone())){
			if(memberDao.findByPhone(member.getPhone()).size()>0){
				MessageUtil.fail("手机号已存在", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriMember, member);
		oriMember.setUpdateTime(new Date());
		memberDao.save(oriMember);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		memberDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
	

}
