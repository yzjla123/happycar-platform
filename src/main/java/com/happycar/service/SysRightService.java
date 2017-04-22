package com.happycar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happycar.dao.SysMenuDao;
import com.happycar.dao.SysRightDao;
import com.happycar.model.HcSysMenu;
import com.happycar.model.HcSysRight;
import com.happycar.vo.ZTreeVO;

@Service
public class SysRightService {
	
	@Autowired
	private SysRightDao rightDao;
	
	public List<ZTreeVO> getTree(){
		List<HcSysRight> firstLevelRights = rightDao.findByLevelOrderBySeqAsc(1);
		List<HcSysRight> secLevelRights = rightDao.findByLevelOrderBySeqAsc(2);
		
		List<ZTreeVO> treeVOs = new ArrayList<ZTreeVO>();
		//最上层根节点
		ZTreeVO rootVO = new ZTreeVO();
		rootVO.setId("0");
		rootVO.setpId("-1");
		rootVO.setName("顶级权限");
		rootVO.setParent(true);
		rootVO.setOpen(true);
		rootVO.setExt("");
		treeVOs.add(rootVO);
		for (HcSysRight right : firstLevelRights) {
			ZTreeVO nodeVO = new ZTreeVO();
			nodeVO.setId(right.getId()+"");
			nodeVO.setpId("0");
			nodeVO.setName(right.getName());
			nodeVO.setParent(true);
			nodeVO.setOpen(true);
			nodeVO.setExt("");
			treeVOs.add(nodeVO);
		}
		for (HcSysRight right : secLevelRights) {
			ZTreeVO nodeVO = new ZTreeVO();
			nodeVO.setId(right.getId()+"");
			nodeVO.setpId(right.getPid()+"");
			nodeVO.setName(right.getName());
			nodeVO.setParent(true);
			nodeVO.setOpen(true);
			nodeVO.setExt("");
			treeVOs.add(nodeVO);
		}
		return treeVOs;
	}

}
