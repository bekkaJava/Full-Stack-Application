package com.example.ApiIntegration.service;

import com.example.ApiIntegration.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO findById(Long id);

    List<PostDTO> findAll();

    void save(PostDTO post);

    PostDTO update(Long id, PostDTO post);

    void deleteById(Long id);
}
