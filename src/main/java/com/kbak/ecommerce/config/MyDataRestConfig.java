package com.kbak.ecommerce.config;

import com.kbak.ecommerce.entity.Product;
import com.kbak.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

        // changes to return id of category as well
        exposeId(config);
    }

    // function which help to return Ids together with query results
    private void exposeId(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();

        for (EntityType et : entities) {
            entityClasses.add(et.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }


}
