package com.example.blog.domain.post.service

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.dto.request.PostCreateRequest
import com.example.blog.domain.post.presentation.dto.request.PostUpdateRequest
import com.example.blog.domain.post.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date
import java.util.UUID

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository,
) {
    fun createPost(request: PostCreateRequest): Post {
        val post = Post(
            title = request.title,
            summary = request.summary,
            publishDate = Date() // 수정
        )
        return postRepository.save(post)
    }

    fun getPostById(postId: UUID): Post {
        return postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException("Post not found with id: $postId")
    }

    fun updatePost(postId: UUID, updateRequest: PostUpdateRequest): Post {
        val existingPost = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException("Post with ID $postId not found")


        existingPost.update(
            title = updateRequest.title,
            summary = updateRequest.summary,
            publishDate = Date()
        )

        return postRepository.save(existingPost)
    }

    fun deletePost(postId: UUID) {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw NotFoundException("Post with ID $postId not found")

        postRepository.delete(post)
    }
}
