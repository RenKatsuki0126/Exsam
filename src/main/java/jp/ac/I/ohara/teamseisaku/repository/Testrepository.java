package jp.ac.I.ohara.teamseisaku.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.I.ohara.teamseisaku.model.Testmodel;
public interface Testrepository extends JpaRepository<Testmodel, Long> {
	Testmodel findBystudentNoEquals(String studentNo);


}
