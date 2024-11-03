package com.example.blog.domain.series.domain

import com.example.blog.domain.post.domain.Post
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "series")
class Series(
    @Id
    @GeneratedValue
    val seriesId: UUID? = null,
    @Column(nullable = false)
    val seriesName: String,
    val description: String? = null,
    @OneToMany(mappedBy = "series", cascade = [CascadeType.ALL])
    val posts: List<Post> = listOf(),
)
