package com.example.ApiIntegration.repository;

import com.example.ApiIntegration.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
