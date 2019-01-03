package com.darian.borwser;

import com.darian.core.EnableDarianSecurityCore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDarianSecurityCore
@ComponentScan({"com.darian.borwser"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
    public AutoConfiguration() {
    }
}