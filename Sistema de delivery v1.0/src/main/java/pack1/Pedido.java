/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pack1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private final double taxaEntrega = 10.00;
    private LocalDate data; 
    private Cliente cliente;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDeDescontoEntrega; 

    public Pedido(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.cuponsDeDescontoEntrega = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    
    public double getValorTotalItens() {
        return itens.stream().mapToDouble(Item::getValorTotal).sum();
    }

  
    public double getDescontoConcedido() {
        return cuponsDeDescontoEntrega.stream().mapToDouble(CupomDescontoEntrega::getValorDesconto).sum();
    }

   
    public void aplicarDesconto(String nomeMetodo, double valorDescontoSugerido) {
        double descontoJaConcedido = getDescontoConcedido();
        double limiteMaximoDesconto = 10.00;
        
        
        double descontoRestante = limiteMaximoDesconto - descontoJaConcedido;

        
        if (descontoRestante <= 0 || valorDescontoSugerido <= 0) {
            return;
        }

       
        double descontoReal = Math.min(valorDescontoSugerido, descontoRestante);
        String nomeFinalMetodo = nomeMetodo;

        
        if (descontoReal < valorDescontoSugerido) {
            nomeFinalMetodo += " (Parcial)";
        }
        
      
        this.cuponsDeDescontoEntrega.add(new CupomDescontoEntrega(nomeFinalMetodo, descontoReal));
    }

  
    public double getTaxaEntregaFinal() {
        return Math.max(0, this.taxaEntrega - getDescontoConcedido());
    }

    
    public double getValorTotalAPagar() {
        return getValorTotalItens() + getTaxaEntregaFinal();
    }

    
    public Cliente getCliente() { return cliente; }
    public List<Item> getItens() { return new ArrayList<>(itens); }
    public double getTaxaEntrega() { return taxaEntrega; }
    public List<CupomDescontoEntrega> getCuponsDeDescontoEntrega() { return new ArrayList<>(cuponsDeDescontoEntrega); }
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        return String.format(
            "Pedido [Data: %s, Cliente: %s]\n" +
            "  Valor dos Itens: R$%.2f\n" +
            "  Taxa de Entrega Original: R$%.2f\n" +
            "  Cupons Aplicados: %s\n" +
            "  Desconto Total Concedido: R$%.2f\n" +
            "  Taxa de Entrega Final: R$%.2f\n" +
            "  >> VALOR TOTAL A PAGAR: R$%.2f",
            data,
            cliente.getNome(),
            getValorTotalItens(),
            taxaEntrega,
            cuponsDeDescontoEntrega.toString(),
            getDescontoConcedido(),
            getTaxaEntregaFinal(),
            getValorTotalAPagar()
        );
    }
}

