/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pack1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pack2.CupomDescontoPedido;
import java.util.stream.Collectors;

public class Pedido {
    private final double taxaEntrega = 10.00;
    private LocalDate data;
    private Cliente cliente;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDeDescontoEntrega;
    private CupomDescontoPedido cupomDePedidoAplicado; // Seu cupom de pedido

    public Pedido(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.cuponsDeDescontoEntrega = new ArrayList<>();
        this.cupomDePedidoAplicado = null;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public double getValorTotalItens() {
        return itens.stream().mapToDouble(Item::getValorTotal).sum();
    }

    public double getDescontoConcedidoTaxaEntrega() {
        return cuponsDeDescontoEntrega.stream().mapToDouble(CupomDescontoEntrega::getValorDesconto).sum();
    }

    public void aplicarDescontoTaxaEntrega(String nomeMetodo, double valorDescontoSugerido) {
        if (this.cupomDePedidoAplicado != null) {
            System.out.println("AVISO: Não é possível aplicar desconto de taxa de entrega, pois um cupom de pedido já está ativo.");
            return;
        }

        double descontoJaConcedido = getDescontoConcedidoTaxaEntrega();
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
        if (this.cupomDePedidoAplicado != null) {
            return this.taxaEntrega;
        }
        return Math.max(0, this.taxaEntrega - getDescontoConcedidoTaxaEntrega());
    }

    public void processarAplicacaoDeCupom(CupomDescontoPedido novoCupom) {
        
        if (novoCupom == null) { 
            System.out.println("Tentativa de aplicar cupom de pedido nulo.");
            return;
        }
        
        if (novoCupom.getPorcentagem() <= 0) {
             System.out.println("Tentativa de aplicar cupom de pedido com percentual zero ou negativo.");
            return;
        }


       
        if (this.cupomDePedidoAplicado == null || novoCupom.getPorcentagem() > this.cupomDePedidoAplicado.getPorcentagem()) {
            this.cupomDePedidoAplicado = novoCupom;
            
            System.out.println("Cupom de Pedido '" + novoCupom.toString() + "' aplicado com sucesso.");

            if (!this.cuponsDeDescontoEntrega.isEmpty()) {
                this.cuponsDeDescontoEntrega.clear();
                System.out.println("-> Atenção: Descontos anteriores na taxa de entrega foram removidos devido à aplicação do cupom de pedido.");
            }
        } else {
            System.out.println("Cupom de Pedido '" + novoCupom.toString() + 
                               "' não foi aplicado, pois já existe um cupom com desconto maior ou igual (" + 
                               this.cupomDePedidoAplicado.toString() + ").");
        }
    }
    
    public CupomDescontoPedido getCupomDePedidoAplicado() {
        return this.cupomDePedidoAplicado;
    }

  
    public double getValorTotalAPagar() {
        double valorItens = getValorTotalItens();
        double taxaEntregaOriginal = this.taxaEntrega;

        if (this.cupomDePedidoAplicado != null) {
            double valorBrutoTotal = valorItens + taxaEntregaOriginal;
            
            double descontoPercentual = valorBrutoTotal * this.cupomDePedidoAplicado.getPorcentagem();
            return valorBrutoTotal - descontoPercentual;
        } else {
            return valorItens + getTaxaEntregaFinal();
        }
    }

    public Cliente getCliente() { return cliente; }
    public List<Item> getItens() { return new ArrayList<>(itens); } 
    public double getTaxaEntrega() { return taxaEntrega; } 
    public List<CupomDescontoEntrega> getCuponsDeDescontoEntrega() { return new ArrayList<>(cuponsDeDescontoEntrega); } 
    public LocalDate getData() { return data; }

    @Override
    public String toString() {
        String infoCupomPedidoAplicado = (this.cupomDePedidoAplicado != null) ? this.cupomDePedidoAplicado.toString() : "Nenhum";
        String infoCuponsEntrega = (this.cupomDePedidoAplicado != null && this.cuponsDeDescontoEntrega.isEmpty()) ? 
                                    "N/A (Cupom de Pedido Ativo)" : this.cuponsDeDescontoEntrega.toString();

        return String.format(
            "\n================ PEDIDO DETALHADO ================\n" +
            "Data: %s\nCliente: %s\n" +
            "---------------- ITENS ---------------------------\n" +
            "Valor Total dos Itens: R$%.2f\n" +
            "---------------- TAXA DE ENTREGA -----------------\n" +
            "Taxa de Entrega Original: R$%.2f\n" +
            "Descontos na Taxa de Entrega: %s\n" +
            "Total Descontos na Taxa: R$%.2f\n" +
            "Taxa de Entrega Final (sem cupom de pedido): R$%.2f\n" + // Ajustei a descrição
            "---------------- CUPOM DE PEDIDO ---------------\n" +
            "Cupom de Pedido Aplicado: %s\n" +
            "------------------ TOTAL -------------------------\n" +
            ">> VALOR TOTAL A PAGAR: R$%.2f\n" +
            "==================================================",
            data,
            (cliente != null ? cliente.getNome() : "N/A"),
            getValorTotalItens(),
            taxaEntrega,
            infoCuponsEntrega,
            getDescontoConcedidoTaxaEntrega(),
            (this.cupomDePedidoAplicado == null ? getTaxaEntregaFinal() : taxaEntrega),
            infoCupomPedidoAplicado,
            getValorTotalAPagar()
        );
    }
}