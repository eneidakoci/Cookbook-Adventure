package com.cookbook.service.impl;

import com.cookbook.domain.dto.*;
import com.cookbook.domain.entity.*;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.domain.mapper.CommentMapper;
import com.cookbook.domain.mapper.MemberMapper;
import com.cookbook.domain.mapper.RatingMapper;
import com.cookbook.domain.mapper.RecipeMapper;
import com.cookbook.filter.Filter;
import com.cookbook.repository.CommentRepository;
import com.cookbook.repository.MemberRepository;
import com.cookbook.repository.RatingRepository;
import com.cookbook.repository.RecipeRepository;
import com.cookbook.service.MemberService;
import com.cookbook.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private UserService userService;

    @Override
    public List<MemberDTO> findAllMembers(Filter...filters) {
        List<MemberEntity> members = memberRepository.findAllMembers(filters);
        return members.stream()
                .map(MemberMapper::memberEntityToDto)
                .toList();
    }

    @Override
    public MemberDTO findMemberById(Integer id) throws ResourceNotFoundException{
        MemberEntity memberEntity = memberRepository.findMemberById(id);
        if(memberEntity == null){
            throw new ResourceNotFoundException("Member does not exist.");
        }
        return MemberMapper.memberEntityToDto(memberEntity);
    }

    @Override
    public MemberDTO createMember(MemberRequest member) {
        MemberEntity memberEntity = MemberMapper.memberRequestToEntity(member);
        UserEntity user = (UserEntity) userService.loadUserByUsername(member.getEmail());
        memberEntity.setUserEntity(user);
        MemberEntity createdMemberEntity = memberRepository.createMember(memberEntity);
        return MemberMapper.memberEntityToDto(createdMemberEntity);
    }

    @Override
    public MemberDTO updateMember(Integer id, MemberRequest member) {
        MemberEntity existingMemberEntity = memberRepository.findMemberById(id);
        if (existingMemberEntity != null) {
            MemberEntity updatedMemberEntity = MemberMapper.memberRequestToEntity(member);
            updatedMemberEntity.setMemberId(id);
            MemberEntity savedMemberEntity = memberRepository.updateMember(id, updatedMemberEntity);
//            savedMemberEntity.setProfile(member.getProfileEntity());
            return MemberMapper.memberEntityToDto(savedMemberEntity);
        }
        return null;
    }

    @Override
    public MemberDTO deleteMember(Integer id) {
        try {
            MemberEntity deleteMember = memberRepository.deleteMember(id);
            return MemberMapper.memberEntityToDto(deleteMember);
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
                    .map(RecipeMapper::recipeEntityToDto)
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
                    .map(CommentMapper::commentEntityToDto)
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
                    .map(RatingMapper::ratingEntityToDto)
                    .toList();
        }
        return null;
    }
}
