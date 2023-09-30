package br.com.desbugando.locadora.controller.exceptions;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class StandardError {
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
