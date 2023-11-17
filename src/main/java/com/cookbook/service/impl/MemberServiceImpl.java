package com.cookbook.service.impl;

import com.cookbook.domain.dto.*;
import com.cookbook.domain.entity.CommentEntity;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RatingEntity;
import com.cookbook.domain.entity.RecipeEntity;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.impl.*;
import com.cookbook.filter.Filter;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.MemberRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<MemberDTO> findAllMembers(Filter...filters) {
        List<MemberEntity> members = memberRepository.findAllMembers(filters);
        return members.stream()
                .map(MemberMapperImpl::memberEntityToDto)
                .toList();
    }

    @Override
    public MemberDTO findMemberById(Integer id) throws ResourceNotFoundException{
        MemberEntity memberEntity = memberRepository.findMemberById(id);
        if(memberEntity == null){
            throw new ResourceNotFoundException("Member does not exist.");
        }
        return MemberMapperImpl.memberEntityToDto(memberEntity);
    }

    @Override
    public MemberDTO createMember(MemberRequest member) {
        MemberEntity memberEntity = MemberMapperImpl.memberRequestToEntity(member);
        MemberEntity createdMemberEntity = memberRepository.createMember(memberEntity);
        return MemberMapperImpl.memberEntityToDto(createdMemberEntity);
    }

    @Override
    public MemberDTO updateMember(Integer id, MemberRequest member) {
        MemberEntity existingMemberEntity = memberRepository.findMemberById(id);
        if (existingMemberEntity != null) {
            MemberEntity updatedMemberEntity = MemberMapperImpl.memberRequestToEntity(member);
            updatedMemberEntity.setMemberId(id);
            MemberEntity savedMemberEntity = memberRepository.updateMember(id, updatedMemberEntity);
//            savedMemberEntity.setProfile(member.getProfileEntity());
            return MemberMapperImpl.memberEntityToDto(savedMemberEntity);
        }
        return null;
    }

    @Override
    public MemberDTO deleteMember(Integer id) {
        try {
            MemberEntity deleteMember = memberRepository.deleteMember(id);
            return MemberMapperImpl.memberEntityToDto(deleteMember);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Member not found with id: " + id);
        }
    }

    @Override
    public List<RecipeDTO> findRecipesByMemberId(Integer memberId) {
        MemberEntity memberEntity = memberRepository.findMemberById(memberId);
        if (memberEntity != null) {
            List<RecipeEntity> recipes = recipeRepository.findRecipesByMember(memberEntity);
            return recipes.stream()
                    .map(RecipeMapperImpl::recipeEntityToDto)
                    .toList();
        }
        return null;
    }

    @Override
    public List<CommentDTO> findCommentsByMemberId(Integer memberId) {
        MemberEntity memberEntity = memberRepository.findMemberById(memberId);
        if (memberEntity != null) {
            List<CommentEntity> comments = commentRepository.findCommentsByMember(memberEntity);
            return comments.stream()
                    .map(CommentMapperImpl::commentEntityToDto)
                    .toList();
        }
        return null;
    }

    @Override
    public List<RatingDTO> findRatingsByMemberId(Integer memberId) {
        MemberEntity memberEntity = memberRepository.findMemberById(memberId);
        if (memberEntity != null) {
            List<RatingEntity> ratings = ratingRepository.findRatingsByMember(memberEntity);
            return ratings.stream()
                    .map(RatingMapperImpl::ratingEntityToDto)
                    .toList();
        }
        return null;
    }
}
