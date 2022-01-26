package com.danlibs;

import java.util.Scanner;

public class Conta {
    private static final String NUM_AGENCIA = "001";
    private static int NUM_CONTA = 1;

    public enum tipoDeConta {CORRENTE, POUPANCA}

    private Integer numConta;
    private String numAgencia;
    private double saldo;
    private final Cliente titular;
    private tipoDeConta tipoConta;
    private String senha;

    private Scanner scan = new Scanner(System.in);

    public Conta(Cliente titular, tipoDeConta tipo, String senha) {
        this.saldo = 0;
        this.numConta = NUM_CONTA++;
        this.numAgencia = NUM_AGENCIA;
        this.titular = titular;
        this.tipoConta = tipo;
        this.senha = senha;
    }

    public void sacar(double valor) {
        System.out.print("Para sacar, informe sua senha: ");
        String senhaInserida = scan.nextLine();
        if (senhaInserida.equals(this.senha)) {
            this.saldo -= valor;
            System.out.printf("R$%.2f sacado com sucesso.\n", valor);
        } else {
            System.out.println("Senha incorreta.");
        }
    }

    public void depositar(double valor) {
        System.out.print("Para depositar, informe sua senha: ");
        String senhaInserida = scan.nextLine();
        if (senhaInserida.equals(this.senha)) {
            this.saldo += valor;
            System.out.printf("R$%.2f depositado com sucesso.\n", valor);
        } else {
            System.out.println("Senha incorreta.");
        }
    }

    public void transferir(double valor, Conta destino) {
        System.out.print("Para transferir, informe sua senha: ");
        String senhaInserida = scan.nextLine();
        if (senhaInserida.equals(this.senha)) {
            this.saldo -= valor;
            destino.saldo += valor;
            System.out.printf("R$%.2f transferido para %s com sucesso.\n", valor, destino.titular.getNome());
        } else {
            System.out.println("Senha incorreta.");
        }
    }

    public void mudarSenha(String novaSenha) {
        this.senha = novaSenha;
        System.out.println("Senha alterada com sucesso.\n");
    }

    public int getNumConta() {
        return this.numConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public String getSenha() {
        return senha;
    }


    public void mostrarExtrato() {
        System.out.println("----------------------------");
        System.out.println("--------- EXTRATO ----------");
        System.out.println("----------------------------");
        System.out.println("Titular: " + this.titular.getNome());
        System.out.println("Tipo de conta: " + this.tipoConta.toString());
        System.out.println("Agência: " + this.numAgencia);
        System.out.println("Conta: " + this.numConta + "\n");
        System.out.printf("SALDO: R$%.2f\n", this.saldo);
    }
}
