package com.example.blog.domain.post.presentation.controller

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
import com.example.blog.domain.post.presentation.dto.response.PostResponse
import com.example.blog.domain.post.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {
    @PostMapping
    fun createPost(
        @RequestBody request: PostCreateRequest,
    ): PostResponse {
        val post = postService.createPost(request)
        return PostResponse.fromEntity(post) // 엔티티에서 dto로 변환
    }

    @GetMapping("/{postId}")
    fun getPostById(
        @PathVariable postId: UUID,
    ): PostResponse? {
        val post = postService.getPostById(postId)
        return PostResponse.fromEntity(post)
    }

    @PatchMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: UUID,
        @RequestBody request: PostUpdateRequest,
    ): PostResponse? {
        val post = postService.updatePost(postId, request)
        return PostResponse.fromEntity(post)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable postId: UUID,
    ) {
        postService.deletePost(postId)
    }
}
