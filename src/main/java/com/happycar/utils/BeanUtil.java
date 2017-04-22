package com.happycar.utils;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtil {
	/**
	 * 通过反射拷贝非空数据(把后面的复制到前面)
	 * @param target
	 * @param src
	 */
	public static void copyProperty(Object target,Object src){
		PropertyDescriptor[] pds=PropertyUtils.getPropertyDescriptors(target);
		for(PropertyDescriptor pd:pds){
			try{
				Object result=PropertyUtils.getProperty(src, pd.getName());
				if(result!=null){
					PropertyUtils.setProperty(target, pd.getName(),result);
				}
			}catch(Exception e){
				new RuntimeException("复制对象"+src.getClass().getName()+"的属性出错了",e);
			}
		}
	}

}
