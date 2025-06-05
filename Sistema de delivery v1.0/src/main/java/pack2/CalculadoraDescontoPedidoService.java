/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack2;
import pack1.Pedido;
/**
 *
 * @author lusky
 */
public class CalculadoraDescontoPedidoService {
     
    private final MetodoDescontoPedido metodoDeCriacaoDeCupom;

    public CalculadoraDescontoPedidoService() {
       
        this.metodoDeCriacaoDeCupom = new MetodoCupomSimples(); 
    }

    public void aplicarCupomAoPedido(Pedido pedido, String codigoCupom, double percentualCupom) {
        if (codigoCupom == null || codigoCupom.trim().isEmpty()) {
            System.out.println("Código do cupom não pode ser vazio.");
            return;
        }
       
        
        
        CupomDescontoPedido novoCupom = metodoDeCriacaoDeCupom.criarCupom(codigoCupom, percentualCupom);

        if (novoCupom == null) {
            System.out.println("Cupom '" + codigoCupom + "' não pôde ser criado (verifique o percentual). Não aplicado.");
            return;
        }

        
       
        pedido.processarAplicacaoDeCupom(novoCupom); 
    }
}
