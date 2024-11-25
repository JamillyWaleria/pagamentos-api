package com.example.pagamentos_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class StatusPagamentoRequest {
    @NonNull
    private Long id;
    @NotBlank
    private String novoStatus;
}
