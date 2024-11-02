package com.example.blog

import com.example.blog.domain.post.domain.Post
import com.example.blog.domain.post.presentation.controller.PostController
import com.example.blog.domain.post.presentation.dto.request.PostRequest
import com.example.blog.domain.post.service.PostService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.delete
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(PostController::class)
class PostControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val postService: PostService = mock(PostService::class.java)

    @Test
    fun `should create a post`() {
        val postRequest = PostRequest("Title", "Summary", "2024-11-02")
        val savedPost = Post(UUID.randomUUID(), null, "Title", "Summary", Date())

        // Mocking service behavior
        `when`(postService.createPost(postRequest)).thenReturn(savedPost)

        mockMvc.post("/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(postRequest)
        }.andExpect {
            status { isCreated() }
            content { json(objectMapper.writeValueAsString(savedPost)) }
        }
    }

    @Test
    fun `should get a post by id`() {
        val postId = UUID.randomUUID()
        val post = Post(postId, null, "Title", "Summary", Date())

        // Mocking service behavior
        `when`(postService.getPostById(postId)).thenReturn(post)

        mockMvc.get("/posts/$postId").andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(post)) }
        }
    }

    @Test
    fun `should update a post`() {
        val postId = UUID.randomUUID()
        val postRequest = PostRequest("Updated Title", "Updated Summary", "2024-11-02")
        val updatedPost = Post(postId, null, "Updated Title", "Updated Summary", Date())

        // Mocking service behavior
        `when`(postService.updatePost(postId, postRequest)).thenReturn(updatedPost)

        mockMvc.patch("/posts/$postId") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(postRequest)
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(updatedPost)) }
        }
    }

    @Test
    fun `should delete a post`() {
        val postId = UUID.randomUUID()

        mockMvc.delete("/posts/$postId").andExpect {
            status { isNoContent() }
        }

        // Verify that the deletePost method was called on the service
        verify(postService).deletePost(postId)
    }
}