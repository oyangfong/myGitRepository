package cn.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.smbms.pojo.User;
import cn.smbms.tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	/*
	 * 判断用户是否登录
	 * 如何处理用户第一次登录的情况呢？
	 * 通过请求资源的签名，完成对授权页面的限制访问
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute(Constants.USER_SESSION);
		//没有登录，跳转至没有授权页面
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/401.jsp");
			return false;
		}
		return true;
	}
}
