package com.aktie;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.aktie.common.IAppModule;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class Initializer {

    @Inject
    Instance<IAppModule> modules;

    void onStart(@Observes StartupEvent ev) {
        // Init por prioridade
        List<IAppModule> sortedModules = modules.stream()
                .sorted(Comparator.comparingInt(IAppModule::getPriority))
                .collect(Collectors.toList());

        for (IAppModule module : sortedModules) {
            module.init();
        }

        var modules = sortedModules.stream().map(IAppModule::getName).toList();
        System.out.println("Loaded modules: ".concat(modules.toString()));
    }

    void onShutdown(@Observes ShutdownEvent ev) {
        List<IAppModule> revertedModules = modules.stream()
                .sorted(Comparator.comparingInt(IAppModule::getPriority).reversed())
                .collect(Collectors.toList());

        for (IAppModule module : revertedModules) {
            module.shutdown();
        }
    }
}
