package com.yss1.bill.controller;




import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yss1.bill.dao.MainDao;
import com.yss1.bill.dao.UserDao;
import com.yss1.bill.util.WebUtils;
import com.yss1.bill.util.ExcelProcessor;

@Controller
public class UserController implements HandlerExceptionResolver {
//public class UserController {
	
	@Autowired
	ExcelProcessor excelProcessor;
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
		ModelAndView model= new ModelAndView("start"); 
		model.addObject("name", WebUtils.getLogin());
		model.addObject("rest", "Начало");
		// Конфигурация
		return  model;
	}
	
	
//	@Bean
//    public MultipartConfigElement multipartConfigElement() {
//        return new MultipartConfigElement("");
//    }
	
//	 @Bean(name = "multipartResolver")
//		public MultipartResolver multipartResolver() {
//		    CommonsMultipartResolver multipartResolver
//		      = new CommonsMultipartResolver();
//		    //multipartResolver.setMaxUploadSize(5242880);
//		    multipartResolver.setMaxUploadSize(5242880);
//		    multipartResolver.setMaxUploadSizePerFile(5242880);
//		    return multipartResolver;
//		}
	
	 
//	 @Bean(name = "multipartResolver")
//	    public MultipartResolver multipartResolver(){
//		 	MultipartResolver mr = CommonsMultipartResolver();
//	        mr.se).setMaxUploadSize(1000);
//	        mr.setMaxUploadSizePerFile(1000);
//	        return mr;
//	    }
	
//	 @Bean
//     public FilterRegistrationBean multipartFilterRegistrationBean() {
//         final MultipartFilter multipartFilter = new MultipartFilter();
//         final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
//         filterRegistrationBean.addInitParameter("multipartResolverBeanName", "multipartResolver");
//         return filterRegistrationBean;
//     }
	 
	@GetMapping(value= {"/upload"})
	public String Upload(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("rest", "Загрузить счет");
		model.addAttribute("apage", "upload");
		// Конфигурация
		return  "start";
	}
	
	@PostMapping(value="/upload")
	public ModelAndView HandleUpload(@RequestParam("file") MultipartFile file) throws IOException {
		ModelAndView model= new ModelAndView("start");
		model.getModel().put("name", WebUtils.getLogin());
		model.getModel().put("apage", "info");
		
        if (!file.isEmpty()) {
            try {
                BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
                //excelProcessor.ProcessInfo(file.getOriginalFilename(),bis);
                model.getModel().put("rest", "Вы удачно загрузили " + file.getOriginalFilename()+"!"+excelProcessor.ProcessInfo(file.getOriginalFilename(),bis));
                bis.close();
            } catch (Exception e) {
            	model.getModel().put("rest", "Не удалось загрузить " + file.getOriginalFilename() + "!");
            	model.getModel().put("err", file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
        		model.getModel().put("rest", "Не удалось загрузить " + file.getOriginalFilename() + "!");
        		model.getModel().put("err",file.getOriginalFilename() + " пустой файл.");
        }
        return model;
	}
	
	
	@PostMapping(value="/uploadtel")
	public ModelAndView HandleUploadFile(@RequestParam("filetel") MultipartFile file) throws IOException {
		ModelAndView model= new ModelAndView("start");
		model.getModel().put("name", WebUtils.getLogin());
		model.getModel().put("apage", "info");
		
        if (!file.isEmpty()) {
            try {
                BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
                //excelProcessor.ProcessInfo(file.getOriginalFilename(),bis);
                model.getModel().put("rest", "Обновлен справочник телефонов " + file.getOriginalFilename()+"!"+excelProcessor.FillTel(file.getOriginalFilename(),bis));
                bis.close();
            } catch (Exception e) {
            	model.getModel().put("rest", "Не удалось загрузить " + file.getOriginalFilename() + "!");
            	model.getModel().put("err", file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
        		model.getModel().put("rest", "Не удалось загрузить " + file.getOriginalFilename() + "!");
        		model.getModel().put("err",file.getOriginalFilename() + " пустой файл.");
        }
        return model;
	}
	
	
	@GetMapping(value= {"/calc"})
	public String calcGet(Model model) throws SQLException {
		model.addAttribute("name", WebUtils.getLogin());
		model.addAttribute("apage", "calc");
		model.addAttribute("koeff", "0");
		model.addAttribute("ijd", "0");
		return "start";
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, 
										 HttpServletResponse arg1, 
										 Object arg2,
										 Exception arg3) {
		ModelAndView modelAndView = new ModelAndView("start");
		modelAndView.getModel().put("res", "Исключительная ситуация!");
	    if (arg3 instanceof MaxUploadSizeExceededException) {
	        modelAndView.getModel().put("err", "Превышен лимит размера файла!");
	    }
	    else {
	    	modelAndView.getModel().put("err", arg3.getMessage());
	    }
	    return modelAndView;
		
	}
	
//	@RequestMapping("/reslist")
//	public String reslist(Model model){
//		String us=WebUtils.getLogin();
//		model.addAttribute("name", us);
//		model.addAttribute("rest", "Список запросов "+us+" за последние 7 дней");
//		model.addAttribute("qlist", sprDao.getResList(7,us));
//		model.addAttribute("apage", "reslist");
//		return "start";
//	}
//	
//	@GetMapping(value= {"/load/{id}"})
//	public String loadGet(Model model,@PathVariable("id") int id) throws SQLException {
//		
//		model.addAttribute("name", WebUtils.getLogin());
//		model.addAttribute("apage", "load");
//		Man man = manDao.restore(id);
//		if (man==null) {
//			model.addAttribute("err","Нет сохраненных данных для этого запроса!");
//		}
//		else {
//			man.calcPens();
//			model.addAttribute("man", man);
//		}
//		return "start";
//	}
//	
//	@RequestMapping("/pdf/{vid}/{id}")
//	public String getPdf(@PathVariable("id") int id,
//						 @PathVariable("vid") int vid,
//						  HttpServletResponse response) throws DocumentException, IOException {
//			byte [] ba=vid==1?sprDao.getRasch(id):sprDao.getRasyasn(id);
//				try {
//					response.setHeader("Content-Disposition", "inline;filename=\""+(vid==1?"Raschet":"Rasyasnenya")+".pdf\"");
//					OutputStream out = response.getOutputStream();
//					response.setContentType("application/pdf");
//					out.write(ba);
//					out.flush();
//					out.close();
//				
//				} catch (IOException e) {
//					e.printStackTrace();
//				
//				}
//				return null;
//	}
//	
	
//	
//	
//	@PostMapping(value= {"/calc"})
//	public String calcPost(Model model,@RequestParam(value="snils", required=true) String snils,
//									   @RequestParam(value="IJselect", required=true) String ijd,
//									   @RequestParam(value="KOEselect", required=true) String koeff
//			) throws SQLException {
//
//		
//		rkDefDao.setDefaultFor(WebUtils.getLogin(),koeff);
//		
//		Man man=null;
//		try {
//			man=mainDao.calculate(snils,0,Integer.parseInt(ijd),Integer.parseInt(koeff),0);
//			model.addAttribute("userid", mainDao.getId());
//		} catch (DocumentException | IOException e) {
//					e.printStackTrace();
//		}
//		
//		model.addAttribute("name", WebUtils.getLogin());
//		if (mainDao.getError().isEmpty())
//		{
//			model.addAttribute("man", man);
//			model.addAttribute("res", man.getRes());
//		}
//		else
//		{
//			model.addAttribute("err", mainDao.getError());	
//		}
//		
//		model.addAttribute("apage", "calcres");
//		
//		return "start";
//	}


	
	
}
