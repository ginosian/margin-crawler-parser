package com.margin.dto;

import com.margin.error.ApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDTO <T> implements Serializable {
    private ApiException exception;
    private T response;
}
