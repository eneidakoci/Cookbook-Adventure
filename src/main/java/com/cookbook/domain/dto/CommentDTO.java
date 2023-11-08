package com.cookbook.domain.dto;

import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO extends BaseDomainDTO{
    private Integer commentId;
    private String commentText;
    private MemberEntity memberEntity;
    private RecipeEntity recipeEntity;

    public CommentDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer commentId, String commentText, MemberEntity memberEntity, RecipeEntity recipeEntity) {
        super(createdDate, lastModified, deleted);
        this.commentId = commentId;
        this.commentText = commentText;
        this.memberEntity = memberEntity;
        this.recipeEntity = recipeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(commentText, that.commentText) && Objects.equals(memberEntity, that.memberEntity) && Objects.equals(recipeEntity, that.recipeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentText, memberEntity, recipeEntity);
    }
}
