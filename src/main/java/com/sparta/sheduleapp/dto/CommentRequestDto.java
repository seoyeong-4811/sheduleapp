package com.sparta.sheduleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private  String comment;
    private  Long memberId;

}

