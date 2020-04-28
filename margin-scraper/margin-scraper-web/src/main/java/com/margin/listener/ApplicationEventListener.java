package com.margin.listener;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.enums.CrawllingType;
import com.margin.enums.DocumentType;
import com.margin.loading.LoadingService;
import com.margin.scheduler.job.api.impl.SchedulerApiClientImpl;
import com.margin.service.SourceService;
import com.margin.service.model.SourceMetaDataModel;
import com.margin.service.model.source_info.SourceInfoCreationRequest;
import com.margin.service.model.source_info.SourceInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApplicationEventListener {

    @Autowired
    private SourceService sourceService;

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private SchedulerApiClientImpl schedulerApiClient;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {


        final SourceMetaDataModel sourceMetaDataModel = new SourceMetaDataModel(null, Country.UKRAINE, Channel.UKRAINE_REGISTRY);
        final SourceMetaDataModel sourceMetaDataModelDB =
                sourceService
                        .findSourceMetaData(sourceMetaDataModel)
                        .orElse(sourceService.createSourceMetaData(sourceMetaDataModel));
        final List<SourceInfoResponse> responseList = sourceService.search(sourceMetaDataModelDB);
        if(responseList.isEmpty()){
            final SourceInfoResponse sourceInfoResponse = createUARegistryParser(sourceMetaDataModelDB);
            loadingService.load(sourceInfoResponse.getId());
        }

//        final GenericListResponseDTO<SchedulerJobDTO> all = schedulerApiClient.scheduler().getAll();
//        final GenericListResponseDTO<SchedulerJobDTO> runningJobs = schedulerApiClient.scheduler().getRunningJobs();
    }

    private SourceInfoResponse createScrapedSource(final SourceMetaDataModel sourceMetaDataModel){
        final SourceInfoCreationRequest scrapedSourceInfoCreationRequest = new SourceInfoCreationRequest(
                sourceMetaDataModel,
                "Ru Banks License.",
                "http://www.cbr.ru/hd_base/co_schema/",
                DocumentType.JSOUP_DOCUMENT,
                LocalDateTime.now(),
                CrawllingType.SCRAPABLE,
                "com.margin.com.margin.model.ru.bank.license.active.object.RU_BankLicenseSource",
                "Represents Russia banks license status."
        );
        return sourceService.create(scrapedSourceInfoCreationRequest);
    }


    private SourceInfoResponse createUARegistryParser(final SourceMetaDataModel sourceMetaDataModel){
        final SourceInfoCreationRequest scrapedSourceInfoCreationRequest = new SourceInfoCreationRequest(
                sourceMetaDataModel,
                "Ukraine Registry",
//                "https://nais.gov.ua/files/general/2019/01/15/20190115161154-55.zip",
                // test small
                "https://sample-videos.com/zip/10mb.zip",
                DocumentType.XML,
                LocalDateTime.now(),
                CrawllingType.DOWNLOADABLE,
                "com.margin.model.ukraine.registry.active.object.UA_RegistrySource",
                "Ukrainian Registry"
        );
        return sourceService.create(scrapedSourceInfoCreationRequest);
    }




}
