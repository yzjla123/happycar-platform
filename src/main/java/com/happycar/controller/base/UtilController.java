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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.happycar.service.QiniuService;
import com.happycar.utils.RedisUtil;

@Controller
@RequestMapping("/")
public class UtilController extends BaseController {

	@Resource
	private QiniuService qiniuService;
	
	@RequestMapping("/qiniu/uptoken")
	@ResponseBody
	public String getQiniuUptoken(){
		return qiniuService.getUploadToken();
	}
}
