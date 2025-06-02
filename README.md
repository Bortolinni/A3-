# Sistema de Desempenho de Alunos

Este é um sistema em Java para gerenciar o desempenho de alunos, permitindo operações de cadastro, atualização, remoção, listagem e geração de estatísticas.

## Funcionalidades

 Cadastro de alunos (nome, nota e sexo)  
 Atualização de dados de alunos  
 Remoção de alunos  
 Listagem de alunos com situação (aprovado ou reprovado)  
 Estatísticas de desempenho:
- Aprovados do sexo masculino
- Reprovadas do sexo feminino
- Média de notas das alunas
- Média geral das notas

Validações de entrada (nota entre 0 e 100, sexo 'M' ou 'F', índice válido)  
 Mensagens amigáveis para o usuário

##Estrutura de pacotes sugerida:

/src
  /main
    /java
      SistemaDesempenhoAlunos.java (código atual)
  /test
    /java
      SistemaDesempenhoAlunosTest.java (arquivo de testes unitários)

Para rodas os testes pode usar Maven ou Gradle e adicionar a dependência de JUnit5

Maven:

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>

Gradle:

testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'

Depois é só rodar com:

mvn test ou gradle test e executar direto pela IDE
