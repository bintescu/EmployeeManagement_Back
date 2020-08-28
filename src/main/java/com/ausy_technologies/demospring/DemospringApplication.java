package com.ausy_technologies.demospring;

import com.ausy_technologies.demospring.Exceptions.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class DemospringApplication {
    public static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {

        ErrorResponse.setupLogger();
        SpringApplication.run(DemospringApplication.class, args);

    }



}
