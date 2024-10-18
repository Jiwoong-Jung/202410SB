package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
public class Dept {
    @Id
    private Integer deptno;
    private String dname;
    private String loc;
}
