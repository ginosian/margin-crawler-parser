package com.margin.entity;

import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceInfoEntity extends BaseEntity {

    @OneToOne
    private SourceMetaData sourceMetaData;

    @Column(nullable = false)
    private String sourceName;

    @Column
    private Boolean isLoadable;

    @Column(nullable = false)
    private String sourceUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private LocalDateTime sourceFoundDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CrawllingType sourceType;

    @Column
    private String modelClassName;

    @Column
    private String description;

    @OneToMany(mappedBy = "sourceInfoEntity", orphanRemoval = true)
    private List<ScrapActionDetailsEntity> scrapActionDetailsEntities;

    public SourceInfoEntity(
            SourceMetaData sourceMetaData,
            String sourceName,
            String sourceUrl,
            DocumentType documentType,
            LocalDateTime sourceFoundDate,
            CrawllingType sourceType,
            String modelCLassName,
            String description,
            Boolean isLoadable) {
        this.sourceMetaData = sourceMetaData;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.documentType = documentType;
        this.sourceFoundDate = sourceFoundDate;
        this.sourceType = sourceType;
        this.modelClassName = modelCLassName;
        this.description = description;
        this.isLoadable = isLoadable;
    }
}
