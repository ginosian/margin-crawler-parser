package com.margin.service;

import com.margin.service.model.scrap_action_details.ScrapActionDetailsCreationRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsResponse;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsSearchRequest;
import com.margin.service.model.scrap_action_details.ScrapActionDetailsUpdateRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoCreationRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoSearchRequest;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoUpdateRequest;

import java.util.List;

public interface ScrapingInfoService {
    ScrapActionDetailsResponse create(ScrapActionDetailsCreationRequest request);
    ScrapActionDetailsResponse update(ScrapActionDetailsUpdateRequest request);
    ScrapActionDetailsResponse get(Long id);
    List<ScrapActionDetailsResponse> search(ScrapActionDetailsSearchRequest request);

    ScrapedUnitInfoResponse create(ScrapedUnitInfoCreationRequest request);
    ScrapedUnitInfoResponse update(ScrapedUnitInfoUpdateRequest request);
    ScrapedUnitInfoResponse getPage(Long id);
    List<ScrapedUnitInfoResponse> search(ScrapedUnitInfoSearchRequest request);
}
