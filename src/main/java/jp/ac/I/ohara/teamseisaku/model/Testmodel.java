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
@Table(name = "TEST")


public class Testmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "studentno", nullable = false, length = 10)
    private String studentNo;
    
    @Column(name = "subjectcd", nullable = false, length = 3)
    private String subjectCd;
    
    @Column(name = "schoolcd", nullable = false, length = 10)
    private String schoolCd;
    
    @Column(name = "no", nullable = false, length = 10)
    private String no;
    
    @Column(name = "point", length = 10)
    private String point;
    
    @Column(name = "classnum", length = 5)
    private String classNum;
    
//    @ManyToOne
//    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "id")
//    private Studentmodel student;
//
//    @ManyToOne
//    @JoinColumn(name="SUBJECT_ID", referencedColumnName="id")
//    private Subjectmodel subject;
//    // Getter and Setter methods
}
