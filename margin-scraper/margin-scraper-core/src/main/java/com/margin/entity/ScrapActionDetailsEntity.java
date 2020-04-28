package com.margin.entity;

import com.margin.enums.DocumentType;
import com.margin.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class ScrapActionDetailsEntity extends BaseEntity {

    @OneToOne
    private SourceMetaData sourceMetadata;

    @Column
    private Integer version;

    @ManyToOne
//    @JoinColumn(name = "scrap_source_entity_id", foreignKey = @ForeignKey(name = "scrap_source_info_scrap_source_entity_id"))
    private SourceInfoEntity sourceInfoEntity;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private String dataPath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @OneToMany(mappedBy = "scrapActionDetailsEntity",  orphanRemoval = true)
    private List<ScrapedUnitInfoEntity> scrapedUnitInfoEntities;

}