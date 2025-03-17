package com.renatoschlogel.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    private Byte admin;

    public Boolean getAdmin() {
        if (admin == null) {
            return null;
        }

        return admin == 1;
    }

    public void setAdmin(Boolean admin) {
        if (admin == null) {
            this.admin = null;
        } else {
            this.admin = (byte) (admin ? 1 : 0);
        }
    }
}
