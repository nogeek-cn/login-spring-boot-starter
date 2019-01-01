package com.darian.darianSecurityDemo;

import com.darian.darianSecurityBrowser.EnableDarianSecurityBrowser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDarianSecurityBrowser
@SpringBootApplication
public class DarianSercurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarianSercurityDemoApplication.class);
    }

}
