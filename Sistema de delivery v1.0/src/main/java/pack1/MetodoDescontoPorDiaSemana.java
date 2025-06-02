/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;
import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 *
 * @author Aluno
 */
public class MetodoDescontoPorDiaSemana implements MetodoDescontoTaxaEntrega{
    @Override
    public boolean seAplica(Pedido pedido) {
        LocalDate dataDoPedido = pedido.getData();
        DayOfWeek diaDaSemana = dataDoPedido.getDayOfWeek();
        
        // A regra se aplica se for Terça-feira (TUESDAY) ou Quarta-feira (WEDNESDAY)
        return diaDaSemana == DayOfWeek.TUESDAY || diaDaSemana == DayOfWeek.WEDNESDAY;
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        DayOfWeek diaDaSemana = pedido.getData().getDayOfWeek();
        double taxaDeEntrega = pedido.getTaxaEntrega();
        double valorDesconto = 0;
        String nomeCupom = "";

        if (diaDaSemana == DayOfWeek.TUESDAY) {
            // 100% de desconto na taxa de entrega
            valorDesconto = taxaDeEntrega * 1.0; 
            nomeCupom = "Promo de Terça (100%)";
        } else if (diaDaSemana == DayOfWeek.WEDNESDAY) {
            // 60% de desconto na taxa de entrega
            valorDesconto = taxaDeEntrega * 0.60;
            nomeCupom = "Promo de Quarta (60%)";
        }
        
        return new CupomDescontoEntrega(nomeCupom, valorDesconto);
    }
}
