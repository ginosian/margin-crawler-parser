package com.margin.nodeType.service;


import org.springframework.stereotype.Service;

@Service
public interface NodeTypeDetectorService {
    // todo change to numeric return type and generic dto
    Boolean isCompany(String name);
}
