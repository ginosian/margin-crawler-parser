package model;

import com.margin.enums.LanguageCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NLPLanguageDetectionResponse {
    private LanguageCode detectedLanguageCode;
}
