package com.cookbook.service;

import com.cookbook.domain.dto.*;

import java.util.List;

public interface MemberService {
    List<MemberDTO> findAllMembers();
    MemberDTO findMemberById(Integer id);
    MemberDTO createMember(MemberRequest member);
    MemberDTO updateMember(Integer id, MemberRequest member);
    MemberDTO deleteMember(Integer id);
    List<RecipeDTO> findRecipesByMemberId(Integer memberId);
    List<CommentDTO> findCommentsByMemberId(Integer memberId);
    List<RatingDTO> findRatingsByMemberId(Integer memberId);
}
