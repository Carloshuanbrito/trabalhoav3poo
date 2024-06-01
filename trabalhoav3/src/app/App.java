package app;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		int opcao = 0;
		
		while(true) {
			System.out.println("Escolha a opção abaixo: ");
			System.out.println("1 - Entra como Funcionario");
			System.out.println("2 - Entra como Hospede");
			
			
			opcao = scanner.nextInt();	
			scanner.nextLine();
			
			switch(opcao) {
				
			
			case 1:
				int escolhaF = 0;
				System.out.println("Você entrou como Funcionario... Segue as funções abaixo:");
				System.out.println(" 1 - Cadastrar Funcionario");
				System.out.println(" 2 - Editar Funcionario");
				System.out.println(" 3 - Consultar Funcionario;");
				System.out.println(" 4 - Listar Funcionario;");
				
				escolhaF = scanner.nextInt();
				scanner.nextLine();
				
				if(escolhaF == 1) {
					
				}
					
			break;
			
			case 2:
				int escolhaH = 0;
				System.out.println("Você entrou como Funcionario... Segue as funções abaixo:");
				System.out.println(" 1 - Cadastrar Funcionario");
				System.out.println(" 2 - Editar Funcionario");
				System.out.println(" 3 - Consultar Funcionario;");
				System.out.println(" 4 - Listar Funcionario;");
				
				escolhaH = scanner.nextInt();
				scanner.nextLine();
				
				if (escolhaH == 1) {
					
				}
				
				break;
			
			}
			
			
		}
			
	}

}
