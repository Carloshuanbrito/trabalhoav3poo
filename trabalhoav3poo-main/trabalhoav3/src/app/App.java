package app;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();

        // Cadastrar funcionários
        cadastrarFuncionario(funcionario, "12345678901", "John Doe", "john@example.com", "HR");
        cadastrarFuncionario(funcionario, "98765432109", "Jane Smith", "jane@example.com", "Finance");
        
        // Listar funcionários cadastrados
        listarFuncionarios(funcionario);
        
        // Consultar um funcionário pelo CPF
        consultarFuncionario(funcionario, "12345678901");
        
        // Editar um funcionário
        editarFuncionario(funcionario, "12345678901", "John Doe", "john.doe@example.com", "IT");
        
        // Listar funcionários após a edição
        listarFuncionarios(funcionario);
    }

    private static void cadastrarFuncionario(Funcionario funcionario, String cpf, String nome, String email, String setor) {
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

    private static void editarFuncionario(Funcionario funcionario, String cpf, String nome, String novoEmail, String novoSetor) {
        Funcionario funcionarioParaEditar = new Funcionario();
        funcionarioParaEditar.setCpf(cpf);
        
        Funcionario encontrado = funcionario.consultar(funcionarioParaEditar);
        
        if (encontrado != null) {
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

    private static void consultarFuncionario(Funcionario funcionario, String cpf) {
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
            System.out.println("CPF: " + f.getCpf());
            System.out.println("Nome: " + f.getNome());
            System.out.println("Email: " + f.getEmail());
            System.out.println("Setor: " + f.getSetor());
            System.out.println("---------------------------");
        }
    }
}
