package com.kimilm.expert.service;

import com.kimilm.expert.model.post.Post;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.repository.PostRepository;
import com.kimilm.expert.util.PostUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 비밀번호 검증
    public boolean validatePassword(Long id, String password) throws NoSuchAlgorithmException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );
        String encoded = PostUtils.encoding(password);

        return post.getPassword().equals(encoded);
    }


    // 게시글 수정
    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) throws NoSuchAlgorithmException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("잘못된 게시글 아이디를 입력하였습니다.")
        );
        post.update(requestDto);

        return id;
    }
}
