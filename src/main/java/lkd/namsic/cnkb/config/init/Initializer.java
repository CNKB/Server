package lkd.namsic.cnkb.config.init;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Initializer {
    
    protected abstract String getName();
    protected abstract void init();
    
}