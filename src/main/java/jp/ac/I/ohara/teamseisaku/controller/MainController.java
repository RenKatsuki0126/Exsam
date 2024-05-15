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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.I.ohara.teamseisaku.model.Studentmodel;
import jp.ac.I.ohara.teamseisaku.model.Subjectmodel;
import jp.ac.I.ohara.teamseisaku.model.Teacher;
import jp.ac.I.ohara.teamseisaku.model.Testmodel;
import jp.ac.I.ohara.teamseisaku.service.Studentservice;
import jp.ac.I.ohara.teamseisaku.service.Subjectservice;
import jp.ac.I.ohara.teamseisaku.service.Teacherservice;
import jp.ac.I.ohara.teamseisaku.service.Testservice;

@Controller
public class MainController {
	@Autowired
	private Studentservice student_service;
//	@Autowired
//	private Student_repository studentrepository;
	@Autowired
	private Subjectservice subjectservice;
//	@Autowired
//	private Subjectrepository subjectrepository;
	@Autowired
	private Teacherservice teacherservice;
	@Autowired
	private Testservice testservice;
	
	@GetMapping("/")
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
	public String student(@Validated @ModelAttribute @NonNull Studentmodel studentmodel, RedirectAttributes redirectAttributes) {
	    try {
	        // 学籍番号の重複チェック
	        if (student_service.existsByNo(studentmodel.getNo())) {
	            throw new Exception("学籍番号が重複しています。");
	        }
 
	        this.student_service.save(studentmodel);
	        redirectAttributes.addFlashAttribute("successMessage", "学生が登録されました。");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	    }
	    return "redirect:/";
	}
	
	//削除
	@GetMapping("/deletestudent/{id}")
	public String deletestudent(@PathVariable(name="id")Long id, Model model, Studentmodel studentmodel){
		student_service.delete(studentmodel.getId());
		return "redirect:/studentlist";
	}
	//削除
		@GetMapping("/deletesubject/{id}")
		public String deletesubject(@PathVariable(name="id")Long id, Model model, Subjectmodel subjectmodel){
			student_service.delete(subjectmodel.getId());
			return "redirect:/studentlist";
		}
	//更新
	@GetMapping("/hyoujistudent/{id}")
	public ModelAndView add2(@PathVariable(name="id")Long id, Studentmodel studentmodel, ModelAndView model) {
		studentmodel = student_service.getOneBook(id);
		model.addObject("lists", this.student_service.get(id));
		model.addObject("student", studentmodel); 
	    model.setViewName("detailstudent");
	    return model;
	}
	// 本の情報を更新する
	@PostMapping("/hyoujistudent/{id}")
	public String update(@ModelAttribute @Validated Studentmodel editstudent, BindingResult result, Model model) {
			
	    // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/studentlist";
	    }
		
	    // 本を更新する
	    student_service.update(editstudent);
		
	    // 本の一覧画面にリダイレクト
	    return "redirect:/studentlist";
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
		@GetMapping("/hyoujisubject/{id}")
		public ModelAndView add2(@PathVariable(name="id")Long id, Subjectmodel subjectmodel, ModelAndView model) {
			subjectmodel = subjectservice.getOneBook(id);
			model.addObject("lists", this.subjectservice.get(id));
			model.addObject("subject", subjectmodel); 
		    model.setViewName("deltailsubject");
		    return model;
		}
	// 本の情報を更新する
	@PostMapping("/hyoujisubject/{id}")
	public String update(@ModelAttribute @Validated Subjectmodel editsubject, BindingResult result, Model model) {
			
	    // バリデーションエラーの場合
	    if (result.hasErrors()) {
	        // 編集画面に遷移
	        return "redirect:/subjectlist";
	    }
	    
	    // 本を更新する
	    subjectservice.update(editsubject);
		
	    // 本の一覧画面にリダイレクト
	    return "redirect:/subjectlist";
	}
	
	//学生・成績・出席リスト表示・出席詳細表示
	@GetMapping("/studentlist")
	public ModelAndView add3(ModelAndView model) {
	System.out.println(student_service.getStudentList().toString());
	model.addObject("studentList", student_service.getStudentList());
	model.setViewName("studentlist");
	return model;
	}
	@GetMapping("/subjectlist")
	public ModelAndView add4(ModelAndView model) {
	System.out.println(subjectservice.getSubjectList().toString());
	model.addObject("subjectList", subjectservice.getSubjectList());
	model.setViewName("subjectlist");
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
	@GetMapping("/seisekikanri")
	  public ModelAndView index2(@Validated @ModelAttribute @NonNull ModelAndView model, Subjectmodel subjectmodel,Studentmodel studentmodel ) {
	    System.out.println(subjectmodel);
		model.addObject("subject", this.subjectservice.getSubjectList());
	    model.addObject("student", studentmodel);
	    return model;
	  }
	@PostMapping("/seisekikanri")
	   public String studentlist(Model model,Studentmodel studentmodel,Subjectmodel subjectmodel,
			   						@RequestParam("entYear")Integer entYear,
			   						@RequestParam("classNum")String classNum,
			   						@RequestParam("subject")String subject,
			   						@RequestParam("count")String count) {
			model.addAttribute("studentList",this.student_service.searchStudents2(entYear,classNum));
			model.addAttribute("subjectname",subject);
			model.addAttribute("count",count+"回目");
			model.addAttribute("no",count);
			model.addAttribute("subjectcd",this.subjectservice.getsubjectcd(subject));
			
		return "seisekikanri";
		}
	@GetMapping("/tourokukanryou")
	public String tourokukanryou() {
		return "tourokukanryou";
	}
	@PostMapping("/tourokukanryou")
	public String touroku(Testmodel testmodel, RedirectAttributes result,
			RedirectAttributes redirectAttributes) {
			testmodel.setSchoolCd("aaa");
			System.out.println(testmodel);
			try {
				this.testservice.save(testmodel);
				System.out.print(12);		
				redirectAttributes.addFlashAttribute("exception", "");
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("exception", e.getMessage());
					}
			return "redirect:/tourokukanryou";
			}
}