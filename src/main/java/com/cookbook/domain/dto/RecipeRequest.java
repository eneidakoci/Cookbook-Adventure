package com.cookbook.domain.dto;

import com.cookbook.domain.entity.CategoryEntity;
import com.cookbook.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest extends BaseDomainRequest{
    private Set<CategoryEntity> categoryEntity;
    private String recipeName;
    private String ingredients;
    private String description;
    private Integer likes;
    private MemberEntity memberEntity;
}
