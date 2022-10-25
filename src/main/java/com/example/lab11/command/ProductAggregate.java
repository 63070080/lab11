package com.example.lab11.command;


import com.example.lab11.core.event.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    //State
    @AggregateIdentifier
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    //Command Handler
    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        //Business Logic
        if(command.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("price cannot be less than or equal zero");
        }
        if(command.getTitle().isBlank()|| command.getTitle() == null){
            throw new IllegalArgumentException("title cannot be blank");
        }

        //Event
        ProductCreatedEvent event = new ProductCreatedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void handler(ProductCreatedEvent event){
        this.productId = event.getProductId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        System.out.println("event work "+this.productId);
    }
}
