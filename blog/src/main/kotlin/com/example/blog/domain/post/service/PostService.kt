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
    private val postRepository: PostRepository
) {

    fun createPost(postRequest: PostRequest): Post {
        val post = Post.createRequiredFields(
            title = postRequest.title,
            summary = postRequest.summary,
            publishDate = postRequest.publishDate
        )
        return postRepository.save(post)
    }

    // ID로 게시물 조회
    fun getPostById(postId: UUID): Post? {
        return postRepository.findById(postId).orElse(null)
    }

    // 게시물 수정
    fun updatePost(postId: UUID, postRequest: PostRequest): Post? {
        val existingPost = postRepository.findById(postId).orElse(null) ?: return null


        val postToSave = existingPost.copy(

            title = postRequest.title,
            summary = postRequest.summary,
            publishDate = postRequest.publishDate
        )


        return postRepository.save(existingPost)
    }

    // 게시물 삭제
    fun deletePost(postId: UUID) {
        postRepository.deleteById(postId)
    }
}