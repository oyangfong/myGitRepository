package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
@Controller
public class LoginController {
  @Resource
  private UserService userService;
  
/*
 * 登录页面
 */
  
  @RequestMapping("login.html")
  public String login(){
	  return "login";
  }
  
  /*
   * 登录
   */
  @RequestMapping(value="dologin.html",method=RequestMethod.POST)
  public String doLogin(User user,HttpServletRequest request,Model model){
	  User ret=userService.login(user);
	  if(ret!=null){
		  //保存到session中
		  request.getSession().setAttribute(Constants.USER_SESSION, ret);
		  return "redirect:/sys/main.html";
	  }else{
		  //保存错误信息
		  model.addAttribute("error", "用户名或密码不正确！");
		  return "login";
	  }
	  
  }
  
  /*
   * 主页
   */
  @RequestMapping("/sys/main.html")
  public String main(){
	  return "frame";
  }
  
  
  /*
   * 注销
   */
  @RequestMapping("logout.html")
  public String logout(HttpSession session){
	  session.removeAttribute(Constants.USER_SESSION);
	  return "login";
  }
  
  /*
   * 国际化测试
   */
  @RequestMapping("i18n.html")
  public String i18n(){
	  return "i18n";
  }
}
