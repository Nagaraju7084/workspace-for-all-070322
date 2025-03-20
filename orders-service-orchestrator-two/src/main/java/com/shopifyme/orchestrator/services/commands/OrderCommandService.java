package com.shopifyme.orchestrator.services.commands;

import java.util.concurrent.CompletableFuture;

import com.shopifyme.orchestrator.dto.commands.OrderCreateDTO;

public interface OrderCommandService {

    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);

}
