package com.example.blog.domain.post.presentation.controller

import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
import com.example.blog.domain.post.presentation.dto.response.PostResponse
import com.example.blog.domain.post.service.PostCreateService
import com.example.blog.domain.post.service.PostUpdateService
import com.example.blog.domain.post.service.PostDeleteService
import com.example.blog.domain.post.service.PostGetService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/posts")
class PostController(
    private val postCreateService: PostCreateService,
    private val postGetService: PostGetService,
    private val postUpdateService: PostUpdateService,
    private val postDeleteService: PostDeleteService,
) {

    @PostMapping
    fun createPost(
        @RequestBody @Valid request: PostCreateRequest,
    ): PostResponse {
        val post = postCreateService.execute(request)
        return PostResponse.fromEntity(post)
    }

    @GetMapping("/{postId}")
    fun getPostById(
        @PathVariable postId: UUID,
    ): PostResponse {
        val post = postGetService.execute(postId)
        return PostResponse.fromEntity(post)
    }

    @PatchMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: UUID,
        @RequestBody @Valid request: PostUpdateRequest,
    ): PostResponse {
        val post = postUpdateService.execute(postId, request)
        return PostResponse.fromEntity(post)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable postId: UUID,
    ) {
        postDeleteService.execute(postId)
    }
}
