package com.example.blog.domain.post.presentation.dto.request

import java.util.Date
import java.util.UUID

class PostRequest(
    val title: String,
    val summary: String,
    val publishDate: Date

)