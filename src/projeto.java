import java.util.Random;
import java.util.Scanner;

public class projeto {

    //Variável para permitir input do utilizador
    static Scanner userInput = new Scanner(System.in);

    //Variável para detetar inputs inválidos
    static boolean inputFlag;

    //Variável para permitir gerar numeros aleatórios
    static Random randomgen = new Random();
    public static void main(String[] args) {

        System.out.println(" ---------------------------------------------------");
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
        //Detetar inputs inválidos
        try {
            //Se o input do utilizador não for convertível em inteiro
            //Significa que inseriu um valor não numérico ou numérico com decimais
            //Será lançado um erro na linha a seguir
            //Que será tratado ativando a variável que deteta inputs inválidos
            input_processado = Integer.parseInt(input);
        } catch (Exception e) {
            return -1;
        }

        return input_processado;
    }

    public static int menu() {
        int escolhaMenu=0;
        System.out.println(" --------/ Menu Principal /--------- \n");
        System.out.println("(1) Desenho de padrões");
        System.out.println("(2) Jogo do adivinha");
        System.out.println("(3) Sudoku simplificado");
        System.out.println("(4) Sair do programa\n");

        do {
            System.out.print("Escolha: ");
            escolhaMenu=processarInput(userInput.nextLine());

            //Se o utilizador escolher uma opção fora dos limites do menu
            if(escolhaMenu<1 || escolhaMenu>4) {
                System.out.println("Erro: Escolha inválida");

                //Esperar por um ENTER
                userInput.nextLine();

                //Forçar a passagem para a próxima iteração
                continue;
            }

            //Quebrar o ciclo
            break;

        } while(true);

        //Retornar a escolha (já validada) do utilizador para entrar no switch da função main
        return escolhaMenu;
    }
    public static void padroes() {
        int numeroInserido,numeroMostrar;
        do {
            System.out.println(" ---------/ Desenho de Padrões /--------- \n");
            System.out.print("Insira um numero entre 1 e 10: ");

            numeroInserido=processarInput(userInput.nextLine());

            if(numeroInserido<1 || numeroInserido>10) {
                System.out.println("""
                        Erro: Escolha inválida
                        Lembre-se do intervalo de números possíveis (1-10) e também de inserir apenas números inteiros.
                        """);
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

        System.out.print("Pressione ENTER para continuar...");
        userInput.nextLine();
    }

    public static void adivinha() {
        int totalTentativas=0,tentativa=-1;

        //Gerar numero de 0 a 1000 (1001 numeros)
        int numeroGerado=randomgen.nextInt(1001);

        System.out.println("\n ---------/ Jogo Da Adivinha /--------- \n");
        System.out.println("O programa gerou um numero aleatório entre 0 e 1000.");
        System.out.println("Tente adivinhá-lo!");
        System.out.println("DEBUG: N="+numeroGerado);

        do {
            System.out.print("A sua tentativa -> ");
            String tempInput=userInput.nextLine();

            if(tempInput.equals("")) {
                continue;
            } else {
                tentativa=processarInput(tempInput);
            }

            if(tentativa<0 || tentativa>1000) {
                System.out.print("""
                        Erro: Tentativa inválida
                        Lembre-se do intervalo de números possíveis (0-1000) e também de inserir apenas números inteiros.
                        """);
            }

            else {
                totalTentativas++;
                if (tentativa > numeroGerado) System.out.println(tentativa + " é maior que o número gerado!");
                else if (tentativa < numeroGerado) System.out.println(tentativa + " é menor que o número gerado!");
                else {
                    System.out.println("Acertou em cheio! " + tentativa + " é o número gerado!\n" +
                            "Foram precisas " + totalTentativas + " tentativas.");

                    userInput.nextLine();
                }
            }

        } while(tentativa != numeroGerado);
    }

    public static void sudoku() {
        int[][] matriz = gerarArray();
        mostrarArray(matriz);
        System.out.println("Insira uma posição com cordenadas de 1 a 9 no seguinte formato - x,y: ");
        String cordenadas=userInput.nextLine();

        int x = processarInput(cordenadas.split(",")[0]);
        int y = processarInput(cordenadas.split(",")[1]);

        int[] possibilidades = new int[9];

        for (int i = 1; i <= 9; i++) {
            if(!checkDup(matriz,i,x,y)) {
                possibilidades[i-1]=i;
            }
        }

        for (int v = 0; v < 9; v++) {
            System.out.println(possibilidades[v]);
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
                //Esta função verifica se existem duplicados no eixo x e y

                //Quando "n" receber um numero aleatório diferente de 0 que não se repete,
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

    public static void mostrarArray(int[][] array) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print("  " + array[x][y]);
            }
            System.out.println();
        }
    }
}

