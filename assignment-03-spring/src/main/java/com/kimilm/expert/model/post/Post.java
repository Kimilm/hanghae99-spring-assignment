package com.kimilm.expert.model.post;

import com.kimilm.expert.model.Timestamped;
import com.kimilm.expert.model.post.dto.PostRequestDto;
import com.kimilm.expert.util.PostUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto requestDto) throws NoSuchAlgorithmException {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.password = PostUtils.encoding(requestDto.getPassword());
    }

    public void update(PostRequestDto requestDto) throws NoSuchAlgorithmException {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.password = PostUtils.encoding(requestDto.getPassword());
    }
}
