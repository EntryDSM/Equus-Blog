package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date

@Service
class PostCreateService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun execute(request: PostCreateRequest): Post {
        val post = Post(
            title = request.title,
            summary = request.summary,
            publishDate = Date() // 현재 시간을 설정
        )
        return postRepository.save(post)
    }
}