package com.happycar;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.happycar.dao.SysUserDao;
import com.happycar.model.HcSysUser;
import com.happycar.utils.SpringContextUtil;

public class AuthFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest)request;  
         HttpServletResponse res = (HttpServletResponse)response;  
         HttpSession session = req.getSession();  
         //如果没有登录.  
         String requestURI = req.getRequestURI();  
         String token = req.getParameter("token");
//         SysUserDao userDao = SpringContextUtil.getApplicationContext().getBean(SysUserDao.class);
//         HcSysUser user = userDao.findOne(82);
//         session.setAttribute(Constant.SESSION_LOGIN_USER, user);
         if(requestURI.indexOf("login.do")==-1&& requestURI.indexOf("logout.do")==-1)  
         {  
             HcSysUser user = (HcSysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
             //如果session中没有任何东西.  
             if(session.getAttribute(Constant.SESSION_LOGIN_USER) == null &&requestURI.indexOf("/admin/")!=-1)  
             {   
            	 res.getWriter().print("<script>top.location.href='"+req.getContextPath()+"/login.do'</script>");
//            	 res.sendRedirect(req.getContextPath() + "/login.do");  
            	 return; 
             }
               
         }  
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
