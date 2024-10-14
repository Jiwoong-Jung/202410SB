package edu.du.sb1014_2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
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
