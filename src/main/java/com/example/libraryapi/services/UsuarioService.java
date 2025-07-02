package com.example.libraryapi.services;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    public Usuario obterPorLogin(String login) {
       return usuarioRepository.findByLogin(login);
    }

    public Usuario obterPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
