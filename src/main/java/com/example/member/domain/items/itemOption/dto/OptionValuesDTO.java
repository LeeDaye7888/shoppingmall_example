package com.example.member.domain.option.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionValuesDTO {

    private String optionName;
    private String optionValue;

    // 더 추가
}
