package com.cookbook.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String field;
    private String value;
    private String operator;
    private String sort;
    private Integer pageNumber;
    private Integer pageSize;
}
