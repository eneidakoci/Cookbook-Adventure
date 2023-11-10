package com.cookbook.domain.dto;

import com.cookbook.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest extends BaseDomainRequest{
    private Integer categoryId;
    private String recipeName;
    private String ingredients;
    private String description;
    private Integer likes;
    private MemberEntity memberEntity;
}
