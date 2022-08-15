package com.example.miniproject.domain.post;

import com.example.miniproject.domain.board.Board;
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


    @Query(value = "SELECT MAX(post_syntax) FROM Post WHERE board_id =:board", nativeQuery = true)
    Optional<Integer> findPostSyntaxByBoardId(int board) ;

    Post findByNameAndPostSyntax(Board board, Long postId);

    void deleteByNameAndId(String boardName, Long postId);




}
