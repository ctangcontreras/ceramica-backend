package com.prueba.demo.core.inputDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EliminarProductoInicialInputDto {

    private Integer idProductoInicial;
    private Integer usuarioElimina;
}
