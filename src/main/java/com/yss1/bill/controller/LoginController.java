package com.yss1.bill.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yss1.bill.dao.UserDao;
import com.yss1.bill.models.User;
import com.yss1.bill.util.ApplicationContextUtil;
import com.yss1.bill.util.WebUtils;

@Controller
public class LoginController {

	@Autowired
	UserDao ud;
	
//	@Autowired
//	ApplicationContext actx;
//	
//	@PostConstruct
//	public void init()
//	{
//		ApplicationContextUtil acu=new ApplicationContextUtil();
//		acu.setApplicationContext(actx);
//	//	System.out.println("ACTX="+actx);
//	}
	
	
	@RequestMapping(value= {"/login"})
	public String login1(Model model,@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false) String logout) {
		if (error!=null)
		{
			model.addAttribute("error","Имя или пароль не верны!");
			model.addAttribute("username","");
			model.addAttribute("password","");
		}
		if (logout!=null)
		{
			model.addAttribute("messa","Выход успешно осуществлен!");
			model.addAttribute("username","");
			model.addAttribute("password","");
		}
		return "login";
	}
	
	@RequestMapping(value= {"/forbidden"})
	public String login1(Model model) {
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("rest", "Ошибка подключения");
		model.addAttribute("err", "Вы не имеете доступа к запрашиваему разделу!");
		return "start";
	}
	
	
	
	
	
	
	
	@RequestMapping(value= {"/chgpwd"},method = RequestMethod.GET)
	public String chgpwdGET(Model model) {
		Authentication au=SecurityContextHolder.getContext().getAuthentication();
		if (au.isAuthenticated())
		{
			model.addAttribute("name", ((User)au.getPrincipal()).getUsername());
		}
		return "chgpass";
	}
	
	
	
	
	
	@RequestMapping(value= {"/chgpwd"},method = RequestMethod.POST)
	public String chgpwdPOST(Model model,
							@RequestParam(value="password_old",required=true) String lpo,
							@RequestParam(value="password_new",required=true) String lpn,
							@RequestParam(value="password_new2",required=true) String lpn2) {
		User u=WebUtils.getUser();
		String result="";
		
		if (u!=null)
		{
			result=ud.changePassword(u.getUsername(), lpo, lpn);	
		}
		
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("apage","home");
		
		
		
		if (result.isEmpty())
		{
			model.addAttribute("rest","Пароль успешно сменен");
			
		}
		else {
			model.addAttribute("err",result);
		}
		return "start";
	}

}
