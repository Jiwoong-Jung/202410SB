package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

//@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

  @Id
  @Column(name = "member_id")
  private String id;

  private String username;

  // 연관관계 매핑
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  public Member(String id, String username) {
    this.id = id;
    this.username = username;
  }
}
