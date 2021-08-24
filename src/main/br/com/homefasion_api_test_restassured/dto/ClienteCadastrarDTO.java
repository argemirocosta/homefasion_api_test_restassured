package br.com.homefasion_api_test_restassured.dto;

import java.util.Objects;

public class ClienteCadastrarDTO {

    private String nome;
    private String cpf;
    private Integer telefone1;
    private UsuarioDTO usuario;

    public ClienteCadastrarDTO(String nome, String cpf, Integer telefone1, UsuarioDTO usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone1 = telefone1;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(Integer telefone1) {
        this.telefone1 = telefone1;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteCadastrarDTO that = (ClienteCadastrarDTO) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(telefone1, that.telefone1) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, telefone1, usuario);
    }

    @Override
    public String toString() {
        return "ClienteCadastrarDTO{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone1=" + telefone1 +
                ", usuario=" + usuario +
                '}';
    }
}
