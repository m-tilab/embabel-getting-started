package com.example.embabel;

import com.embabel.agent.config.annotation.EnableAgents;
import com.embabel.agent.config.annotation.LocalModels;
import com.embabel.agent.config.annotation.LoggingThemes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAgents(loggingTheme = LoggingThemes.SEVERANCE)
public class EmbabelRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbabelRestDemoApplication.class, args);
    }

}
