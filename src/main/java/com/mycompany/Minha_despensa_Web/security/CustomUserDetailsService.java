package com.mycompany.Minha_despensa_Web.security;

import com.mycompany.Minha_despensa_Web.entities.Usuario;
import com.mycompany.Minha_despensa_Web.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscando o usuário pelo email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // Convertendo o usuário para um UserDetails
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // A senha já está criptografada
                .roles("USER") // Definindo a role como USER
                .build();
    }
}
