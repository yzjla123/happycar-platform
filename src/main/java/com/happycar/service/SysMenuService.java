package com.happycar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happycar.dao.SysMenuDao;
import com.happycar.model.HcSysMenu;
import com.happycar.vo.ZTreeVO;

@Service
public class SysMenuService {
	
	@Autowired
	private SysMenuDao menuDao;
	
	public List<ZTreeVO> getTree(int type){
		List<HcSysMenu> firstLevelMenus = menuDao.findByTypeAndLevelOrderBySeqAsc(type,1);
		List<HcSysMenu> secLevelMenus = menuDao.findByTypeAndLevelOrderBySeqAsc(type,2);
		
		List<ZTreeVO> treeVOs = new ArrayList<ZTreeVO>();
		//最上层根节点
		ZTreeVO rootVO = new ZTreeVO();
		rootVO.setId("0");
		rootVO.setpId("-1");
		rootVO.setName("顶级菜单");
		rootVO.setParent(true);
		rootVO.setOpen(true);
		rootVO.setExt(type+"");
		treeVOs.add(rootVO);
		for (HcSysMenu menu : firstLevelMenus) {
			ZTreeVO nodeVO = new ZTreeVO();
			nodeVO.setId(menu.getId()+"");
			nodeVO.setpId("0");
			nodeVO.setName(menu.getName());
			nodeVO.setParent(true);
			nodeVO.setOpen(true);
			nodeVO.setExt(menu.getType()+"");
			treeVOs.add(nodeVO);
		}
		for (HcSysMenu menu : secLevelMenus) {
			ZTreeVO nodeVO = new ZTreeVO();
			nodeVO.setId(menu.getId()+"");
			nodeVO.setpId(menu.getPid()+"");
			nodeVO.setName(menu.getName());
			nodeVO.setParent(true);
			nodeVO.setOpen(true);
			nodeVO.setExt(menu.getType()+"");
			treeVOs.add(nodeVO);
		}
		return treeVOs;
	}

}
