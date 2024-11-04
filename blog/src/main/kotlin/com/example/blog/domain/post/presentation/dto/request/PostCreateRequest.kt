package com.example.blog.domain.post.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class PostCreateRequest(
    @field:NotBlank(message = "Title must not be blank")
    @field:Size(max = 100, message = "Title must not exceed 100 characters")
    val title: String,

    @field:NotBlank(message = "Summary must not be blank")
    @field:Size(max = 500, message = "Summary must not exceed 500 characters")
    val summary: String
)
