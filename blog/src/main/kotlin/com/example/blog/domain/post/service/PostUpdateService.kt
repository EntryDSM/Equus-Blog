package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
import com.example.blog.domain.post.repository.PostRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Date
import java.util.UUID

@Service
class PostUpdateService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun execute(postId: UUID, updateRequest: PostUpdateRequest): Post {
        val existingPost = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException()

        existingPost.update(
            title = updateRequest.title,
            summary = updateRequest.summary,
            publishDate = Date()
        )

        return postRepository.save(existingPost)
    }
}