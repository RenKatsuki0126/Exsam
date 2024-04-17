package jp.ac.I.ohara.teamseisaku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.I.ohara.teamseisaku.model.Studentmodel;
import jp.ac.I.ohara.teamseisaku.repository.Student_repository;
import jp.ac.I.ohara.teamseisaku.service.Student_service;



 
@Controller
public class MainController {
	@Autowired
	private Student_service student_service;
	@Autowired
	private Student_repository repository;


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


	//更新
	@GetMapping("/hyouji/{id}")
	public ModelAndView add2(@PathVariable(name="id")Long id, Studentmodel studentmodel, ModelAndView model) {
		studentmodel = student_service.getOneBook(id);
		model.addObject("lists", this.student_service.get(id));
		model.addObject("student", studentmodel); 
	    model.setViewName("deltail");
	    return model;
	}
	// 編集画面を表示する
//	@GetMapping("/student-edit/")
//	public String editBook(Model model, Studentmodel editstudent) {
//			
//	    editstudent = student_service.getOneBook(editstudent.getId());
//	    model.addAttribute(editstudent);
//			
//	    return "studentlist";
//	}
	// 本の情報を更新する
	@PostMapping("/hyouji/{id}")
	public String update(@ModelAttribute @Validated Studentmodel editstudent, BindingResult result, Model model) {
			
	    // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/studentlist/";
	    }
		
	    // 本を更新する
	    student_service.update(editstudent);
		
	    // 本の一覧画面にリダイレクト
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
@	GetMapping("/studentlist/")
	public ModelAndView add3(ModelAndView model) {
	System.out.println(student_service.getStudentList().toString());
	model.addObject("studentList", student_service.getStudentList());
	model.setViewName("studentlist");
	return model;
	}

}