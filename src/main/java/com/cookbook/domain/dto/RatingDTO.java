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
public class RatingDTO extends BaseDomainDTO{
    private Integer ratingId;
    private MemberEntity memberEntity;
    private RecipeEntity recipeEntity;
    private Integer rate;

    public RatingDTO(LocalDateTime createdDate, LocalDateTime lastModified, boolean deleted, Integer ratingId, MemberEntity memberEntity, RecipeEntity recipeEntity, Integer rate) {
        super(createdDate, lastModified, deleted);
        this.ratingId = ratingId;
        this.memberEntity = memberEntity;
        this.recipeEntity = recipeEntity;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO ratingDTO = (RatingDTO) o;
        return Objects.equals(ratingId, ratingDTO.ratingId) && Objects.equals(memberEntity, ratingDTO.memberEntity) && Objects.equals(recipeEntity, ratingDTO.recipeEntity) && Objects.equals(rate, ratingDTO.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, memberEntity, recipeEntity, rate);
    }
}
