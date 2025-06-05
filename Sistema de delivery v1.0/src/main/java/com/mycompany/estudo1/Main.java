/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.estudo1;
import pack1.*; 
import pack2.*;
import java.time.LocalDate;
import java.time.Month;
/**
 *
 * @author lusky
 */
public class Main {

    public static void main(String[] args) {
        // Instanciando as duas calculadoras
        CalculadoraDeDescontoService calcDescontoEntrega = new CalculadoraDeDescontoService();
        CalculadoraDescontoPedidoService calcCupomPedido = new CalculadoraDescontoPedidoService();

        System.out.println("INICIANDO SUÍTE DE TESTES COMPLETA DO SISTEMA DE DESCONTOS\n");

        // --- Clientes para os testes ---
        Cliente clienteComum = new Cliente("Carlos Comum", "Pessoa Física", "BRONZE", "Rua Normal, 1", "Bairro X", "Cidade Y");
        Cliente clienteOuroCentro = new Cliente("Ana Ouro", "Pessoa Jurídica", "OURO", "Rua das Palmeiras, 10", "Centro", "Rio de Janeiro");
        Cliente clientePrataBelaVista = new Cliente("Bruno Prata", "Pessoa Física", "PRATA", "Av. Brasil, 200", "Bela Vista", "São Paulo");

        //================================================================================
        // SEUS CENÁRIOS EXISTENTES (DESCONTOS NA TAXA DE ENTREGA)
        //================================================================================

        System.out.println("--- CENÁRIO 1 (Existente): Pedido Básico, Sem Descontos na Taxa ---");
        Pedido pedidoBasico = new Pedido(LocalDate.now(), clienteComum); // Usando LocalDate.now() pode variar o dia da semana
        pedidoBasico.adicionarItem(new Item("Produto Genérico", 1, 50.0, "Outros"));
        calcDescontoEntrega.calcularDesconto(pedidoBasico);
        System.out.println(pedidoBasico);
        System.out.println("---------------------------------------------------\n");

        System.out.println("--- CENÁRIO 2 (Existente): Combo de Descontos na Taxa (Teste do Limite) ---");
        Pedido pedidoSuperDescontoTaxa = new Pedido(LocalDate.of(2025, Month.JULY, 10), clienteOuroCentro); // Uma quinta-feira
        pedidoSuperDescontoTaxa.adicionarItem(new Item("Livro de Receitas", 1, 150.0, "Alimentação"));
        pedidoSuperDescontoTaxa.adicionarItem(new Item("Curso Online", 1, 100.0, "Educação"));
        calcDescontoEntrega.calcularDesconto(pedidoSuperDescontoTaxa);
        System.out.println(pedidoSuperDescontoTaxa);
        System.out.println("---------------------------------------------------\n");

        System.out.println("--- CENÁRIO 3 (Existente): Teste de Desconto de Terça-feira (100%) na Taxa ---");
        LocalDate tercaFeira = LocalDate.of(2025, Month.JUNE, 3);
        Pedido pedidoDeTercaTaxa = new Pedido(tercaFeira, clienteComum);
        pedidoDeTercaTaxa.adicionarItem(new Item("Cinema", 2, 30.0, "Lazer"));
        calcDescontoEntrega.calcularDesconto(pedidoDeTercaTaxa);
        System.out.println(pedidoDeTercaTaxa);
        System.out.println("---------------------------------------------------\n");
        
        System.out.println("--- CENÁRIO 4 (Existente): Teste de Desconto por Intervalo de Datas (Conflito) na Taxa ---");
        LocalDate dataConflitanteTaxa = LocalDate.of(2025, Month.MAY, 25);
        Pedido pedidoEmPromoTaxa = new Pedido(dataConflitanteTaxa, clienteComum);
        pedidoEmPromoTaxa.adicionarItem(new Item("Jogo", 1, 150.0, "Lazer"));
        calcDescontoEntrega.calcularDesconto(pedidoEmPromoTaxa);
        System.out.println(pedidoEmPromoTaxa);
        System.out.println("---------------------------------------------------\n");

        //================================================================================
        // NOVOS CENÁRIOS (CUPOM DE DESCONTO NO PEDIDO TOTAL)
        //================================================================================

        System.out.println("--- NOVO CENÁRIO 5: Aplicando Cupom de Pedido Simples (DESC20) ---");
        Pedido pedidoComCupomTotal = new Pedido(LocalDate.now(), clientePrataBelaVista);
        pedidoComCupomTotal.adicionarItem(new Item("Super Produto", 1, 200.0, "Eletrônico"));
        
        System.out.println("\nPedido 5 ANTES de aplicar cupom de PEDIDO:");
        System.out.println(pedidoComCupomTotal);

        // Aplicando o cupom de pedido. Lembre-se que o percentual é passado diretamente.
        calcCupomPedido.aplicarCupomAoPedido(pedidoComCupomTotal, "DESC20", 0.20); // 20% de desconto
        System.out.println("\nPedido 5 APÓS aplicar cupom de PEDIDO (DESC20):");
        System.out.println(pedidoComCupomTotal);
        // Esperado: Cupom DESC20 aplicado, sem descontos de taxa. Valor total reflete 20% off.
        System.out.println("---------------------------------------------------\n");


        System.out.println("--- NOVO CENÁRIO 6: Testando Substituição de Cupom de Pedido e Exclusividade ---");
        Pedido pedidoMultiCupom = new Pedido(LocalDate.of(2025, Month.JUNE, 3), clienteOuroCentro); // Terça (teria 100% na entrega)
        pedidoMultiCupom.adicionarItem(new Item("Item A", 1, 50.0, "Alimentação"));
        pedidoMultiCupom.adicionarItem(new Item("Item B", 1, 50.0, "Lazer"));

        System.out.println("\nPedido 6 - Calculando descontos de ENTREGA primeiro:");
        calcDescontoEntrega.calcularDesconto(pedidoMultiCupom); // Deve aplicar descontos de entrega
        System.out.println(pedidoMultiCupom);

        System.out.println("\nPedido 6 - Aplicando cupom de PEDIDO 'DESC10' (10%):");
        calcCupomPedido.aplicarCupomAoPedido(pedidoMultiCupom, "DESC10", 0.10);
        // Esperado: DESC10 aplicado, descontos de entrega devem ter sido removidos.
        System.out.println(pedidoMultiCupom);

        System.out.println("\nPedido 6 - Tentando aplicar 'NATAL12' (12%) - deve ser REJEITADO (pois já tem DESC30 ou DESC10):");
        // Vamos aplicar um melhor primeiro e depois tentar um pior
        System.out.println("Aplicando cupom 'RELAMPAGO50' (50%) - deve substituir o DESC10:");
        calcCupomPedido.aplicarCupomAoPedido(pedidoMultiCupom, "RELAMPAGO50", 0.50);
        System.out.println(pedidoMultiCupom);

        System.out.println("\nPedido 6 - Tentando aplicar 'NATAL12' (12%) sobre o RELAMPAGO50 - deve ser REJEITADO:");
        calcCupomPedido.aplicarCupomAoPedido(pedidoMultiCupom, "NATAL12", 0.12);
        System.out.println(pedidoMultiCupom); // Deve continuar com RELAMPAGO50
        // Esperado: No final, RELAMPAGO50 ativo, descontos de entrega zerados.
        System.out.println("---------------------------------------------------\n");
        
        System.out.println("FIM DOS TESTES.");
    }
}
