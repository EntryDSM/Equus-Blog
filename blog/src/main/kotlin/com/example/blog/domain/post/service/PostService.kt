package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
import com.example.blog.domain.post.repository.PostRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date
import java.util.UUID

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun createPost(request: PostCreateRequest): Post {
        val post = Post(
            title = request.title,
            summary = request.summary,
            publishDate = Date() // 수정
        )
        return postRepository.save(post)
    }

    @Transactional(readOnly = true)
    fun getPostById(postId: UUID): Post {
        return postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()
    }

    @Transactional
    fun updatePost(postId: UUID, updateRequest: PostUpdateRequest): Post {
        val existingPost = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()

        existingPost.update(
            title = updateRequest.title,
            summary = updateRequest.summary,
            publishDate = Date()
        )

        return postRepository.save(existingPost)
    }

    @Transactional
    fun deletePost(postId: UUID) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()

        postRepository.delete(post)
    }
}
