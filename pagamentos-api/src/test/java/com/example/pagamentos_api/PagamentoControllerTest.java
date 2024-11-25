package com.example.pagamentos_api;

import com.example.pagamentos_api.controller.PagamentoController;
import com.example.pagamentos_api.dto.PagamentoRequest;
import com.example.pagamentos_api.entity.Pagamento;
import com.example.pagamentos_api.service.PagamentoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoController.class)
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService pagamentoService;

    @Test
    void deveCriarPagamento() throws Exception {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);

        when(pagamentoService.criarPagamento(any(PagamentoRequest.class))).thenReturn(pagamento);

        mockMvc.perform(post("/api/pagamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"codigoDebito\":123,\"cpfCnpj\":\"12345678901\",\"metodoPagamento\":\"pix\",\"valor\":100.0}"))
                .andExpect(status().isOk());
    }
}
