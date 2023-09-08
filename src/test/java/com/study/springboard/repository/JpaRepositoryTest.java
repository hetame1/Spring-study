package com.study.springboard.repository;

import com.study.springboard.config.JpaConfig;
import com.study.springboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

  private final ArticleRepository articleRepository;
  private final ArticleCommentRepository articleCommentRepository;

  public JpaRepositoryTest(
          @Autowired ArticleRepository articleRepository,
          @Autowired ArticleCommentRepository articleCommentRepository
  ) {
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }

  @DisplayName("select 테스트")
  @Test
  void givenTestData_whenSelecting_thenWorksFine() {
    // given

    // when
    List<Article> articles = articleRepository.findAll();

    // then
    assertThat(articles)
            .isNotNull()
            .hasSize(0);
  }

}