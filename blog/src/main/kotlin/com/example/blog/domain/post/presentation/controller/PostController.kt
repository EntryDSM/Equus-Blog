package com.example.blog.domain.post.presentation.controller

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
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
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @RequestBody @Valid postRequest: PostCreateRequest,
    ): Post {
        return postService.createPost(postRequest)
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    fun getPostById(
        @PathVariable postId: UUID,
    ): Post {
        return postService.getPostById(postId)
    }

    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePost(
        @PathVariable postId: UUID,
        @RequestBody @Valid postRequest: PostUpdateRequest,
    ): Post {
        return postService.updatePost(postId, postRequest)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable postId: UUID,
    ) {
        postService.deletePost(postId)
    }
}
}
