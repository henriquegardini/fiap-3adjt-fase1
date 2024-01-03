package br.com.fiap.techchallenge.repository;

import br.com.fiap.techchallenge.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
