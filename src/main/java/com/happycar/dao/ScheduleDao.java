package com.happycar.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.happycar.model.HcCoach;
import com.happycar.model.HcSchedule;

@Repository
public interface ScheduleDao extends JpaRepository<HcSchedule, Integer>,JpaSpecificationExecutor<HcSchedule>{

	public List<HcSchedule> findByCoachId(Integer id);

}
