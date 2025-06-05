/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack2;

/**
 *
 * @author lusky
 */
public class MetodoCupomSimples implements MetodoDescontoPedido {
    @Override
    public CupomDescontoPedido criarCupom(String codigo, double percentual) {
        
        if (percentual < 0 || percentual > 1.0) {
            System.err.println("Aviso: Percentual de cupom inválido: " + percentual*100 + "%. Cupom não criado.");
            return null; 
        }
        return new CupomDescontoPedido(codigo, percentual);
    }
}
