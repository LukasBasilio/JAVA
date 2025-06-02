/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;

/**
 *
 * @author lusky
 */
public class MetodoDescontoValorPedido implements MetodoDescontoTaxaEntrega {
    
    private static final double LIMITE_VALOR_PEDIDO = 200.00;
    private static final double VALOR_DESCONTO = 5.0;

    @Override
    public boolean seAplica(Pedido pedido) {
        return pedido.getValorTotalItens() > LIMITE_VALOR_PEDIDO;
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        return new CupomDescontoEntrega("Desconto por Valor do Pedido", VALOR_DESCONTO);
    }
}
