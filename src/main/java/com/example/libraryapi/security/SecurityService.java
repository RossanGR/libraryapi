package com.example.libraryapi.security;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {
    private final UsuarioService usuarioService;
 public Usuario obterUsuarioLogado(){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     if(authentication instanceof CustomAuthetication customAuth){
         return customAuth.getUsuario();
     }
     return null;
 }
}
