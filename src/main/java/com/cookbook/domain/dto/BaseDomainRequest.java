package com.cookbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomainRequest {
    public LocalDateTime createdDate;
    public LocalDateTime lastModified;
    private boolean deleted;
}

