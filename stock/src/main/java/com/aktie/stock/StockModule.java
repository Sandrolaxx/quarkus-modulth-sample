package com.aktie.stock;

import com.aktie.common.IAppModule;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockModule implements IAppModule {

    @Override
    public String getName() {
        return "stock";
    }
    
    @Override
    public void init() {
        System.out.println("📦Módulo de estoque inicializado com sucesso!");
    }
    
    @Override
    public void shutdown() {
        System.out.println("📦Módulo de estoque encerrado com sucesso!");
    }
    
    @Override
    public int getPriority() {
        return 20;
    }
}
