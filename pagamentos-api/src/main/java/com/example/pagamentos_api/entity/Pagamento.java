
package com.example.pagamentos_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigoDebito;
    private String cpfCnpj;
    private String metodoPagamento;
    private String numeroCartao;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusPagamento status = StatusPagamento.PENDENTE;
    private boolean ativo = true;

    public enum StatusPagamento {
        PROCESSADO_SUCESSO,
        PROCESSADO_FALHA,
        PENDENTE,
    }
}
