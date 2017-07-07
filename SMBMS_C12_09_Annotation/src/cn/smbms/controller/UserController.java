package cn.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("sys/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
    
	/**
	 * 显示用户列表(分页)
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(PageSupport page,Model model) {
		
		model.addAttribute("userList", userService.getUserListByPage(page));
		model.addAttribute("page", page);
		return "user/userlist";
	}
    
	/**
	 * 显示新增用户页面
	 * @return
	 */
	@RequestMapping("add")
	public String add(@ModelAttribute("user") User user) {
		
		return "user/useradd";
	}

	/**
	 * 增加
	 * 
	 * @param user
	 * @return
	 * 
	 * 
	 * @Valid User user
	 * @RequestParam(value="a_idPicPath",required=false) MultipartFile attach //使用CommonsMultipartResolver处理上传文件时，用@RequestParam
	 * @RequestPart("a_idPicPath") MultipartFile attach  //使用StandardServletMultipartResolver处理上传文件时，用@RequestPart
	 * 
	 */
	@RequestMapping("doadd")
	public  String doAdd(@Valid User user,BindingResult bindingResult,HttpServletRequest request,
			@RequestPart("a_idPicPath") MultipartFile attach,
			@RequestPart("a_workIdPicPath") MultipartFile photo) {
		
		if(bindingResult.hasErrors()){	//验证未通过，仍跳转至新增用户页面		
			return "user/useradd"; 
		}
		
		/*
		 * *********处理身份证等证件照文件上传************************
		 */
		
		String idPicPath=null;
		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		//判断是否有文件上传
		if(!attach.isEmpty()){
			
		    String fileName=attach.getOriginalFilename();//原文件名
		    String suffix=FilenameUtils.getExtension(fileName);//取文件后缀
		    
		    int fileSize=1000000;//1M大小
		    
		    if(attach.getSize()>fileSize){
		    	request.setAttribute("uploadFileError", " * 上传大小不得超过1M");
		    	return "user/useradd";//返回至新增用户页面
		    }else if(suffix.equalsIgnoreCase("jpg")
		    		|| suffix.equalsIgnoreCase("jpeg") 
		    		|| suffix.equalsIgnoreCase("png")
		    		|| suffix.equalsIgnoreCase("pneg")){//根据文件扩展名判断上传文件类型是否是图片文件格式
		    	String saveFileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
		    	File targetFile=new File(path,saveFileName);//创建文件对象
		    	/*if(!targetFile.exists()){
		    		targetFile.mkdirs();//创建文件夹
		    	}*/
		    	
		    	//保存文件
		    	try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError", " * 上传失败！");
					return "user/useradd";
				}
		    	idPicPath=path+File.separator+saveFileName;
		    }else{
		    	request.setAttribute("uploadFileError", " * 上传图片格式不正确！");
		    	return "user/useradd";
		    }
		    
		}
		
		
		/*
		 * *********处理工作证等证件照文件上传************************
		 */
		String workIdPicPath=null;
		if(photo!=null){
			String saveFileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Work.jpg";
			try {
				photo.transferTo(new File(path,saveFileName));
				workIdPicPath=path+File.separator+saveFileName;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//获取登录用户的id
		int loginUserId=((User)(request.getSession().getAttribute(Constants.USER_SESSION))).getId();
		user.setCreatedBy(loginUserId);
		user.setCreationDate(new Date());
		user.setIdPicPath(idPicPath);
		user.setWorkIdPicPath(workIdPicPath);  
		if(userService.add(user)>0){
			return "redirect:list.html";
		}
		return "user/useradd";
	}
	
	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("dodelete")
	public @ResponseBody String doDelete(int uid) {
		if(userService.delete(uid)){
			return "success";
		}
		return "failure";
	}
	
	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("modify")
	public String toModify(int uid,Model model){
		model.addAttribute("user", userService.selectById(uid));
		return "user/usermodify";
	}
	@RequestMapping("domodify")
	public @ResponseBody String doModify(User user) {	
		if(userService.update2(user)){
			return "success";
		}
		return "failure";
	}

	/**
	 * 检查注册账号是否已存在
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping("checkUserExist")
	public @ResponseBody
	String checkUserExist(String userCode) {
		if(StringUtils.isEmpty(userCode)){
			return "isempty";
		}
		boolean ret = userService.checkUserExist(userCode);
		if (ret) {
			return "exist";
		}
		return null;
	}
     
	

	/**
	 * 查看用户详细信息
	 * 
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("view")
	/*public String view(int uid, Model model) {
		model.addAttribute("user", userService.selectById(uid));
		return "user/userview";
	}*/
	
	public @ResponseBody User view(int uid) {
		User ret= userService.selectById(uid);
		return ret;
	}
}
