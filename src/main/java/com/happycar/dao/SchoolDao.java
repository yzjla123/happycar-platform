package com.happycar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.happycar.model.HcCar;
import com.happycar.model.HcSchool;
import com.happycar.model.HcSysMenu;

@Repository
public interface SchoolDao extends JpaRepository<HcSchool, Integer>,JpaSpecificationExecutor<HcSchool>{

	


}
