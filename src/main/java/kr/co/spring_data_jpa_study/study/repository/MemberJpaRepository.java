package kr.co.spring_data_jpa_study.study.repository;

import kr.co.spring_data_jpa_study.study.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
