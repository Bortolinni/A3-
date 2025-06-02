import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ponto de entrada da aplicação.
 * Inicializa a execução do sistema de desempenho de alunos.
 */
public class SistemaDesempenhoAlunos {
    public static void main(String[] args) {
        new Aplicacao().executar();
    }
}

// ---------------- CLASSE DOMÍNIO ----------------

/**
 * Entidade que representa um aluno.
 */
class Aluno {
    private String nome;
    private int nota;
    private char sexo;

    public Aluno(String nome, int nota, char sexo) {
        this.nome = nome;
        this.nota = nota;
        this.sexo = sexo;
    }

    public String getNome() { return nome; }
    public int getNota() { return nota; }
    public char getSexo() { return sexo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setNota(int nota) { this.nota = nota; }
    public void setSexo(char sexo) { this.sexo = sexo; }

    public boolean estaAprovado() {
        return nota >= 60;
    }

    public String getSituacao() {
        return estaAprovado() ? "APROVADO" : "REPROVADO";
    }

    @Override
    public String toString() {
        return String.format("%s | Nota: %d | Sexo: %c | Situação: %s", nome, nota, sexo, getSituacao());
    }
}

// ---------------- CLASSE DE APLICAÇÃO ----------------

/**
 * Classe principal que gerencia a execução do menu e dados dos alunos.
 */
class Aplicacao {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private final Menu menu = new Menu(scanner, alunos);

    public void executar() {
        int opcao;
        do {
            menu.exibir();
            opcao = menu.lerOpcao();
            menu.executarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
        System.out.println("Programa encerrado.");
    }
}

// ---------------- CLASSE MENU ----------------

/**
 * Controlador de interface e opções disponíveis para o usuário.
 */
class Menu {
    private final Scanner scanner;
    private final ArrayList<Aluno> alunos;

    public Menu(Scanner scanner, ArrayList<Aluno> alunos) {
        this.scanner = scanner;
        this.alunos = alunos;
    }

    public void exibir() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Adicionar aluno");
        System.out.println("2. Listar alunos");
        System.out.println("3. Atualizar aluno");
        System.out.println("4. Remover aluno");
        System.out.println("5. Ver estatísticas");
        System.out.println("0. Sair");
    }

    public int lerOpcao() {
        return Util.lerInteiro(scanner, "Escolha uma opção: ");
    }

    public void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> adicionarAluno();
            case 2 -> listarAlunos();
            case 3 -> atualizarAluno();
            case 4 -> removerAluno();
            case 5 -> Estatisticas.exibir(alunos);
            case 0 -> {}
            default -> System.out.println("Opção inválida.");
        }
    }

    private void adicionarAluno() {
        String nome = Util.lerTexto(scanner, "Nome: ");
        int nota = Util.lerNota(scanner);
        char sexo = Util.lerSexo(scanner);

        alunos.add(new Aluno(nome, nota, sexo));
        System.out.println("Aluno adicionado com sucesso!");
    }

    private void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\nLISTA DE ALUNOS:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.printf("%d - %s%n", i, alunos.get(i));
        }
    }

    private void atualizarAluno() {
        listarAlunos();
        if (alunos.isEmpty()) return;

        int indice = Util.lerInteiro(scanner, "Número do aluno a atualizar: ");
        if (!Util.indiceValido(indice, alunos.size())) return;

        Aluno aluno = alunos.get(indice);

        String novoNome = Util.lerTexto(scanner, "Novo nome (" + aluno.getNome() + "): ", true);
        if (!novoNome.isEmpty()) aluno.setNome(novoNome);

        String novaNota = Util.lerTexto(scanner, "Nova nota (" + aluno.getNota() + "): ", true);
        if (!novaNota.isEmpty()) aluno.setNota(Integer.parseInt(novaNota));

        String novoSexo = Util.lerTexto(scanner, "Novo sexo (" + aluno.getSexo() + "): ", true);
        if (!novoSexo.isEmpty()) aluno.setSexo(novoSexo.toUpperCase().charAt(0));

        System.out.println("Aluno atualizado com sucesso!");
    }

    private void removerAluno() {
        listarAlunos();
        if (alunos.isEmpty()) return;

        int indice = Util.lerInteiro(scanner, "Número do aluno a remover: ");
        if (!Util.indiceValido(indice, alunos.size())) return;

        alunos.remove(indice);
        System.out.println("Aluno removido com sucesso!");
    }
}

// ---------------- CLASSE ESTATÍSTICAS ----------------

/**
 * Responsável por gerar e exibir estatísticas dos alunos.
 */
class Estatisticas {

    public static void exibir(ArrayList<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        int aprovadosM = 0, reprovadasF = 0, somaNotasF = 0, qtdF = 0, somaTotal = 0;

        for (Aluno aluno : alunos) {
            int nota = aluno.getNota();
            somaTotal += nota;

            if (aluno.getSexo() == 'F') {
                somaNotasF += nota;
                qtdF++;
                if (!aluno.estaAprovado()) reprovadasF++;
            } else if (aluno.getSexo() == 'M' && aluno.estaAprovado()) {
                aprovadosM++;
            }
        }

        double mediaF = qtdF > 0 ? (double) somaNotasF / qtdF : 0;
        double mediaGeral = (double) somaTotal / alunos.size();

        System.out.println("\n===== ESTATÍSTICAS =====");
        System.out.println("Aprovados do sexo masculino: " + aprovadosM);
        System.out.println("Reprovadas do sexo feminino: " + reprovadasF);
        System.out.printf("Média das notas femininas: %.2f%n", mediaF);
        System.out.printf("Média geral das notas: %.2f%n", mediaGeral);
    }
}

// ---------------- CLASSE UTIL ----------------

/**
 * Classe utilitária para entrada e validação.
 */
class Util {

    public static int lerNota(Scanner scanner) {
        int nota;
        do {
            nota = lerInteiro(scanner, "Nota (0 a 100): ");
            if (nota >= 0 && nota <= 100) return nota;
            System.out.println("Nota inválida.");
        } while (true);
    }

    public static char lerSexo(Scanner scanner) {
        char sexo;
        do {
            System.out.print("Sexo (M ou F): ");
            String entrada = scanner.nextLine().toUpperCase();
            if (entrada.length() == 1) {
                sexo = entrada.charAt(0);
                if (sexo == 'M' || sexo == 'F') return sexo;
            }
            System.out.println("Sexo inválido.");
        } while (true);
    }

    public static int lerInteiro(Scanner scanner, String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                return valor;
            } else {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next(); // limpar entrada inválida
            }
        }
    }

    public static String lerTexto(Scanner scanner, String mensagem, boolean podeSerVazio) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = scanner.nextLine();
            if (!texto.trim().isEmpty() || podeSerVazio) return texto.trim();
            System.out.println("Entrada obrigatória.");
        } while (true);
    }

    public static String lerTexto(Scanner scanner, String mensagem) {
        return lerTexto(scanner, mensagem, false);
    }

    public static boolean indiceValido(int indice, int tamanhoLista) {
        if (indice < 0 || indice >= tamanhoLista) {
            System.out.println("Índice inválido.");
            return false;
        }
        return true;
    }
}
