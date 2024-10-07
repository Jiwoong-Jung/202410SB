package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.SimpleBbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SimpleBbsDao implements ISimpleBbsDao {

    @Autowired
    JdbcTemplate template;
    
    @Override
    public List<SimpleBbsDto> listDao() {
        System.out.println("listDao()");

        String query = "select * from simple_bbs order by id desc";
        List<SimpleBbsDto> dtos = template.query(
                query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        
        return dtos;
    }

    @Override
    public SimpleBbsDto viewDao(String id) {
        System.out.println("viewDao()");
        
        String query = "select * from simple_bbs where id = " + id;
        SimpleBbsDto dto = template.queryForObject(
                query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        return dto;
    }

    @Override
    public int writeDao(final String writer, final String title, final String content) {
        System.out.println("writeDao()");
        
        String query = 
                "insert into simple_bbs (writer, title, content) " +
                " values (?, ?, ?)";
        return template.update(query, writer, title, content);
    }

    @Override
    public int deleteDao(final String id) {
        System.out.println("deleteDao()");
        
        String query = "delete from simple_bbs where id = ?";
        return template.update(query, Integer.parseInt(id));
    }

    @Override
    public int updateDao(String id, String writer, String title, String content) {
        System.out.println("updateDao()");

        String sql = "update simple_bbs set writer = ?, title = ?, content = ? where id = ?";
        return template.update(sql, writer, title, content, Integer.parseInt(id));
    }

}
