package com.example.blog.domain.content.domain

import com.example.blog.domain.post.domain.Post
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "content"
    )
class Content (

    @Id
    @GeneratedValue
    val contentId: UUID? = null,

    @Column(nullable = false)
    val text: String,

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post





)