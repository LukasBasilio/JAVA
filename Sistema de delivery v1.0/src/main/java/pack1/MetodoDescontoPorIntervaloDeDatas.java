/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aluno
 */
public class MetodoDescontoPorIntervaloDeDatas implements MetodoDescontoTaxaEntrega{
    private record IntervaloPromocional(LocalDate inicio, LocalDate fim, double percentual, String nome) {}
    
    private final List<IntervaloPromocional> intervalos;

    public MetodoDescontoPorIntervaloDeDatas() {
        this.intervalos = new ArrayList<>();
        // Populamos a lista com as regras de negócio definidas
        intervalos.add(new IntervaloPromocional(
            LocalDate.of(2025, 5, 22), 
            LocalDate.of(2025, 5, 27), 
            1.00, // 100%
            "Promo Semana de Maio (100%)"
        ));
        intervalos.add(new IntervaloPromocional(
            LocalDate.of(2025, 6, 16), 
            LocalDate.of(2025, 6, 16), 
            0.60, // 60%
            "Promo Feriado de Junho (60%)"
        ));
        intervalos.add(new IntervaloPromocional(
            LocalDate.of(2025, 5, 25), 
            LocalDate.of(2025, 5, 25), 
            0.50, // 50%
            "Promo Especial de Domingo (50%)"
        ));
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        LocalDate dataPedido = pedido.getData();
        // A regra se aplica se a data do pedido cair em QUALQUER um dos intervalos.
        for (IntervaloPromocional intervalo : intervalos) {
            // A verificação: a data não é anterior ao início E não é posterior ao fim.
            if (!dataPedido.isBefore(intervalo.inicio) && !dataPedido.isAfter(intervalo.fim)) {
                return true; // Encontrou um intervalo aplicável, já pode retornar true.
            }
        }
        return false; // Passou por todos os intervalos e não encontrou nenhum.
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {
        LocalDate dataPedido = pedido.getData();
        
        // Variáveis para guardar a melhor promoção encontrada
        double maiorPercentual = 0.0;
        String nomeMelhorPromocao = "";

        // Itera por todos os intervalos para encontrar o de maior desconto aplicável
        for (IntervaloPromocional intervalo : intervalos) {
            if (!dataPedido.isBefore(intervalo.inicio) && !dataPedido.isAfter(intervalo.fim)) {
                // A data está neste intervalo. Ele é melhor que o melhor que já achamos?
                if (intervalo.percentual > maiorPercentual) {
                    maiorPercentual = intervalo.percentual;
                    nomeMelhorPromocao = intervalo.nome;
                }
            }
        }

       
        if (maiorPercentual > 0) {
            double valorDesconto = pedido.getTaxaEntrega() * maiorPercentual;
            return new CupomDescontoEntrega(nomeMelhorPromocao, valorDesconto);
        }
        
        return new CupomDescontoEntrega("Sem Desconto por Data", 0.0);
    }
}
