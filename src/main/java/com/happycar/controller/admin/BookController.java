package com.happycar.controller.admin;

import java.text.ParseException;
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
import com.happycar.dao.BookDao;
import com.happycar.dao.MemberDao;
import com.happycar.dao.SchoolDao;
import com.happycar.model.HcSchool;
import com.happycar.model.HcBook;
import com.happycar.utils.BeanUtil;
import com.happycar.utils.DateUtil;
import com.happycar.utils.MessageUtil;


@Controller
@RequestMapping("/admin/book")
public class BookController extends BaseController{
	@Resource
	private BookDao bookDao;
	@Resource
	private MemberDao memberDao;
	
	@RequestMapping("/index")
	public void index(final String name,
			final String date,
			Model model,Pageable pageable){
		Page<HcBook> page = bookDao.findAll(new Specification<HcBook>() {
			
			@Override
			public Predicate toPredicate(Root<HcBook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
		        if(StringUtils.isNotEmpty(name)){  
		            list.add(cb.like(root.join("member").get("name").as(String.class), "%"+name+"%"));  
		        }
		        if(date!=null){  
		            try {
						list.add(cb.equal(root.join("schedule").get("date").as(Date.class), DateUtil.parseTime(date, DateUtil.YYYYMMDD)));
					} catch (ParseException e) {
						logger.error("Date parse error", e);
					}  
		        }
				//过滤已删除的数据
//		        list.add(cb.equal(root.get("isDeleted").as(Integer.class), 0));  
		        Predicate[] p = new Predicate[list.size()];  
		        return cb.and(list.toArray(p)); 
			}
		}, pageable);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		model.addAttribute("date",date);
		
	}

}
