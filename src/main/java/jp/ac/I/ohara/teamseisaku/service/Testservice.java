package jp.ac.I.ohara.teamseisaku.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.I.ohara.teamseisaku.model.Testmodel;
import jp.ac.I.ohara.teamseisaku.repository.Testrepository;
;

@Service
@Transactional
public class Testservice {

	@Autowired
	private Testrepository repository;

	/**
	 * アドレス帳一覧の取得
	 * @return List<oharabank>
	 */
	public List<Testmodel> getStudentList() {
	    List<Testmodel> entityList = this.repository.findAll();
	    return entityList;
	}

	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  AddressBook
	 */
	public Testmodel get(@NonNull Long index) {
		Testmodel test = this.repository.findById(index).orElse(new Testmodel());
		return test;
	}

	/**
	 * アドレス帳一覧の取得
	 * @param AddressBook addressBook
	 */
	public void save(Testmodel testmodel) {
		System.out.println(testmodel);
		this.repository.save(testmodel);
	}

	/**
	 * アドレス帳データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long id) {
		this.repository.deleteById(id);
	}
	public Testmodel getOneBook(Long id) {
        // idを指定して本の情報を取得する
		Testmodel test = this.repository.findById(id).orElseThrow();
        // 画面返却用のFormに値を設定する
        return test;
    }

    // 本を更新する
    public void update(Testmodel edittest) {
        // データベースに登録する値を保持するインスタンスの作成
        System.out.println(edittest);
       
        
        // データベースを更新する
        this.repository.save(edittest);
        System.out.println("aaa");
    }
   


	public Testmodel getStudent(Long id) {
		Testmodel test = this.repository.findById(id).orElse(null);
		return test;
	}
}