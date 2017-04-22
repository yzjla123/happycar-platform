package com.happycar.controller.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.happycar.utils.StringUtil;

public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	
	@InitBinder    
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
//		binder.registerCustomEditor(int.class, new CustomNumberEditor(int.class, true));
//		binder.registerCustomEditor(int.class, new IntegerEditor());
//		binder.registerCustomEditor(long.class, new CustomNumberEditor(
//				long.class, true));
//		binder.registerCustomEditor(long.class, new LongEditor());
//		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Float.class, new FloatEditor());
	}
	
	public class FloatEditor extends PropertiesEditor {    
	    @Override    
	    public void setAsText(String text) throws IllegalArgumentException {    
	        if (text == null || text.equals("")) {    
	        	setValue(null);  
	        }else{
	        	setValue(Float.parseFloat(text.replace(",", "")));    
	        }
	    }    
	    
	    @Override    
	    public String getAsText() {
	    	if(getValue()!=null) return getValue().toString();   
	    	else return null;
	    }    
	}    
	
	public class CustomDateEditor extends PropertiesEditor {  
		  
	    private DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");  
	    private DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	  
	    private DateFormat dateFormat;  
	    private boolean allowEmpty = true;  
	  
	    public CustomDateEditor() {  
	    }  
	  
	    public CustomDateEditor(DateFormat dateFormat) {  
	        this.dateFormat = dateFormat;  
	    }  
	  
	    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {  
	        this.dateFormat = dateFormat;  
	        this.allowEmpty = allowEmpty;  
	    }  
	  
	    /** 
	     * Parse the Date from the given text, using the specified DateFormat. 
	     */  
	    @Override  
	    public void setAsText(String text) throws IllegalArgumentException {  
	        if (this.allowEmpty && StringUtil.isNull(text)) {  
	            // Treat empty String as null value.  
	            setValue(null);  
	        } else {  
	            try {  
	                if(this.dateFormat != null)  
	                    setValue(this.dateFormat.parse(text));  
	                else {  
	                    if(text.contains(":"))  
	                        setValue(TIMEFORMAT.parse(text));  
	                    else  
	                        setValue(DATEFORMAT.parse(text));  
	                }  
	            } catch (ParseException ex) {  
	                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);  
	            }  
	        }  
	    }  
	  
	    /** 
	     * Format the Date as String, using the specified DateFormat. 
	     */  
	    @Override  
	    public String getAsText() {  
	        Date value = (Date) getValue();  
	        DateFormat dateFormat = this.dateFormat;  
	        if(dateFormat == null)  
	            dateFormat = TIMEFORMAT;  
	        return (value != null ? dateFormat.format(value) : "");  
	    }  
	}  



}
