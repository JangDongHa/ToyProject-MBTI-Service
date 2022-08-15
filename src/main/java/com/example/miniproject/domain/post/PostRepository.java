package com.example.miniproject.domain.post;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.dto.response.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByBoard(Board board);

    @Query(value = "SELECT *" +
            " FROM Post " +
            "WHERE TITLE " +
            "LIKE %:title%", nativeQuery = true)
    Optional<List<Post>> findAllPostByTitle(@Param("title") String title);

    @Query(value = "SELECT MAX(post_syntax) FROM Post WHERE board_id =:board", nativeQuery = true)
    Optional<Integer> findPostSyntaxByBoardId(int board) ;

    Post findByNameAndPostSyntax(Board board, Long postId);

    void deleteByNameAndId(String boardName, Long postId);
}
