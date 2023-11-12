package com.cookbook.controller;

import com.cookbook.domain.dto.CommentDTO;
import com.cookbook.domain.dto.CommentRequest;
import com.cookbook.domain.dto.MemberDTO;
import com.cookbook.domain.dto.RecipeDTO;
import com.cookbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllComments(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<CommentDTO> comments = commentService.findAllComments(pageNumber, pageSize);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable Integer commentId) {
        CommentDTO comment = commentService.findCommentById(commentId);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Integer commentId) {
        CommentDTO deletedComment = commentService.deleteComment(commentId);
        if (deletedComment != null) {
            return ResponseEntity.ok(deletedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{commentId}/member")
    public ResponseEntity<MemberDTO> findMemberByCommentId(@PathVariable Integer commentId) {
        MemberDTO member = commentService.findMemberByCommentId(commentId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
