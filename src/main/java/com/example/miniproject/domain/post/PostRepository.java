package com.example.miniproject.domain.post;

import com.example.miniproject.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByBoard(Board board);

    @Query(value = "SELECT *" +
            " FROM post " +
            "WHERE TITLE " +
            "LIKE %:title%", nativeQuery = true)
    Optional<List<Post>> findAllPostByTitle(@Param("title") String title);
}
