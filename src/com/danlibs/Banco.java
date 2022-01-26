package com.danlibs;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Conta> listaContas = new ArrayList<>();

    /*Cliente daniel = new Cliente("Daniel Gomes", "01016071213", "5682775",  new Endereco("Av. Pedro Miranda", 465, "Ap. 603 A", "Pedreira", "Belém", "Pará", "Brasil"));
    Conta danielConta = new Conta(daniel, Conta.tipoDeConta.valueOf("corrente".toUpperCase()), "1234");
    Cliente carol = new Cliente("Carolina Quaresma", "01016071213", "5682775",  new Endereco("Av. Pedro Miranda", 465, "Ap. 603 A", "Pedreira", "Belém", "Pará", "Brasil"));
    Conta carolConta = new Conta(carol, Conta.tipoDeConta.valueOf("poupanca".toUpperCase()), "1234");*/

    private void exibirContas() {
        for (int i = 0; i < listaContas.size(); i++) {
            System.out.println(listaContas.get(i).getNumConta() + " - " + listaContas.get(i).getTitular().getNome());
        }
    }

    public void criarConta() {
        System.out.println("Informe o nome completo do titular da conta: ");
        String nome = scan.nextLine();
        System.out.println("Informe o tipo de conta [Corrente/Poupanca]: ");
        String strTipo = scan.nextLine();
        while (!strTipo.equalsIgnoreCase("corrente") && !strTipo.equalsIgnoreCase("poupanca")) {
            System.out.println("Informe o tipo de conta [Corrente/Poupanca]: ");
            strTipo = scan.nextLine();
        }
        System.out.println("Crie uma senha: ");
        String senha = scan.nextLine();
        System.out.println("CPF: ");
        String cpf = scan.nextLine();
        System.out.println("RG: ");
        String rg = scan.nextLine();
        System.out.println("Endereço (nome da rua, travessa etc.): ");
        String endereco = scan.nextLine();
        System.out.println("Numero: ");
        String numeroStr = scan.nextLine();
        int numero = Integer.parseInt(numeroStr);
        System.out.println("Complemento: ");
        String complemento = scan.nextLine();
        System.out.println("Bairro: ");
        String bairro = scan.nextLine();
        System.out.println("Cidade: ");
        String cidade = scan.nextLine();
        System.out.println("Estado: ");
        String estado = scan.nextLine();
        System.out.println("País: ");
        String pais = scan.nextLine();
        Conta conta = new Conta(new Cliente(nome, cpf, rg, new Endereco(endereco, numero, complemento, bairro, cidade, estado, pais)), Conta.tipoDeConta.valueOf(strTipo.toUpperCase()), senha);
        listaContas.add(conta);
        System.out.println("Conta criada com sucesso!");
        conta.mostrarExtrato();
        travarTela();
        clearScreen();
    }

    public void acessarConta() {
        /*listaContas.add(danielConta);
        listaContas.add(carolConta);*/
        clearScreen();
        if (listaContas.isEmpty()) {
            System.out.println("Não há contas cadastradas no banco.");
            clearScreen();
        } else {
            System.out.print("Informe o número da conta: ");
            int numConta = scan.nextInt();
            scan.nextLine();
            if (listaContas.get(numConta - 1) != null) {
                Conta conta = listaContas.get(numConta - 1);
                System.out.print("Senha: ");
                String senha = scan.nextLine();
                if (listaContas.get(numConta - 1).getSenha().equals(senha)) {
                    int opcao = 0;
                    while (opcao != 6) {
                        System.out.println("----------------------------------------");
                        System.out.println("Bem-vindo(a), " + conta.getTitular().getNome() + "!");
                        System.out.println("Opções: ");
                        System.out.println("1 - Ver extrato");
                        System.out.println("2 - Sacar");
                        System.out.println("3 - Depositar");
                        System.out.println("4 - Transferir");
                        System.out.println("5 - Mudar senha");
                        System.out.println("6 - Voltar ao menu inicial");
                        System.out.print("O que você gostaria de fazer? ");
                        opcao = scan.nextInt();
                        scan.nextLine();
                        switch (opcao) {
                            case 1:
                                conta.mostrarExtrato();
                                travarTela();
                                break;
                            case 2:
                                System.out.print("Quanto você deseja sacar? R$");
                                double saque = scan.nextDouble();
                                conta.sacar(saque);
                                scan.nextLine();
                                travarTela();
                                break;
                            case 3:
                                System.out.print("Quanto você deseja depositar? R$");
                                double deposito = scan.nextDouble();
                                conta.depositar(deposito);
                                scan.nextLine();
                                travarTela();
                                break;
                            case 4:
                                System.out.println("Para quem você quer transferir? ");
                                exibirContas();
                                System.out.print("Informe o número da conta de destino: ");
                                int destino = scan.nextInt();
                                while (destino == conta.getNumConta()) {
                                    System.out.println("Você não pode transferir para si mesmo(a).");
                                    System.out.print("Informe o número da conta de destino: ");
                                    destino = scan.nextInt();
                                }
                                System.out.print("Quanto você quer transferir? R$");
                                double transferencia = scan.nextDouble();
                                conta.transferir(transferencia, listaContas.get(destino - 1));
                                scan.nextLine();
                                travarTela();
                                break;
                            case 5:
                                System.out.print("Informe a nova senha: ");
                                String novaSenha = scan.nextLine();
                                conta.mudarSenha(novaSenha);

                                travarTela();
                                break;
                            default:
                                System.out.println("Opção indisponível!");
                        }
                    }
                } else {
                    System.out.println("Senha incorreta.");
                }
            } else {
                System.out.println("Conta não existe.");
            }
        }

    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void travarTela() {
        String travaTela;
        System.out.println("Aperte ENTER para voltar");
        travaTela = scan.nextLine();
    }
}
