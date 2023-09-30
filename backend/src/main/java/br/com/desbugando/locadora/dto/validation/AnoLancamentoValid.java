package br.com.desbugando.locadora.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = AnoLancamentoValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnoLancamentoValid {
  String message() default "O ano de lançamento não é valido";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
