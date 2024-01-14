package kr.co.spring_data_jpa_study.study.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kr.co.spring_data_jpa_study.study.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("회원 저장")
//    @Transactional
//    @Rollback(value = false)
    void save() {
        Member member = new Member("테스트 이름");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberRepository.findOneById(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원 조회")
    void find() {
        memberJpaRepository.findById(1L);
    }


    @Test
    @DisplayName("회원 저장2")
    @Transactional
    // @Rollback(value = false)
    void test() throws Exception {

        // given


        // when
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(Charset.forName("UTF-8"))
                )
                .andExpect(status().isOk());

        // then
        assertThat("").isEqualTo("");
    }


}