package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostRequest
import com.example.blog.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository,
) {
    fun createPost(request: PostRequest): Post {
        val post = Post(
            title = request.title,
            summary = request.summary,
            publishDate = Date() // 수정
        )
        return postRepository.save(post)
    }

    fun getPostById(postId: UUID): Post? {
        return postRepository.findById(postId).orElse(null)
    }

    fun updatePost(
        postId: UUID,
        postRequest: PostRequest
    ): Post? {
        val existingPost = postRepository.findById(postId).orElse(null) ?: return null

        val publishDate = Date()

        val postToSave = existingPost.copy(
            title = postRequest.title,
            summary = postRequest.summary,
            publishDate = publishDate // 현재 날짜로 설정
        )

        return postRepository.save(postToSave)
    }

    fun deletePost(postId: UUID) {
        postRepository.deleteById(postId)
    }
}
