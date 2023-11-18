package com.cookbook.controller;

import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MemberControllerMockTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllMembers() {
        // Mocking the service method
        when(memberService.findAllMembers(any())).thenReturn(Arrays.asList(new MemberDTO(), new MemberDTO()));

        // Testing the controller method
        ResponseEntity<List<MemberDTO>> responseEntity = memberController.findAllMembers(1, 10, null, null);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testFindMemberById() {
        when(memberService.findMemberById(anyInt())).thenReturn(new MemberDTO());

        ResponseEntity<MemberDTO> responseEntity = memberController.findMemberById(1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(MemberDTO.class, responseEntity.getBody().getClass());
    }

    @Test
    void testCreateMemberWithValidRequest() {
        MemberDTO expectedMember = new MemberDTO();
        expectedMember.setName("John");
        when(memberService.createMember(any(MemberRequest.class))).thenReturn(expectedMember);

        MemberRequest validRequest = new MemberRequest();
        validRequest.setName("John");
        ResponseEntity<MemberDTO> responseEntity = memberController.createMember(validRequest);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(MemberDTO.class, responseEntity.getBody().getClass());
        assertEquals("John", responseEntity.getBody().getName());
    }

    @Test
    void testCreateMemberWithoutName() {
        when(memberService.createMember(any(MemberRequest.class))).thenThrow(new GenericException("Member name is required."));

        GenericException exception = assertThrows(GenericException.class, () -> {
            memberController.createMember(new MemberRequest());
        });

        assertEquals("Member name is required.", exception.getMessage());
    }

}
