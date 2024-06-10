package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.MatePost;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatePostResDto {

    private Long matePostId;
    private String title;
    private String contents;
    private String gender;
    private int age;


    public MatePostResDto(MatePost matePost) {
        this.matePostId = matePost.getMatePostId();
        this.title = matePost.getTitle();
        this.contents = matePost.getContents();
        this.gender = matePost.getGender();
        this.age = matePost.getAge();
    }
}
