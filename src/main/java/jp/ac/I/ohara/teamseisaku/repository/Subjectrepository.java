package jp.ac.I.ohara.teamseisaku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.I.ohara.teamseisaku.model.Subjectmodel;

@Repository
public interface Subjectrepository extends JpaRepository<Subjectmodel, Long> {

}
