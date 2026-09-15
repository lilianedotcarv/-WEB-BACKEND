import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try {

            listaFuncionarios.add(
                    new FuncionarioADM(
                            "Ana Silva",
                            28,
                            3500.00,
                            "Financeiro"
                    )
            );

            listaFuncionarios.add(
                    new FuncionarioTecnico(
                            "Carlos Oliveira",
                            35,
                            5200.00,
                            "TI",
                            "Backend"
                    )
            );

            listaFuncionarios.add(
                    new FuncionarioADM(
                            "Mariana Costa",
                            31,
                            4100.00,
                            "RH"
                    )
            );

            listaFuncionarios.add(
                    new FuncionarioTecnico(
                            "Beatriz Lima",
                            26,
                            4800.00,
                            "TI",
                            "Infraestrutura"
                    )
            );

        } catch (IllegalArgumentException erro) {

            System.out.println("Erro ao cadastrar funcionário: "
                    + erro.getMessage());
        }

        System.out.print("Digite a idade do funcionário: ");

        int idadeBuscada;

        try {
            idadeBuscada = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException erro) {

            System.out.println("Erro: digite uma idade válida.");
            scanner.close();
            return;
        }

        Funcionario funcionarioEncontrado = null;

        for (Funcionario funcionario : listaFuncionarios) {

            if (funcionario.getIdade() == idadeBuscada) {
                funcionarioEncontrado = funcionario;
                break;
            }
        }

        if (funcionarioEncontrado != null) {

            System.out.println("\n--- Registro de Empregado! ---");

            funcionarioEncontrado.exibirDados();

        } else {

            System.out.println(
                    "\nNenhum funcionário encontrado com a idade: "
                            + idadeBuscada
            );
        }

        scanner.close();
    }
}

