package com.amido;

import com.amido.model.Healthcheck;
import com.amido.model.User;
import com.amido.service.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import java.net.URI;

@Controller("/healthcheck")
public class HealthCheckController {

    @Get("/")
    public HttpResponse healthcheck() {
        return HttpResponse.ok(new Healthcheck("OK"));
    }
}
