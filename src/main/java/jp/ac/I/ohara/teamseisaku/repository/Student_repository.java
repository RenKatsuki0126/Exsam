package jp.ac.I.ohara.teamseisaku.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jp.ac.I.ohara.teamseisaku.model.Studentmodel;



@Repository
public interface Student_repository extends JpaRepository<Studentmodel, Long> ,

	 
		 JpaSpecificationExecutor<Studentmodel>{
		// 独自条件生成の関数　userNameに値が設定されている場合のみ、条件を生成する。
		static Specification<Studentmodel> userNameContains(String no) {
		  return StringUtils.isEmpty(no) ? null : new Specification<Studentmodel>() {
		      @Override
		      public Predicate toPredicate(Root<Studentmodel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.like(root.get("no"), "%" + no + "%");
		      }
		   };
		}
	 
	 
			static Specification<Studentmodel> isAttendContains(Boolean isAttend) {
		        return (root, query, cb) -> {
		            if (isAttend == null) {
		                // isAttendがnullの場合、isAttendがfalseであるレコードを非表示にする条件を追加
		                return cb.or(cb.isNull(root.get("isAttend")), cb.isTrue(root.get("isAttend")));
		            }
		            // isAttendが指定されている場合は、その値に基づいてクエリを生成する
		            return null;
		        };
		    }


			List<Studentmodel> findByEntYearAndClassNum(Integer entYear, String classNum);


	 
		
	 
	 
	 
}
