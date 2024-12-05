package com.example.blog.domain.post.service

import com.example.blog.domain.post.repository.PostRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PostDeleteService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun execute(postId: UUID) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()

        postRepository.delete(post)
    }
}