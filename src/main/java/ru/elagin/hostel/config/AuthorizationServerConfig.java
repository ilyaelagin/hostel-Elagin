package ru.elagin.hostel.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    // Два эндпоинта, для проверки токенов (по умолчанию доступ denyAll())
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    // Указываем информацию о клиентах и о том, где хранится информация о них
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String clientId = "hostel-client-id";
        String clientSecret = "$2y$12$Hpm60JNH4yRo2ekNI1krGuITyM6p7Gf45iKyRihX9WKdQoAV4U3xi";

        clients
                .inMemory().withClient(clientId)
                .secret(clientSecret)
                .scopes("read", "write")
                .authorizedGrantTypes("client_credentials", "password")
                .accessTokenValiditySeconds(3600);
    }

    // Настройки эндпоинтов несвязанные с security, authenticationManager - нужен для предоставления пароля
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }
}
