package com.cookbook.domain.dto;

import com.cookbook.domain.entity.MemberEntity;
import com.cookbook.domain.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest extends BaseDomainRequest{
    private String commentText;
    private MemberEntity memberEntity;
    private RecipeEntity recipeEntity;
}
