package com.example.blog.domain.post.domain

import com.example.blog.domain.category.domain.Category
import com.example.blog.domain.content.domain.Content
import com.example.blog.domain.postLocation.domain.PostLocation
import com.example.blog.domain.series.domain.Series
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

import java.util.Date
import java.util.UUID

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue
    val postId: UUID? = null,

    val userId: UUID? = null,
    val title: String,
    val summary: String,
    val publishDate: Date,
    val isPinned: Boolean = false,
    val createdAt: Date = Date(),

    @OneToOne(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postLocation: PostLocation? = null,

    @ManyToMany
    @JoinTable(
        name = "postcategories",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<Category> = emptyList(),

    @ManyToOne
    @JoinColumn(name = "series_id")
    val series: Series? = null,

    @OneToOne(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val content: Content? = null
) {
    companion object {
        fun createRequiredFields(
            title: String,
            summary: String,
            publishDate: Date
        ): Post {
            return Post(
                title = title,
                summary = summary,
                publishDate = publishDate
            )
        }
    }
}