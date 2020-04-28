package com.margin.dto.tmp.support;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatabaseDTO {

    private String ipAddress;
    private Integer port;
    private String userName;
    private CharSequence password; // TODO: 1/8/19 handle this properly
    private String name;
    private List<String> collections;
}
