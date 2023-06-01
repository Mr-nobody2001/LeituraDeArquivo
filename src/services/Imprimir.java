package services;

import entities.Dependente;
import entities.Funcionario;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Imprimir {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void imprimirArquivo(List<Funcionario> funcionarios) {
        int contadorLinhas = 3;

        imprimirCabecalho();

        for (Funcionario temp : funcionarios) {
            if (contadorLinhas >= 15) {
                imprimirCabecalho();
                contadorLinhas = 3;
            }

            System.out.printf("%04d %-30s", temp.getMatricula(), temp.getNome());
            System.out.print(temp.getDataNascimento().format(dtf) + " ");
            System.out.printf("%s%n%n", Formatar.formatarCPF(temp.getCpf()));

            contadorLinhas++;

            System.out.print("   Dependentes: Nome                 Tipo Data Nasc.\n\n");

            contadorLinhas++;

            if (temp.getDependentes().size() > 0) {
                for (Dependente temp2 : temp.getDependentes()) {
                    if (contadorLinhas >= 15) {
                        imprimirCabecalho();
                        contadorLinhas = 3;
                    }

                    System.out.printf("%30s %10s ", temp2.getNome(), Formatar.formatarNome(temp2.getTipoDependente().name()));
                    System.out.println(temp2.getDataNascimento().format(dtf) + "\n");

                    contadorLinhas++;
                }
            } else {
                System.out.print("\n   Dependentes: (NÃO POSSUI)\n");

                contadorLinhas++;
            }

            System.out.println("............................................................\n");

            contadorLinhas++;
        }
    }

    private static void imprimirCabecalho() {
        System.out.print("RELAÇÃO DE FUNCIONÁRIOS E SEUS DEPENDENTES\n\n" +
                "Seq. Nome do Funcionário           Data Nasc.      Cpf\n\n" +
                "============================================================\n\n");
    }
}
