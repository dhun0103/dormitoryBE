package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.MatePost;
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
    private int age;
    private String gender;
//    private String grade;

    public MatePostResDto(MatePost matePost) {
        this.matePostId = matePost.getMatePostId();
        this.title = matePost.getTitle();
        this.contents = matePost.getContents();
        this.age = matePost.getAge();
        this.gender = matePost.getGender();
    }
}
