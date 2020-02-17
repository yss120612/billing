package com.yss1.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.yss1.bill.dao.RoleDao;
import com.yss1.bill.dao.UserDao;
import com.yss1.bill.models.Role;
import com.yss1.bill.models.User;
import com.yss1.bill.util.WebUtils;

@Controller


public class AdminController {

	@Autowired
	UserDao ud;
	
	@Autowired
	RoleDao rd;
	
	private String erroradd="";
	@RequestMapping(value= {"/userslist"},method=RequestMethod.GET)
	public String uList(Model model) {
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("rest", "Список пользователей");
		model.addAttribute("apage","ulist");
		model.addAttribute("users",ud.getAllUsers());
		return "start";
	}
	
	@RequestMapping(value= {"/roleslist"},method=RequestMethod.GET)
	public String gList(Model model) {
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("rest", "Список ролей");
		model.addAttribute("apage","rlist");
		model.addAttribute("roles",rd.getRoleList());
		return "start";
	}
	
	
	@GetMapping(value= {"/useredit"})
	public String usereditGET(Model model,@RequestParam(value="userid",required=true) int id)
	{
		User u= ud.getUserById(id);
		model.addAttribute("titleform", "Изменение пользователя");
		model.addAttribute("action","edit");
		model.addAttribute("user",u);
		model.addAttribute("roles",rd.getRoleList());
		return "user";
	}
	
	@PostMapping(value= {"/useredit"})
	public String usereditPOST(Model model,@RequestParam(value="userid",required=true) int id,
										   @RequestParam(value="uroles",required=false) String  roles,
										   @RequestParam(value="access",required=false,defaultValue="false") boolean acc)
	{
		User u=ud.getUserById(id);
		u.setEnabled(acc);
		ud.setRoles(u,roles);
		ud.saveUser(u);
		return "redirect:/userslist";
	}
	
	@GetMapping(value= {"/roleedit"})
	public String roleeditGET(Model model,@RequestParam(value="roleid",required=true) int id)
	{
		Role r= rd.findRoleById((long)id);
		model.addAttribute("titleform", "Изменение роли");
		model.addAttribute("action","edit");
		model.addAttribute("role",r);
		if (!erroradd.isEmpty()) model.addAttribute("err",erroradd);
		erroradd="";
		return "role";
	}	
						   		
	@PostMapping(value= {"/roleedit"})
	public String roleeditPOST(Model model,@RequestParam(value="roleid",required=true) int id,
										   @RequestParam(value="rolename",required=false) String  rolename)
	{
		if (rolename==null || rolename.length()<3)
		{
			erroradd="Длина не менее 3 символов!";
			return "redirect:/roleedit?roleid="+id;
		}
		rolename=rolename.trim();
		if (!rolename.startsWith("ROLE_"))
		{
			erroradd="Имя роли должно начинаться с 'ROLE_'";
			return "redirect:/roleedit?roleid="+id;
		}
		
		Role r= rd.findRoleById((long)id);
		if (!rd.editRole(r, rolename)) {
		erroradd=rolename+"<br>Такая роль существует!";
		return "redirect:/roleedit?roleid="+id;
		}
		return "redirect:/roleslist";
	}
	
	
	@GetMapping(value= {"/useradd"})
	public String useraddGET(Model model)
	{
		model.addAttribute("titleform", "Создание пользователя");
		model.addAttribute("action","add");
		if (!erroradd.isEmpty()) model.addAttribute("err",erroradd);
		model.addAttribute("access",true);
		model.addAttribute("roles",rd.getRoleList());
		erroradd="";
		return "user";
	}
	
	@PostMapping(value= {"/useradd"})
	public String useraddPOST(Model model,@RequestParam(value="username",required=true) String username,
										  @RequestParam(value="password",required=true) String password,
										  @RequestParam(value="password2",required=true) String password2,
										  @RequestParam(value="uroles",required=false) String  roles,
										  @RequestParam(value="access",required=false,defaultValue="false") boolean acc)
	{
		
		if (roles==null) roles="";
		erroradd="";
		if (!password.equals(password2))
		{
			erroradd="Пароли не совпадают!";
		}
		
		if (password.length()<4)
		{
			erroradd=(erroradd.isEmpty())?"Пароли менее 4х символов!":erroradd+"<br>Пароли менее 4х символов!";
		}
		User us=null;
		if (erroradd.isEmpty())
		{
			us=ud.addUser(username, password, acc,roles);
		}
		if (us==null) {
			erroradd=(erroradd.isEmpty())?"Пользователь "+username+" уже существует":erroradd+"<br>Пользователь "+username+" уже существует";
		}
		
		if (!erroradd.isEmpty()) {
			return "redirect:/useradd";
		}
		
    	return "redirect:/userslist";
	}
	
	
	@GetMapping(value= {"/roleadd"})
	public String roleaddGET(Model model)
	{
		model.addAttribute("titleform", "Создание роли");
		model.addAttribute("action","add");
		if (!erroradd.isEmpty()) model.addAttribute("err",erroradd);
		erroradd="";
		return "role";
	}
	
	@PostMapping(value= {"/roleadd"})
	public String roleaddPOST(Model model,@RequestParam(value="rolename",required=true) String rolename)
	{

		erroradd="";
		if (rolename==null)
		{
			erroradd="Пустое имя роли!";
			rolename="";
		}
		
		rolename=rolename.trim();
		
		if (rolename.length()<3)
		{
			erroradd="Имя роли менее 3х символов!";
		}
		
		if (!rolename.startsWith("ROLE_"))
		{
			erroradd="Имя роли должно начинаться с 'ROLE_'";
		}
		
		if (rd.checkExist(rolename)) {
			erroradd=(erroradd.isEmpty())?"Роль "+rolename+" уже существует":erroradd+"<br>Пользователь "+rolename+" уже существует";
		}
		if (erroradd.isEmpty())
		{
		  rd.addRole(rolename);
		}
		else{
			return "redirect:/roleadd";
		}
		
    	return "redirect:/roleslist";
	}
	
	
	@GetMapping(value= {"/userdel"})
	public String userdelPOST(@RequestParam(value="userid",required=true) int id)
	{
		User us = ud.getUserById(id);
		
		ud.deleteUser(id);
		if (us.getUsername().equals(WebUtils.getLogin()))
		{
			return "redirect:/login?logout";
		}
		return "redirect:/userslist";
	}
	
	@GetMapping(value= {"/roledel"})
	public String roledelPOST(@RequestParam(value="roleid",required=true) int id)
	{
		rd.deleteRole(id);
		return "redirect:/roleslist";
	}
	
	
}
