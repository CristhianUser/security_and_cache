package com.example.api.agregates.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class VentaRequest {
    private Long metodoSeleccionado;
    private Set<ItemsRequest> items;
}
