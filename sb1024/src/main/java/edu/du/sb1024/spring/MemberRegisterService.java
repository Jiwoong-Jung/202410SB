package edu.du.sb1024.spring;

import edu.du.sb1024.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;

	public Long regist(RegisterRequest req) {
		Optional<Member> member = memberDao.selectByEmail(req.getEmail());
		if (member.isPresent()) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}

		Member newMember = Member.builder()
				.email(req.getEmail())
				.password(req.getPassword())
				.regdate(LocalDateTime.now())
				.role("USER")
				.build();
		memberDao.insert(newMember);
		System.out.println("====>" + newMember);
		return newMember.getId();
	}
}
