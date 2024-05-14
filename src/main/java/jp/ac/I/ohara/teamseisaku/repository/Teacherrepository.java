package jp.ac.I.ohara.teamseisaku.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.I.ohara.teamseisaku.model.Teacher;

public interface Teacherrepository extends JpaRepository<Teacher, Long> {
	
	Teacher findByteacherIdEquals(String teacherId);
}
