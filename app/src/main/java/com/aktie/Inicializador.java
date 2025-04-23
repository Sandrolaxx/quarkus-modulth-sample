package com.aktie;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.aktie.common.ModuloAplicacao;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class Inicializador {

    @Inject
    Instance<ModuloAplicacao> modulos;
    
    void onStart(@Observes StartupEvent ev) {
        // Ordenar por prioridade
        List<ModuloAplicacao> modulosOrdenados = modulos.stream()
            .sorted(Comparator.comparingInt(ModuloAplicacao::getPrioridade))
            .collect(Collectors.toList());
            
        // Inicializar cada módulo
        for (ModuloAplicacao modulo : modulosOrdenados) {
            modulo.inicializar();
        }

        System.out.println(modulosOrdenados);
    }
    
    void onShutdown(@Observes ShutdownEvent ev) {
        // Encerrar na ordem inversa
        List<ModuloAplicacao> modulosReversos = modulos.stream()
            .sorted(Comparator.comparingInt(ModuloAplicacao::getPrioridade).reversed())
            .collect(Collectors.toList());
            
        for (ModuloAplicacao modulo : modulosReversos) {
            modulo.encerrar();
        }
    }
}
