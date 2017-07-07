package cn.smbms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;

@Controller
@RequestMapping("sys/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	@RequestMapping("list")
	public @ResponseBody
	List<Role> list() {
		return roleService.getRoleList();
	}
}
