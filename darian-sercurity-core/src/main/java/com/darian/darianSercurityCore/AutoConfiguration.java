package com.darian.darianSercurityCore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan({"com.darian.darianSercurityCore"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
    public AutoConfiguration() {
    }
}