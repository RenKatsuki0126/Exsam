package jp.ac.I.ohara.teamseisaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.I.ohara.teamseisaku.model.Studentmodel;
import jp.ac.I.ohara.teamseisaku.repository.Student_repository;
;

@Service
@Transactional
public class Studentservice {

	@Autowired
	private Student_repository repository;
	
	@Autowired
	Student_repository rep;
	
	 
	public List<Studentmodel> getList(String no, Boolean isAttend) {
	  List<Studentmodel> list = rep.findAll(Specification
	    .where(Student_repository.userNameContains(no)
	  	.and(Student_repository.isAttendContains(isAttend))));
	    // 複数項目の場合は.and(),.or()でつなげる
	 
	  	return list;
	}
	
	

	/**
	 * アドレス帳一覧の取得
	 * @return List<oharabank>
	 */
	public List<Studentmodel> getStudentList() {
	    List<Studentmodel> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public Studentmodel get(@NonNull Long index) {
		Studentmodel student = this.repository.findById(index).orElse(new Studentmodel());
		return student;
	}

	/**
	 * アドレス帳一覧の取得
	 * @param AddressBook addressBook
	 */
	public void save(Studentmodel studentmodel) {
		System.out.println(studentmodel);
		studentmodel.setIsAttend(true);
		this.repository.save(studentmodel);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long id) {
		this.repository.deleteById(id);
	}
	public Studentmodel getOneBook(Long id) {
        // idを指定して本の情報を取得する
        Studentmodel student = this.repository.findById(id).orElseThrow();
        // 画面返却用のFormに値を設定する
        return student;
    }

    // 本を更新する
    public void update(Studentmodel editStudent) {
        // データベースに登録する値を保持するインスタンスの作成
        System.out.println(editStudent);
        // データベースを更新する
        this.repository.save(editStudent);
        System.out.println("aaa");
    }

 
	public List<Studentmodel> searchStudents2(Integer entYear, String classNum) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByEntYearAndClassNum(entYear, classNum);
		
	}
	public Studentmodel getStudent(Long id) {
		Studentmodel student = this.repository.findById(id).orElse(null);
		return student;
		}
	public List<Studentmodel> search(String keyword) {
	// TODO 自動生成されたメソッド・スタブ
		return null;
		}
	public boolean existsByNo(String no) {
	// TODO 自動生成されたメソッド・スタブ
		return false;
		}
	public boolean checkDuplicateStudentNo(String no) {
	// TODO 自動生成されたメソッド・スタブ
		return false;
		}
	}
