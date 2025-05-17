package com.aktie.app;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Initializer {

    public static void main(String ... args) {
        System.out.println("" + //
        "   _____                 __           __              __  ___          __      ___ __  __  \n" + //
        "  / ___/____ _____  ____/ /________  / /___ __  __   /  |/  /___  ____/ /_  __/ (_) /_/ /_ \n" + //
        "  \\__ \\/ __ `/ __ \\/ __  / ___/ __ \\/ / __ `/ |/_/  / /|_/ / __ \\/ __  / / / / / / __/ __ \\\n" + //
        " ___/ / /_/ / / / / /_/ / /  / /_/ / / /_/ />  <   / /  / / /_/ / /_/ / /_/ / / / /_/ / / /\n" + //
        "/____/\\__,_/_/ /_/\\__,_/_/   \\____/_/\\__,_/_/|_|  /_/  /_/\\____/\\__,_/\\__,_/_/_/\\__/_/ /_/ \n" + //
        "");
        Quarkus.run(args); 
    }

}
