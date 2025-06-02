/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lusky
 */
public class CalculadoraDeDescontoService {
    private List<MetodoDescontoTaxaEntrega> metodosDeDesconto;

    public CalculadoraDeDescontoService() {
        metodosDeDesconto = new ArrayList<>();
        
        metodosDeDesconto.add(new MetodoDescontoPorIntervaloDeDatas());
        metodosDeDesconto.add(new MetodoDescontoPorDiaSemana());
        metodosDeDesconto.add(new MetodoDescontoTaxaPorBairro());
        metodosDeDesconto.add(new MetodoDescontoTaxaPorTipoCliente());
        metodosDeDesconto.add(new MetodoDescontoTipoItem());
        metodosDeDesconto.add(new MetodoDescontoValorPedido());
    }

    public List<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {
        for (MetodoDescontoTaxaEntrega metodo : metodosDeDesconto) {
            
            if (metodo.seAplica(pedido)) {
             
                CupomDescontoEntrega cupom = metodo.calcularDesconto(pedido);
                
                
                if (cupom != null && cupom.getValorDesconto() > 0) {
                     pedido.aplicarDesconto(cupom.getNomeMetodo(), cupom.getValorDesconto());
                }
            }
        }
        return pedido.getCuponsDeDescontoEntrega();
    }
}
