import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SistemaDesempenhoAlunosTest {

    @Test
    void testAlunoAprovado() {
        Aluno aluno = new Aluno("João", 75, 'M');
        assertTrue(aluno.estaAprovado(), "Aluno com nota 75 deveria estar aprovado");
        assertEquals("APROVADO", aluno.getSituacao(), "Situação deveria ser 'APROVADO'");
    }

    @Test
    void testAlunoReprovado() {
        Aluno aluno = new Aluno("Maria", 50, 'F');
        assertFalse(aluno.estaAprovado(), "Aluno com nota 50 deveria estar reprovado");
        assertEquals("REPROVADO", aluno.getSituacao(), "Situação deveria ser 'REPROVADO'");
    }

    @Test
    void testToString() {
        Aluno aluno = new Aluno("Carlos", 90, 'M');
        String esperado = "Carlos | Nota: 90 | Sexo: M | Situação: APROVADO";
        assertEquals(esperado, aluno.toString(), "toString() não gerou a saída esperada");
    }

    @Test
    void testEstatisticasComDados() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("Ana", 80, 'F')); // aprovada
        alunos.add(new Aluno("Beatriz", 50, 'F')); // reprovada
        alunos.add(new Aluno("Carlos", 90, 'M')); // aprovado
        alunos.add(new Aluno("Daniel", 40, 'M')); // reprovado

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outputStream));

        Estatisticas.exibir(alunos);
        String saida = outputStream.toString();

        assertTrue(saida.contains("Aprovados do sexo masculino: 1"));
        assertTrue(saida.contains("Reprovadas do sexo feminino: 1"));
        assertTrue(saida.contains("Média das notas femininas: 65.00"));
        assertTrue(saida.contains("Média geral das notas: 65.00"));

        System.setOut(System.out);
    }

    @Test
    void testEstatisticasSemDados() {
        ArrayList<Aluno> alunos = new ArrayList<>();

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outputStream));

        Estatisticas.exibir(alunos);
        String saida = outputStream.toString();

        assertTrue(saida.contains("Nenhum aluno cadastrado."));

        System.setOut(System.out);
    }

    @Test
    void testIndiceValido() {
        assertTrue(Util.indiceValido(0, 5));
        assertTrue(Util.indiceValido(4, 5));
        assertFalse(Util.indiceValido(-1, 5));
        assertFalse(Util.indiceValido(5, 5));
    }
}
