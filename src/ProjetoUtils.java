import java.util.Random;
public class ProjetoUtils {
    //Códigos para permitir cores
    static String AZUL="\u001B[34m";
    static String RESET="\u001B[0m";

    //Variável para permitir geração de numeros aleatórios a partir da biblioteca java.util.Random
    static Random randomgen = new Random();

    //Função utilizada para processar TODOS os inputs do utilizador
    public static int processarInput(String input) {
        //Remover espaços e outras interferências à integridade da String
        input=input.trim();

        int input_processado;

        //Se o utilizador der input de um espaço em branco, retorna -2,
        //um código pré-definido já identificado pelo corpo principal do programa
        if(input.equals("")) {
            return -2;
        }

        else {
            try {
                //Se o input do utilizador não for convertível em inteiro,
                //significa que inseriu um valor não numérico ou numérico com decimais,
                //e será lançado um erro na linha a seguir
                input_processado = Integer.parseInt(input);
            } catch (Exception e) {
                //Este erro será tratado retornando -1 à chamada da função
                //um código pré-definido já identificado pelo corpo principal do programa
                return -1;
            }
            //Caso o input seja válido, será retornado na forma de inteiro, de forma a ser utilizado
            return input_processado;
        }

        //Outputs desta função
        //Input = espaço em branco(Enter) -> Output = -2
        //Input = string não numérica inteira -> Output = -1
        //Input = string numérica inteira -> Output = (int)input
    }
    public static int[][] gerarArray() {
        //Criar array 2d
        int[][] matriz = new int[9][9];
        int tempNum;
        //Percorrer todos os espaços do array 2d
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                do {
                    //Gerar numero aleatório de 0 a 9 (10 numeros)
                    tempNum = randomgen.nextInt(10);
                } while (verificarDuplicados(matriz,tempNum, x, y));
                //A variavel "tempNum" vai receber numeros aleatórios até passar na função verificarDuplicados
                //Esta função verifica se existem duplicados na linha e coluna atual da geração

                //Quando "n" receber um numero aleatório que não se repete ou que seja 0,
                //esse valor é aplicado na tabela final
                matriz[x][y]=tempNum;
            }
        }
        //Retornar a tabela preenchida
        return matriz;
    }

    //Função para verificar duplicados com o numero recebido numa certa linha e coluna
    public static boolean verificarDuplicados(int[][] matriz, int tempNum, int x, int y) {
        //Se "tempNum" for 0, é retornado não duplicado, porque 0 não passa por essa verificação
        if(tempNum==0) return false;

        for (int z = 0; z < 9; z++) {
            //Verificar linhas
            if (z != y) {
                if (tempNum == matriz[x][z]) return true;
            }
            //Verificar colunas
            if (z != x) {
                if (tempNum == matriz[z][y]) return true;
            }
        }
        return false;
    }

    //Função para obter os numeros que podem entrar numa determinada posição sem estarem duplicados na linha e coluna
    public static int[] obterPossiveis(int[][] matriz,int x, int y) {
        boolean arrayVazio=true;
        int[] possiveis = new int[9];

        for (int i = 1; i <= 9; i++) {
            if(!verificarDuplicados(matriz,i,x,y)) {
                possiveis[i - 1] = i;
                arrayVazio=false;
            }
        }
        //Se não existirem numeros possiveis retorna um array nulo,
        //caso contrário retorna um array com os numeros possiveis
        return (arrayVazio ? new int[0] : possiveis);
    }

    //Método para mostrar o array 2d recebido
    public static void mostrarArray(int[][] array) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(array[x][y]+" ");
            }
            System.out.println();
        }
    }

    //Método overloaded para mostrar o array 2d recebido e destacar um determinado numero com cor
    public static void mostrarArray(int[][] array, int NumX, int NumY) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(x==NumX && y==NumY) System.out.print(AZUL+array[x][y]+" "+RESET);
                else System.out.print(array[x][y]+" ");
            }
            System.out.println();
        }
    }
}