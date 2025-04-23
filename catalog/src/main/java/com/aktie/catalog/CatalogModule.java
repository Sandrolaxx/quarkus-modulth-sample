package com.aktie.catalog;

import com.aktie.common.ModuloAplicacao;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatalogModule implements ModuloAplicacao {

    @Override
    public String getNome() {
        return "catalog";
    }
    
    @Override
    public void inicializar() {
        // Inicialização específica do módulo catalog
        // productRepository.verifyConnection();
        // catalogService.loadInitialConfiguration();
        
        System.out.println("Módulo de catálogo inicializado com sucesso");
    }
    
    @Override
    public void encerrar() {
        // Lógica de encerramento do módulo
        System.out.println("Módulo de catálogo encerrado com sucesso");
    }
    
    @Override
    public int getPrioridade() {
        // Define a ordem de inicialização em relação a outros módulos
        // Números menores inicializam primeiro
        return 20;
    }
}
