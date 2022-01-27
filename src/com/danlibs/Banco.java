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
        System.out.println("Crie uma senha de 6 dígitos: ");
        String senha = scan.nextLine();
        while (!validaSenha(senha)) {
            System.out.println("Crie uma senha de 6 dígitos: ");
            senha = scan.nextLine();
        }
        System.out.println("CPF: ");
        String cpf = scan.nextLine();
        while (!validaCpf(cpf)) {
            System.out.println("CPF: ");
            cpf = scan.nextLine();
        }
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
        Conta conta = new Conta(new Cliente(nome, cpf, new Endereco(endereco, numero, complemento, bairro, cidade, estado, pais)), Conta.tipoDeConta.valueOf(strTipo.toUpperCase()), senha);
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
                        clearScreen();
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

    private boolean validaCpf(String cpf) {
        if (cpf.length() == 11) {
            for (int i = 0; i < cpf.length(); i++) {
                if (!Character.isDigit(cpf.charAt(i))) {
                    System.out.println("CPF inválido.");
                    return false;
                }
            }
            // Cada dígito do CPF
            int num1 = Integer.parseInt(String.valueOf(cpf.charAt(0)));
            int num2 = Integer.parseInt(String.valueOf(cpf.charAt(1)));
            int num3 = Integer.parseInt(String.valueOf(cpf.charAt(2)));
            int num4 = Integer.parseInt(String.valueOf(cpf.charAt(3)));
            int num5 = Integer.parseInt(String.valueOf(cpf.charAt(4)));
            int num6 = Integer.parseInt(String.valueOf(cpf.charAt(5)));
            int num7 = Integer.parseInt(String.valueOf(cpf.charAt(6)));
            int num8 = Integer.parseInt(String.valueOf(cpf.charAt(7)));
            int num9 = Integer.parseInt(String.valueOf(cpf.charAt(8)));
            int num10 = Integer.parseInt(String.valueOf(cpf.charAt(9)));
            int num11 = Integer.parseInt(String.valueOf(cpf.charAt(10)));

            if ((num1 == num2) && (num2 == num3) && (num3 == num4) && (num4 == num5) && (num5 == num6) && (num6 == num7) && (num7 == num8) && (num8 == num9) && (num9 == num10) && (num10 == num11)) {
                System.out.println("CPF inválido.");
                return false;
            }
            // Verificação do primeiro dos últimos dois dígitos
            int soma1 = num1 * 10 + num2 * 9 + num3 * 8 + num4 * 7 + num5 * 6 + num6 * 5 + num7 * 4 + num8 * 3 + num9 * 2;
            int resto1 = (soma1 * 10) % 11;
            if (resto1 == 10) resto1 = 0;
            if (resto1 != num10) {
                System.out.println("CPF inválido.");
                return false;
            } else {
                // Verificação do último digito
                int soma2 = num1 * 11 + num2 * 10 + num3 * 9 + num4 * 8 + num5 * 7 + num6 * 6 + num7 * 5 + num8 * 4 + num9 * 3 + num10 * 2;
                int resto2 = (soma2 * 10) % 11;
                if (resto2 == 10) resto2 = 0;
                if (resto2 == num11) return true;
            }
        } else {
            System.out.println("CPF inválido.");
            return false;
        }
        return false;
    }

    private boolean validaSenha(String senha) {
        if (senha.length() == 6) {
            for (int i = 0; i < senha.length(); i++) {
                if (!Character.isDigit(senha.charAt(i))) {
                    System.out.println("Senha inválida.");
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("Senha inválida.");
            return false;
        }
    }
}
