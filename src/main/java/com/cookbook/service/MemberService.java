package com.cookbook.service;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.MemberRequest;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;

import java.util.List;

public interface MemberService {
    List<MemberDTO> findAllMembers();
    MemberDTO findMemberById(Integer id);
    MemberDTO createMember(MemberRequest member);
    MemberDTO updateMember(Integer id, MemberRequest member);
    void deleteMember(Integer id);
    List<RecipeDTO> findRecipesByMemberId(Integer memberId);
    List<CommentDTO> findCommentsByMemberId(Integer memberId);
    List<CommentDTO> findRatingsByMemberId(Integer memberId);
}
