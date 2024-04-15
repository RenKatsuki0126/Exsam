package jp.ac.I.ohara.teamseisaku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.I.ohara.teamseisaku.model.Studentmodel;



@Repository
public interface Student_repository extends JpaRepository<Studentmodel, Long> {
	
}