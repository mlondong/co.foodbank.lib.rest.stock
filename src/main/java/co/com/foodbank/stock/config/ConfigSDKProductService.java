package co.com.foodbank.stock.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.com.foodbank.product.sdk.service.SDKProductService;

@Configuration
@Qualifier("sdkProduct")
public class ConfigSDKProductService {
    @Bean
    public SDKProductService sdkProduct() {
        return new SDKProductService();
    }
}
