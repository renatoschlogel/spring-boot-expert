package com.renatoschlogel.api.controller;

import com.renatoschlogel.api.rest.dto.CredenciaisDTO;
import com.renatoschlogel.api.rest.dto.TokenDTO;
import com.renatoschlogel.domain.entity.Usuario;
import com.renatoschlogel.security.jwt.JwtService;
import com.renatoschlogel.service.impl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioServiceImpl usuarioService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {

        try {
            Usuario usuario = Usuario
                    .builder()
                    .login(credenciaisDTO.getLogin())
                    .senha(credenciaisDTO.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);

            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        }catch (Exception e) {
            System.out.println("Deu ruim");
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }


}
