package com.example.demo.dao;

import com.example.demo.dto.SimpleBbsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISimpleBbsDao3 {

    public List<SimpleBbsDto> list();
    public SimpleBbsDto viewDao(String id);
    public int writeDao(String writer, String title, String content);
    public int deleteDao(String id);
    public int updateDao(String id, String writer, String title, String content);
}
