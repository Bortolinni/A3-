import java.util.Scanner;

public class ProjetoDesempenhoAluno {
    public static void main(String[] args) {
        final int TOTAL_ALUNOS = 50;
        Scanner scanner = new Scanner(System.in);

        String[] nomesAprovados = new String[TOTAL_ALUNOS];
        int[] notasAprovados = new int[TOTAL_ALUNOS];
        int countAprovados = 0;

        int aprovadosMasculino = 0;
        int reprovadosFeminino = 0;
        int somaNotasFemininas = 0;
        int countFemininas = 0;
        int somaNotasGeral = 0;

        for (int i = 0; i < TOTAL_ALUNOS; i++) {
            System.out.println("Aluno " + (i + 1));

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            int nota;
            while (true) {
                System.out.print("Nota (0 a 100): ");
                nota = scanner.nextInt();
                if (nota >= 0 && nota <= 100) break;
                System.out.println("Nota inválida! Digite uma nota entre 0 e 100.");
            }

            char sexo;
            while (true) {
                System.out.print("Sexo (M ou F): ");
                sexo = scanner.next().toUpperCase().charAt(0);
                if (sexo == 'M' || sexo == 'F') break;
                System.out.println("Sexo inválido! Digite M ou F.");
            }
            scanner.nextLine();

            somaNotasGeral += nota;
            if (sexo == 'F') {
                somaNotasFemininas += nota;
                countFemininas++;
            }

            if (nota >= 60) {
                System.out.println("Situação: APROVADO");
                if (sexo == 'M') aprovadosMasculino++;
                nomesAprovados[countAprovados] = nome;
                notasAprovados[countAprovados] = nota;
                countAprovados++;
            } else {
                System.out.println("Situação: REPROVADO");
                if (sexo == 'F') reprovadosFeminino++;
            }
            System.out.println("---------------------------");
        }

        System.out.println("\n===== RESULTADOS FINAIS =====");
        System.out.println("Número de aprovações do sexo masculino: " + aprovadosMasculino);
        System.out.println("Número de reprovações do sexo feminino: " + reprovadosFeminino);

        double mediaFeminina = countFemininas > 0 ? (double) somaNotasFemininas / countFemininas : 0;
        double mediaGeral = (double) somaNotasGeral / TOTAL_ALUNOS;

        System.out.printf("Média das notas femininas: %.2f%n", mediaFeminina);
        System.out.printf("Média geral das notas: %.2f%n", mediaGeral);

        System.out.println("\nRELATÓRIO DE APROVADOS");
        System.out.println("NOME");
        for (int i = 0; i < countAprovados; i++) {
            System.out.println(nomesAprovados[i]);
        }
        System.out.println("NOTA");
        for (int i = 0; i < countAprovados; i++) {
            System.out.println(notasAprovados[i]);
        }

        scanner.close();
    }
}