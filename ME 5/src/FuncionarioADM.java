
public class FuncionarioADM extends Funcionario {

    public FuncionarioADM(
            String nome,
            int idade,
            double salario,
            String departamento
    ) {
        super(nome, idade, salario, departamento);
    }

    @Override
    public String getCargo() {
        return "Administrativo";
    }
}
