package com.margin.entity;

import com.margin.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ScrapedUnitInfoEntity extends BaseEntity {

    @OneToOne
    private SourceMetaData sourceMetaData;

    @ManyToOne
//    @JoinColumn(name = "scraped_source_info_id", foreignKey = @ForeignKey(name = "scraped_page_scrap_source_info_id"))
    private ScrapActionDetailsEntity scrapActionDetailsEntity;

    @Column
    private String mongoId;

    @Column(nullable = false)
    private String url;

    @Column
    private String fileName;

    @Column(nullable = false)
    private LocalDateTime scrapDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private IndexInfo index;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = FailedScrapedUnitInfoEntity.class)
////    @JoinColumn(name = "scraped_page_id", foreignKey = @ForeignKey(name = "failed_page_scraped_page_id"))
    private FailedScrapedUnitInfoEntity failedScrapedUnitInfoEntity;

}