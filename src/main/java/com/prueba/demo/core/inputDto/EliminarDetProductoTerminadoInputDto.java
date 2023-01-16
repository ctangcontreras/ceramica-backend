package com.prueba.demo.core.inputDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EliminarDetProductoTerminadoInputDto {

    private Integer idDetalleProductoTerminado;
}
