/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author lusky
 */
public class MetodoDescontoTaxaPorTipoCliente implements MetodoDescontoTaxaEntrega {
 
    private final Map<String, Double> descontosPorNivel;
     
    public MetodoDescontoTaxaPorTipoCliente() {
        descontosPorNivel = new HashMap<>();
        descontosPorNivel.put("OURO", 3.00);
        descontosPorNivel.put("PRATA", 2.00);
        descontosPorNivel.put("BRONZE", 1.00);
    }
     
    @Override
    public boolean seAplica(Pedido pedido) {
        String nivel = pedido.getCliente().getFidelidade();
        
        return nivel != null && descontosPorNivel.containsKey(nivel.toUpperCase());
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        
        String nivel = pedido.getCliente().getFidelidade().toUpperCase();
        double valorDesconto = descontosPorNivel.get(nivel);
        return new CupomDescontoEntrega("Desconto por Fidelidade (" + nivel + ")", valorDesconto);
    }
}
