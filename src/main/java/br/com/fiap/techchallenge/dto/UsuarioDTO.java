package br.com.fiap.techchallenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O campo nome não pode estar em branco.")
        String nome,
        @Email(message = "O campo email é inválido.")
        String email,
        @CPF(message = "O campo cpf é inválido.")
        String cpf,
        LocalDate dataNascimento
) {
}
