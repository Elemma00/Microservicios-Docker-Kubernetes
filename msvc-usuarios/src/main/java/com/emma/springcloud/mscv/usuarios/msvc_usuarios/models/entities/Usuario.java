package com.emma.springcloud.mscv.usuarios.msvc_usuarios.models.entities;

import com.emma.springcloud.mscv.usuarios.msvc_usuarios.validations.ExistsByEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

 
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    @Email
    @ExistsByEmail
    @NotEmpty(message = "no debe estar vacio")
    private String email;

    @NotEmpty
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", nombre=" + nombre + ", email=" + email + "}";
    }
    
    
}
