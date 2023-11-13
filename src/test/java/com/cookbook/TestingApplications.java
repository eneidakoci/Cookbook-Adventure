//package com.cookbook;
//import com.cookbook.domain.dto.MemberDTO;
//import com.cookbook.domain.entity.MemberEntity;
//import com.cookbook.repository.MemberRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import
//
//        org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import
//
//        static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import
//
//        static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//
//@AutoConfigureMockMvc
//
//@RunWith(SpringRunner.class)
//
//public class TestingApplications {
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//
//
//    private MockMvc mockMvc;
//
//    @Autowired
//
//
//    private MemberRepository memberRepository;
//
//    @Test
//    public void save() throws Exception {
//
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setEmail("eneida.koci@ikubinfo.al");
//        memberDTO.setName("eneida");
//        memberDTO.setLastName("koci");
//
//        mockMvc
//                .perform(post("/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(memberDTO)))
//                .andExpect(status().is2xxSuccessful());
//
//        MemberEntity memberEntity = memberRepository.findMemberById(1); // assumed
//        log.info("USER DAO - {} ", memberEntity);
//
//        Assertions.assertEquals(memberEntity.getMemberId(), 1);
//        Assertions.assertEquals(memberEntity.getEmail(), "eneida.koci@ikubinfo.al");
//        Assertions.assertEquals(memberEntity.getName(), "eneida");
//        Assertions.assertEquals(memberEntity.getLastName(), "koci");
//    }
//}