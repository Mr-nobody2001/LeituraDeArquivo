package services;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class GerarArquivo {
    private static final Random randomico = new Random();
    private static final NumberFormat formatarNumero = NumberFormat.getInstance();
    private static String nomeArquivo;
    private static String diretorio;

    public static String[] gerar() {
        StringBuilder textoArquivo = new StringBuilder();

        String quantidadeFuncionarios;
        quantidadeFuncionarios = JOptionPane.showInputDialog("Com quantos funcionários o arquivo deve ser criado?");

        if (quantidadeFuncionarios == null) {
            JOptionPane.showMessageDialog(null, "Programa encerrado");
            return null;
        }

        while (quantidadeFuncionarios.equals("") || Integer.parseInt(quantidadeFuncionarios) < 15) {
            quantidadeFuncionarios = JOptionPane.showInputDialog("O número mínimo é 15" + "\n" +
                    "Insira outro valor: ");

            if (quantidadeFuncionarios == null) {
                JOptionPane.showMessageDialog(null, "Programa encerrado");
                return null;
            }
        }

        int quantidadeFuncionariosInt = Integer.parseInt(quantidadeFuncionarios);

        for (int i = 0; i < quantidadeFuncionariosInt; i++) {
            // Gera um número de matrícula aleatório
            textoArquivo.append(gerarMatriculaAleatoria());

            // Gera um nome e um sobrenome aleatório
            String nomeMemoria = gerarNomeAleatorio();
            textoArquivo.append(nomeMemoria);

            // Gera uma data aleatória
            String dataMemoria = gerarDataAleatoria();
            textoArquivo.append(dataMemoria);
            dataMemoria = dataMemoria.substring(4);

            // Gera um cpf aleatório (inválido)
            textoArquivo.append(gerarCpfAleatorio());

            // Gera dependentes de forma aleatória
            textoArquivo.append(gerarDependentesAleatorios(dataMemoria, nomeMemoria));

            textoArquivo.append("\n");
        }

        gravarArquivoTeste(textoArquivo.toString());

        return new String[]{diretorio, nomeArquivo};
    }

    private static void gravarArquivoTeste(String texto) {
        nomeArquivo = JOptionPane.showInputDialog("Insira o nome do arquivo: ");

        if (nomeArquivo == null) {
            JOptionPane.showMessageDialog(null, "Programa encerrado");
            return;
        }

        while (nomeArquivo.equals("")) {
            nomeArquivo = JOptionPane.showInputDialog("Nome de arquivo inválido" + "\n" + "Insira o nome" +
                    " do arquivo novamente:");

            if (nomeArquivo == null) {
                JOptionPane.showMessageDialog(null, "Programa encerrado");
                return;
            }
        }

        nomeArquivo = "\\" + nomeArquivo + ".txt";

        diretorio = JOptionPane.showInputDialog("Insira o diretório do local de gravação: ");

        if (diretorio == null) {
            diretorio = JOptionPane.showInputDialog("Insira o diretório do local de gravação: ");
        }

        while (diretorio.equals("")) {
            diretorio = JOptionPane.showInputDialog("Diretório inválido" + "\n" + "Insira o diretório" +
                    " do local de gravação novamente:");

            if (diretorio == null) {
                JOptionPane.showMessageDialog(null, "Programa encerrado");
                return;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(diretorio + nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(texto);

            bufferedWriter.close();
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro");
            e.printStackTrace();
        }
    }

    private static String gerarMatriculaAleatoria() {
        // Formata o número da matrícula
        formatarNumero.setMinimumIntegerDigits(4);
        formatarNumero.setMaximumFractionDigits(0);

        return formatarNumero.format(randomico.nextInt(1, 1001)).replaceAll("\\.", "");
    }

    private static String gerarNomeAleatorio() {
        StringBuilder stringBuilder = new StringBuilder();

        String[] nomes = {"João", "Maria", "Pedro", "Ana", "Lucas", "Mariana", "Rafael", "Larissa", "Felipe", "Carla",
                "Thiago", "Giovanna", "Vinicius", "Aline", "Gustavo", "Letícia", "Bruno", "Camila", "Diego", "Isabela",
                "Marcelo", "Amanda", "Ricardo", "Julia", "Alexandre", "Gabriela", "Rodrigo", "Caroline", "Fernando",
                "Guilherme", "Bianca", "Leandro", "Renata", "Igor", "Vanessa", "Danilo", "Luana", "Eduardo", "Pamela",
                "Caio", "Priscila", "Feliciano", "Adriana", "Luiz", "Sabrina", "Antônio", "Patricia", "Márcio", "Daniela",
                "Sérgio", "Laura", "Raul", "Lorena", "Paulo", "Fernanda", "Jorge", "Tatiana", "Vitor", "Roberta",
                "Tiago", "Raquel", "Júlio", "Débora", "Cristiano", "Vivian", "Mário", "Andréia", "Humberto", "Mirella",
                "Valdir", "Renata", "Jair", "Evelyn", "José", "Elaine", "Ronaldo", "Verônica", "Matheus", "Natália",
                "César", "Brenda", "Arthur", "Fabiana", "Erick", "Cintia", "Enzo", "Jéssica", "Alisson", "Luiza",
                "Nathalia"};

        stringBuilder.append(nomes[randomico.nextInt(0, nomes.length)].toUpperCase());

        stringBuilder.append(gerarSobrenomeAleatorio());

        String auxiliar;

        do {
            auxiliar = gerarSobrenomeAleatorio();
        } while (stringBuilder.indexOf(auxiliar) != -1);

        stringBuilder.append(auxiliar);

        for (int i = 30 - stringBuilder.length(); i > 0; i--) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    private static String gerarSobrenomeAleatorio() {
        String[] sobrenomes = {"ALMEIDA", "SILVA", "PEREIRA", "SANTOS", "OLIVEIRA", "RODRIGUES", "MARTINS", "FERREIRA",
                "GONÇALVES", "ALVES", "COSTA", "AZEVEDO", "CAMPOS", "RAMOS", "CARVALHO", "LOPES", "NASCIMENTO", "ARAÚJO",
                "BARBOSA", "CARDOSO", "SOUZA", "SILVEIRA", "CASTRO", "NUNES", "REIS", "MENDES", "GOMES", "TEIXEIRA",
                "MOREIRA", "FREITAS", "BRITO", "CORREA", "PAIVA", "PAULO", "AMORIM", "PIMENTEL", "LIMA", "AZEITONA",
                "SOARES", "FERREIRA", "DANTAS", "SOUSA", "CABRAL", "ANDRADE", "MORAES", "MENDES", "BARROS", "LUCENA",
                "SILVESTRE", "MACHADO", "XAVIER", "MARINHO", "FURTADO", "AMARAL", "DUARTE", "FERREIRA", "FAGUNDES",
                "PERES", "PIRES", "FRANCO", "RIBEIRO", "SOBRAL", "MELO", "ROCHA", "LOURENÇO", "NOGUEIRA", "FONSECA",
                "GUEDES", "ARAUJO", "LEITE", "MEIRELES", "AZEVEDO", "MARTINS", "SILVA", "SILVEIRA", "ALMEIDA", "PINTO",
                "SANTANA", "CORREIA", "FONSECA", "OLIVEIRA", "RODRIGUES", "COSTA", "SILVA", "GOMES", "SOUZA", "SANTOS",
                "ALVES", "LOPES", "NASCIMENTO"};

        return " " + sobrenomes[randomico.nextInt(0, sobrenomes.length)];
    }

    private static String gerarCpfAleatorio() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            stringBuilder.append(randomico.nextInt(0, 10));
        }

        return stringBuilder.toString();
    }

    private static String gerarDataAleatoria() {
        LocalDate localDateMaximo = LocalDate.now();
        localDateMaximo = localDateMaximo.minusYears(20);

        LocalDate localDateMinimo = localDateMaximo.minusYears(47);

        StringBuilder stringBuilder = new StringBuilder();

        formatarNumero.setMinimumIntegerDigits(2);

        stringBuilder.append(formatarNumero.format(randomico.nextInt(1, 32)));
        stringBuilder.append(formatarNumero.format(randomico.nextInt(1, 13)));
        stringBuilder.append(formatarNumero.format(randomico.nextInt(localDateMinimo.getYear(), localDateMaximo.getYear())).
                replaceAll("\\.", ""));

        return stringBuilder.toString();
    }

    private static String gerarDependentesAleatorios(String dataMemoria, String nomeMemoria) {
        // Quantidade de dependentes referente ao que foi passado:
        // 2 não devem conter dependentes;
        // 3 devem possuir 1 dependente;
        // 4 devem possuir 2 dependentes;
        // 1 deve possuir 3 dependentes;
        // 3 devem possuir 4 dependentes;
        // O restante deve possuir mais de 4 dependentes;
        ArrayList<Integer> numeroDependentes =
                new ArrayList<>(Arrays.asList(0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4));

        ArrayList<Integer> controleDependentes = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        int auxiliar;

        if (numeroDependentes.isEmpty()) {
            auxiliar = randomico.nextInt(5, 10);
        } else {
            auxiliar = numeroDependentes.remove(randomico.nextInt(0, numeroDependentes.size()));
        }

        int tipoDependente;

        for (int j = 0; j < auxiliar; j++) {
            tipoDependente = randomico.nextInt(1, 6);

            if (tipoDependente != 4 && tipoDependente != 5) {
                if (controleDependentes.contains(tipoDependente)) {
                    tipoDependente = randomico.nextInt(4, 6);
                } else {
                    controleDependentes.add(tipoDependente);
                }
            }

            stringBuilder.append(gerarDependente(tipoDependente, Integer.parseInt(dataMemoria), nomeMemoria.split(" ")));
        }

        return stringBuilder.toString();
    }

    private static String gerarDependente(int tipoDependente, int dataMemoria, String[] nomeMemoria) {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDate localDate = LocalDate.now();

        int numero;

        if (tipoDependente == 3 || tipoDependente == 5) {
            stringBuilder.append(gerarNomeAleatorioDependente(2, tipoDependente));
        } else {
            stringBuilder.append(gerarNomeAleatorioDependente(1, tipoDependente));

            String auxiliar = nomeMemoria[2];

            if (stringBuilder.indexOf(auxiliar) != -1) {
                auxiliar = nomeMemoria[1];
            }

            if (stringBuilder.length() + auxiliar.length() + 1 <= 25) {
                stringBuilder.append(" ").append(auxiliar);
            }
        }

        for (int i = 25 - stringBuilder.length(); i > 0; i--) {
            stringBuilder.append(" ");
        }

        stringBuilder.append(tipoDependente);

        String dataMemoriaDependente = gerarDataAleatoria();
        int dataMemoriaDependenteI = Integer.parseInt(dataMemoriaDependente.substring(4));

        if (tipoDependente == 1 || tipoDependente == 2) {
            if (dataMemoria - dataMemoriaDependenteI < 18) {
                dataMemoriaDependenteI = dataMemoria - randomico.nextInt(18, 30);
            }
        } else {
            if (tipoDependente == 3) {
                if (Math.abs(dataMemoria - dataMemoriaDependenteI) > 15) {
                    dataMemoriaDependenteI = dataMemoria - randomico.nextInt(0, 15);
                }
            } else {
                if (tipoDependente == 4) {
                    if (dataMemoria > dataMemoriaDependenteI) {
                        numero = dataMemoria + randomico.nextInt(18, 30);
                        dataMemoriaDependenteI = dataMemoria + numero > localDate.getYear() ? dataMemoria + 18 :
                                dataMemoria + numero;
                    }
                }
            }
        }

        dataMemoriaDependente = dataMemoriaDependente.substring(0, 4) + dataMemoriaDependenteI;

        stringBuilder.append(dataMemoriaDependente);
        return stringBuilder.toString();
    }

    private static String gerarNomeAleatorioDependente(int quantidadeSobrenome, int tipoDependente) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] nomesFemininos = {"Aline", "Ana", "Bianca", "Camila", "Carla", "Cristina", "Daniela", "Eduarda", "Fernanda",
                "Gabriela", "Isabela", "Juliana", "Larissa", "Leticia", "Mariana", "Nathalia", "Patricia",
                "Priscila", "Renata", "Sabrina", "Tatiana", "Thais", "Vanessa", "Vivian", "Yasmin"};

        String[] nomesMasculinos = {"João", "Pedro", "Lucas", "Rafael", "Felipe", "Thiago", "Vinicius", "Gustavo", "Bruno", "Diego",
                "Marcelo", "Ricardo", "Alexandre", "Rodrigo", "Fernando", "Guilherme", "Leandro", "Igor", "Danilo",
                "Eduardo", "Caio", "Feliciano", "Luiz", "Antônio"};

        if (tipoDependente != 1) {
            stringBuilder.append(nomesFemininos[randomico.nextInt(0, nomesFemininos.length)].toUpperCase());
        } else {
            stringBuilder.append(nomesMasculinos[randomico.nextInt(0, nomesMasculinos.length)].toUpperCase());
        }

        String auxiliar;

        for (int i = 0; i < quantidadeSobrenome; i++) {
            auxiliar = gerarSobrenomeAleatorio();

            if (stringBuilder.length() + auxiliar.length() <= 25) {
                stringBuilder.append(auxiliar);
            }
        }

        return stringBuilder.toString();
    }
}
