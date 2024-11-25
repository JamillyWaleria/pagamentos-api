package com.example.pagamentos_api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.pagamentos_api.dto.PagamentoRequest;
import com.example.pagamentos_api.entity.Pagamento;
import com.example.pagamentos_api.repository.PagamentoRepository;
import com.example.pagamentos_api.service.PagamentoService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class PagamentoServiceTest {

    private final PagamentoRepository pagamentoRepository = Mockito.mock(PagamentoRepository.class);
    private final PagamentoService pagamentoService = new PagamentoService(pagamentoRepository);

    @Test
    void deveCriarPagamento() {
        PagamentoRequest request = new PagamentoRequest();
        request.setCodigoDebito(123);
        request.setCpfCnpj("12345678901");
        request.setMetodoPagamento("pix");
        request.setValor(BigDecimal.valueOf(100.0));

        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setCodigoDebito(123);
        pagamento.setCpfCnpj("12345678901");
        pagamento.setMetodoPagamento("pix");
        pagamento.setValor(BigDecimal.valueOf(100.0));

        Mockito.when(pagamentoRepository.save(any(Pagamento.class))).thenReturn(pagamento);

        Pagamento criado = pagamentoService.criarPagamento(request);

        assertEquals(1L, criado.getId());
        assertEquals("pix", criado.getMetodoPagamento());
        assertEquals(BigDecimal.valueOf(100.0), criado.getValor());
    }
}
