package edu.du.sb1014_2.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_board")
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardIdx;

    private String title;

    private String contents;

    private int hitCnt;

    private String creatorId;

    private Date createdDatetime;

    private String updaterId;

    private Date updatedDatetime;

    private String deletedYn;
}
