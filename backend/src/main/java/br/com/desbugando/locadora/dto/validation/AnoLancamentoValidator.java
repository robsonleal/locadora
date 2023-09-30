package br.com.desbugando.locadora.dto.validation;

import java.time.Year;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnoLancamentoValidator implements ConstraintValidator<AnoLancamentoValid, Integer> {

  @Override
  public void initialize(AnoLancamentoValid constraintAnnotation) {
  }

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }

    int currentYear = Year.now().getValue();
    return value >= 1900 && value <= currentYear;
  }

}
