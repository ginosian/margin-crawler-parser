package com.margin.thirdparty.source;

import com.margin.error.ApiException;
import com.margin.model.source.ThirdPartySourceCreationRequest;
import com.margin.model.source.ThirdPartySourceResponse;
import com.margin.model.source.ThirdPartySourceUpdateRequest;
import com.margin.service.source.ThirdPartySourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
@ActiveProfiles("test")
public class ThirdPartySourceCRUDIntegrationTest {

    @Autowired
    private ThirdPartySourceService thirdPartySourceService;

    @Test
    public void getSuccessful(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        final ThirdPartySourceResponse response = thirdPartySourceService.create(request);
        final ThirdPartySourceResponse result = thirdPartySourceService.get(response.getId());
        assertNotNull(result);
    }

    @Test(expected = ApiException.class)
    public void getFailure(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        final ThirdPartySourceResponse response = thirdPartySourceService.create(request);
        final ThirdPartySourceResponse result = thirdPartySourceService.get(response.getId() + "i");
        assertNotNull(result);
    }

    @Test
    public void testCreationSuccessful(){
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        final ThirdPartySourceResponse response = thirdPartySourceService.create(request);
        assertNotNull(response);
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
        final ThirdPartySourceCreationRequest request = random(ThirdPartySourceCreationRequest.class);
        final ThirdPartySourceResponse response = thirdPartySourceService.create(request);
        final ThirdPartySourceUpdateRequest updateRequest = random(ThirdPartySourceUpdateRequest.class);
        updateRequest.setId(response.getId());
        final ThirdPartySourceResponse result = thirdPartySourceService.update(updateRequest);
        assertEquals(updateRequest.getName(), result.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateFailedMissingId(){
        final ThirdPartySourceUpdateRequest request = random(ThirdPartySourceUpdateRequest.class);
        request.setId(null);
        thirdPartySourceService.update(request);
    }

}