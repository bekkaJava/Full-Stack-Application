package com.example.ApiIntegration.dto;

import com.example.ApiIntegration.model.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {
    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent());
    }
}
