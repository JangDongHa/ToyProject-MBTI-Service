package com.example.miniproject.domain.comment;

import com.example.miniproject.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM Comment WHERE post_id=:postId AND post_syntax=:post_syntax", nativeQuery = true)
    Optional<Comment> findByBoardAndpost_syntax(long postId, long post_syntax);

}
