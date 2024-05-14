package jp.ac.I.ohara.teamseisaku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.I.ohara.teamseisaku.model.Subjectmodel;
import jp.ac.I.ohara.teamseisaku.repository.Subjectrepository;

@Service
@Transactional
public class Subjectservice {
	

		@Autowired
		private Subjectrepository repository;

		/**
		 * アドレス帳一覧の取得
		 * @return List<oharabank>
		 */
		public List<Subjectmodel> getSubjectList() {
		    List<Subjectmodel> entityList = this.repository.findAll();
		    return entityList;
		}

		/**
		 * 詳細データの取得
		 * @param @NonNull Long index
		 * @return  AddressBook
		 */
		public Subjectmodel get(@NonNull Long index) {
			Subjectmodel subject = this.repository.findById(index).orElse(new Subjectmodel());
			return subject;
		}
		
		/**
		 * アドレス帳一覧の取得
		 * @param AddressBook addressBook
		 */
		public void save(@Nonnull Subjectmodel subjectmodel) {
			
			this.repository.save(subjectmodel);
			System.out.println(subjectmodel);
		}
		/**
		 * アドレス帳データの削除
		 * @param @NonNull Long index
		 */
		public void delete(@NonNull Long id) {
			this.repository.deleteById(id);
		}
		public Subjectmodel getOneBook(Long id) {
	        // idを指定して本の情報を取得する
	        Subjectmodel subject = this.repository.findById(id).orElseThrow();
	        // 画面返却用のFormに値を設定する
	        return subject;
	    }

	    // 本を更新する
	    public void update(Subjectmodel editSubject) {
	        // データベースに登録する値を保持するインスタンスの作成
	        System.out.println(editSubject);
	       
	        
	        // データベースを更新する
	        this.repository.save(editSubject);
	        System.out.println("aaa");
	    }
	    
	    public String getsubjectcd(String name) {
	    	Subjectmodel subject = this.repository.findByname(name);
	    	return subject.getSchoolCd();
	    }
	    public Subjectmodel getSubject(Long id) {
	    	Subjectmodel subject = this.repository.findById(id).orElse(null);
	    	return subject;
	    	}
	    }

