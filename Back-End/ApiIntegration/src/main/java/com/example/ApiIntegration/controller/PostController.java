package com.example.ApiIntegration.controller;

import com.example.ApiIntegration.dto.PostDTO;
import com.example.ApiIntegration.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> findById(@PathVariable Long postId) {

        return ok(postService.findById(postId));
    }


    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> findAll() {

        return ok(postService.findAll());
    }


    @PostMapping("/")
    public ResponseEntity<Void> createCategory(@RequestBody PostDTO post) {

        postService.save(post);

        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long postId) {

        postService.deleteById(postId);

        return noContent().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> update(@PathVariable Long postId, @RequestBody PostDTO post) {

        return new ResponseEntity<>(postService.update(postId, post), ACCEPTED);

    }
}
