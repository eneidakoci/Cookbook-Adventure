package com.cookbook.controller;

import com.cookbook.aspect.MeasureTime;
import com.cookbook.configuration.UserAndAdminAccess;
import com.cookbook.domain.dto.*;
import com.cookbook.domain.exception.GenericException;
import com.cookbook.domain.exception.ResourceNotFoundException;
import com.cookbook.filter.Filter;
import com.cookbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @UserAndAdminAccess
    @MeasureTime
    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllComments(@RequestParam Integer pageNumber,
                                                            @RequestParam Integer pageSize,
                                                            @RequestParam(required = false) String sort,
                                                            @RequestParam(required = false) String dateCreated) {
        Filter dateFilter = new Filter("createdDate",dateCreated,"LIKE", null, pageNumber, pageSize);
        if(sort != null){
            String[] sortValue = sort.split(":");
            if(Objects.equals(sortValue[0], "createdDate")){
                dateFilter.setSort(sortValue[1]);
            }else{
                throw new RuntimeException("Invalid sort field.");
            }
        }
        List<CommentDTO> commentsDTOs = commentService.findAllComments(dateFilter);
        if(commentsDTOs == null){
            throw new ResourceNotFoundException("comments not found.");
        }
        return ResponseEntity.ok(commentsDTOs);
    }

    @UserAndAdminAccess
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable Integer commentId) {
        CommentDTO comment = commentService.findCommentById(commentId);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentRequest commentRequest) {
        CommentDTO createdComment = commentService.createComment(commentRequest);
        if (createdComment != null) {
            URI locationOfCreatedComment = URI.create("/api/comments/" + createdComment.getCommentId());
            return ResponseEntity.created(locationOfCreatedComment).body(createdComment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @UserAndAdminAccess
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Integer commentId,
            @RequestBody CommentRequest commentRequest) {
        CommentDTO updatedComment = commentService.updateComment(commentId, commentRequest);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Integer commentId) {
        CommentDTO deletedComment = commentService.deleteComment(commentId);
        if (deletedComment != null) {
            return ResponseEntity.ok(deletedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{commentId}/member")
    public ResponseEntity<MemberDTO> findMemberByCommentId(@PathVariable Integer commentId) {
        MemberDTO member = commentService.findMemberByCommentId(commentId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @UserAndAdminAccess
    @GetMapping("/{commentId}/recipe")
    public ResponseEntity<RecipeDTO> findRecipeByCommentId(@PathVariable Integer commentId) {
        RecipeDTO recipe = commentService.findRecipeByCommentId(commentId);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
