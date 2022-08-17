package com.example.miniproject.domain.post;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.dto.response.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post WHERE board_id=:boardId ORDER BY post_syntax DESC", nativeQuery = true)
    Optional<List<Post>> findAllByBoard(long boardId);

    @Query(value = "SELECT *" +
            " FROM Post " +
            "WHERE TITLE " +
            "LIKE %:title%", nativeQuery = true)
    Optional<List<Post>> findAllPostByTitle(@Param("title") String title);

    @Query(value = "SELECT MAX(post_syntax) FROM Post WHERE board_id =:boardId", nativeQuery = true)
    Optional<Long> findpost_syntaxByBoardId(long boardId);

    @Query(value = "SELECT * FROM Post WHERE board_id=:boardId AND post_syntax=:post_syntax", nativeQuery = true)
    Optional<Post> findByBoardAndpost_syntax(long boardId, long post_syntax);

}
