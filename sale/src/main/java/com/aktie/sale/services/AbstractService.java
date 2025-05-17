package com.aktie.sale.services;

import com.aktie.sale.infra.database.postgres.repositories.PgSaleRepository;
import com.aktie.user.infra.database.postgres.repositories.PgUserRepository;

import jakarta.inject.Inject;

public abstract class AbstractService {

    @Inject
    PgSaleRepository pgSaleRepository;

    @Inject
    PgUserRepository pgUserRepository;

}
