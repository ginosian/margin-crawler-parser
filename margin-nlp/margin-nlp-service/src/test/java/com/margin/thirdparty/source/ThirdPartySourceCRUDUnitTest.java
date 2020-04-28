package com.margin.thirdparty.source;

import com.margin.entity.thirdparty.source.entity.ThirdPartySourceEntity;
import com.margin.error.ApiException;
import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import com.margin.model.source.ThirdPartySourceUpdateRequest;
import com.margin.repository.ThirdPartySourceRepository;
import com.margin.service.source.component.ThirdPartySourceConverter;
import com.margin.service.source.impl.ThirdPartySourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ThirdPartySourceCRUDUnitTest {

    @Mock
    private ThirdPartySourceRepository thirdPartySourceRepository;

    @Mock
    private ThirdPartySourceConverter converter;

    @InjectMocks
    private ThirdPartySourceServiceImpl thirdPartySourceService;


    @Test
    public void getSuccessful(){
        final String id = "id";
        final ThirdPartySourceResponse response = random(ThirdPartySourceResponse.class);
        response.setId(id);
        final ThirdPartySourceEntity entity = random(ThirdPartySourceEntity.class);
        entity.set_id(id);
        when(thirdPartySourceRepository.findById(eq(id))).thenReturn(Optional.of(entity));
        when(converter.convert(eq(entity))).thenReturn(response);
        final ThirdPartySourceResponse result = thirdPartySourceService.get(id);
        assertNotNull(result);
    }

    @Test(expected = ApiException.class)
    public void getFailure(){
        final String id = "";
        final ThirdPartySourceResponse response = random(ThirdPartySourceResponse.class);
        response.setId(id);
        final ThirdPartySourceEntity entity = random(ThirdPartySourceEntity.class);
        entity.set_id(id);
        when(thirdPartySourceRepository.findById(eq(id))).thenReturn(Optional.empty());
        final ThirdPartySourceResponse result = thirdPartySourceService.get(id);
        assertNotNull(result);
    }

    @Test
    public void createSuccessful(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        final ThirdPartySourceResponse response = new ThirdPartySourceResponse();
        final ThirdPartySourceEntity entity = new ThirdPartySourceEntity();
        when(converter.convert(eq(request))).thenReturn(entity);
        when(converter.convert(eq(entity))).thenReturn(response);
        when(thirdPartySourceRepository.save(eq(entity))).thenReturn(entity);
        final ThirdPartySourceResponse result = thirdPartySourceService.create(request);
        assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void createFailedMissingName(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        request.setName(null);
        thirdPartySourceService.create(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFailedMissingURL(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        request.setUrl(null);
        thirdPartySourceService.create(request);
    }

    @Test(expected = ApiException.class)
    public void createFailedMissingServiceTypes(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        request.setServiceTypes(null);
        thirdPartySourceService.create(request);
    }

    @Test
    public void updateSuccessful(){
        final String id = "id";
        final ThirdPartySourceUpdateRequest request = random(ThirdPartySourceUpdateRequest.class);
        request.setId(id);
        final ThirdPartySourceResponse response = new ThirdPartySourceResponse();
        final ThirdPartySourceEntity entity = new ThirdPartySourceEntity();
        when(thirdPartySourceRepository.findById(eq(id))).thenReturn(Optional.of(entity));
        when(converter.convert(eq(entity))).thenReturn(response);
        when(thirdPartySourceRepository.save(eq(entity))).thenReturn(entity);
        final ThirdPartySourceResponse result = thirdPartySourceService.update(request);
        assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateFailedMissingId(){
        final ThirdPartySourceUpdateRequest request = random(ThirdPartySourceUpdateRequest.class);
        request.setId(null);
        thirdPartySourceService.update(request);
    }
}
