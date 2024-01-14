package kr.co.spring_data_jpa_study.study.service;

import jakarta.persistence.EntityManager;
import kr.co.spring_data_jpa_study.study.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("회원 저장")
    @Transactional
    // @Rollback(value = false)
    void save() throws Exception {

        // given
        Member member = new Member("memberA");

        /// when
        Long savedId = memberService.join(member);
        em.flush();

        // then
        Assertions.assertThat(member).isEqualTo(memberService.findOne(savedId));
    }

    @Test
    @DisplayName("회원 중복 검사")
    @Transactional
    // @Rollback(value = false)
    void validationDuplicate() throws Exception {

        // given
        Member member1 = new Member("kim");
        Member member2 = new Member("kim");

        /// when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); // 예외가 발생해야 한다.
        });
    }


}