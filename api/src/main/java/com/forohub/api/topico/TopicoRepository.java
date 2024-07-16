package com.forohub.api.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<DatosListaTopico> findAllByStatusTrue();
}
