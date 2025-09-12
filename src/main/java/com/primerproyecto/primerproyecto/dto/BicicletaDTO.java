package com.primerproyecto.primerproyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BicicletaDTO {
    private Long id;
    private String modelo;
    private String color;
    private String marca;
    private Double precio;
    private Integer stock;
}
