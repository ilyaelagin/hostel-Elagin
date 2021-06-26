package ru.elagin.hostel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

import static ru.elagin.hostel.ConfigurationProperties.DataSource.CONSOLE;
import static ru.elagin.hostel.ConfigurationProperties.DataSource.PIPE;

public class ConfigurationProperties {
    public static Properties PROPERTIES;

    public static Properties readConfigurationProperties(DataSource dataSource) {
        if (PIPE == dataSource) {
            System.out.println("Read configuration from pipe");
            PROPERTIES = init(getPipeInput());
        } else if (CONSOLE == dataSource) {
            System.out.println("Read configuration from console");
            PROPERTIES = init(getConsoleInput());
        } else {
            System.out.println("Read configuration from file");
            PROPERTIES = new ConfigurationProperties().init();
        }
        return PROPERTIES;
    }

    private static String getPipeInput() {
        String configuration = "";
        try {
            try (InputStream inputStream = System.in) {
                if (inputStream.available() > 0) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            configuration += line + "\n";
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return configuration;
    }

    private static String getConsoleInput() {
        String configuration = "";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter configuration data (enter END to finish):\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ("END".equalsIgnoreCase(line)) {
                    break;
                }
                configuration += line + "\n";
            }
        }
        return configuration;
    }

    private Properties init() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            System.out.println(e);
        }
        return properties;
    }

    private static Properties init(String configuration) {
        Properties properties = new Properties();
        try (InputStream is = new ByteArrayInputStream(configuration.getBytes(StandardCharsets.UTF_8))) {
            properties.load(is);
        } catch (IOException e) {
            System.out.println(e);
        }
        return properties;
    }

    public enum DataSource {
        /**
         * Чтение из pipe
         */
        PIPE("pipe"),
        /**
         * Чтение из консоли
         */
        CONSOLE("console"),
        /**
         * Чтение из файла src/main/resources/application.properties
         */
        FILE("file");

        private final String value;

        DataSource(final String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static DataSource fromValue(String value) {
            for (DataSource source: DataSource.values()) {
                if (source.value.equals(value)) {
                    return source;
                }
            }
            throw new IllegalArgumentException(value);
        }
    }
}
