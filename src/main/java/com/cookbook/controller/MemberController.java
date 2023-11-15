package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.domain.dto.*;
import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.mapper.impl.MemberMapperImpl;
import com.cookbook.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAllMembers(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<MemberDTO> members = memberService.findAllMembers(pageNumber, pageSize);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> findMemberById(@PathVariable Integer memberId) {
        MemberDTO member = memberService.findMemberById(memberId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberRequest memberRequest) {
        if(memberRequest.getName() == null || memberRequest.getName().isEmpty()){
            throw new GenericException("Member name is required.");
        }
        MemberDTO createdMember = memberService.createMember(memberRequest);
        if (createdMember != null) {
            URI locationOfCreatedMember = URI.create("/api/members/" + createdMember.getMemberId());
            return ResponseEntity.created(locationOfCreatedMember).body(createdMember);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberDTO> updateMember(
            @PathVariable Integer memberId,
            @RequestBody MemberRequest memberRequest) {
        MemberDTO updatedMember = memberService.updateMember(memberId, memberRequest);
        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberDTO> deleteMember(@PathVariable Integer memberId) {
        MemberDTO deletedMember = memberService.deleteMember(memberId);
        if (deletedMember != null) {
            return ResponseEntity.ok(deletedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{memberId}/recipes")
    public ResponseEntity<List<RecipeDTO>> findRecipesByMemberId(@PathVariable Integer memberId) {
        List<RecipeDTO> recipes = memberService.findRecipesByMemberId(memberId);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{memberId}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByMemberId(@PathVariable Integer memberId) {
        List<CommentDTO> comments = memberService.findCommentsByMemberId(memberId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{memberId}/ratings")
    public ResponseEntity<List<RatingDTO>> findRatingsByMemberId(@PathVariable Integer memberId) {
        List<RatingDTO> ratings = memberService.findRatingsByMemberId(memberId);
        return ResponseEntity.ok(ratings);
    }
}
