package com.aktie.user;

import com.aktie.common.IAppModule;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserModule implements IAppModule {

    @Override
    public String getName() {
        return "user";
    }
    
    @Override
    public void init() {
        System.out.println("👤Módulo de usuário inicializado com sucesso!");
    }
    
    @Override
    public void shutdown() {
        System.out.println("👤Módulo de usuário encerrado com sucesso!");
    }
    
    @Override
    public int getPriority() {
        return 5;
    }
}
