package com.alya.web.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author alya
 */
@Component
public class ApplicationInfoPrint implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInfoPrint.class);

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String startSuccess = "\n  _______  _      _    _____    _____  _______   _______   _______ \n /  _____|| |    | | .' ____| .' ____||  _____| /  _____| /  _____|\n|  (__    | |    | |/ /      / /      | |____  |  (__    |  (__  \n '.___` '.| |    | || |      | |      |  ____|  '.___ `'. '.___ `'. \n ______) || |____| |\\ `.____ \\ `.____ | |_____  ______) | ______) |\n|_______.'`.______.' `._____| `._____||_______||_______.'|_______.'\n";
        String profileActive = environment.getProperty("spring.profiles.active");
        if ("dev".equals(profileActive)) {
            String port = environment.getProperty("server.port");
            port = port == null ? "8080" : port;

            String contextPath = environment.getProperty("server.servlet.context-path");
            contextPath = contextPath == null ? "" : contextPath;

            String serverIp = "127.0.0.1";
            LOGGER.info("profileActive : {}", profileActive);
            LOGGER.info("contextPath : {}", contextPath);
            LOGGER.info("serverIp : {}", serverIp);
            LOGGER.info("port : {}", port);

            String homeUrl = "http://" + serverIp + ":" + port + contextPath;
            LOGGER.info("Home:    {}", homeUrl);

            String swaggerUrl = "http://" + serverIp + ":" + port + contextPath + "/doc.html";
            LOGGER.info("Swagger: {}", swaggerUrl);
        }
        LOGGER.info("\n{}", startSuccess);
    }

}
