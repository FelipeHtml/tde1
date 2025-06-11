package cadastro;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cadastroAutomovel cadastro = new cadastroAutomovel();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    if (cadastro.inserirAutomovel(new automovel(placa, modelo, marca, ano, valor))) {
                        System.out.println("Automóvel incluído com sucesso.");
                    } else {
                        System.out.println("Erro: Placa já cadastrada.");
                    }
                }
                case 2 -> {
                    System.out.print("Informe a placa: ");
                    String placa = sc.nextLine();
                    if (cadastro.removerAutomovel(placa)) {
                        System.out.println("Automóvel removido.");
                    } else {
                        System.out.println("Automóvel não encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Novo modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Nova marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Novo ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Novo valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    if (cadastro.alterarAutomovel(placa, modelo, marca, ano, valor)) {
                        System.out.println("Dados atualizados.");
                    } else {
                        System.out.println("Automóvel não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    automovel a = cadastro.buscarPorPlaca(placa);
                    System.out.println((a != null) ? a : "Automóvel não encontrado.");
                }
                case 5 -> {
                    System.out.print("Ordenar por (placa/modelo/marca): ");
                    String criterio = sc.nextLine();
                    for (automovel a : cadastro.listarOrdenado(criterio)) {
                        System.out.println(a);
                    }
                }
                case 6 -> {
                    cadastro.salvarNoArquivo();
                    System.out.println("Dados salvos. Encerrando...");
                }
                default -> {
                    if (opcao < 1 || opcao > 6) {
                        System.out.println("Opção inválida.");
                    }
                }
            }
        } while (opcao != 6);

        sc.close();
    }
} 