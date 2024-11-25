package com.example.pagamentos_api.repository;

import org.springframework.data.jpa.domain.Specification;
import com.example.pagamentos_api.entity.Pagamento;
import com.example.pagamentos_api.entity.Pagamento.StatusPagamento;

public class PagamentoFiltro {

    public static Specification<Pagamento> comCodigoDebito(Integer codigoDebito) {
        return (root, query, criteriaBuilder) -> codigoDebito == null ? null
                : criteriaBuilder.equal(root.get("codigoDebito"), codigoDebito);
    }

    public static Specification<Pagamento> comCpfCnpj(String cpfCnpj) {
        return (root, query, criteriaBuilder) -> cpfCnpj == null ? null
                : criteriaBuilder.equal(root.get("cpfCnpj"), cpfCnpj);
    }

    public static Specification<Pagamento> comStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null)
                return null;
            try {
                StatusPagamento statusPagamento = StatusPagamento.valueOf(status.toUpperCase());
                return criteriaBuilder.equal(root.get("status"), statusPagamento);
            } catch (IllegalArgumentException e) {
                return null;
            }
        };
    }
}
