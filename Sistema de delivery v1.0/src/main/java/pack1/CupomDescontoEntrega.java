/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;

/**
 *
 * @author lusky
 */
public class CupomDescontoEntrega {
    private String nomeMetodo;
    private double valorDesconto;
    
    public CupomDescontoEntrega (String nomeMetodo, double valorDesconto){
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
        
    }
    
    public double getValorDesconto(){
        return valorDesconto;
    }
    
    public String getNomeMetodo(){
        return nomeMetodo;
    }
    
    @Override
    public String toString() {
        return "CupomDescontoEntrega{" +
                "nomeMetodo='" + nomeMetodo + '\'' +
                ", valorDesconto=" + valorDesconto +
                '}';
    }
    
}
