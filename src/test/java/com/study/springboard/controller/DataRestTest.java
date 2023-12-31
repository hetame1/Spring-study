package com.study.springboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("DataRest 테스트는 아직 작성 중입니다.")
@DisplayName("DataRest 테스트")
@Transactional
@AutoConfigureMockMvc // MockMvc를 주입받기 위해 필요
@SpringBootTest
public class DataRestTest {

  private final MockMvc mvc;

  public DataRestTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[api] 게시글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 게시글 단건 조회")
  @Test
  void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles/1/articleComments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 댓글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles/articleComments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 댓글 단건 조회")
  @Test
  void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/articles/articleComments/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
  }

  @DisplayName("[api] 회원 관련 API 는 일체 제공하지 않는다.")
  @Test
  void givenNothing_whenRequestingUserAccounts_thenThrowsException() throws Exception {
    // Given

    // When & Then
    mvc.perform(get("/api/userAccounts")).andExpect(status().isNotFound());
    mvc.perform(post("/api/userAccounts")).andExpect(status().isNotFound());
    mvc.perform(put("/api/userAccounts")).andExpect(status().isNotFound());
    mvc.perform(patch("/api/userAccounts")).andExpect(status().isNotFound());
    mvc.perform(delete("/api/userAccounts")).andExpect(status().isNotFound());
    mvc.perform(head("/api/userAccounts")).andExpect(status().isNotFound());
  }
}
