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
@Table(name="subject")


public class Subjectmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name="name",length = 10, nullable = true)
    private String name;
    
    @Column(name="school_cd", length = 4,nullable = true)
    private String schoolCd;
    
    @Column(name="cd",length = 3, nullable = true)
     private String cd;
    
}

