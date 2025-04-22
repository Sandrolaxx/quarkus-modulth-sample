package com.aktie.app.src.main.java.com.aktie;

import org.jboss.logging.Logger;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class AppMain {
    private static final Logger LOG = Logger.getLogger(AppMain.class);

    public static void main(String[] args) {
        LOG.info("Iniciando aplicação do monólito modular");
        Quarkus.run(ModularMonolithApp.class, args);
    }

    public static class ModularMonolithApp implements QuarkusApplication {
        @Override
        public int run(String... args) throws Exception {
            LOG.info("Monólito modular inicializado com sucesso");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
