/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author lusky
 */
public class MetodoDescontoTipoItem implements MetodoDescontoTaxaEntrega{
    
    private final Map<String, Double> descontosPorTipoItem;

    public MetodoDescontoTipoItem() {
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 5.00);
        descontosPorTipoItem.put("Educação", 2.00);
        descontosPorTipoItem.put("Lazer", 1.50);
    }
    
    @Override
    public boolean seAplica(Pedido pedido) {
        
        for (Item item : pedido.getItens()) {
            if (descontosPorTipoItem.containsKey(item.getTipo())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        
        double valorTotalDesconto = 0;
        Set<String> categoriasComDescontoAplicado = new HashSet<>();

        for (Item item : pedido.getItens()) {
            String tipo = item.getTipo();
            
            if (descontosPorTipoItem.containsKey(tipo) && !categoriasComDescontoAplicado.contains(tipo)) {
                valorTotalDesconto += descontosPorTipoItem.get(tipo);
                categoriasComDescontoAplicado.add(tipo);
            }
        }
        return new CupomDescontoEntrega("Desconto por Tipo de Item", valorTotalDesconto);
    }
}
