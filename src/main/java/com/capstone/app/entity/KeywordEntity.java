package com.capstone.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_KEYWORDS")
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_KEYWORDS_SEQ")
    @SequenceGenerator(name = "G_KEYWORDS_SEQ", sequenceName = "G_KEYWORDS_SEQ", allocationSize = 1)
    @Column(name = "KEYWORD_ID")
    private int keywordId;

    @Column(name = "KEYWORD")
    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    @JsonIgnore
    private List<RecipeEntity> recipes = new ArrayList<>();
}
