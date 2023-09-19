package com.study.springboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @Column(nullable = false) private String title; // 제목
  @Setter @Column(nullable = false, length = 10000) private String content; // 내용

  @Setter private String hashtag; // 해시태그

  // 연관 관계 매핑
  @ToString.Exclude // Article 객체에서 ArticleComment 객체를 출력할 때 ArticleComment 객체에서 다시 Article 객체를 출력하는 것을 방지
  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); // 게시글 댓글 (1:N)

  // 기본 생성자
  protected Article() {}

  private Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  // 바로 생성할 수 있도록 of() 메서드를 제공
  // 이유 : 생성자를 직접 호출하는 것보다 가독성이 좋기 때문
  public static Article of(String title, String content, String hashtag) {
    return new Article(title, content, hashtag);
  }

  // equals()와 hashCode() 구현
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Article article)) return false;
    return id != null && id.equals(article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
