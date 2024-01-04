package kr.co.spring_data_jpa_study.study.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.co.spring_data_jpa_study.study.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
