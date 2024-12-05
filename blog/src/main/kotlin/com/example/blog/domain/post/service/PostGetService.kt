package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.repository.PostRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PostGetService(
    private val postRepository: PostRepository
) {
    @Transactional(readOnly = true)
    fun execute(postId: UUID): Post {
        return postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()
    }
}