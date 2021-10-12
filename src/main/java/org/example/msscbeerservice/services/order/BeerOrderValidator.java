package org.example.msscbeerservice.services.order;

import org.example.brewery.model.BeerOrderDto;
import org.example.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Slf4j
@Component
public class BeerOrderValidator {
    private final BeerRepository repository;

    public Boolean validateOrder(BeerOrderDto dto){
        AtomicInteger beersNotFound = new AtomicInteger();
        dto.getBeerOrderLines().forEach(orderline ->{
            if(repository.findByUpc(orderline.getUpc()) == null){
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }
}
