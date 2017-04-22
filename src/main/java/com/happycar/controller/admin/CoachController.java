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
import com.happycar.model.HcSchool;
import com.happycar.model.HcCoach;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/coach")
public class CoachController extends BaseController{
	@Resource
	private CoachDao coachDao;
	@Resource
	private SchoolDao schoolDao;
	@Resource
	private MemberDao memberDao;
	
	@RequestMapping("/index")
	public void index(final String name,
			final Integer schoolId,
			Model model,Pageable pageable){
		Page<HcCoach> page = coachDao.findAll(new Specification<HcCoach>() {
			
			@Override
			public Predicate toPredicate(Root<HcCoach> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(schoolId!=null){  
		            list.add(cb.equal(root.join("school").get("id").as(Integer.class), schoolId));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		for (HcCoach hcCoach : page.getContent()) {
			int totalMember = memberDao.findByCoachId(hcCoach.getId()).size();
			int learningMember = memberDao.findByCoachIdAndProgressLessThan(hcCoach.getId(),12).size();
			hcCoach.setTotalMember(totalMember);
			hcCoach.setLearningMember(learningMember);
		}
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		model.addAttribute("schoolId",schoolId);
		
	}
	
	@RequestMapping("/add")
	public void add(Model model){
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcCoach coach,Model model){
		if(coach.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(coach.getPhone()==null){
			MessageUtil.fail("手机号不能为空", model);
			return;
		}
		if(coach.getSchoolId()<=0){
			MessageUtil.fail("驾校不能为空", model);
			return;
		}
		if(coachDao.findByPhone(coach.getPhone()).size()>0){
			MessageUtil.fail("手机号已存在", model);
			return;
		}
		coach.setAddTime(new Date());
		coach.setIsDeleted(0);
		coachDao.save(coach);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		
		HcCoach coach = coachDao.findOne(id);
		model.addAttribute("coach", coach);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcCoach coach,Model model){
		if(coach.getName()==null){
			MessageUtil.fail("名称不能为空", model);
			return;
		}
		if(coach.getPhone()==null){
			MessageUtil.fail("手机号不能为空", model);
			return;
		}
		if(coach.getSchoolId()<=0){
			MessageUtil.fail("驾校不能为空", model);
			return;
		}
		HcCoach oriCoach = coachDao.findOne(coach.getId());
		if(!oriCoach.getPhone().equals(coach.getPhone())){
			if(coachDao.findByPhone(coach.getPhone()).size()>0){
				MessageUtil.fail("手机号已存在", model);
				return;
			}
		}
		BeanUtil.copyProperty(oriCoach, coach);
		oriCoach.setUpdateTime(new Date());
		coachDao.save(oriCoach);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		coachDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
	

}
