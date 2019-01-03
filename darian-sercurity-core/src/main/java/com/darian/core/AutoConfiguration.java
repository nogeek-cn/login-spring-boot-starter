package com.darian.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan({"com.darian.core"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
    public AutoConfiguration() {
    }
}