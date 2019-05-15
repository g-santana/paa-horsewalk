public class Cavalo {

    // movimentos possiveis
    private int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
    private int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

    // numero de posicoes
    private int n;

    // quantidade de casas do tabuleiro
    private int numCasas;

    // estrutura de dados
    private int[][] tabuleiro;

    public Cavalo(int n) {
        this.n = n;
        this.numCasas = n * n;
        this.tabuleiro = new int[n][n];
    }

    /* método para verificar se a posição atual é aceita aceitável quando as coordenadas passadas não ultrapassam
       as bordas do tabuleiro e quando ainda não foi visitado */
    private boolean aceitavel(int x, int y) {
        boolean resultado = (x >= 0 && x <= n - 1);
        resultado = resultado && (y >= 0 && y <= n - 1);
        resultado = resultado && (tabuleiro[x][y] == 0);
        return resultado;
    }

    /* método tenta o próximo passo é passado o próximo (i-ésimo) passo e as coordenadas da posicao para ser
       verificada */
    private boolean tenta(int passo, int x, int y) {
        // verificar se não ultrapassa a quantidade total de casas
        boolean achou = (passo > numCasas);

        int k = 0, u, v; // k auxiliar na quantidade de movimentos, u e v para movimentos

        while (!achou && k < 8) {
            u = x + dx[k];
            v = y + dy[k];
            if (aceitavel(u, v)) {
                // registra como POSSÍVEL solução
                tabuleiro[u][v] = passo;

                // se achou, tenta dar mais um passo recursivamente
                achou = tenta(passo + 1, u, v);
                if (!achou) {
                    tabuleiro[u][v] = 0;
                }
            }
            k++;
        }
        return achou;
    }

    // imprimir passeio
    public void imprimePasseio(int x, int y){
        tabuleiro[x][y] = 1;
        boolean achou = tenta(2, x, y);
        // verifica se achou
        if (achou){
            // percorre as coordenadas x e y do tabuleiro[0] e [1].length
            for (int i = 0; i < tabuleiro[0].length; i++) {
                for (int j = 0; j < tabuleiro[1].length; j++) {
                    System.out.print(tabuleiro[i][j] + "\t");
                }
                System.out.println();
                System.out.println();
            }
        } else {
            System.out.println("Não encontrou o passeio do cavalo.");
        }
    }
}