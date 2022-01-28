package com.danlibs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Banco banco = new Banco();
        String opcao = "";

        while (!opcao.equals("3")) {
            System.out.println("====================================");
            System.out.println("========== BANCO CAPITAL ===========");
            System.out.println("====================================\n");
            System.out.println("Bom dia! Opções: ");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("3 - Sair");
            System.out.print("O que você deseja fazer: ");
            opcao = scan.nextLine();
            if (opcao.equals("1")) {
                banco.criarConta();
            } else if (opcao.equals("2")) {
                banco.acessarConta();
            } else if (opcao.equals("3")) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
