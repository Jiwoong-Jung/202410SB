package edu.du.sb1021_2.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import edu.du.sb1021_2.entity.Member;
import edu.du.sb1021_2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDao {

	private final MemberRepository memberRepository;


	public Optional<Member> selectByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public void insert(Member member) {
		memberRepository.save(member);
	}

	public void update(Member member) {
		memberRepository.save(member);
	}

	public List<Member> selectAll() {
		return memberRepository.findAll();
	}

}
