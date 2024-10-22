package edu.du.sb1022secu3.repository;

import edu.du.sb1022secu3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
