package com.example.ApiIntegration.service;

import com.example.ApiIntegration.dto.PostDTO;
import com.example.ApiIntegration.dto.PostDTOMapper;
import com.example.ApiIntegration.model.Post;
import com.example.ApiIntegration.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostDTOMapper postDTOMapper;

    @Override
    public PostDTO findById(Long id) {
        return postRepository.findById(id)
                .map(postDTOMapper)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll()
                .stream()
                .map(postDTOMapper)
                .toList();
    }

    @Override
    public void save(PostDTO post) {

        Post savedPost = new Post();
        savedPost.setTitle(post.title());
        savedPost.setContent(post.content());
        postRepository.save(savedPost);

    }


    @Override
    public PostDTO update(Long id, PostDTO post) {

        Post updatedPost = postRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        updatedPost.setTitle(post.title());
        updatedPost.setContent(post.content());

        postRepository.save(updatedPost);

        return postDTOMapper.apply(updatedPost);
    }

    @Override
    public void deleteById(Long id) {

     postRepository.deleteById(id);

    }
}
