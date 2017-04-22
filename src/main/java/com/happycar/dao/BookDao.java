package com.happycar.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.happycar.model.HcBook;
import com.happycar.model.HcCoach;

@Repository
public interface BookDao extends JpaRepository<HcBook, Integer>,JpaSpecificationExecutor<HcBook>{

	public List<HcCoach> findByScheduleIdAndStatusIn(Integer scheduleId,List<Integer> status);

}
