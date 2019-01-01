package com.darian.darianSecurityBrowser;

import com.darian.darianSercurityCore.EnableDarianSecurityCore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDarianSecurityCore
@ComponentScan({"com.darian.darianSecurityBrowser"})
@EnableAspectJAutoProxy
public class AutoConfiguration {
    public AutoConfiguration() {
    }
}