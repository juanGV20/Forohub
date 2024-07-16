package com.forohub.api.topico;

import java.time.LocalDate;

public record DatosListaTopico(String titulo, String mensaje, LocalDate fechaCreacion, Boolean status,
                               String autor, Curso curso) {

    public DatosListaTopico(Topico topico){
        this(topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),
                topico.getStatus(),topico.getAutor(),topico.getCurso());
    }
}
