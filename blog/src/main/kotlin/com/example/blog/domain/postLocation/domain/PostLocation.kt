package com.example.blog.domain.postLocation.domain

import com.example.blog.domain.post.domain.Post
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity(name = "post_locations")
data class PostLocation(
    @Id
    @GeneratedValue
    val locationId: UUID? = null,
    @Column(nullable = false)
    val latitude: Double,
    @Column(nullable = false)
    val longitude: Double,
    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,
)
