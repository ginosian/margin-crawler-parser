package com.margin.dto.nlp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NLPResponseDTO {
    private NLPDataResponseDTO localResponse;
    private NLPDataResponseDTO thirdPartyResponse;
}
