package jp.ac.I.ohara.teamseisaku.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.I.ohara.teamseisaku.model.Teacher;
import jp.ac.I.ohara.teamseisaku.repository.Teacherrepository;

@Service
@Transactional


public class Teacherservice {


	

		@Autowired
		private Teacherrepository repository;

		/**
		 * アドレス帳一覧の取得
		 * @return List<oharabank>
		 */
		public List<Teacher> getStudentList() {
		    List<Teacher> entityList = this.repository.findAll();
		    return entityList;
		}

		/**
		 * 詳細データの取得
		 * @param @NonNull Long index
		 * @return  AddressBook
		 */
		public Teacher get(@NonNull Long index) {
			Teacher teacher = this.repository.findById(index).orElse(new Teacher());
			return teacher;
		}
		
		/**
		 * アドレス帳一覧の取得
		 * @param AddressBook addressBook
		 */
		public void save(@Nonnull Teacher teacher) {
			
			this.repository.save(teacher);
			System.out.println(teacher);
		}
		/**
		 * アドレス帳データの削除
		 * @param @NonNull Long index
		 */
		public void delete(@NonNull Long id) {
			this.repository.deleteById(id);
		}
		public Teacher getOneBook(Long id) {
	        // idを指定して本の情報を取得する
			Teacher teacher = this.repository.findById(id).orElseThrow();
	        // 画面返却用のFormに値を設定する
	        return teacher;
	    }

	    // 本を更新する
	    public void update(Teacher editteacher) {
	        // データベースに登録する値を保持するインスタンスの作成
	        System.out.println(editteacher);
	       
	        
	        // データベースを更新する
	        this.repository.save(editteacher);
	        System.out.println("aaa");
	    }
	   


	    public Teacher getStudent(Long id) {
	    	Teacher teacher = this.repository.findById(id).orElse(null);
	    	return teacher;
	    	}
	    }


