package cn.smbms.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * preHandle(..) is called before the actual handler is executed;
 * postHandle(..) is called after the handler is executed; 
 * afterCompletion(..) is called after the complete request has finished. 
 */
public class MyInterceptor extends HandlerInterceptorAdapter {
  private Logger logger=Logger.getLogger(MyInterceptor.class);
  @Override
  public void afterCompletion(HttpServletRequest request,
          HttpServletResponse response, Object handler, Exception exception)
          throws Exception {
	  
	  System.out.println("我回来啦。。。。");
	  System.out.println("After completion handle");
  }
  
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler) throws Exception {
      final String ENC = "UTF-8";
      
      System.out.println("Pre-handle");
      
      String fullPath = request.getRequestURI();
      String queryString = request.getQueryString();
      String contextPath = request.getContextPath();
      String controllerPath = fullPath.replace(contextPath, "");
      System.out.println("fullPath: "+fullPath);
      System.out.println("queryString: "+queryString);
      System.out.println("contextPath: "+contextPath);
      System.out.println("controllerPath: "+controllerPath);
      
     
      return true;
  }
  
  @Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post-handle");
	}
}
