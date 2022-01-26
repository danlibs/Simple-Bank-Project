package com.danlibs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("====================================");
            System.out.println("========== BANCO CAPITAL ===========");
            System.out.println("====================================\n");
            System.out.println("Bom dia! Opções: ");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("3 - Sair");
            System.out.print("O que você deseja fazer: ");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1) {
                banco.criarConta();
            } else if (opcao == 2) {
                banco.acessarConta();
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
