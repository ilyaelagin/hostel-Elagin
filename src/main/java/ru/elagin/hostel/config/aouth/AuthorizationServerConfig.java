package ru.elagin.hostel.config.aouth;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
@Import(OAuth2AuthorizationServerConfiguration.class) // так отмечается конфигурация сервера авторизации OAuth
public class AuthorizationServerConfig {

    // Репозиторий клиентских сервисов. Тут регистрируются клиенты, которые будут запрашивать доступ к ресурсам (такие как postman или приложение-клиент)
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("hostel-client-id") // Идентификатор клиента. Spring будет использовать его для определения того, какой клиент пытается получить доступ к ресурсу
                .clientSecret("hostel-client-secret") // Секрет, известный клиенту и серверу, который обеспечивает доверие между ними
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC) // Базовую аутентификация (логин и пароль)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // Клиент может генерировать код авторизации
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) // Клиент может генерировать токен обновления
                .redirectUri("https://www.getpostman.com/oauth2/callback") // URL на который будет возвращаться ответ от сервера ресурсов
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    // Уникальный URL-адрес сервера авторизации
    @Bean
    public ProviderSettings providerSettings() {
        return new ProviderSettings().issuer("http://localhost:8080");
    }

    // Генерация ключа для подписи токенов
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}