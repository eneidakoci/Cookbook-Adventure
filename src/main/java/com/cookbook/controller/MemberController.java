package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.configuration.AdminAccess;
import com.cookbook.configuration.UserAndAdminAccess;
import com.cookbook.domain.dto.*;
import com.cookbook.domain.entity.UserEntity;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.filter.Filter;
import com.cookbook.service.MemberService;
import com.cookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @MeasureTime
    @AdminAccess
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAllMembers(@RequestParam Integer pageNumber,
                                                          @RequestParam Integer pageSize,
                                                          @RequestParam(required = false) String sort,
                                                          @RequestParam(required = false) String name) {
        Filter nameFilter = new Filter("name",name,"LIKE", null, pageNumber, pageSize);
        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "name")){
                nameFilter.setSort(sortValue[1]);
            }else{
                throw new RuntimeException("Invalid sort field.");
            }
        }
        List<MemberDTO> memberDTOS = memberService.findAllMembers(nameFilter);
        if(memberDTOS == null){
            throw new ResourceNotFoundException("members not found.");
        }
        return ResponseEntity.ok(memberDTOS);
    }

    @GetMapping("/{memberId}")
    @AdminAccess
    public ResponseEntity<MemberDTO> findMemberById(@PathVariable Integer memberId) {
        MemberDTO member = memberService.findMemberById(memberId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
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

    @UserAndAdminAccess
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

    @AdminAccess
    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberDTO> deleteMember(@PathVariable Integer memberId) {
        MemberDTO deletedMember = memberService.deleteMember(memberId);
        if (deletedMember != null) {
            return ResponseEntity.ok(deletedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{memberId}/recipes")
    public ResponseEntity<List<RecipeDTO>> findRecipesByMemberId(@PathVariable Integer memberId) {
        List<RecipeDTO> recipes = memberService.findRecipesByMemberId(memberId);
        return ResponseEntity.ok(recipes);
    }

    @AdminAccess
    @GetMapping("/{memberId}/comments")
    public ResponseEntity<List<CommentDTO>> findCommentsByMemberId(@PathVariable Integer memberId) {
        List<CommentDTO> comments = memberService.findCommentsByMemberId(memberId);
        return ResponseEntity.ok(comments);
    }

    @AdminAccess
    @GetMapping("/{memberId}/ratings")
    public ResponseEntity<List<RatingDTO>> findRatingsByMemberId(@PathVariable Integer memberId) {
        List<RatingDTO> ratings = memberService.findRatingsByMemberId(memberId);
        return ResponseEntity.ok(ratings);
    }
}
