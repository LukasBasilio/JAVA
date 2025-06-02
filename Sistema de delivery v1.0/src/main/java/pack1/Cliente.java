/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;

/**
 *
 * @author lusky
 */
public class Cliente {
    private String nome;
    private String tipo;
    private String fidelidade;
    private String logradouro;
    private String bairro;
    private String cidade;

    public Cliente (String nome, String tipo, String fidelidade, String logradouro,String bairro, String cidade){
        this.nome = nome;
        this.tipo = tipo;
        this.fidelidade = fidelidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
   public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFidelidade() {
        return fidelidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
    
    public void setFidelidade(String fidelidade) {
        this.fidelidade = fidelidade;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
               "nome='" + nome + '\'' +
               ", tipo='" + tipo + '\'' +
               ", fidelidade=" + fidelidade +
               ", logradouro='" + logradouro + '\'' +
               ", bairro='" + bairro + '\'' +
               ", cidade='" + cidade + '\'' +
               '}';
    }
}
