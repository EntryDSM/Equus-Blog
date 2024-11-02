package com.example.blog.domain.post.presentation.controller

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostRequest
import com.example.blog.domain.post.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @PostMapping
    fun createPost(@RequestBody postRequest: PostRequest): Post {
        return postService.createPost(postRequest)
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: UUID): Post? {
        return postService.getPostById(postId)
    }

    @PatchMapping("/{postId}")
    fun updatePost(@PathVariable postId: UUID, @RequestBody postRequest: PostRequest): Post? {
        return postService.updatePost(postId, postRequest)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: UUID) {
        postService.deletePost(postId)
    }
}