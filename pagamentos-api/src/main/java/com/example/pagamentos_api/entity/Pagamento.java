// package com.example.pagamentos_api.entity;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.math.BigDecimal;

// @Data
// @Entity
// public class Pagamento {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Integer codigoDebito;
//     private String cpfCnpj;
//     private String metodoPagamento;
//     private String numeroCartao;
//     private BigDecimal valor;

//     @Enumerated(EnumType.STRING)
//     private StatusPagamento status = StatusPagamento.PENDENTE_PROCESSO;

//     private boolean ativo = true;

//     public enum StatusPagamento {
//         PENDENTE_PROCESSO,
//         PROCESSADO_SUCESSO,
//         PROCESSADO_FALHA
//     }
// }
package com.example.pagamentos_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

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
    private StatusPagamento status = StatusPagamento.PENDENTE_PROCESSO;
    private boolean ativo = true;

    public enum StatusPagamento {
        PENDENTE_PROCESSO,
        PROCESSADO_SUCESSO,
        PROCESSADO_FALHA
    }
}
