// src/main/java/com/meuprojeto/modelo/Usuario.java
package com.meuprojeto.modelo;

public class Usuario {
    private String idUsuario;
    private String email;
    private String senha;
    private String nome;

    // Construtor vazio
    public Usuario() {
    }

    // Construtor com todos os campos
    public Usuario(String idUsuario, String email, String senha, String nome) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    // Getters e Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario='" + idUsuario + '\'' +
               ", email='" + email + '\'' +
               ", nome='" + nome + '\'' +
               '}';
    }
}