package com.prueba.demo.core.inputDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarVentaRecojoInputDto {
    
    private Integer idVentaRecojo;
    private Integer idVenta;
    private Date fecha;
    private String observacion;
}
