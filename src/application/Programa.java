package application;

import entities.Funcionario;
import services.Criar;
import services.Imprimir;

import java.io.BufferedReader;
import java.time.format.DateTimeParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Programa {
    public static void main(String[] args) {
        String path = "C:\\Users\\gabri\\OneDrive\\Documentos\\Conteúdo Faculdade\\Segundo Semestre\\" +
                "Técnicas de programação\\trabalhos\\trabalho2\\src\\files\\teste.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            StringBuilder stringBuilder = new StringBuilder();

            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append("\n");
            }

            String[] arquivoLido = stringBuilder.toString().split("\n");

            List<Funcionario> funcionarios = new ArrayList<>(arquivoLido.length);

            for (String temp : arquivoLido) {
                funcionarios.add(Criar.criarFuncionario(temp));
            }

            Imprimir.imprimirArquivo(funcionarios);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("Erro de conversão númerica: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Erro de formatação de data: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}