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
import jp.ac.I.ohara.teamseisaku.model.Subjectmodel;
import jp.ac.I.ohara.teamseisaku.model.Teacher;
import jp.ac.I.ohara.teamseisaku.repository.Student_repository;
import jp.ac.I.ohara.teamseisaku.repository.Subjectrepository;
import jp.ac.I.ohara.teamseisaku.service.Studentservice;
import jp.ac.I.ohara.teamseisaku.service.Subjectservice;
import jp.ac.I.ohara.teamseisaku.service.Teacherservice;


 
@Controller
public class MainController {
	@Autowired
	private Studentservice student_service;
	@Autowired
	private Student_repository studentrepository;
	@Autowired
	private Subjectservice subjectservice;
	@Autowired
	private Subjectrepository subjectrepository;
	@Autowired
	private Teacherservice teacherservice;


	@GetMapping("/top/")
	  public String index(Model model) {
	    model.addAttribute("Hello", "World");
	    return "top";
	  }

	@GetMapping("/createstudent/")
	public ModelAndView add1(Studentmodel studentmodel, ModelAndView model) {
	      model.addObject("student", studentmodel); 
	      model.setViewName("createstudent");
	      return model;
	}

	@PostMapping("/createstudent/")
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
	@GetMapping("/base2/")
	public ModelAndView add(Studentmodel student_model, ModelAndView model) {
		model.addObject("student", student_model);
		model.setViewName("base2");
		return model;
	
	}
	
//	//削除
//	@GetMapping("/delete")
//	public String delete(Model model, Studentmodel studentmodel){
//		student_service.delete(studentmodel.getId());
//		return "redirect:/studentlist/";
//	}
	//更新
	@GetMapping("/hyoujistudent/{id}")
	public ModelAndView add2(@PathVariable(name="id")Long id, Studentmodel studentmodel, ModelAndView model) {
		studentmodel = student_service.getOneBook(id);
		model.addObject("lists", this.student_service.get(id));
		model.addObject("student", studentmodel); 
	    model.setViewName("deltail");
	    return model;
	}
	// 本の情報を更新する
	@PostMapping("/hyoujistudent/{id}")
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
	@GetMapping("/createsubject/")
	public ModelAndView add2(Subjectmodel subjectmodel, ModelAndView model) {
	      model.addObject("subject", subjectmodel); 
	      model.setViewName("createsubject");
	      return model;
	}

	@PostMapping("/createsubject/")
	public String subject(@Validated @ModelAttribute @NonNull Subjectmodel subjectmodel, RedirectAttributes result,
		RedirectAttributes redirectAttributes) {	
		try {
			this.subjectservice.save(subjectmodel);
			redirectAttributes.addFlashAttribute("exception", "");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("exception", e.getMessage());
				}
		return "redirect:/";
		}

	//更新
		@GetMapping("/hyoujikamoku/{id}")
		public ModelAndView add2(@PathVariable(name="id")Long id, Subjectmodel subjectmodel, ModelAndView model) {
			subjectmodel = subjectservice.getOneBook(id);
			model.addObject("lists", this.student_service.get(id));
			model.addObject("subject", subjectmodel); 
		    model.setViewName("deltail");
		    return model;
		}
	// 本の情報を更新する
	@PostMapping("/hyoujikamoku/{id}")
	public String update(@ModelAttribute @Validated Subjectmodel editsubject, BindingResult result, Model model) {
			
	    // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/studentlist/";
	    }
		
	    // 本を更新する
	    subjectservice.update(editsubject);
		
	    // 本の一覧画面にリダイレクト
	    return "redirect:/studentlist/";
	}
	
	//学生・成績・出席リスト表示・出席詳細表示
	@GetMapping("/studentlist/")
	public ModelAndView add3(ModelAndView model) {
	System.out.println(student_service.getStudentList().toString());
	model.addObject("studentList", student_service.getStudentList());
	model.setViewName("studentlist");
	return model;
	}
	@GetMapping("/creatteacher/")
	public ModelAndView teacher(Teacher teachermodel, ModelAndView model) {
	      model.addObject("teacher", teachermodel); 
	      model.setViewName("teacher");
	      return model;
	}

	@PostMapping("/creatteacher/")
	public String teacher(@Validated @ModelAttribute @NonNull Teacher teachermodel, RedirectAttributes result,
		RedirectAttributes redirectAttributes) {
		try {
			this.teacherservice.save(teachermodel);
			System.out.print(12);		
			redirectAttributes.addFlashAttribute("exception", "");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("exception", e.getMessage());
				}
		return "redirect:/";
		}

}