package com.cookbook.domain.dto;

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
    private Integer memberId;
    private Integer recipeId;
}
