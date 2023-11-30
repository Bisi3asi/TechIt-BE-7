package com.mysite.restsbb.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.restsbb.global.entity.BaseEntity;
import com.mysite.restsbb.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member author;
    private String title;
    private String body;
}
