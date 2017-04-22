package com.happycar.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import com.happycar.dao.ScheduleDao;
import com.happycar.model.HcCoach;
import com.happycar.model.HcSchedule;
import com.happycar.model.HcSchool;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/schedule")
public class ScheduleController extends BaseController{
	@Resource
	private ScheduleDao scheduleDao;
	@Resource
	private CoachDao coachDao;
	@Resource
	private MemberDao memberDao;
	
	@RequestMapping("/index")
	public void index(final Integer coachId,
			final Date date,
			Model model,Pageable pageable){
		Page<HcSchedule> page = scheduleDao.findAll(new Specification<HcSchedule>() {
			
			@Override
			public Predicate toPredicate(Root<HcSchedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(date!=null){  
		            list.add(cb.equal(root.get("date").as(Date.class), date));  
		        }
		        if(coachId!=null){  
		            list.add(cb.equal(root.get("coachId").as(Integer.class), coachId));  
		        }
				//过滤已删除的数据
//		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
		model.addAttribute("page", page);
		model.addAttribute("date", date);
		model.addAttribute("coachId",coachId);
		
	}
	
	@RequestMapping("/add")
	public void add(Model model){
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
	}
	
	@RequestMapping("/save")
	@Transactional
	public void save(HcSchedule schedule,Model model){
		if(schedule.getTime1()==null){
			MessageUtil.fail("时间段不能为空", model);
			return;
		}
		if(schedule.getTime1()==null){
			MessageUtil.fail("时间段不能为空", model);
			return;
		}
		schedule.setAddTime(new Date());
		scheduleDao.save(schedule);
		MessageUtil.success("添加成功", model);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Model model){
		List<HcCoach> coachs = coachDao.findAllByIsDeleted(0);
		model.addAttribute("coachs",coachs);
		
		HcSchedule schedule = scheduleDao.findOne(id);
		model.addAttribute("schedule", schedule);
	}
	
	@RequestMapping("/update")
	@Transactional
	public void update(HcSchedule schedule,Model model){
		if(schedule.getTime1()==null){
			MessageUtil.fail("时间段不能为空", model);
			return;
		}
		if(schedule.getTime1()==null){
			MessageUtil.fail("时间段不能为空", model);
			return;
		}
		HcSchedule oriSchedule = scheduleDao.findOne(schedule.getId());
		BeanUtil.copyProperty(oriSchedule, schedule);
		oriSchedule.setUpdateTime(new Date());
		scheduleDao.save(oriSchedule);
		MessageUtil.success("更新成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		scheduleDao.delete(id);
		MessageUtil.success("删除成功", model);
	}
	

}
