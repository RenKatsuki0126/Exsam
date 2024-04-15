package jp.ac.I.ohara.teamseisaku.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="student")
public class Studentmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name="no",length = 10, nullable = false)
    private String no;


    @Column(name="name",length = 10, nullable = true)
    private String name;
    
    
    @Column(name="ent_year",length = 10, nullable = true)
    private String entYear;
    
    
    @Column(name="class_num",length = 3, nullable = true)
     private Integer classNum;
    
    @Column(name="is_attend", nullable = true)
    private Boolean isAttend;
    
    
    @Column(name="school_cd", length = 3,nullable = true)
    private String schoolCd;
    
//    @Column(nullable = true)
//    private Integer age;

}