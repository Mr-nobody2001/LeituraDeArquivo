package services;

import entities.Dependente;
import entities.Funcionario;
import entities.enums.TipoDependente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Criar {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static Funcionario criarFuncionario(String linhaArquivo) throws NumberFormatException,
            DateTimeParseException, NullPointerException {
        int matricula;
        String nome;
        LocalDate dataNascimento;
        String cpf;

        matricula = Integer.parseInt(linhaArquivo.substring(0, 4));

        nome = Formatar.formatarNome(linhaArquivo.substring(4, 34).trim());

        dataNascimento = LocalDate.parse(linhaArquivo.substring(34, 42), dtf);

        cpf = linhaArquivo.substring(42, 53);

        Funcionario novoFuncionario = new Funcionario(matricula, nome, dataNascimento, cpf);

        linhaArquivo = linhaArquivo.substring(53);

        criarDependente(novoFuncionario, linhaArquivo);

        return novoFuncionario;
    }

    private static void criarDependente(Funcionario novoFuncionario, String linhaArquivo) throws DateTimeParseException {
        int contador = linhaArquivo.length() / 34;
        String nome;
        TipoDependente tipoDependente = null;
        LocalDate dataNascimento;

        for (int i = 0; i < contador; i++) {
            nome = Formatar.formatarNome(linhaArquivo.substring(0, 25).trim());

            switch (linhaArquivo.charAt(25) - '0') {
                case 1 -> tipoDependente = TipoDependente.PAI;
                case 2 -> tipoDependente = TipoDependente.MAE;
                case 3 -> tipoDependente = TipoDependente.CONJUGE;
                case 4 -> tipoDependente = TipoDependente.FILHO;
                case 5 -> tipoDependente = TipoDependente.OUTROS;
            }

            if (tipoDependente == null) {
                throw new NullPointerException("Erro: Tipo dependente não existe");
            }

            dataNascimento = LocalDate.parse(linhaArquivo.substring(26, 34), dtf);

            novoFuncionario.getDependentes().add(new Dependente(nome, tipoDependente, dataNascimento));

            linhaArquivo = linhaArquivo.substring(34);
        }

    }
}
