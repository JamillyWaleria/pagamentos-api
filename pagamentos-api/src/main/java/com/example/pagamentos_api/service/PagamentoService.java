package com.example.pagamentos_api.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.pagamentos_api.dto.PagamentoRequest;
import com.example.pagamentos_api.dto.StatusPagamentoRequest;
import com.example.pagamentos_api.entity.Pagamento;
import com.example.pagamentos_api.entity.Pagamento.StatusPagamento;
import com.example.pagamentos_api.repository.PagamentoFiltro;
import com.example.pagamentos_api.repository.PagamentoRepository;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento criarPagamento(PagamentoRequest request) {
        Pagamento pagamento = new Pagamento();
        pagamento.setCodigoDebito(request.getCodigoDebito());
        pagamento.setCpfCnpj(request.getCpfCnpj());
        pagamento.setMetodoPagamento(request.getMetodoPagamento());

        if (!request.getMetodoPagamento().equalsIgnoreCase("cartao_credito") &&
                !request.getMetodoPagamento().equalsIgnoreCase("cartao_debito")) {
            pagamento.setNumeroCartao(null);
        } else {
            pagamento.setNumeroCartao(request.getNumeroCartao());
        }

        pagamento.setValor(request.getValor());
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarStatus(Long id, StatusPagamentoRequest request) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        StatusPagamento novoStatus = StatusPagamento.valueOf(request.getNovoStatus());

        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_SUCESSO) {
            throw new IllegalStateException("Pagamento já processado com sucesso, não pode ser alterado.");
        }

        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_FALHA) {

            if (novoStatus != StatusPagamento.PENDENTE) {
                throw new IllegalStateException("Pagamento com falha só pode voltar para 'Pendente de Processamento'.");
            }
        }

        if (pagamento.getStatus() == StatusPagamento.PENDENTE) {

            if (novoStatus != StatusPagamento.PROCESSADO_SUCESSO && novoStatus != StatusPagamento.PROCESSADO_FALHA) {
                throw new IllegalStateException(
                        "Pagamento pendente só pode ser alterado para 'Processado com Sucesso' ou 'Processado com Falha'.");
            }
        }

        pagamento.setStatus(novoStatus);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos(Integer codigoDebito, String cpfCnpj, String status) {
        Specification<Pagamento> filtro = Specification
                .where(PagamentoFiltro.comCodigoDebito(codigoDebito))
                .and(PagamentoFiltro.comCpfCnpj(cpfCnpj))
                .and(PagamentoFiltro.comStatus(status));
        if (codigoDebito == null && cpfCnpj == null && status == null) {
            return pagamentoRepository.findAll();
        }

        return pagamentoRepository.findAll(filtro);
    }

    public void excluirPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        if (pagamento.getStatus() != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Apenas pagamentos pendentes podem ser excluídos");
        }
        pagamento.setAtivo(false);
        pagamentoRepository.save(pagamento);
    }
}
