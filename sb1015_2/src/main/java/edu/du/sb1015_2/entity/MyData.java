package edu.du.sb1015_2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="mydata")
@Getter
@Setter
@ToString
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = true)
    private String mail;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String memo;

}
