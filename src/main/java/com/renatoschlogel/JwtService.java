package com.renatoschlogel;

import com.renatoschlogel.domain.entity.Usuario;
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

        HashMap<String, Object> claims = new HashMap<>();

        claims.put("email", "renato.s@outlook.com");
        claims.put("roles", "admin");

        Date data = Date.from(dataHoraExpericao.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura )
                .compact()
                ;
    }

}
