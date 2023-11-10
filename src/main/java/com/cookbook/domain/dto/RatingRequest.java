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
public class RatingRequest extends BaseDomainRequest{
    private Integer memberId;
    private Integer recipeId;
    private Integer rate;
}
