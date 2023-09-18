
package com.multicampus.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloperAapplication {

    public static void main(String[] args){
        SpringApplication.run(SpringBootDeveloperAapplication.class,args);
    }
}
