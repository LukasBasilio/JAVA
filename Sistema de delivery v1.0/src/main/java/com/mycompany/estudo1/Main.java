/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.estudo1;
import pack1.*; 
import java.time.LocalDate;
/**
 *
 * @author lusky
 */
public class Main {

    public static void main(String[] args) {
        // Criamos uma única instância da calculadora que será usada em todos os testes.
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        System.out.println("INICIANDO SUÍTE DE TESTES DO MÓDULO DE DESCONTOS\n");

        //================================================================================
        // CENÁRIO 1: Pedido Básico
        // Objetivo: Verificar um pedido sem nenhuma regra de desconto aplicável.
        //================================================================================
        System.out.println("--- CENARIO 1: Pedido Básico, Sem Descontos ---");
        Cliente clienteComum = new Cliente("Carlos Comum", "Pessoa Física", "NENHUM", "Rua Normal, 1", "Bairro X", "Cidade Y");
        // Uma segunda-feira (hoje) fora de qualquer promoção de data.
        Pedido pedidoBasico = new Pedido(LocalDate.now(), clienteComum);
        pedidoBasico.adicionarItem(new Item("Produto Genérico", 1, 50.0, "Outros"));
        calculadora.calcularDesconto(pedidoBasico);
        System.out.println(pedidoBasico);
        System.out.println("---------------------------------------------------\n");


        //================================================================================
        // CENÁRIO 2: Combo de Descontos e Teste do Limite de R$ 10,00
        // Objetivo: Acionar várias regras ao mesmo tempo e ver se o limite é respeitado.
        //================================================================================
        System.out.println("--- CENARIO 2: Combo de Descontos (Teste do Limite) ---");
        Cliente clienteVip = new Cliente("Ana Vip", "Pessoa Física", "OURO", "Av. Principal, 100", "Bela Vista", "Cidade Z");
        Pedido pedidoSuperDesconto = new Pedido(LocalDate.of(2025, 6, 5), clienteVip); // Uma quinta-feira
        pedidoSuperDesconto.adicionarItem(new Item("Livro de Receitas", 1, 150.0, "Alimentação")); // Valor > 200 com o próximo item
        pedidoSuperDesconto.adicionarItem(new Item("Curso Online", 1, 100.0, "Educação"));
        // Descontos esperados: Bairro(3.0) + Fidelidade(3.0) + Item Alimentação(5.0) + Item Educação(2.0) + Valor Pedido(5.0) = R$ 18.00
        // Resultado esperado: Soma dos cupons deve ser R$ 10.00, com o último sendo parcial.
        calculadora.calcularDesconto(pedidoSuperDesconto);
        System.out.println(pedidoSuperDesconto);
        System.out.println("---------------------------------------------------\n");


        //================================================================================
        // CENÁRIO 3: Teste de Desconto por Dia da Semana (Terça-feira 100%)
        // Objetivo: Isolar e confirmar a regra de dia da semana.
        //================================================================================
        System.out.println("--- CENARIO 3: Teste de Desconto de Terça-feira (100%) ---");
        LocalDate tercaFeira = LocalDate.of(2025, 6, 3);
        Pedido pedidoDeTerca = new Pedido(tercaFeira, clienteComum); // Cliente comum para não interferir
        pedidoDeTerca.adicionarItem(new Item("Cinema", 2, 30.0, "Lazer"));
        // Resultado esperado: Um único cupom de R$ 10.00 (100% da taxa).
        calculadora.calcularDesconto(pedidoDeTerca);
        System.out.println(pedidoDeTerca);
        System.out.println("---------------------------------------------------\n");


        //================================================================================
        // CENÁRIO 4: Teste de Conflito de Intervalo de Datas
        // Objetivo: Confirmar que a regra de "maior desconto prevalece" funciona.
        //================================================================================
        System.out.println("--- CENÁRIO 4: Teste de Desconto por Intervalo de Datas (Conflito) ---");
        // 25/05/2025 cai na promoção de 100% (22 a 27/05) e na de 50% (só dia 25/05).
        LocalDate dataConflitante = LocalDate.of(2025, 5, 25);
        Pedido pedidoEmPromo = new Pedido(dataConflitante, clienteComum);
        pedidoEmPromo.adicionarItem(new Item("Jogo", 1, 150.0, "Lazer"));
        // Resultado esperado: Apenas o cupom de 100% deve ser aplicado.
        calculadora.calcularDesconto(pedidoEmPromo);
        System.out.println(pedidoEmPromo);
        System.out.println("---------------------------------------------------\n");

    }
}
