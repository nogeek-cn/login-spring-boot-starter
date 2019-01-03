package com.darian.demo;

import com.darian.borwser.EnableDarianSecurityBrowser;
import com.darian.core.EnableDarianSecurityCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDarianSecurityCore
@EnableDarianSecurityBrowser
@SpringBootApplication
public class DarianSercurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarianSercurityDemoApplication.class);
    }

}
