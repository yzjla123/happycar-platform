package com.happycar.vo;
public class ZTreeVO {
	//{ id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true,open:true},
	private String id;
	private String pId;
	private String name;
	private boolean isParent;
	private boolean open;
	private boolean  checked;
	private String ext;
	
	public String getId() {
		return id;
	}
	public String getpId() {
		return pId;
	}
	public String getName() {
		return name;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}

}
