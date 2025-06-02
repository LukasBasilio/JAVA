/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pack1;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author lusky
 */
public class MetodoDescontoTaxaPorBairro implements MetodoDescontoTaxaEntrega {
    
  
    private final Map<String, Double> descontosPorBairro;
    
    public MetodoDescontoTaxaPorBairro() {
        descontosPorBairro = new HashMap<>();
        
        descontosPorBairro.put("Centro", 2.00);
        descontosPorBairro.put("Bela Vista", 3.00);
        descontosPorBairro.put("Cidade Maravilhosa", 1.50);
       
    }   

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        String bairro = pedido.getCliente().getBairro();
        Double desconto = descontosPorBairro.getOrDefault(bairro, 0.0);
        return new CupomDescontoEntrega("Desconto por Bairro (" + bairro + ")", desconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        String bairro = pedido.getCliente().getBairro();
        return bairro != null && descontosPorBairro.containsKey(bairro);
    }
    
    
    public Map<String, Double> getDescontosPorBairro() {
        return new HashMap<>(descontosPorBairro);
    }
}