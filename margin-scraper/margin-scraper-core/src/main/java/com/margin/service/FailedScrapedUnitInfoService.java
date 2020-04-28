package com.margin.service;

import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoRequest;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FailedScrapedUnitInfoService {
    FailedScrapedUnitInfoResponse create(FailedScrapedUnitInfoRequest request);
    FailedScrapedUnitInfoResponse get(Long id);
    List<FailedScrapedUnitInfoResponse> getAll();
}
