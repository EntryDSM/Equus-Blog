package com.example.blog.domain.category.domain

import com.example.blog.domain.categoryLocation.domain.CategoryLocation
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "categories",
    indexes = [Index(name = "idx_category_name", columnList = "category_name")],
)
class Category(
    @Id
    @GeneratedValue
    val categoryId: UUID? = null,
    @Column(nullable = false)
    val categoryName: String,
    @OneToOne(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    val categoryLocation: CategoryLocation? = null,
)
