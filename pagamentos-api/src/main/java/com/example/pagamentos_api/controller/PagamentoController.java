package com.example.pagamentos_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pagamentos_api.dto.PagamentoRequest;
import com.example.pagamentos_api.dto.StatusPagamentoRequest;
import com.example.pagamentos_api.entity.Pagamento;
import com.example.pagamentos_api.service.PagamentoService;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Pagamentos", description = "Operações relacionadas a pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    @Operation(summary = "Criar pagamento", description = "Cria um novo pagamento com status pendente")
    public ResponseEntity<Pagamento> criarPagamento(
            @Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.ok(pagamentoService.criarPagamento(request));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status", description = "Atualiza o status de um pagamento existente")
    public ResponseEntity<Pagamento> atualizarStatus(
            @PathVariable @Parameter(description = "ID do pagamento a ser atualizado") Long id,
            @RequestBody StatusPagamentoRequest request) {
        return ResponseEntity.ok(pagamentoService.atualizarStatus(id, request));
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos", description = "Lista todos os pagamentos com filtros opcionais")
    public ResponseEntity<List<Pagamento>> listarPagamentos(
            @RequestParam(required = false) @Parameter(description = "Código do débito") Integer codigoDebito,
            @RequestParam(required = false) @Parameter(description = "CPF/CNPJ do pagador") String cpfCnpj,
            @RequestParam(required = false) @Parameter(description = "Status do pagamento") String status) {
        return ResponseEntity.ok(pagamentoService.listarPagamentos(codigoDebito, cpfCnpj, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pagamento", description = "Realiza a exclusão lógica de um pagamento pendente")
    public ResponseEntity<Void> excluirPagamento(
            @PathVariable @Parameter(description = "ID do pagamento a ser excluído") Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
