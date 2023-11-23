import java.util.Scanner;

public class CacaPalavras {

    private CacaPalavras() {
        Scanner leitor = new Scanner(System.in);

        String palavras[][] = new String[5][2];
        char mapa[][] = new char[10][5];

        int opcao = 0;

        palavrasEntrada(palavras);
        mapaEntrada(mapa);
        mapaPesquisa(palavras, mapa);

        do {
            System.out.print("__ Menu: Caça Palavras __" +
                    "\n1. listar palavras" +
                    "\n2. listar mapa" +
                    "\n3. listar respostas" +
                    "\n4. sair" +
                    "\n __ opção: ");

            opcao = leitor.nextInt();

            switch (opcao) {
                case 1:
                    palavrasImprimir(palavras);
                    break;
                case 2:
                    mapaImprimir(mapa);
                    break;
                case 3:
                    palavrasRespostas(palavras);
                    break;

                default:
                    System.out.println("Opção ERRADA, tente novamente!...");
                    break;
            }

        } while (opcao != 4);

    }

    private void palavrasEntrada(String[][] palavras) {

        palavras[0][0] = "IFELSE";
        palavras[1][0] = "FORA";
        palavras[2][0] = "WHILE";
        palavras[3][0] = "OBJETO";
        palavras[4][0] = "VETOR";

    }

    private void mapaEntrada(char[][] mapa) {

        mapa[0][0] = 'D';
        mapa[0][1] = 'C';
        mapa[0][2] = 'Q';
        mapa[0][3] = 'W';
        mapa[0][4] = 'E';
        mapa[1][0] = 'I';
        mapa[1][1] = 'X';
        mapa[1][2] = 'F';
        mapa[1][3] = 'O';
        mapa[1][4] = 'R';
        mapa[2][0] = 'F';
        mapa[2][1] = 'F';
        mapa[2][2] = 'R';
        mapa[2][3] = 'G';
        mapa[2][4] = 'F';
        mapa[3][0] = 'E';
        mapa[3][1] = 'L';
        mapa[3][2] = 'I';
        mapa[3][3] = 'H';
        mapa[3][4] = 'W';
        mapa[4][0] = 'L';
        mapa[4][1] = 'S';
        mapa[4][2] = 'F';
        mapa[4][3] = 'O';
        mapa[4][4] = 'U';
        mapa[5][0] = 'S';
        mapa[5][1] = 'D';
        mapa[5][2] = 'G';
        mapa[5][3] = 'T';
        mapa[5][4] = 'S';
        mapa[6][0] = 'E';
        mapa[6][1] = 'J';
        mapa[6][2] = 'H';
        mapa[6][3] = 'E';
        mapa[6][4] = 'T';
        mapa[7][0] = 'I';
        mapa[7][1] = 'I';
        mapa[7][2] = 'I';
        mapa[7][3] = 'J';
        mapa[7][4] = 'M';
        mapa[8][0] = 'X';
        mapa[8][1] = 'C';
        mapa[8][2] = 'K';
        mapa[8][3] = 'B';
        mapa[8][4] = 'G';
        mapa[9][0] = 'V';
        mapa[9][1] = 'E';
        mapa[9][2] = 'T';
        mapa[9][3] = 'O';
        mapa[9][4] = 'R';

    }

    public void palavrasImprimir(String[][] palavras) {
        for (int i = 0; i < palavras.length; i++) {
            System.out.println(palavras[i][0]);
        }
    }

    private void mapaImprimir(char mapa[][]) {
        for (int i = 0; i < mapa.length; i++) {
            System.out.println();
            for (int j = 0; j < mapa[0].length; j++) {

                if (j == 0) {
                    System.out.println(" ---------------------");
                    System.out.print(" | " + mapa[i][j] + " | ");
                } else {
                    System.out.print(mapa[i][j] + " | ");
                }
            }
        }
        System.out.println();
        System.out.println(" ---------------------");
    }

