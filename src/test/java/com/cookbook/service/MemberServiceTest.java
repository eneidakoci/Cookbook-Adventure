package com.cookbook.service;
import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.dto.RatingDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.UserEntity;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.MemberRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private MemberService memberService = Mockito.spy(new MemberServiceImpl());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllMembers() {
        when(memberRepository.findAllMembers(any())).thenReturn(new ArrayList<>());

        List<MemberDTO> result = memberService.findAllMembers();

        Mockito.verify(memberRepository).findAllMembers();
        assertEquals(0, result.size());
    }

    @Test
    void testFindMemberById() {
        when(memberRepository.findMemberById(anyInt())).thenReturn(new MemberEntity());

        MemberDTO result = memberService.findMemberById(1);

        assertEquals(MemberDTO.class, result.getClass());
    }

    @Test
    void testCreateMember() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("Test Member");

        UserEntity user = new UserEntity();
        when(userService.loadUserByUsername(anyString())).thenReturn(user);

        when(memberRepository.createMember(any(MemberEntity.class))).thenReturn(new MemberEntity());

        MemberDTO result = memberService.createMember(memberRequest);

        assertEquals(MemberDTO.class, result.getClass());
    }

    @Test
    void testUpdateMember() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("Updated Member");

        when(memberRepository.findMemberById(anyInt())).thenReturn(new MemberEntity());

        when(memberRepository.updateMember(anyInt(), any(MemberEntity.class))).thenAnswer(invocation -> {
            MemberEntity argument = invocation.getArgument(1);
            return argument;
        });

        MemberDTO result = memberService.updateMember(1, memberRequest);

        assertEquals(MemberDTO.class, result.getClass());
        assertEquals("Updated Member", result.getName());
    }

    @Test
    void testDeleteMember() {
        when(memberRepository.deleteMember(anyInt())).thenReturn(new MemberEntity());

        MemberDTO result = memberService.deleteMember(1);

        assertEquals(MemberDTO.class, result.getClass());
    }

    @Test
    void testFindRecipesByMemberId() {
        when(memberRepository.findMemberById(anyInt())).thenReturn(new MemberEntity());
        when(recipeRepository.findRecipesByMember(any(MemberEntity.class))).thenReturn(new ArrayList<>());

        List<RecipeDTO> result = memberService.findRecipesByMemberId(1);

        Mockito.verify(recipeRepository).findRecipesByMember(any(MemberEntity.class));
        assertEquals(0, result.size());
    }

    @Test
    void testFindCommentsByMemberId() {
        when(memberRepository.findMemberById(anyInt())).thenReturn(new MemberEntity());
        when(commentRepository.findCommentsByMember(any(MemberEntity.class))).thenReturn(new ArrayList<>());

        List<CommentDTO> result = memberService.findCommentsByMemberId(1);

        Mockito.verify(commentRepository).findCommentsByMember(any(MemberEntity.class));
        assertEquals(0, result.size());
    }

    @Test
    void testFindRatingsByMemberId() {
        when(memberRepository.findMemberById(anyInt())).thenReturn(new MemberEntity());
        when(ratingRepository.findRatingsByMember(any(MemberEntity.class))).thenReturn(new ArrayList<>());

        List<RatingDTO> result = memberService.findRatingsByMemberId(1);

        Mockito.verify(ratingRepository).findRatingsByMember(any(MemberEntity.class));
        assertEquals(0, result.size());
    }
}