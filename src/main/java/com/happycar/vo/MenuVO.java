package com.happycar.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuVO {
	
	private String name;
	private String url;
	private List<MenuVO> childs = new ArrayList<MenuVO>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuVO> getChilds() {
		return childs;
	}
	public void setChilds(List<MenuVO> childs) {
		this.childs = childs;
	}

}
