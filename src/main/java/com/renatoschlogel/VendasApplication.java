package com.renatoschlogel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @Value("${application.name}")
    public String applicationNameProperties;

    @GetMapping("/oi")
    public String oi(){
        return applicationNameProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
        System.out.println("oi");
        System.out.println(new VendasApplication().applicationNameProperties);
        System.out.println(new VendasApplication().applicationName);

      //  System.out.printf(applicationNameProperties);
    }
}
