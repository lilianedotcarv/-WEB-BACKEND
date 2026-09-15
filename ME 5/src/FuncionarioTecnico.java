
public class FuncionarioTecnico extends Funcionario {

    private String especialidade;

    public FuncionarioTecnico(
            String nome,
            int idade,
            double salario,
            String departamento,
            String especialidade
    ) {
        super(nome, idade, salario, departamento);

        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A especialidade não pode estar vazia."
            );
        }

        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String getCargo() {
        return "Técnico";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();

        System.out.printf(
                "|Especialidade: %s%n",
                especialidade
        );
    }
}

