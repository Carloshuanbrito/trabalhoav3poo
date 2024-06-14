package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        executarMenuPrincipal();
    }

    private static void executarMenuPrincipal() {
        Funcionario funcionario = new Funcionario();
        Categoria categoria = new Categoria();
        CategoriaItem categoriaItem = new CategoriaItem();
        Consumo consumo = new Consumo();
        ConsumoServico consumoServico = new ConsumoServico();
        Servico servico = new Servico();
        Hospede hospede = new Hospede();
        Item item = new Item();
        Quarto quarto = new Quarto();
        Reserva reserva = new Reserva();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1 - Funcionario");
            System.out.println("2 - Categoria");
            System.out.println("3 - CategoriaItem");
            System.out.println("4 - Item");
            System.out.println("5 - Hospede");
            System.out.println("6 - Quarto");
            System.out.println("7 - Serviço");
            System.out.println("8 - Consumo");
            System.out.println("9 - ConsumoServiço");
            System.out.println("10 - Reserva");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    menuFuncionario(scanner, funcionario);
                    break;
                case 2:
                    menuCategoria(scanner, categoria);
                    break;
                case 3:
                    menuCategoriaItem(scanner, categoriaItem);
                    break;
                case 4:
                    menuItem(scanner, item);
                    break;
                case 5:
                    menuHospede(scanner, hospede);
                    break;
                case 6:
                    menuQuarto(scanner, quarto, categoria);
                    break;
                case 7:
                    menuServico(scanner, servico);
                    break;
                case 8:
                    menuConsumo(scanner, consumo);
                    break;
                case 9:
                    menuConsumoServico(scanner, consumoServico);
                    break;
                case 10:
                    menuReserva(scanner, reserva);
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
        System.out.println(" ");
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
        System.out.println(" ");
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
            System.out.println(" ");
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }
    }

    private static void editarFuncionario(Scanner scanner, Funcionario funcionario) {
        System.out.println(" ");
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
                System.out.println(" ");
                System.out.println("Funcionário editado com sucesso!");
            } else {
                System.out.println("Erro ao editar funcionário.");
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void consultarFuncionario(Scanner scanner, Funcionario funcionario) {
        System.out.println(" ");
        System.out.println("Consultar Funcionário");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Funcionario funcionarioParaConsultar = new Funcionario();
        funcionarioParaConsultar.setCpf(cpf);

        Funcionario encontrado = funcionario.consultar(funcionarioParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("Listagem de Funcionários:");
        ArrayList<Funcionario> funcionarios = funcionario.listar();
        for (Funcionario f : funcionarios) {
            System.out.println(f);
            System.out.println("---------------------------");
        }
    }

    // Menu Categoria e suas Funções

    private static void menuCategoria(Scanner scanner, Categoria categoria) {
        int opcao;
        System.out.println(" ");
        do {
            System.out.println("Menu Funcionario:");
            System.out.println("1 - Cadastrar Categoria");
            System.out.println("2 - Editar Categoria");
            System.out.println("3 - Consultar Categoria");
            System.out.println("4 - Listar Categoria");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarCategoria(scanner, categoria);
                    break;
                case 2:
                    editarCategoria(scanner, categoria);
                    break;
                case 3:
                    consultarCategoria(scanner, categoria);
                    break;
                case 4:
                    listarCategoria(scanner, categoria);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCategoria(Scanner scanner, Categoria categoria) {
        System.out.println(" ");
        System.out.println("Cadastrar Categoria");
        System.out.print("Codigo: ");
        int cod = scanner.nextInt();
        System.out.print("Descrição: ");
        String desc = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();

        Categoria novaCategoria = new Categoria();
        novaCategoria.setCodigo(cod);
        novaCategoria.setDescricao(desc);
        novaCategoria.setValor(valor);

        if (categoria.cadastrar(novaCategoria)) {
            System.out.println(" ");
            System.out.println("Categoria cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar Categoria.");
        }
    }

    private static void editarCategoria(Scanner scanner, Categoria categoria) {
        System.out.println(" ");
        System.out.println("Editar Categoria");
        System.out.print("Codigo da Categoria a editar: ");
        int cod = scanner.nextInt();

        Categoria categoriaParaEditar = new Categoria();
        categoriaParaEditar.setCodigo(cod);

        Categoria encontrado = categoria.consultar(categoriaParaEditar);

        if (encontrado != null) {
            System.out.print("Novo Codigo: ");
            int codigo = scanner.nextInt();
            System.out.print("Nova Descrição: ");
            String desc = scanner.next();
            System.out.print("Novo Valor: ");
            double novoValor = scanner.nextDouble();

            encontrado.setCodigo(codigo);
            encontrado.setDescricao(desc);
            encontrado.setValor(novoValor);

            if (categoria.editar(encontrado)) {
                System.out.println(" ");
                System.out.println("Categoria editada com sucesso!");
            } else {
                System.out.println("Erro ao editar Categoria.");
            }
        } else {
            System.out.println("Categoria não encontrado.");
        }

    }

    private static void consultarCategoria(Scanner scanner, Categoria categoria) {
        System.out.println(" ");
        System.out.println("Consultar Categoria");
        System.out.print("Codigo: ");
        int codigo = scanner.nextInt();

        Categoria categoriaParaConsultar = new Categoria();
        categoriaParaConsultar.setCodigo(codigo);

        Categoria encontrado = categoria.consultar(categoriaParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.println("Consulta de Categoria:");
            System.out.println("Codigo: " + encontrado.getCodigo());
            System.out.println("Descrição: " + encontrado.getDescricao());
            System.out.println("Valor: " + encontrado.getValor());

        } else {
            System.out.println("Categoria não encontrado.");
        }
    }

    private static void listarCategoria(Scanner scanner, Categoria categoria) {
        System.out.println(" ");
        System.out.println("Listagem de Categoria:");
        ArrayList<Categoria> categorias = categoria.listar();
        for (Categoria c : categorias) {
            System.out.println(c);
            System.out.println("---------------------------");
        }
    }

    // Menu CategoriaItem e suas Funções

    private static void menuCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
        int opcao;
        System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("Cadastrar CategoriaItem");
        System.out.print("Código do Item: ");
        int codigoItem = scanner.nextInt();
        System.out.print("Código da Categoria: ");
        int codigoCategoria = scanner.nextInt();

        Item item = new Item();
        item.setCodigo(codigoItem);

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        System.out.print("Quantidade do Item: ");
        int quantidade = scanner.nextInt();

        CategoriaItem novaCategoriaItem = new CategoriaItem();
        novaCategoriaItem.setItem(item);
        novaCategoriaItem.setCategoria(categoria);
        novaCategoriaItem.setQuantidade(quantidade);

        if (categoriaItem.cadastrar(novaCategoriaItem)) {
            System.out.println(" ");
            System.out.println("CategoriaItem cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar CategoriaItem.");
        }

    }

    private static void editarCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
        System.out.println("Editar CategoriaItem");

        System.out.print("Código do Item: ");
        int codigoItem = scanner.nextInt();
        System.out.print("Código da Categoria: ");
        int codigoCategoria = scanner.nextInt();

        Item item = new Item();
        item.setCodigo(codigoItem);

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        CategoriaItem categoriaItemParaEditar = new CategoriaItem();
        categoriaItemParaEditar.setItem(item);
        categoriaItemParaEditar.setCategoria(categoria);

        CategoriaItem encontrada = categoriaItem.consultar(categoriaItemParaEditar);

        if (encontrada != null) {
            System.out.println("CategoriaItem encontrado:");
            System.out.println("Item: " + encontrada.getItem().getCodigo());
            System.out.println("Categoria: " + encontrada.getCategoria().getCodigo());
            System.out.println("Quantidade atual: " + encontrada.getQuantidade());

            System.out.print("Nova Quantidade: ");
            int novaQuantidade = scanner.nextInt();

            encontrada.setQuantidade(novaQuantidade);

            if (categoriaItem.editar(encontrada)) {
                System.out.println("CategoriaItem editada com sucesso!");
            } else {
                System.out.println("Erro ao editar CategoriaItem.");
            }
        } else {
            System.out.println("CategoriaItem não encontrado.");
        }
    }

    private static void consultarCategoriaItem(Scanner scanner, CategoriaItem categoriaItem) {
        System.out.println("Consultar CategoriaItem");
        System.out.print("Código do Item: ");
        int codigoItem = scanner.nextInt();
        System.out.print("Código da Categoria: ");
        int codigoCategoria = scanner.nextInt();

        Item item = new Item();
        item.setCodigo(codigoItem);

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        CategoriaItem categoriaItemParaConsultar = new CategoriaItem();
        categoriaItemParaConsultar.setItem(item);
        categoriaItemParaConsultar.setCategoria(categoria);

        CategoriaItem encontrada = categoriaItem.consultar(categoriaItemParaConsultar);

        if (encontrada != null) {
            System.out.println("CategoriaItem encontrado:");
            System.out.println("Item: " + encontrada.getItem().getCodigo());
            System.out.println("Categoria: " + encontrada.getCategoria().getCodigo());
            System.out.println("Quantidade: " + encontrada.getQuantidade());
        } else {
            System.out.println("CategoriaItem não encontrado.");
        }
    }

    private static void listarCategoriaItems(CategoriaItem categoriaItem) {
        System.out.println("Listar CategoriaItens");

        ArrayList<CategoriaItem> listaCategoriaItens = categoriaItem.listar();

        if (!listaCategoriaItens.isEmpty()) {
            System.out.println("Lista de CategoriaItens:");
            for (CategoriaItem ci : listaCategoriaItens) {
                System.out.println("Item: " + ci.getItem().getCodigo());
                System.out.println("Categoria: " + ci.getCategoria().getCodigo());
                System.out.println("Quantidade: " + ci.getQuantidade());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Não há CategoriaItens cadastrados.");
        }
    }

    // Menu Item e suas Funções

    private static void menuItem(Scanner scanner, Item item) {
        int opcao;
        System.out.println(" ");
        do {
            System.out.println("Menu Item:");
            System.out.println("1 - Cadastrar Item");
            System.out.println("2 - Editar Item");
            System.out.println("3 - Consultar Item");
            System.out.println("4 - Listar Itens");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarItem(scanner, item);
                    break;
                case 2:
                    editarItem(scanner, item);
                    break;
                case 3:
                    consultarItem(scanner, item);
                    break;
                case 4:
                    listarItens(item);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarItem(Scanner scanner, Item item) {
        System.out.println(" ");
        System.out.println("Cadastrar Item");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();

        Item novoItem = new Item();
        novoItem.setCodigo(codigo);
        novoItem.setDescricao(descricao);
        novoItem.setValor(valor);

        if (item.cadastrar(novoItem)) {
            System.out.println(" ");
            System.out.println("Item cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar item.");
        }
    }

    private static void editarItem(Scanner scanner, Item item) {
        System.out.println(" ");
        System.out.println("Editar Item");
        System.out.print("Código do Item a editar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Item itemParaEditar = new Item();
        itemParaEditar.setCodigo(codigo);

        Item encontrado = item.consultar(itemParaEditar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.print("Nova Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Novo Valor: ");
            double valor = scanner.nextDouble();

            encontrado.setDescricao(descricao);
            encontrado.setValor(valor);

            if (item.editar(encontrado)) {
                System.out.println(" ");
                System.out.println("Item editado com sucesso!");
            } else {
                System.out.println("Erro ao editar item.");
            }
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private static void consultarItem(Scanner scanner, Item item) {
        System.out.println(" ");
        System.out.println("Consultar Item");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();

        Item itemParaConsultar = new Item();
        itemParaConsultar.setCodigo(codigo);

        Item encontrado = item.consultar(itemParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.println("Consulta de Item:");
            System.out.println("Código: " + encontrado.getCodigo());
            System.out.println("Descrição: " + encontrado.getDescricao());
            System.out.println("Valor: " + encontrado.getValor());
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private static void listarItens(Item item) {
        System.out.println(" ");
        System.out.println("Listagem de Itens:");
        ArrayList<Item> itens = item.listar();
        for (Item i : itens) {
            System.out.println(i);
            System.out.println("---------------------------");
        }
    }

    // Menu Hospede e suas Funções

    private static void menuHospede(Scanner scanner, Hospede hospede) {
        int opcao;
        System.out.println(" ");
        do {
            System.out.println("Menu Hospede:");
            System.out.println("1 - Cadastrar Hospede");
            System.out.println("2 - Editar Hospede");
            System.out.println("3 - Consultar Hospede");
            System.out.println("4 - Listar Hospedes");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarHospede(scanner, hospede);
                    break;
                case 2:
                    editarHospede(scanner, hospede);
                    break;
                case 3:
                    consultarHospede(scanner, hospede);
                    break;
                case 4:
                    listarHospedes(hospede);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void cadastrarHospede(Scanner scanner, Hospede hospede) {
        System.out.println(" ");
        System.out.println("Cadastrar Hospede");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço Completo: ");
        String enderecoCompleto = scanner.nextLine();

        Hospede novoHospede = new Hospede();
        novoHospede.setCpf(cpf);
        novoHospede.setNome(nome);
        novoHospede.setEmail(email);
        novoHospede.setEnderecoCompleto(enderecoCompleto);

        if (hospede.cadastrar(novoHospede)) {
            System.out.println(" ");
            System.out.println("Hospede cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar hospede.");
        }
    }

    private static void editarHospede(Scanner scanner, Hospede hospede) {
        System.out.println(" ");
        System.out.println("Editar Hospede");
        System.out.print("CPF do Hospede a editar: ");
        String cpf = scanner.nextLine();

        Hospede hospedeParaEditar = new Hospede();
        hospedeParaEditar.setCpf(cpf);

        Hospede encontrado = hospede.consultar(hospedeParaEditar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Email: ");
            String novoEmail = scanner.nextLine();
            System.out.print("Novo Endereço Completo: ");
            String novoEndereco = scanner.nextLine();

            encontrado.setNome(nome);
            encontrado.setEmail(novoEmail);
            encontrado.setEnderecoCompleto(novoEndereco);

            if (hospede.editar(encontrado)) {
                System.out.println(" ");
                System.out.println("Hospede editado com sucesso!");
            } else {
                System.out.println("Erro ao editar hospede.");
            }
        } else {
            System.out.println("Hospede não encontrado.");
        }
    }

    private static void consultarHospede(Scanner scanner, Hospede hospede) {
        System.out.println(" ");
        System.out.println("Consultar Hospede");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Hospede hospedeParaConsultar = new Hospede();
        hospedeParaConsultar.setCpf(cpf);

        Hospede encontrado = hospede.consultar(hospedeParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.println("Consulta de Hospede:");
            System.out.println("CPF: " + encontrado.getCpf());
            System.out.println("Nome: " + encontrado.getNome());
            System.out.println("Email: " + encontrado.getEmail());
            System.out.println("Endereço Completo: " + encontrado.getEnderecoCompleto());
        } else {
            System.out.println("Hospede não encontrado.");
        }
    }

    private static void listarHospedes(Hospede hospede) {
        System.out.println(" ");
        System.out.println("Listagem de Hospedes:");
        ArrayList<Hospede> hospedes = hospede.listar();
        for (Hospede h : hospedes) {
            System.out.println(h);
            System.out.println("---------------------------");
        }
    }

    // Menu Quarto e suas Funções

    private static void menuQuarto(Scanner scanner, Quarto quarto, Categoria categoria) {
        int opcao;
        System.out.println(" ");
        do {
            System.out.println("Menu Quarto:");
            System.out.println("1 - Cadastrar Quarto");
            System.out.println("2 - Editar Quarto");
            System.out.println("3 - Consultar Quarto");
            System.out.println("4 - Listar Quartos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarQuarto(scanner, quarto);
                    break;
                case 2:
                    editarQuarto(scanner, quarto);
                    break;
                case 3:
                    consultarQuarto(scanner, quarto);
                    break;
                case 4:
                    listarQuartos(quarto);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void cadastrarQuarto(Scanner scanner, Quarto quarto) {
        System.out.println(" ");
        System.out.println("Cadastrar Quarto");

        System.out.print("Código Quarto: ");
        int codigo = scanner.nextInt();

        System.out.println("Cadastrar Categoria");
        System.out.print("Codigo Categoria: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir nova linha

        Categoria novaCategoria = new Categoria();
        novaCategoria.setCodigo(cod);
        novaCategoria.setDescricao(desc);
        novaCategoria.setValor(valor);

        System.out.print("Status: ");
        String status = scanner.nextLine();

        Quarto novoQuarto = new Quarto();
        novoQuarto.setCodigo(codigo);
        novoQuarto.setCategoria(novaCategoria);
        novoQuarto.setStatus(status);

        if (quarto.cadastrar(novoQuarto)) {
            System.out.println(" ");
            System.out.println("Quarto cadastrado com sucesso!");
            System.out.println(novoQuarto);
        } else {
            System.out.println("Erro ao cadastrar quarto.");
        }
    }

    private static void editarQuarto(Scanner scanner, Quarto quarto) {
        System.out.println(" ");
        System.out.println("Editar Quarto");
        System.out.print("Código do Quarto a editar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Quarto quartoParaEditar = new Quarto();
        quartoParaEditar.setCodigo(codigo);

        Quarto encontrado = quarto.consultar(quartoParaEditar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.print("Novo Status: ");
            String status = scanner.nextLine();

            encontrado.setStatus(status);

            if (quarto.editar(encontrado)) {
                System.out.println(" ");
                System.out.println("Quarto editado com sucesso!");
            } else {
                System.out.println("Erro ao editar quarto.");
            }
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    private static void consultarQuarto(Scanner scanner, Quarto quarto) {
        System.out.println(" ");
        System.out.println("Consultar Quarto");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();

        Quarto quartoParaConsultar = new Quarto();
        quartoParaConsultar.setCodigo(codigo);

        Quarto encontrado = quarto.consultar(quartoParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.println("Consulta de Quarto:");
            System.out.println("Código Quarto: " + encontrado.getCodigo());
            System.out.println("Categoria: " + encontrado.getCategoria().getDescricao());
            System.out.println("Status do Quarto: " + encontrado.getStatus());
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    private static void listarQuartos(Quarto quarto) {
        System.out.println(" ");
        System.out.println("Listagem de Quartos:");
        ArrayList<Quarto> quartos = quarto.listar();
        for (Quarto q : quartos) {
            System.out.println("Código Quarto: " + q.getCodigo());
            System.out.println("Categoria: " + q.getCategoria().getDescricao());
            System.out.println("Status: " + q.getStatus());
            System.out.println("---------------------------");
        }
    }

    // Menu Serviço e suas Funções
    private static void menuServico(Scanner scanner, Servico servico) {
        int opcao;
        System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("Cadastrar Servico");
        System.out.print("Codigo do Serviço:");
        int codigo = scanner.nextInt();
        System.out.print("Descrição: ");
        String descricao = scanner.next();
        System.out.print("Valor do Serviço: ");
        double valor = scanner.nextDouble();

        Servico servico2 = new Servico();
        servico2.setCodigo(codigo);
        servico2.setDescricao(descricao);
        servico2.setValor(valor);

        if (servico.cadastrar(servico2)) {
            System.out.println(" ");
            System.out.println("Serviço cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar serviço.");
        }

        System.out.println(servico2);
    }

    private static void editarServico(Scanner scanner, Servico servico) {
        System.out.println(" ");
        System.out.println("Editar Serviço");
        System.out.print("Código Atual: ");
        int codigoAtual = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha após nextInt()

        if (servico.getCodigo() == codigoAtual) {
            System.out.println(" ");
            System.out.print("Novo Código: ");
            int novoCodigo = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha após nextInt()

            System.out.print("Nova Descrição: ");
            String novaDescricao = scanner.nextLine();

            System.out.print("Novo Valor: ");
            double novoValor = scanner.nextDouble();

            servico.setCodigo(novoCodigo);
            servico.setDescricao(novaDescricao);
            servico.setValor(novoValor);

            System.out.println("Serviço editado com sucesso: " + servico);
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

    private static void consultarServico(Scanner scanner, Servico servico) {
        System.out.println(" ");
        System.out.println("Consultar Serviço");
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha após nextInt()

        Servico servicoParaConsultar = new Servico();
        servicoParaConsultar.setCodigo(codigo);

        Servico encontrado = servico.consultar(servicoParaConsultar);

        if (encontrado != null) {
            System.out.println(" ");
            System.out.println("Consulta de Serviço:");
            System.out.println("Código: " + encontrado.getCodigo());
            System.out.println("Descrição: " + encontrado.getDescricao());
            System.out.println("Valor: " + encontrado.getValor());
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

    private static void listarServicos(Servico servico) {
        System.out.println(" ");
        System.out.println("Listagem de Serviços:");
        ArrayList<Servico> servicos = servico.listar();
        for (Servico s : servicos) {
            System.out.println(s);
            System.out.println("---------------------------");
        }
    }

    // Menu Consumo e suas Funções

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
        System.out.print("Quantidade Solicitada: ");
        int quantidadeSolicitada = scanner.nextInt();

        System.out.print("Data do Consumo (dd/MM/yyyy): ");
        String dataConsumo = scanner.next();
        Date dataConsumoDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataConsumoDate = sdf.parse(dataConsumo);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        Consumo consumo2 = new Consumo();
        consumo2.setDescricao(descricao);
        consumo2.setQuantidadeSolicitada(quantidadeSolicitada);
        consumo2.setDataConsumo(dataConsumoDate);

        if (consumo.cadastrar(consumo2)) {
            System.out.println("Consumo cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar consumo.");
        }
    }

    private static void editarConsumo(Scanner scanner, Consumo consumo) {
        System.out.println("Editar Consumo");
        System.out.print("Descrição Atual: ");
        String descricaoAtual = scanner.nextLine();

        Consumo consumoParaEditar = new Consumo();
        consumoParaEditar.setDescricao(descricaoAtual);

        Consumo encontrado = consumo.consultar(consumoParaEditar);

        if (encontrado != null) {
            System.out.print("Nova Descrição: ");
            String novaDescricao = scanner.nextLine();
            encontrado.setDescricao(novaDescricao);

            System.out.print("Nova Quantidade Solicitada: ");
            int novaQuantidade = scanner.nextInt();
            encontrado.setQuantidadeSolicitada(novaQuantidade);

            System.out.print("Nova Data do Consumo (dd/MM/yyyy): ");
            String novaDataConsumo = scanner.next();
            Date novaDataConsumoDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                novaDataConsumoDate = sdf.parse(novaDataConsumo);
                encontrado.setDataConsumo(novaDataConsumoDate);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            if (consumo.editar(encontrado)) {
                System.out.println("Consumo editado com sucesso!");
            } else {
                System.out.println("Erro ao editar consumo.");
            }
        } else {
            System.out.println("Consumo não encontrado.");
        }
    }

    private static void consultarConsumo(Scanner scanner, Consumo consumo) {
        System.out.println("Consultar Consumo");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Consumo consumoParaConsultar = new Consumo();
        consumoParaConsultar.setDescricao(descricao);

        Consumo encontrado = consumo.consultar(consumoParaConsultar);

        if (encontrado != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Consumo encontrado:");
            System.out.println("Descrição: " + encontrado.getDescricao());
            System.out.println("Quantidade Solicitada: " + encontrado.getQuantidadeSolicitada());
            System.out.println("Data de Consumo: " + sdf.format(encontrado.getDataConsumo()));
        } else {
            System.out.println("Consumo não encontrado.");
        }
    }

    private static void listarConsumos(Consumo consumo) {
        System.out.println("Listar Consumos");
        ArrayList<Consumo> consumos = consumo.listar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Consumo c : consumos) {
            System.out.println("Descrição: " + c.getDescricao());
            System.out.println("Quantidade Solicitada: " + c.getQuantidadeSolicitada());
            System.out.println("Data de Consumo: " + sdf.format(c.getDataConsumo()));
            System.out.println("---------------------------");
        }
    }

    // Menu ConsumoServiço e suas Funções

    private static void menuConsumoServico(Scanner scanner, ConsumoServico consumoServico) {
        int opcao;
        do {
            System.out.println("Menu Reserva:");
            System.out.println("1 - Cadastrar ConsumoServiço");
            System.out.println("2 - Editar ConsumoServiço");
            System.out.println("3 - Consultar ConsumoServiço");
            System.out.println("4 - Listar ConsumoServiço");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarConsumoServico(scanner, consumoServico);
                    break;
                case 2:
                    editarConsumoServico(scanner, consumoServico);
                    break;
                case 3:
                    consultarConsumoServico(scanner, consumoServico);
                    break;
                case 4:
                    listarConsumosServico(consumoServico);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarConsumoServico(Scanner scanner, ConsumoServico consumoServico) {
        System.out.println("Cadastrar Consumo Serviço");

        System.out.print("Código do Serviço: ");
        int codigoServico = scanner.nextInt();

        System.out.print("Código da Reserva: ");
        int codigoReserva = scanner.nextInt();

        System.out.print("Quantidade Solicitada: ");
        int quantidadeSolicitada = scanner.nextInt();

        System.out.print("Data do Serviço (dd/MM/yyyy): ");
        String dataServico = scanner.next();
        Date dataServicoDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataServicoDate = sdf.parse(dataServico);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        ConsumoServico novoConsumoServico = new ConsumoServico();
        Servico servico = new Servico();
        servico.setCodigo(codigoServico);
        novoConsumoServico.setServico(servico);

        Reserva reserva = new Reserva();
        reserva.setCodigo(codigoReserva);
        novoConsumoServico.setReserva(reserva);

        novoConsumoServico.setQuantidadeSolicitada(quantidadeSolicitada);
        novoConsumoServico.setDataServico(dataServicoDate);

        if (consumoServico.cadastrar(novoConsumoServico)) {
            System.out.println("Consumo serviço cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar consumo serviço.");
        }
    }

    private static void editarConsumoServico(Scanner scanner, ConsumoServico consumoServico) {
        System.out.println("Editar Consumo Serviço");

        System.out.print("Código do Serviço: ");
        int codigoServico = scanner.nextInt();

        System.out.print("Código da Reserva: ");
        int codigoReserva = scanner.nextInt();

        ConsumoServico consumoServicoParaEditar = new ConsumoServico();
        Servico servico = new Servico();
        servico.setCodigo(codigoServico);
        consumoServicoParaEditar.setServico(servico);

        Reserva reserva = new Reserva();
        reserva.setCodigo(codigoReserva);
        consumoServicoParaEditar.setReserva(reserva);

        ConsumoServico encontrado = consumoServico.consultar(consumoServicoParaEditar);

        if (encontrado != null) {
            System.out.print("Nova Quantidade Solicitada: ");
            int novaQuantidadeSolicitada = scanner.nextInt();

            System.out.print("Nova Data do Serviço (dd/MM/yyyy): ");
            String novaDataServico = scanner.next();
            Date novaDataServicoDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                novaDataServicoDate = sdf.parse(novaDataServico);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            encontrado.setQuantidadeSolicitada(novaQuantidadeSolicitada);
            encontrado.setDataServico(novaDataServicoDate);

            if (consumoServico.editar(encontrado)) {
                System.out.println("Consumo serviço editado com sucesso!");
            } else {
                System.out.println("Erro ao editar consumo serviço.");
            }
        } else {
            System.out.println("Consumo serviço não encontrado.");
        }
    }

    private static void consultarConsumoServico(Scanner scanner, ConsumoServico consumoServico) {
        System.out.println("Consultar Consumo Serviço");

        System.out.print("Código do Serviço: ");
        int codigoServico = scanner.nextInt();

        System.out.print("Código da Reserva: ");
        int codigoReserva = scanner.nextInt();

        ConsumoServico consumoServicoParaConsultar = new ConsumoServico();
        Servico servico = new Servico();
        servico.setCodigo(codigoServico);
        consumoServicoParaConsultar.setServico(servico);

        Reserva reserva = new Reserva();
        reserva.setCodigo(codigoReserva);
        consumoServicoParaConsultar.setReserva(reserva);

        ConsumoServico encontrado = consumoServico.consultar(consumoServicoParaConsultar);

        if (encontrado != null) {
            System.out.println("Consumo Serviço Encontrado: " + encontrado);
        } else {
            System.out.println("Consumo serviço não encontrado.");
        }
    }

    private static void listarConsumosServico(ConsumoServico consumoServico) {
        System.out.println("Listar Consumos Serviço");

        ArrayList<ConsumoServico> consumosServico = consumoServico.listar();
        for (ConsumoServico cs : consumosServico) {
            System.out.println(cs);
        }
    }

    // Menu Reserva e suas Funções

    private static void menuReserva(Scanner scanner, Reserva reserva) {
        int opcao;
        do {
            System.out.println("Menu Reserva:");
            System.out.println("1 - Cadastrar Reserva");
            System.out.println("2 - Editar Reserva");
            System.out.println("3 - Consultar Reserva");
            System.out.println("4 - Listar Reservas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarReserva(scanner, reserva);
                    break;
                case 2:
                    editarReserva(scanner, reserva);
                    break;
                case 3:
                    consultarReserva(scanner, reserva);
                    break;
                case 4:
                    listarReservas(reserva);
                    break;
                case 5:
                    pagarReserva(scanner, reserva);
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarReserva(Scanner scanner, Reserva reserva) {
        System.out.println("Cadastrar Reserva");

        System.out.print("Código da Reserva: ");
        int codigo = scanner.nextInt();

        System.out.print("CPF do Hóspede: ");
        String cpfHospede = scanner.next();

        System.out.print("Código do Quarto: ");
        int codigoQuarto = scanner.nextInt();

        System.out.print("CPF do Funcionário que realizou a reserva: ");
        String cpfFuncionarioReserva = scanner.next();

        System.out.print("CPF do Funcionário que fechou a reserva: ");
        String cpfFuncionarioFechamento = scanner.next();

        System.out.print("Data de Entrada da Reserva (dd/MM/yyyy): ");
        String dataEntrada = scanner.next();
        Date dataEntradaDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataEntradaDate = sdf.parse(dataEntrada);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        System.out.print("Data de Saída da Reserva (dd/MM/yyyy): ");
        String dataSaida = scanner.next();
        Date dataSaidaDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataSaidaDate = sdf.parse(dataSaida);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        System.out.print("Data de Check-in (dd/MM/yyyy): ");
        String dataCheckin = scanner.next();
        Date dataCheckinDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataCheckinDate = sdf.parse(dataCheckin);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        System.out.print("Data de Check-out (dd/MM/yyyy): ");
        String dataCheckout = scanner.next();
        Date dataCheckoutDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataCheckoutDate = sdf.parse(dataCheckout);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        System.out.print("Valor da Reserva: ");
        double valorReserva = scanner.nextDouble();

        System.out.print("Valor Pago: ");
        double valorPago = scanner.nextDouble();

        Reserva novaReserva = new Reserva();
        novaReserva.setCodigo(codigo);

        Hospede hospede = new Hospede();
        hospede.setCpf(cpfHospede);
        novaReserva.setHospede(hospede);

        Quarto quarto = new Quarto();
        quarto.setCodigo(codigoQuarto);
        novaReserva.setQuarto(quarto);

        Funcionario funcionarioReserva = new Funcionario();
        funcionarioReserva.setCpf(cpfFuncionarioReserva);
        novaReserva.setFuncionarioReserva(funcionarioReserva);

        Funcionario funcionarioFechamento = new Funcionario();
        funcionarioFechamento.setCpf(cpfFuncionarioFechamento);
        novaReserva.setFuncionarioFechamento(funcionarioFechamento);

        novaReserva.setDataEntradaReserva(dataEntradaDate);
        novaReserva.setDataSaidaReserva(dataSaidaDate);
        novaReserva.setDataCheckin(dataCheckinDate);
        novaReserva.setDataCheckout(dataCheckoutDate);
        novaReserva.setValorReserva(valorReserva);
        novaReserva.setValorPago(valorPago);

        if (reserva.cadastrar(novaReserva)) {
            System.out.println("Reserva cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar reserva.");
        }
    }

    private static void editarReserva(Scanner scanner, Reserva reserva) {
        System.out.println("Editar Reserva");

        System.out.print("Código da Reserva: ");
        int codigo = scanner.nextInt();

        Reserva reservaParaEditar = new Reserva();
        reservaParaEditar.setCodigo(codigo);

        Reserva encontrada = reserva.consultar(reservaParaEditar);

        if (encontrada != null) {
            System.out.print("Novo CPF do Hóspede: ");
            String cpfHospede = scanner.next();

            System.out.print("Novo Código do Quarto: ");
            int codigoQuarto = scanner.nextInt();

            System.out.print("Novo CPF do Funcionário que realizou a reserva: ");
            String cpfFuncionarioReserva = scanner.next();

            System.out.print("Novo CPF do Funcionário que fechou a reserva: ");
            String cpfFuncionarioFechamento = scanner.next();

            System.out.print("Nova Data de Entrada da Reserva (dd/MM/yyyy): ");
            String dataEntrada = scanner.next();
            Date dataEntradaDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataEntradaDate = sdf.parse(dataEntrada);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            System.out.print("Nova Data de Saída da Reserva (dd/MM/yyyy): ");
            String dataSaida = scanner.next();
            Date dataSaidaDate = null;
            try {
               
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataSaidaDate = sdf.parse(dataSaida);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            System.out.print("Nova Data de Check-in (dd/MM/yyyy): ");
            String dataCheckin = scanner.next();
            Date dataCheckinDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataCheckinDate = sdf.parse(dataCheckin);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            System.out.print("Nova Data de Check-out (dd/MM/yyyy): ");
            String dataCheckout = scanner.next();
            Date dataCheckoutDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dataCheckoutDate = sdf.parse(dataCheckout);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            System.out.print("Novo Valor da Reserva: ");
            double valorReserva = scanner.nextDouble();

            System.out.print("Novo Valor Pago: ");
            double valorPago = scanner.nextDouble();

            Hospede hospede = new Hospede();
            hospede.setCpf(cpfHospede);
            encontrada.setHospede(hospede);

            Quarto quarto = new Quarto();
            quarto.setCodigo(codigoQuarto);
            encontrada.setQuarto(quarto);

            Funcionario funcionarioReserva = new Funcionario();
            funcionarioReserva.setCpf(cpfFuncionarioReserva);
            encontrada.setFuncionarioReserva(funcionarioReserva);

            Funcionario funcionarioFechamento = new Funcionario();
            funcionarioFechamento.setCpf(cpfFuncionarioFechamento);
            encontrada.setFuncionarioFechamento(funcionarioFechamento);

            encontrada.setDataEntradaReserva(dataEntradaDate);
            encontrada.setDataSaidaReserva(dataSaidaDate);
            encontrada.setDataCheckin(dataCheckinDate);
            encontrada.setDataCheckout(dataCheckoutDate);
            encontrada.setValorReserva(valorReserva);
            encontrada.setValorPago(valorPago);

            if (reserva.editar(encontrada)) {
                System.out.println("Reserva editada com sucesso!");
            } else {
                System.out.println("Erro ao editar reserva.");
            }
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private static void consultarReserva(Scanner scanner, Reserva reserva) {
        System.out.println("Consultar Reserva");

        System.out.print("Código da Reserva: ");
        int codigo = scanner.nextInt();

        Reserva reservaParaConsultar = new Reserva();
        reservaParaConsultar.setCodigo(codigo);

        Reserva encontrada = reserva.consultar(reservaParaConsultar);

        if (encontrada != null) {
            System.out.println("Reserva encontrada: " + encontrada);
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    private static void listarReservas(Reserva reserva) {
        System.out.println("Listar Reservas");

        for (Reserva r : reserva.listar()) {
            System.out.println(r);
        }
    }

    private static void pagarReserva(Scanner scanner, Reserva reserva) {
        System.out.println("Pagar Reserva");

        System.out.print("Código da Reserva: ");
        int codigo = scanner.nextInt();

        reserva.pagarReserva(codigo);
        System.out.println("Reserva paga com sucesso!");
    }

}