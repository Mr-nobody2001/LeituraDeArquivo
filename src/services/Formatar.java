package services;

public abstract class Formatar {
    public static String formatarNome(String nome) {
        String nomeFormatado;
        String[] nomesBrutos;

        nomeFormatado = "";
        nomesBrutos = nome.split("ss ");

        for (String temp : nomesBrutos) {
            nomeFormatado += temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + " ";
        }

        return nomeFormatado;
    }

    public static String formatarCPF(String cpf) {
        cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-"
                + cpf.substring(9);

        return cpf;
    }
}
