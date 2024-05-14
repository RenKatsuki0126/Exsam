package jp.ac.I.ohara.teamseisaku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.ac.I.ohara.teamseisaku.model.Teacher;
import jp.ac.I.ohara.teamseisaku.repository.Teacherrepository;

@Service
public class StudentDetailsServiceImplt implements UserDetailsService {
    @Autowired
    private Teacherrepository userRepository; // ユーザモデルのRepository
    /**
     * ユーザの検索を行う
     */
    @Override
    
    public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
        System.out.println("serach teacherId : " + teacherId);
        Teacher teacher = this.userRepository.findByteacherIdEquals(teacherId); // emailで検索するので「EmailEquals」としている
        //System.out.println(teacher.toString());
        return teacher;
    }
}