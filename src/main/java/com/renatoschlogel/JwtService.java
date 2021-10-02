package com.renatoschlogel;

import com.renatoschlogel.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpericao = LocalDateTime.now().plusMinutes(expString);

        Date data = Date.from(dataHoraExpericao.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura )
                .compact()
                ;
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            LocalDateTime dataExpiracao = claims.getExpiration().toInstant()
                                                       .atZone(ZoneId.systemDefault())
                                                       .toLocalDateTime();
            return !LocalDateTime.now().isAfter(dataExpiracao);
        }catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) {
        return obterClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplication().run(VendasApplication.class);
        JwtService jwtService = run.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("renato").build();
        String token = jwtService.gerarToken(usuario);
        System.out.println(token);
        System.out.println("valido "+ jwtService.tokenValido(token));
        System.out.println("Login" + jwtService.obterLoginUsuario(token));
    }

}
