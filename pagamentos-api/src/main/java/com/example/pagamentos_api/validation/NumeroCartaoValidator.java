package com.example.pagamentos_api.validation;

import com.example.pagamentos_api.dto.PagamentoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroCartaoValidator implements ConstraintValidator<NumeroCartaoValid, PagamentoRequest> {

    @Override
    public boolean isValid(PagamentoRequest request, ConstraintValidatorContext context) {
        if (request.getMetodoPagamento() == null) {
            return true;
        }

        boolean isCartao = request.getMetodoPagamento().equalsIgnoreCase("cartao_credito") ||
                request.getMetodoPagamento().equalsIgnoreCase("cartao_debito");

        if (isCartao && (request.getNumeroCartao() == null || request.getNumeroCartao().isEmpty())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "O número do cartão é obrigatório para pagamentos com cartão de crédito ou débito")
                    .addConstraintViolation();
            return false;
        }

        if (!isCartao && request.getNumeroCartao() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "O número do cartão só pode ser enviado para métodos de pagamento com cartão de crédito ou débito")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
