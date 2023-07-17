package com.kbak.ecommerce.config;

import com.kbak.ecommerce.entity.Product;
import com.kbak.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //Disable PUT, POST, DELETE methods for Product
        HttpMethod[] methodsToDisableProduct= {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(methodsToDisableProduct))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methodsToDisableProduct));

        //Disable PUT, POST, DELETE methods for ProductCategory
        HttpMethod[] methodsToDisableProductCategory= {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(methodsToDisableProductCategory))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(methodsToDisableProductCategory));
    }


}
