package edu.du.chap17.dao;

import edu.du.chap17.model.Article;
import org.apache.ibatis.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper
public interface ArticleDao {

	@Select("select count(*) from article")
	public int selectCount();

	@Select("select article_id id, group_id groupId, sequence_no sequenceNumber, posting_date postingDate, read_count readCount, writer_name writerName, password, title from article order by sequence_no desc limit #{firstRow}, #{endRow}")
	public List<Article> select(int firstRow, int endRow);

//	@Insert("INSERT INTO user(email, name, password) VALUES (#{user.email}, #{user.name}, #{user.password})")
//	@Options(useGeneratedKeys = true, keyProperty = "dbid", keyColumn="dbid") //SQL이 생성한 KEY 값을 매핑된 객체의 dbid 필드에도 담아주겠다.
//	int insert(@Param("user") User user);

	@Insert("insert into article "
			+ "(group_id, sequence_no, posting_date, read_count, "
			+ "writer_name, password, title, content) "
			+ "values (#{article.groupId}, #{article.sequenceNumber}, #{article.postingDate}, 0, #{article.writerName}, #{article.password}, #{article.title}, #{article.content})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id") //SQL이 생성한 KEY 값을 매핑된 객체의 id 필드에도 담아주겠다.
	public int insert(@Param("article") Article article);

	@Select("select article_id id, group_id groupId, sequence_no sequenceNumber, posting_date postingDate, read_count readCount, writer_name writerName, password, title, content from article where article_id = #{articleId}")
	public Article selectById(int articleId);

	@Update("update article "
			+ "set read_count = read_count + 1 "
			+ "where article_id = #{articleId}")
	public void increaseReadCount(int articleId);
	public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum);
	public int update(Article article);
	public void delete(int articleId);

}
