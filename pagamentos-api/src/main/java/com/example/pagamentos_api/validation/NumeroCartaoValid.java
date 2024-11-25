package com.example.pagamentos_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NumeroCartaoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroCartaoValid {
    String message() default "Número do cartão deve ser informado para métodos de pagamento com cartão";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
