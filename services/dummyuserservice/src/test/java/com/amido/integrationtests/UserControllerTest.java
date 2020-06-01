package com.amido.integrationtests;

import com.amido.model.User;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@Tag("integration")
public class UserControllerTest {


    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void integrationUserTest() {

        List<Long> genreIds = new ArrayList<>();

        HttpRequest request = HttpRequest.POST("/users", new User("Pinco", "Pallino", "pincoPallino@amido.com"));
        HttpResponse response = client.toBlocking().exchange(request);
        genreIds.add(entityId(response));

        assertEquals(HttpStatus.CREATED, response.getStatus());

        request = HttpRequest.POST("/users", new User("Gianni", "Ingegnere", "gianniIngegnere@amido.com"));
        response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.CREATED, response.getStatus());

        Long id = entityId(response);
        genreIds.add(id);
        request = HttpRequest.GET("/users/" + id);

        User user = client.toBlocking().retrieve(request, User.class);

        assertEquals("Gianni", user.getName());


    }

    protected Long entityId(HttpResponse response) {
        String path = "/users/";
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }

}
