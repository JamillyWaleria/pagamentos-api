package com.example.pagamentos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.pagamentos_api.entity.Pagamento;

@Repository

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, JpaSpecificationExecutor<Pagamento> {
}
