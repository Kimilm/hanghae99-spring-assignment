package com.kimilm.expert.controller;

import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostListResponseDto;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.model.post.dto.PostResponseDto;
import com.kimilm.expert.repository.PostRepository;
import com.kimilm.expert.service.PostService;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository postRepository;
    private final PostService postService;

    private final String NAME_SPACE = "/expert";

    // 게시글 전체 목록 조회 api
    @GetMapping(NAME_SPACE + "/api/posts")
    public ResponseEntity<Map<String, Object>> getPostList() {
        List<PostListResponseDto> postListResponseDtoList = postRepository
                .findAllByOrderByCreatedAtDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, postListResponseDtoList);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 조회 api
    @GetMapping(NAME_SPACE + "/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );
        PostResponseDto responseDto = new PostResponseDto(post);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.DATA, responseDto);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 작성 api
    @PostMapping(NAME_SPACE + "/api/posts")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostRequestDto requestDto) throws NoSuchAlgorithmException {
        Post post = new Post(requestDto);
        Post saved = postRepository.save(post);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "작성 완료");
        result.put("id", saved.getId());

        return ResponseEntity.ok().body(result);
    }

    // 비밀번호 검증 api
    @PostMapping(NAME_SPACE + "/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> validatePassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws NoSuchAlgorithmException {
        boolean validation = postService.validatePassword(id, requestDto.getPassword());

        if (!validation) {
            throw new IllegalArgumentException("잘못된 비밀번호를 입력하였습니다.");
        }

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "비밀번호가 일치합니다.");

        return ResponseEntity.ok().body(result);
    }

    // 게시글 수정 api
    @PutMapping(NAME_SPACE + "/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws NoSuchAlgorithmException {
        Long updatedId = postService.updatePost(id, requestDto);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "수정 성공");
        result.put("id", updatedId);

        return ResponseEntity.ok().body(result);
    }

    // 게시글 삭제 api
    @DeleteMapping(NAME_SPACE + "/api/posts/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable Long id) {
        postRepository.deleteById(id);

        Map<String, Object> result = new HashMap<>();
        result.put(PostUtils.MESSAGE, "삭제 성공");

        return ResponseEntity.ok().body(result);
    }
}