    public void mapaPesquisa(String[][] palavras, char[][] mapa) {
        int linhaMatrizPalavra = 0;

        do {

            // Esses dois laços for para percorrer o caça palavras
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {

                    // Aqui eu pego a primeira linha e coluna da matriz PALAVRAS, nela contém a
                    // primeira palavra com a palavra em mãos chamamos o metodo charAt para pegar a
                    // primeira letra palavra e comparar com a letra do mapa
                    if (palavras[linhaMatrizPalavra][0].charAt(0) == mapa[i][j]) {

                        // Aqui eu pego o tamanho da palavra e vejo se tem espaço no caça palavras pra
                        // aquela palavra nessa
                        if (palavras[linhaMatrizPalavra][0].length() <= (mapa[0].length - j)) {

                            // Aqui eu chamo o método pra analisar se tem a palavra toda dentro do caça
                            // se tiver vai retornar true, se retornar true entra no if, e aí a gente 
                            // marca a segunda coluna da matriz palavras, que é o lugar pra ficar salvo
                            // as coordenadas da primeira letra ou a mensagem de que não encontrou
                            if (analisaDaEsquerdaPraDireita(palavras, mapa, i, j, linhaMatrizPalavra)) {
                                palavras[linhaMatrizPalavra][1] = "[" + i + "," + j + "] - "
                                        + palavras[linhaMatrizPalavra][0];
                            }

                        } else if (palavras[linhaMatrizPalavra][0].length() <= (j + 1)) {

                            if (analisaDaDireitaPraEsquerda(palavras, mapa, i, j, linhaMatrizPalavra)) {
                                palavras[linhaMatrizPalavra][1] = "[" + i + "," + j + "] - "
                                        + palavras[linhaMatrizPalavra][0];
                            }

                        } else if (palavras[linhaMatrizPalavra][0].length() <= (mapa.length - i)) {

                            if (analisaDeCimaPraBaixo(palavras, mapa, i, j, linhaMatrizPalavra)) {
                                palavras[linhaMatrizPalavra][1] = "[" + i + "," + j + "] - "
                                        + palavras[linhaMatrizPalavra][0];
                                break;
                            }

                        } else if ((i - palavras[linhaMatrizPalavra][0].length()) >= 0) {

                            if (analisaDeBaixoPraCima(palavras, mapa, i, j, linhaMatrizPalavra)) {
                                palavras[linhaMatrizPalavra][1] = "[" + i + "," + j + "] - "
                                        + palavras[linhaMatrizPalavra][0];
                                break;
                            }

                        }

                        if (palavras[linhaMatrizPalavra][1] == null) {
                            palavras[linhaMatrizPalavra][1] = "Palavra NÃO encontrada: "
                                    + palavras[linhaMatrizPalavra][0];
                        }
                    }
                }
            }

            linhaMatrizPalavra++;

        } while (linhaMatrizPalavra < palavras.length);

    }

    public boolean analisaDaEsquerdaPraDireita(String[][] palavras, char[][] mapa, int i, int j,
            int linhaMatrizPalavra) {

        int letraDaPalavra = 0;

        while (true) {
            if (mapa[i][j] != palavras[linhaMatrizPalavra][0].charAt(letraDaPalavra)) {
                return false;
            }

            j++;
            letraDaPalavra++;

            if (letraDaPalavra == palavras[linhaMatrizPalavra][0].length()) {
                return true;
            }
        }
    }

    public boolean analisaDaDireitaPraEsquerda(String[][] palavras, char[][] mapa, int i, int j,
            int linhaMatrizPalavra) {

        int letraDaPalavra = 0;

        while (true) {
            if (mapa[i][j] != palavras[linhaMatrizPalavra][0].charAt(letraDaPalavra)) {
                return false;
            }

            j--;
            letraDaPalavra++;

            if (letraDaPalavra == palavras[linhaMatrizPalavra][0].length()) {
                return true;
            }
        }
    }

    public boolean analisaDeCimaPraBaixo(String[][] palavras, char[][] mapa, int i, int j, int linhaMatrizPalavra) {

        int letraDaPalavra = 0;

        while (true) {
            if (mapa[i][j] != palavras[linhaMatrizPalavra][0].charAt(letraDaPalavra)) {
                return false;
            }

            i++;
            letraDaPalavra++;

            if (letraDaPalavra == palavras[linhaMatrizPalavra][0].length()) {
                return true;
            }
        }
    }

    public boolean analisaDeBaixoPraCima(String[][] palavras, char[][] mapa, int i, int j, int linhaMatrizPalavra) {

        int letraDaPalavra = 0;

        while (true) {
            if (mapa[i][j] != palavras[linhaMatrizPalavra][0].charAt(letraDaPalavra)) {
                return false;
            }

            i--;
            letraDaPalavra++;

            if (letraDaPalavra == palavras[linhaMatrizPalavra][0].length()) {
                return true;
            }
        }
    }

    public void palavrasRespostas(String[][] palavras) {
        for (int i = 0; i < palavras.length; i++) {
            System.out.println(palavras[i][1]);
        }
    }

    public static void main(String[] args) {
        new CacaPalavras();
    }
}