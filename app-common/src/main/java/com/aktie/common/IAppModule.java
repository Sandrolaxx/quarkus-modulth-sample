package com.aktie.common;

public interface IAppModule {
    
    String getName();
    
    void init();
    
    void shutdown();

    int getPriority();

}
