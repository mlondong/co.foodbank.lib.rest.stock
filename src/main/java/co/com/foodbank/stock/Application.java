package co.com.foodbank.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import co.com.foodbank.contribution.sdk.config.EnableContributionSDK;
import co.com.foodbank.product.sdk.config.EnableProductSDK;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableContributionSDK
@EnableProductSDK
@ComponentScan({"co.com.foodbank.stock", "co.com.foodbank.stock.restcontroller",
        "co.com.foodbank.stock.v1.controller", "co.com.foodbank.stock.service",
        "co.com.foodbank.stock.config", "co.com.foodbank.stock.v1.model",
        "co.com.foodbank.stock.repository", "co.com.foodbank.stock.exception"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
