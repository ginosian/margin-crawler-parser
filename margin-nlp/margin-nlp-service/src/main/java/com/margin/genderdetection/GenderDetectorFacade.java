package com.margin.genderdetection;

import com.margin.dto.GenericResponseDTO;
import com.margin.dto.nlp.genderdetector.GenderDetectorCreateRequestDTO;
import com.margin.dto.nlp.genderdetector.GenderDetectorCreateResponseDTO;

public interface GenderDetectorFacade {
    GenericResponseDTO<GenderDetectorCreateResponseDTO> create(final GenderDetectorCreateRequestDTO createDTO);
}
