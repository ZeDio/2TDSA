package com.fiap.demotestes.service;

import com.fiap.demotestes.model.Usuario;
import com.fiap.demotestes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> listarTodos(){
        return  usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: "+id));

    }
    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() ->UsuarioNaoEncontradoException("Usuário não encontrado com email: "+email));
    }

    public  Usuario criar(Usuario usuario){
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email já cadastrado: "+usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = buscarPorId(id);

        //atualiza os campos permitidos
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setIdade(usuarioAtualizado.getIdade());

        if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail())){
            if (usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())){
                throw new RuntimeException("Email já cadastrado: "+usuarioAtualizado.getEmail());

            }
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        }
        return usuarioRepository.save(usuarioExistente);
    }

    public void deletar(Long id){
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
