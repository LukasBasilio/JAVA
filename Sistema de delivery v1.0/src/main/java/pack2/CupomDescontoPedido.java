/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack2;

/**
 *
 * @author lusky
 */
public class CupomDescontoPedido {
    private String nome;
    private double porcentagem;
    
    
    public CupomDescontoPedido(String nome, double porcentagem){
        this.nome = nome;
        this.porcentagem = porcentagem;
    }
    
    public double getPorcentagem(){
        return porcentagem;
    }
    
    public String getNome(){
        return nome;
    }
    
@Override
    public String toString() {
        return String.format("Cupom{código='%s', desconto=%.0f%%}", nome, porcentagem * 100);
    }
}
