package com.amido.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
@Tag("integration")
public class HealthCheckTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void testHealthCheck() {
        HttpRequest<String> request = HttpRequest.GET("/healthcheck");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("{\"message\":\"OK\"}", body);
    }
}

