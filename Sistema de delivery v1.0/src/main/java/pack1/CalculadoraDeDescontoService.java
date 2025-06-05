package pack1;

import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;

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
       
        if (pedido.getCupomDePedidoAplicado() != null) {
           
            return Collections.emptyList(); 
        }

        for (MetodoDescontoTaxaEntrega metodo : metodosDeDesconto) {
            if (metodo.seAplica(pedido)) {
                CupomDescontoEntrega cupom = metodo.calcularDesconto(pedido);
                if (cupom != null && cupom.getValorDesconto() > 0) {
                    
                    pedido.aplicarDescontoTaxaEntrega(cupom.getNomeMetodo(), cupom.getValorDesconto());
                }
            }
        }
        return pedido.getCuponsDeDescontoEntrega();
    }
}