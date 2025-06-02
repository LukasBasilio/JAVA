/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;

/**
 *
 * @author lusky
 */
public class Item {
   private String nome;
   private Integer quantidade;
   private Double valorUnitario;
   private String tipo;
   
   public Item (String nome, Integer quantidade, Double valorUnitario, String tipo){
       this.nome = nome;
       this.quantidade = quantidade;
       this.valorUnitario = valorUnitario;
       this.tipo = tipo;
   }
   
   public double getValorTotal (){
       return quantidade*valorUnitario;
   }
   
   public String getTipo(){
       return tipo;
   }
   
   @Override
   public String toString(){
    return "Item{" +
               "nome='" + nome + '\'' +
               ", quantidade=" + quantidade +
               ", valorUnitario=" + valorUnitario +
               ", tipo='" + tipo + '\'' +
               ", valorTotal=" + getValorTotal() +
               '}';
    }
}
