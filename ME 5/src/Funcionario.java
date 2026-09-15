
public abstract class Funcionario {

    private String nome;
    private int idade;
    private double salario;
    private String departamento;

    public Funcionario(String nome, int idade, double salario, String departamento) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        if (idade <= 0 || idade > 120) {
            throw new IllegalArgumentException("A idade deve estar entre 1 e 120 anos.");
        }

        if (salario <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que zero.");
        }

        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("O departamento não pode estar vazio.");
        }

        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public abstract String getCargo();

    public void exibirDados() {
        System.out.printf(
                "[%s]%n" +
                        "|Nome: %s%n" +
                        "|Idade: %d anos%n" +
                        "|Salário: R$ %.2f%n" +
                        "|Depto: %s%n",
                getCargo(),
                nome,
                idade,
                salario,
                departamento
        );
    }
}
