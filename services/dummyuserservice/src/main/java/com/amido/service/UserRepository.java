package com.amido.service;

import com.amido.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(@NotNull Long id);

    User save(@NotBlank String name, @NotBlank String surname, @NotBlank String email);

    void deleteById(@NotNull Long id);

}
