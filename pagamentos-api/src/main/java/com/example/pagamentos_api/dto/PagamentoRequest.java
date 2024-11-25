package com.example.pagamentos_api.dto;

import com.example.pagamentos_api.validation.NumeroCartaoValid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@NumeroCartaoValid
public class PagamentoRequest {

    @NotNull(message = "O código do débito é obrigatório")
    private Integer codigoDebito;

    @NotBlank(message = "O CPF ou CNPJ é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "O método de pagamento é obrigatório")
    private String metodoPagamento;

    private String numeroCartao;

    @NotNull(message = "O valor do pagamento é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;
}
