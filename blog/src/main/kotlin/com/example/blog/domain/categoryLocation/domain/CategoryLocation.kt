package com.example.blog.domain.categoryLocation.domain

import com.example.blog.domain.category.domain.Category
import jakarta.persistence.*

import java.util.UUID

@Entity
@Table(name = "category_locations")
data class CategoryLocation(
    @Id
    @GeneratedValue
    val locationId: UUID? = null,

    @Column(nullable = false)
    val latitude: Double,

    @Column(nullable = false)
    val longitude: Double,

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category
)