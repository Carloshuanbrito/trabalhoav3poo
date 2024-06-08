package app;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    	public static void main(String[] args) {
            executarMenuPrincipal();
        }

    	private static void executarMenuPrincipal() {
            Funcionario funcionario = new Funcionario();
            CategoriaItem categoriaItem = new CategoriaItem();
            Consumo consumo = new Consumo();
            Servico servico = new Servico();
            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("Menu:");
                System.out.println("1 - Funcionario");
                System.out.println("2 - CategoriaItem");
                System.out.println("3 - Consumo");
                System.out.println("4 - Servico");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        menuFuncionario(scanner, funcionario);
                        break;
                    case 2:
                        menuCategoriaItem(scanner, categoriaItem);
                        break;
                    case 3:
                        menuConsumo(scanner, consumo);
                        break;
                    case 4:
                        menuServico(scanner, servico);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);

            scanner.close();
        }

        private static void menuFuncionario(Scanner scanner, Funcionario funcionario) {
            int opcao;
            do {
                System.out.println("Menu Funcionario:");
                System.out.println("1 - Cadastrar Funcionário");
                System.out.println("2 - Editar Funcionário");
                System.out.println("3 - Consultar Funcionário");
                System.out.println("4 - Listar Funcionários");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        cadastrarFuncionario(scanner, funcionario);
                        break;
                    case 2:
                        editarFuncionario(scanner, funcionario);
                        break;
                    case 3:
                        consultarFuncionario(scanner, funcionario);
                        break;
                    case 4:
                        listarFuncionarios(funcionario);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }

        private static void cadastrarFuncionario(Scanner scanner, Funcionario funcionario) {
            System.out.println("Cadastrar Funcionário");
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Setor: ");
            String setor = scanner.nextLine();

            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setCpf(cpf);
            novoFuncionario.setNome(nome);
            novoFuncionario.setEmail(email);
            novoFuncionario.setSetor(setor);

            if (funcionario.cadastrar(novoFuncionario)) {
                System.out.println("Funcionário cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar funcionário.");
            }
        }

        private static void editarFuncionario(Scanner scanner, Funcionario funcionario) {
            System.out.println("Editar Funcionário");
            System.out.print("CPF do Funcionário a editar: ");
            String cpf = scanner.nextLine();

            Funcionario funcionarioParaEditar = new Funcionario();
            funcionarioParaEditar.setCpf(cpf);
            
            Funcionario encontrado = funcionario.consultar(funcionarioParaEditar);
            
            if (encontrado != null) {
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo Email: ");
                String novoEmail = scanner.nextLine();
                System.out.print("Novo Setor: ");
                String novoSetor = scanner.nextLine();

                encontrado.setNome(nome);
                encontrado.setEmail(novoEmail);
                encontrado.setSetor(novoSetor);

                if (funcionario.editar(encontrado)) {
                    System.out.println("Funcionário editado com sucesso!");
                } else {
                    System.out.println("Erro ao editar funcionário.");
                }
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        }

        private static void consultarFuncionario(Scanner scanner, Funcionario funcionario) {
            System.out.println("Consultar Funcionário");
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            Funcionario funcionarioParaConsultar = new Funcionario();
            funcionarioParaConsultar.setCpf(cpf);
            
            Funcionario encontrado = funcionario.consultar(funcionarioParaConsultar);
            
            if (encontrado != null) {
                System.out.println("Consulta de Funcionário:");
                System.out.println("CPF: " + encontrado.getCpf());
                System.out.println("Nome: " + encontrado.getNome());
                System.out.println("Email: " + encontrado.getEmail());
                System.out.println("Setor: " + encontrado.getSetor());
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        }

        private static void listarFuncionarios(Funcionario funcionario) {
            System.out.println("Listagem de Funcionários:");
            ArrayList<Funcionario> funcionarios = funcionario.listar();
            for (Funcionario f : funcionarios) {
                System.out.println(f);
                System.out.println("---------------------------");
            }
        }

        private static void menuCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
            int opcao;
            do {
                System.out.println("Menu CategoriaItem:");
                System.out.println("1 - Cadastrar CategoriaItem");
                System.out.println("2 - Editar CategoriaItem");
                System.out.println("3 - Consultar CategoriaItem");
                System.out.println("4 - Listar CategoriaItems");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        cadastrarCategoriaItem(scanner, categoriaItem);
                        break;
                    case 2:
                        editarCategoriaItem(scanner, categoriaItem);
                        break;
                    case 3:
                        consultarCategoriaItem(scanner, categoriaItem);
                        break;
                    case 4:
                        listarCategoriaItems(categoriaItem);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }

        private static void cadastrarCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
            System.out.println("Cadastrar CategoriaItem");
            System.out.print("Nome: ");
            String item = scanner.nextLine();
            categoriaItem.setItem(item);
            System.out.println("CategoriaItem cadastrado com sucesso: " + categoriaItem);
        }

        private static void editarCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
            System.out.println("Editar CategoriaItem");
            System.out.print("Nome Atual: ");
            String nomeAtual = scanner.nextLine();
            if (categoriaItem.getNome().equals(nomeAtual)) {
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine();
                categoriaItem.setNome(novoNome);
                System.out.println("CategoriaItem editado com sucesso: " + categoriaItem);
            } else {
                System.out.println("CategoriaItem não encontrado.");
            }
        }

        private static void consultarCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
            System.out.println("Consultar CategoriaItem");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            if (categoriaItem.getNome().equals(nome)) {
                System.out.println("CategoriaItem encontrado: " + categoriaItem);
            } else {
                System.out.println("CategoriaItem não encontrado.");
            }
        }

        private static void listarCategoriaItems(CategoriaItem categoriaItem) {
            System.out.println("CategoriaItem: " + categoriaItem);
        }

        private static void menuConsumo(Scanner scanner, Consumo consumo) {
            int opcao;
            do {
                System.out.println("Menu Consumo:");
                System.out.println("1 - Cadastrar Consumo");
                System.out.println("2 - Editar Consumo");
                System.out.println("3 - Consultar Consumo");
                System.out.println("4 - Listar Consumos");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        cadastrarConsumo(scanner, consumo);
                        break;
                    case 2:
                        editarConsumo(scanner, consumo);
                        break;
                    case 3:
                        consultarConsumo(scanner, consumo);
                        break;
                    case 4:
                        listarConsumos(consumo);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }

        private static void cadastrarConsumo(Scanner scanner, Consumo consumo) {
            System.out.println("Cadastrar Consumo");
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            consumo.setDescricao(descricao);
            System.out.println("Consumo cadastrado com sucesso: " + consumo);
        }

        private static void editarConsumo(Scanner scanner, Consumo consumo) {
            System.out.println("Editar Consumo");
            System.out.print("Descrição Atual: ");
            String descricaoAtual = scanner.nextLine();
            if (consumo.getDescricao().equals(descricaoAtual)) {
                System.out.print("Nova Descrição: ");
                String novaDescricao = scanner.nextLine();
                consumo.setDescricao(novaDescricao);
                System.out.println("Consumo editado com sucesso: " + consumo);
            } else {
                System.out.println("Consumo não encontrado.");
            }
        }

        private static void consultarConsumo(Scanner scanner, Consumo consumo) {
            System.out.println("Consultar Consumo");
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            if (consumo.getDescricao().equals(descricao)) {
                System.out.println("Consumo encontrado: " + consumo);
            } else {
                System.out.println("Consumo não encontrado.");
            }
        }

        private static void listarConsumos(Consumo consumo) {
            System.out.println("Consumo: " + consumo);
        }

        private static void menuServico(Scanner scanner, Servico servico) {
            int opcao;
            do {
                System.out.println("Menu Servico:");
                System.out.println("1 - Cadastrar Servico");
                System.out.println("2 - Editar Servico");
                System.out.println("3 - Consultar Servico");
                System.out.println("4 - Listar Servicos");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");
                
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        cadastrarServico(scanner, servico);
                        break;
                    case 2:
                        editarServico(scanner, servico);
                        break;
                    case 3:
                        consultarServico(scanner, servico);
                        break;
                    case 4:
                        listarServicos(servico);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }

        private static void cadastrarServico(Scanner scanner, Servico servico) {
            System.out.println("Cadastrar Servico");
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            servico.setDescricao(descricao);
            System.out.println("Servico cadastrado com sucesso: " + servico);
        }

        private static void editarServico(Scanner scanner, Servico servico) {
            System.out.println("Editar Servico");
            System.out.print("Descrição Atual: ");
            String descricaoAtual = scanner.nextLine();
            if (servico.getDescricao().equals(descricaoAtual)) {
                System.out.print("Nova Descrição: ");
                String novaDescricao = scanner.nextLine();
                servico.setDescricao(novaDescricao);
                System.out.println("Servico editado com sucesso: " + servico);
            } else {
                System.out.println("Servico não encontrado.");
            }
        }

        private static void consultarServico(Scanner scanner, Servico servico) {
            System.out.println("Consultar Servico");
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            if (servico.getDescricao().equals(descricao)) {
                System.out.println("Servico encontrado: " + servico);
            } else {
                System.out.println("Servico não encontrado.");
            }
        }

        private static void listarServicos(Servico servico) {
            System.out.println("Servico: " + servico);
        }
}