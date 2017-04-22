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
import com.happycar.dao.SignupDao;
import com.happycar.dao.MemberDao;
import com.happycar.dao.SchoolDao;
import com.happycar.model.HcSchool;
import com.happycar.model.HcSignup;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/signup")
public class SignupController extends BaseController{
	@Resource
	private SignupDao signupDao;
	@Resource
	private SchoolDao schoolDao;
	@Resource
	private MemberDao memberDao;
	
	@RequestMapping("/index")
	public void index(
			final Integer memberId,
			Model model,
			Pageable pageable){
		Page<HcSignup> page = signupDao.findAll(new Specification<HcSignup>() {
			
			@Override
			public Predicate toPredicate(Root<HcSignup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(memberId!=null){  
		            list.add(cb.equal(root.join("member").get("id").as(Integer.class), memberId));  
		        }
				//过滤已删除的数据
		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		List<HcSchool> schools = schoolDao.findAll();
		model.addAttribute("schools",schools);
		model.addAttribute("page", page);
		model.addAttribute("memberId",memberId);
		
	}
	
	@RequestMapping("/detail")
	@Transactional
	public void detail(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		HcSignup signup = signupDao.findOne(id);
		model.addAttribute("signup",signup);
		MessageUtil.success("操作成功", model);
	}
	
	@RequestMapping("/del")
	@Transactional
	public void del(Integer id,Model model){
		if(id==null){
			MessageUtil.fail("ID不能为空！", model);
			return;
		}
		signupDao.deleteById(id);
		MessageUtil.success("删除成功", model);
	}
	

}
