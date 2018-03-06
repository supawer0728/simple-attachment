package com.parfait.study.simpleattachment.board.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "board")
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    public Board(@NonNull String title, @NonNull String content) {
        this.title = title;
        this.content = content;
    }
}
