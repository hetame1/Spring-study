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
            .hasSize(123);
  }

  @DisplayName("insert 테스트")
  @Test
  void givenTestData_whenInserting_thenWorksFine() {
    // given
    long beforeCount = articleRepository.count();
    Article article = Article.of("new article", "new content", "#spring");

    // when
    Article savedArticle = articleRepository.save(article);

    // then
    assertThat(articleRepository.count()).isEqualTo(beforeCount + 1);
  }

  @DisplayName("update 테스트")
  @Test
  void givenTestData_whenUpdating_thenWorksFine() {
    // given
     Article article = articleRepository.findById(1L).orElseThrow();
     String updatedHashtag = "#springboot";
     article.setHashtag(updatedHashtag);

    // when
    Article savedArticle = articleRepository.saveAndFlush(article);

    // then
    assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
  }

  @DisplayName("delete 테스트")
  @Test
  void givenTestData_whenDeleting_thenWorksFine() {
    // given
    Article article = articleRepository.findById(1L).orElseThrow();
    long beforeCount = articleRepository.count();
    long beforeCommentsCount = articleCommentRepository.count();
    int deletedCommentsSize = article.getArticleComments().size();

    // when
    articleRepository.delete(article);

    // then
    assertThat(articleRepository.count()).isEqualTo(beforeCount - 1);
    assertThat(articleCommentRepository.count()).isEqualTo(beforeCommentsCount - deletedCommentsSize);
  }


}