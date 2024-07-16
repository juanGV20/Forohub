package com.forohub.api.topico;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

public record DatosRegistroTopico(


        @NotBlank
        @Column(unique = true)
        String titulo,
        @NotBlank
        @Column(unique = true)
        String mensaje,
        @NotNull
        LocalDate fechaCreacion,
        @NotNull
        Boolean status,
        @NotBlank
        String autor,
        @NotNull
        Curso curso) {
}
