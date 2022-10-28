import java.util.Random;
import java.util.Scanner;

public class projeto {

    static String AZUL="\u001B[34m";
    static String RESET="\u001B[0m";
    static String VERMELHO = "\u001B[31m";


    //Variável para permitir input do utilizador
    static Scanner userInput = new Scanner(System.in);

    //Variável para permitir gerar numeros aleatórios
    static Random randomgen = new Random();
    public static void main(String[] args) {

        System.out.println("\n ---------------------------------------------------");
        System.out.println("| Bem-vindo ao 1º Projeto de Programação do Grupo 5 |");
        System.out.println("|       Paulo Oliveira     e     Rafael Cosme       |");
        System.out.println(" ---------------------------------------------------\n");

        boolean sair=false;
        do {
            switch (menu()) {
                case 1 -> padroes();
                case 2 -> adivinha();
                case 3 -> sudoku();
                case 4 -> {
                    System.out.println("Obrigado por ter utilizado o programa, preesione ENTER para sair.");
                    userInput.nextLine();
                    sair=true;
                }
            }
        } while(!sair);
    }
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
    public static int menu() {
        int escolhaMenu;

        System.out.println("\n --------/ Menu Principal /--------- \n");
        System.out.println("(1) Desenho de padrões");
        System.out.println("(2) Jogo do adivinha");
        System.out.println("(3) Sudoku simplificado");
        System.out.println("(4) Sair do programa\n");


        do {
            System.out.print("Escolha: ");
            escolhaMenu=processarInput(userInput.nextLine());

            //Se o utilizador escolher uma opção fora dos limites do menu
            if(escolhaMenu<1 || escolhaMenu>4) {
                if(escolhaMenu==-2) continue;

                System.out.println(VERMELHO+"Escolha inválida"+RESET);
                //Forçar a passagem para a próxima iteração
                continue;
            }

            //Quebrar o ciclo
            break;

        } while(true);

        //Retornar a escolha (já validada) do utilizador para a função main
        return escolhaMenu;
    }
    public static void padroes() {
        int numeroInserido,numeroMostrar;
        do {
            System.out.println(" ---------/ Desenho de Padrões /--------- \n");
            System.out.print("Insira um numero entre 1 e 10: ");

            numeroInserido=processarInput(userInput.nextLine());

            if(numeroInserido<1 || numeroInserido>10) {
                if(numeroInserido==-2) continue;
                System.out.println(VERMELHO+"""
                        Escolha inválida
                        Lembre-se do intervalo de números possíveis (1-10) e também de inserir apenas números inteiros.
                        """+RESET);
                continue;
            }
            break;
            
        }while(true);

        //Padrão A
        System.out.println(" --/ Padrão A /-- ");
        for (int i = 0; i < numeroInserido; i++) {
            numeroMostrar = 1;
            for (int j = 0; j <=i; j++) {
                System.out.print(numeroMostrar+" ");
                numeroMostrar++;
            }
            System.out.println();
        }

        System.out.println();

        //Padrão B
        System.out.println(" --/ Padrão B /-- ");
        for (int i = numeroInserido; i >=1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

        System.out.println();

        //Padrão C
        System.out.println(" --/ Padrão C /-- ");
        for (int i = 0; i < numeroInserido; i++) {
            numeroMostrar = i+1;
            for (int x=numeroInserido-i; x>1; x--) { //Imprime espaços por linha antes dos numeros
                System.out.print("  ");
            }
            for (int j = 0; j <=i; j++) {
                System.out.print(numeroMostrar+" ");
                numeroMostrar--;
            }
            System.out.println();
        }
    }
    public static void adivinha() {
        int totalTentativas=0,tentativa;

        //Gerar numero de 0 a 1000 (1001 numeros)
        int numeroGerado=randomgen.nextInt(1001);

        System.out.println("\n ---------/ Jogo Da Adivinha /--------- \n");
        System.out.println("O programa gerou um numero aleatório entre 0 e 1000.");
        System.out.println("Tente adivinhá-lo!");

        do {
            System.out.print("A sua tentativa -> ");
            tentativa=processarInput(userInput.nextLine());

            if(tentativa<0 || tentativa>1000) {
                System.out.print(VERMELHO+"""
                        Erro: Tentativa inválida
                        Lembre-se do intervalo de números possíveis (0-1000) e também de inserir apenas números inteiros.
                        """+RESET);
            }

            else {
                totalTentativas++;
                if (tentativa > numeroGerado) System.out.println(tentativa + " é maior que o número gerado!");
                else if (tentativa < numeroGerado) System.out.println(tentativa + " é menor que o número gerado!");
                else {
                    System.out.println("Acertou em cheio! " + tentativa + " é o número gerado!\n" +
                            "Foram precisas " + totalTentativas + " tentativas.");
                }
            }
        } while(tentativa != numeroGerado);
    }

    public static void sudoku() {
        int[][] matriz = gerarArray();
        mostrarArray(matriz);
        int x,y;

        while(true) {
            try {
                System.out.print("Insira a coluna e linha no seguinte formato - lin,col: ");
                String cordenadas=userInput.nextLine();

                String Sx = cordenadas.split(",")[0];
                String Sy = cordenadas.split(",")[1];

                x = Integer.parseInt(Sx) - 1;
                y = Integer.parseInt(Sy) - 1;

                if (x < 0 || x > 8 || y < 0 || y > 8) {
                    System.out.println(VERMELHO+"Posição inválida\n" +
                            "Lembre-se do intervalo de posições possíveis \"(1-9),(1-9)\" e também de inserir apenas números inteiros."+RESET);
                    continue;

                } else if (matriz[x][y] != 0) {
                    System.out.println(VERMELHO+"A posição que inseriu não contém um 0 (" + matriz[x][y] + ")"+RESET);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println(VERMELHO+"Não foi possível obter a posição, tente novamente."+RESET);
            }
        }

        int[] possibilidades = obterPossiveis(matriz,x,y);

        if(possibilidades.length==0) {
            System.out.println("A posição referida não contém numeros possiveis.");
        }

        else {
            System.out.print("Numeros possiveis: ");
            for (int i = 0; i < 9; i++) {
                if(possibilidades[i]!=0) System.out.print(possibilidades[i]+" ");
            }

            int numEscolhido;
            while(true) {
                System.out.print("\nInsira o número possivel que deseja escolher: ");
                numEscolhido = processarInput(userInput.nextLine());

                //Espaço em branco
                if(numEscolhido==-2) continue;

                //String não numérica
                if(numEscolhido==-1) System.out.println(VERMELHO+"Insira um número válido!"+RESET);
                else break;
            }

            //Verificar se o numero escolhido existe dentro do array
            boolean existe=false;
            for (int i = 0; i < 9; i++) {
                if(numEscolhido==possibilidades[i]) {
                    existe = true;
                    break;
                }
            }

            if(existe) {
                matriz[x][y]=numEscolhido;
                mostrarArray(matriz,x,y);
            }

            else System.out.println(VERMELHO+"O número que inseriu não é um numero possivel."+RESET);
        }
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