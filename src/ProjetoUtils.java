import java.util.Random;
public class ProjetoUtils {
    static String AZUL="\u001B[34m";
    static String RESET="\u001B[0m";
    static Random randomgen = new Random();
    public static int processarInput(String input) {
        int input_processado;

        if(input.equals("")) {
            return -2;
        } else {
            //Detetar inputs inválidos
            try {
                //Se o input do utilizador não for convertível em inteiro
                //Significa que inseriu um valor não numérico ou numérico com decimais
                //Será lançado um erro na linha a seguir
                //Que será tratado retornando -1 à chamada da função
                //Caso o input seja válido, será retornado na forma de inteiro, de forma a ser utilizado
                input_processado = Integer.parseInt(input);
            } catch (Exception e) {
                return -1;
            }

            return input_processado;
        }

        //outputs desta função
        //Input = espaço em branco(Enter) -> Output = -2
        //Input = string não numérica inteira -> Output = -1
        //Input = string numérica inteira -> Output = (int)input
    }
    public static int[][] gerarArray() {
        //Criar array 2d
        int[][] matriz = new int[9][9];
        int n;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                do {
                    //Gerar numero aleatório de 0 a 9
                    n = randomgen.nextInt(10);
                } while (checkDup(matriz,n, x, y));
                //A variavel "n" vai receber numeros aleatórios até passar na função checkDup
                //Esta função verifica se existem duplicados

                //Quando "n" receber um numero aleatório que não se repete ou 0,
                //esse valor é aplicado na tabela final
                matriz[x][y]=n;
            }
        }
        return matriz;
    }
    public static boolean checkDup(int[][] matriz,int n, int x, int y) {
        //Se "n" for 0, é retornada a informação de não é duplicado, porque 0 não passa por essa verificação
        if(n==0) return false;
        for (int z = 0; z < 9; z++) {
            //Verificar linhas
            if (z != y) {
                if (n == matriz[x][z]) return true;
            }
            //Verificar colunas
            if (z != x) {
                if (n == matriz[z][y]) return true;
            }
        }
        return false;
    }
    public static int[] obterPossiveis(int[][] matriz,int x, int y) {
        boolean arrayVazio=true;
        int[] possiveis = new int[9];
        for (int i = 1; i <= 9; i++) {
            if(!checkDup(matriz,i,x,y)) {
                possiveis[i - 1] = i;
                arrayVazio=false;
            }
        }
        return (arrayVazio ? new int[0] : possiveis);
    }
    public static void mostrarArray(int[][] array) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(array[x][y]+" ");
            }
            System.out.println();
        }
    }
    public static void mostrarArray(int[][] array, int Nx, int Ny) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(x==Nx && y==Ny) System.out.print(AZUL+array[x][y]+" "+RESET);
                else System.out.print(array[x][y]+" ");
            }
            System.out.println();
        }
    }
}