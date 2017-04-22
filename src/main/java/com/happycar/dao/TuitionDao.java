package com.happycar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.happycar.model.HcCoach;
import com.happycar.model.HcTuition;

@Repository
public interface TuitionDao extends JpaRepository<HcTuition, Integer>,JpaSpecificationExecutor<HcTuition>{

	@Modifying
	@Query("update HcTuition set isDeleted=1 where id=?")
	public int deleteById(Integer id);

}
