package com.renatoschlogel.service.impl;

import com.renatoschlogel.domain.entity.Usuario;
import com.renatoschlogel.domain.repository.UsuarioRepository;
import com.renatoschlogel.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository
                .findByLogin(username)
                .map(this::prepararUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

    private UserDetails prepararUserDetails(Usuario usuario) {

        String[] roles = new String[]{"USER"};
        if (usuario.isAdmin()) {
            roles = new String[]{"USER", "ADMIN"};
        }

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhaCoferi = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());
        if (senhaCoferi) {
            return userDetails;
        }

        throw new RegraNegocioException("Senha inválida!");
    }
}
