package com.leticia.helpDesk.config;

import com.leticia.helpDesk.services.DBServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServices dbServices;

    @PostConstruct
    public void instanciaDB() {
        this.dbServices.instanciaDB();

    }
}
