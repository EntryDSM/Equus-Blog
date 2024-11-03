package com.example.blog.domain.post.repository

import com.example.blog.domain.post.domain.Post
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PostRepository : CrudRepository<Post, UUID>
