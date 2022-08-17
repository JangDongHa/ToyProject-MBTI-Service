package com.example.miniproject.dto.request;

import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestCommentDto{
    private String content;
}

