package com.amido;

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

@Controller("/users")
public class UserController {

    protected final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get("/{id}")
    public User show(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }


    @Post("/")
    public HttpResponse<User> save(@Body @Valid User command) {
        User user = userRepository.save(command.getName(), command.getSurname(), command.getEmail());

        return HttpResponse
                .created(user)
                .headers(headers -> headers.location(location(user.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        userRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/users/" + id);
    }

}
