package com.example.demo.dao;

import com.example.demo.dto.SimpleBbsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ISimpleBbsDao2 {

    @Select("select * from simple_bbs order by id desc")
    public List<SimpleBbsDto> listDao();

    @Select("select * from simple_bbs where id = #{id}")
    public SimpleBbsDto viewDao(@Param("id") int id);
//    public int writeDao(String writer, String title, String content);
//    public int deleteDao(String id);
//    public int updateDao(String id, String writer, String title, String content);
}
