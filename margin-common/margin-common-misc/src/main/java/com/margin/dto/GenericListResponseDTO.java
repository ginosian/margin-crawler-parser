package com.margin.dto;

import com.margin.error.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericListResponseDTO<T> {
    private ApiException exception;
    private long totalItems;
    private List<T> items = new ArrayList<>();
}
