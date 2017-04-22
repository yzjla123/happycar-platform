package com.happycar.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CVSUtil {
	private static String rootPath = "";
	private static FileOutputStream out=null;
	private static OutputStreamWriter osw=null;
	private static BufferedWriter bw=null;
	/**
     * 导出
     * 
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param objList 数据
     * @return
     */
    public static String exportCsv(String path,List<Object[]> objList,Object[] header){
        
        rootPath = path;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //创建csv文件，返回相对路径
        String filepath = CreateFile();
        try {
        	
        	File file = new File(rootPath+File.separatorChar+filepath);
        	
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out,"GBK");
            bw =new BufferedWriter(osw);
            int len = header.length;
            //打印头标题
            for(int h=0;h<header.length;h++){
            	if (h!=0) {
					bw.write(",");
				}
            	bw.write(header[h].toString()+"\t");
            }
            bw.write("\r");
            
            //打印内容部分
            if(objList!=null && !objList.isEmpty()){
                for(Object[] obj : objList){
                	
                	 //打印行数据                	 
                	for(int i=0;i<(len<obj.length?len:obj.length);i++){
                		if (obj[i]!=null) {
                			if (obj[i] instanceof Date) {
                				bw.append(sdf.format(obj[i])+"\t,");
							}else{
								bw.append(new String(obj[i].toString().getBytes("utf-8"))+"\t,");
							}
                			
						}else{
							bw.append(" \t,");
						}
            		}
                    bw.append("\r");
                }
            }
          
        } catch (Exception e) {
        	e.printStackTrace();
        	close();
        }finally{
        	close();
        }
        return filepath;
       
    }
    
    
    /**
     * 创建导出文件
     * @param path 绝对路径
     * @return filepath 相对路径
     * */
    private static String CreateFile(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMmddHHmmss");
    	
    	String filepath =  "Uploads"+File.separatorChar+new SimpleDateFormat("yyyy").format(new Date())+File.separatorChar +new SimpleDateFormat("MMdd").format(new Date())+File.separatorChar;
		
		File file = new File(rootPath+File.separatorChar+filepath);
		if (!file.exists()) {
			file.mkdirs();			
		}

		filepath +=sdf.format(new Date())+""+String.valueOf((int)((Math.random()*9+1)*100))+".csv";
    	return filepath;
    }
    
    /**
     * 关闭资源
     * */
    private static void close(){
    	 if(bw!=null){
             try {
                 bw.close();
                 bw=null;
             } catch (IOException e) {
                 e.printStackTrace();
             } 
         }
         if(osw!=null){
             try {
                 osw.close();
                 osw=null;
             } catch (IOException e) {
                 e.printStackTrace();
             } 
         }
         if(out!=null){
             try {
                 out.close();
                 out=null;
             } catch (IOException e) {
                 e.printStackTrace();
             } 
         }
    }
}
