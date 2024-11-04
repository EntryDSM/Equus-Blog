package com.example.blog.domain.post.presentation.dto.response

import com.example.blog.domain.post.domain.Post
import java.util.Date
import java.util.UUID

class PostResponse(
    val postId: UUID,
    val title: String,
    val summary: String,
    val publishDate: Date,
    val createdAt: Date
) {
    companion object {
        fun fromEntity(post: Post): PostResponse {
            return PostResponse(
                postId = post.postId ?: UUID.randomUUID(),
                title = post.title,
                summary = post.summary,
                publishDate = post.publishDate,
                createdAt = post.createdAt // 추가: 생성 일시 포함
            )
        }
    }
}