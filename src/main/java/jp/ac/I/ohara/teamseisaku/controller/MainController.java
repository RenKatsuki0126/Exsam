package jp.ac.I.ohara.teamseisaku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.I.ohara.teamseisaku.model.Studentmodel;
import jp.ac.I.ohara.teamseisaku.service.Student_service;




 
@Controller
public class MainController {
	@Autowired
	private Student_service student_service;



	@GetMapping("/")
	  public String index(Model model) {
	    model.addAttribute("Hello", "World");
	    return "top";
	  }

	@GetMapping("/createstudent")
	public ModelAndView add1(Studentmodel studentmodel, ModelAndView model) {
	      model.addObject("student", studentmodel); 
	      model.setViewName("createstudent");
	      return model;
	}
	
	@GetMapping("/base2/")
	public ModelAndView add(Studentmodel student_model, ModelAndView model) {
		model.addObject("student", student_model);
		model.setViewName("base2");
		return model;
	
	}
	
	//削除
	@GetMapping("/delete")
	public String delete(Model model, Studentmodel studentmodel){
		student_service.delete(studentmodel.getId());
		return "redirect:/studentlist/";
	}


	
@PostMapping("/createstudent")
public String student(@Validated @ModelAttribute @NonNull Studentmodel studentmodel, RedirectAttributes result,
		RedirectAttributes redirectAttributes) {
	try {
		this.student_service.save(studentmodel);
		System.out.print(12);		
		redirectAttributes.addFlashAttribute("exception", "");
	} catch (Exception e) {
		redirectAttributes.addFlashAttribute("exception", e.getMessage());
	}
	return "redirect:/";
}


//学生・成績・出席リスト表示・出席詳細表示
@GetMapping("/studentlist/")
public String add3(Model model) {
System.out.println(student_service.getStudentList().toString());
  model.addAttribute("studentList", student_service.getStudentList());
  return "studentlist";
}

}