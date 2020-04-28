package com.margin.loading;

import com.margin.model.AbstractSource;
import com.margin.model.ru.bank.license.active.object.RU_BankLicenseSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

/*
 *   @author ironman
 *   @since  12/4/18
 */

@RunWith(MockitoJUnitRunner.class)
public class ModelResolverTest {

    @InjectMocks
    private ModelResolver modelResolver;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void resolve() {
        final AbstractSource source = modelResolver.resolve("com.margin.model.ru.bank.license.active.object.RU_BankLicenseSource");
        assertTrue(source instanceof RU_BankLicenseSource);
    }
}