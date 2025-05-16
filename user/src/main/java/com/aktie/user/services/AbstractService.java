package com.aktie.user.services;

import jakarta.inject.Inject;

import com.aktie.user.infra.database.postgres.repositories.PgUserRepository;

public abstract class AbstractService {

    @Inject
    PgUserRepository pgUserRepository;

}
