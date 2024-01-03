package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.controller.exception.ControllerNotFoundException;
import br.com.fiap.techchallenge.dto.UsuarioDTO;
import br.com.fiap.techchallenge.entities.UsuarioEntity;
import br.com.fiap.techchallenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<UsuarioEntity> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(this::toUsuarioDTO);
    }

    public UsuarioDTO findById(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(()
                -> new ControllerNotFoundException("Usuario não encontrado."));
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = toUsuarioEntity(usuarioDTO);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return toUsuarioDTO(usuarioEntity);
    }

    public UsuarioDTO updateById(Long id, UsuarioDTO usuarioDTO) {
        try {
            UsuarioEntity buscaUsuario = usuarioRepository.getReferenceById(id);
            buscaUsuario.setNome(usuarioDTO.nome());
            buscaUsuario.setEmail(usuarioDTO.email());
            buscaUsuario.setCpf(usuarioDTO.cpf());
            buscaUsuario.setDataNascimento(usuarioDTO.dataNascimento());
            buscaUsuario = usuarioRepository.save(buscaUsuario);
            return toUsuarioDTO(buscaUsuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario não encontrado");
        }
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toUsuarioDTO(UsuarioEntity usuarioEntity) {
        return new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getCpf(),
                usuarioEntity.getDataNascimento()
        );
    }

    private UsuarioEntity toUsuarioEntity(UsuarioDTO usuarioDTO) {
        return new UsuarioEntity(
                usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.cpf(),
                usuarioDTO.dataNascimento()
        );
    }
}
