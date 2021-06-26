package ru.elagin.hostel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import static ru.elagin.hostel.ConfigurationProperties.DataSource.*;
import static ru.elagin.hostel.ConfigurationProperties.readConfigurationProperties;

@SpringBootApplication
public class HostelApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HostelApplication.class).properties(readConfigurationProperties(fromValue(args[0]))).run(args);
    }
}
