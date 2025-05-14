package com.aktie.sale;

import com.aktie.common.IAppModule;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaleModule implements IAppModule {

    @Override
    public String getName() {
        return "sale";
    }
    
    @Override
    public void init() {
        System.out.println("🛒Módulo de vendas inicializado com sucesso!");
    }
    
    @Override
    public void shutdown() {
        System.out.println("🛒Módulo de vendas encerrado com sucesso!");
    }
    
    @Override
    public int getPriority() {
        return 15;
    }
}
