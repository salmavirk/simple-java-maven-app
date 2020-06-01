package com.amido.service;

import com.amido.ApplicationConfiguration;
import com.amido.model.User;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;


    public UserRepositoryImpl(@CurrentSession EntityManager entityManager,
                               ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional
    public Optional<User> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User save(@NotBlank String name, @NotBlank String surname, @NotBlank String email) {
        User user = new User(name, surname, email);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(user -> entityManager.remove(user));
    }
}
