package com.happycar.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.happycar.utils.RedisUtil;

@Controller
@RequestMapping("/admin")
public class FileUploadController extends BaseController {

	private static final HashMap<String, String> TypeMap = new HashMap<String, String>();

	static {
		TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
		TypeMap.put("flash", "swf,flv");
		TypeMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		TypeMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,dwg,pdf");
	}

	/**
	 * 文件上传 之 图片上传
	 * 
	 * @param file
	 * @param request
	 * @return message: -1 没有文件上传 0 上传成功 1 上传失败 2 文件超过上传大小 3 文件格式错误 4 上传文件路径非法 5
	 *         上传目录没有写权限
	 * 
	 * 
	 */
	@RequestMapping(value = "/fileUpload/imageUpload", method = RequestMethod.POST)
	public void imageUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("上传图片名 :" + file.getOriginalFilename());

			if (!file.isEmpty()) {
//				Properties p = new Properties();// 属性集合对象 
//	    		String path = RedisUtil.class.getClassLoader().getResource("").getPath()+"global.properties";
//	    		FileInputStream fis = new FileInputStream(path);// 属性文件输入流   
//	    		p.load(fis);// 将属性文件流装载到Properties对象中     
//	            fis.close();// 关闭流     
//	            String uploadPath = p.getProperty("imgUpload.url");
//				//路径名称上加上-年/月日：yyyy/MMdd
//				uploadPath += "Uploads/"+new SimpleDateFormat("yyyy").format(new Date())+ "/" +new SimpleDateFormat("MMdd").format(new Date())+"/";
				
				String path=request.getRealPath("/");
				//路径名称上加上-年/月日：yyyy/MMdd
				String uploadPath = File.separatorChar+"Uploads"+File.separatorChar+new SimpleDateFormat("yyyy").format(new Date())+File.separatorChar +new SimpleDateFormat("MMdd").format(new Date())+File.separatorChar;
				// 文件上传大小
				long fileSize = 10 * 1024 * 1024;
				//判断文件大小是否超过
				if (file.getSize() > fileSize) {
					backInfo(response, false, 2, "");
					return;
				}
				//获取上传文件名称
				String OriginalFilename = file.getOriginalFilename();
				//获取文件后缀名：如jpg
				String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.asList(TypeMap.get("image").split(",")).contains(fileSuffix)) {
					backInfo(response, false, 3, "");
					return;
				}
				//判断是否有文件上传
				if (!ServletFileUpload.isMultipartContent(request)) {
					backInfo(response, false, -1, "");
					return;
				}

				// 检查上传文件的目录
				File uploadDir = new File(path+uploadPath);
				if (!uploadDir.isDirectory()) {
					if (!uploadDir.mkdirs()) {
						backInfo(response, false, 4, "");
						return;
					}
				}

				// 是否有上传的权限
				if (!uploadDir.canWrite()) {
					backInfo(response, false, 5, "");
					return;
				}

				// 新文件名-加13为随机字符串
				 String newname = getRandomString(13) +"." + fileSuffix;

				File saveFile = new File(path+uploadPath, newname);

				try {
					file.transferTo(saveFile);
					backInfo(response, true, 0, uploadPath+newname);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					backInfo(response, false, 1, "");
					return;
				}
			} else {
				backInfo(response, false, -1, "");
				return;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	// 返回信息
	private void backInfo(HttpServletResponse response, boolean flag, int message, String fileName) {
		fileName=fileName.replace("\\","/");
		String json = "";
		if (flag) {
			json = "{ \"status\": \"success";
		} else {
			json = "{ \"status\": \"error";
		}
		json += "\",\"fileName\": \"" + fileName + "\",\"message\": \"" + message + "\"}";
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	/**生成指定长度的字符串
	 * @param length 生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	public static void main(String[] args) {
		String aString= "E:\\apache-tomcat-7.0.70\\webapps\\ucar_server";
		System.out.println(aString.replaceAll("\\\\", "/"));
	}
}
