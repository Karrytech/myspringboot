package com.ccm.autoconfig;

import com.ccm.base.properties.HttpsProperties;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ HttpsProperties.class })
public class HttpsConfigurer {

    @Autowired
    private HttpsProperties httpsProperties;

    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        if (httpsProperties.isEnabled()) {
            tomcat.addAdditionalTomcatConnectors(createSslConnector());
        }
        return tomcat;
    }*/

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(httpsProperties.getPort());

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            protocol.setSSLEnabled(true);
            protocol.setKeyAlias(httpsProperties.getKeyAlias());
            // protocol.setKeyPass(httpsProperties.getKeyPassword());

            protocol.setKeystoreFile(ResourceUtils.getURL(httpsProperties.getKeyStore()).toString());
            protocol.setKeystorePass(httpsProperties.getKeyStorePassword());
            protocol.setKeystoreType(httpsProperties.getKeyStoreType());

            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("Can't access keystore: [" + httpsProperties.getKeyStore() + "]", ex);
        }
    }
}