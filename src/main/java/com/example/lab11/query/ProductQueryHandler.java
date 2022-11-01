package com.example.lab11.query;

import com.example.lab11.core.ProductEntity;
import com.example.lab11.core.data.ProductRepository;
import com.example.lab11.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {


    private final ProductRepository productRepository;


    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    List<ProductRestModel> findProduct(FindProductQuery query){
        List<ProductRestModel> models = new ArrayList<>();
        List<ProductEntity> entities = productRepository.findAll();
        for(ProductEntity entity:entities){
            ProductRestModel model = new ProductRestModel();
            BeanUtils.copyProperties(entity,model);
            models.add(model);
        }
        return models;
    }
}
