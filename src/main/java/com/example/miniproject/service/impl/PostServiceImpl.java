package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.comment.CommentRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.user.UserRepository;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.response.CommentDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsByBoardName(String boardName) {
        Board boardPS = boardRepository.findByName(boardName).orElseThrow(IllegalArgumentException::new);
        List<Post> postsPS = postRepository.findAllByBoard(boardPS.getId()).orElseThrow(IllegalArgumentException::new);


        List<PostDto> postDtos = new ArrayList<>();
        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostByTitle(String title) {
        List<Post> postsPS = postRepository.findAllPostByTitle(title).orElseThrow(IllegalArgumentException::new);
        List<PostDto> postDtos = new ArrayList<>();

        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }


    @Override
    @Transactional
    public CommentDto registerComment(String boardName, String username, RequestCommentDto requestCommentDto, long reqPostSyntax) {
        if (!requestCommentDto.isEmpty()){
            Board board = boardRepository.findByName(boardName).orElseThrow();
            Post post = postRepository.findByBoardAndpost_syntax(board.getId(), reqPostSyntax).orElseThrow();
            User user = userRepository.findByUsername(username).orElseThrow();
            Comment comment = Comment.builder().board(board).user(user).post(post).content(requestCommentDto.getContent()).build();
            return new CommentDto(commentRepository.save(comment));
        }
        else
            throw new IllegalArgumentException();
    }


    @Override
    @Transactional
    public Boolean updateComment(Long commentId, String username, RequestCommentDto requestCommentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if(comment.getUser().getUsername().equals(username)){
            comment.update(requestCommentDto);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean deleteComment(String username, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if(comment.getUser().getUsername().equals(username)){
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public PostResponseDto createPost(String boardName, String username, PostRequestDto requestDto) {
        Board board = boardRepository.findByName(boardName).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();
        long postSyntax = postRepository.findpost_syntaxByBoardId(board.getId()).orElse(0L) + 1 ;

        Post post = Post.builder()
                .board(board)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .user(user)
                .post_syntax(postSyntax)
                .build();

        return new PostResponseDto(postRepository.save(post));
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPost(String boardName, Long postSyntax) {
        Board board = boardRepository.findByName(boardName).orElseThrow(IllegalCallerException::new);
        Post post = postRepository.findByBoardAndpost_syntax(board.getId(), postSyntax).orElseThrow();
        PostResponseDto postResponseDto = new PostResponseDto(post);

        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setNickname(post.getUser().getNickname());
        postResponseDto.setContent(post.getContent());

        return postResponseDto;
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(String boardName,
                                      Long postSyntax,
                                      PostUpdateRequestDto postUpdateRequestDto,
                                      String username) {
        Board board = boardRepository.findByName(boardName).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();
        Post post = postRepository.findByBoardAndpost_syntax(board.getId(), postSyntax).orElseThrow();

        if(post.getUser().getUsername().equals(username)){
            post.update(postUpdateRequestDto);
        }

        return new PostResponseDto(postRepository.save(post));
    }


    @Override
    @Transactional
    public void deletePost(String boardName,
                           Long postSyntax,
                           String username) {
        Board board = boardRepository.findByName(boardName).orElseThrow();
        Post post = postRepository.findByBoardAndpost_syntax(board.getId(), postSyntax).orElseThrow();

        if(post.getUser().getUsername().equals(username)){
            postRepository.delete(post);
        }
    }
}
