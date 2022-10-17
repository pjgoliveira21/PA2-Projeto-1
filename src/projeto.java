import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class projeto {
    static Scanner userInput = new Scanner(System.in);
    static boolean inputFlag;
    public static void main(String[] args) {

        System.out.println(" ---------------------------------------------------");
        System.out.println("| Bem-vindo ao 1º Projeto de Programação do Grupo 5 |");
        System.out.println("|       Paulo Oliveira     e     Rafael Cosme       |");
        System.out.println(" ---------------------------------------------------\n");
        switch (menu()) {
            case 1 -> padroes();
            case 2 -> adivinha();
            case 3 -> sudoku();
        }
    }
    public static int menu() {
        int escolhaMenu=0;
        do {
            System.out.println(" --------/ Menu Principal /--------- \n");
            System.out.println("(1) Desenho de padrões");
            System.out.println("(2) Jogo do adivinha");
            System.out.println("(3) Sudoku simplificado\n");
            System.out.print("Escolha: ");

            try {
                escolhaMenu = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                inputFlag=true;
            }

            if(escolhaMenu<1 || escolhaMenu>3 || inputFlag) {
                System.out.println("Erro: Escolha inválida");
                userInput.nextLine();
                continue;
            }
            break;

        } while(true);
        return escolhaMenu;
    }
    public static void padroes() {
        int numeroInserido,numeroMostrar;
        do {
            System.out.println(" ---------/ Desenho de Padrões /--------- \n");
            System.out.print("Insira um numero entre 1 e 10: ");
            numeroInserido = Integer.parseInt(userInput.nextLine());

            try {
                numeroInserido = Integer.parseInt(userInput.nextLine());
            } catch (Exception e) {
                inputFlag=true;
            }

            if(numeroInserido<1 || numeroInserido>10 || inputFlag) System.out.println("Erro: "+numeroInserido+" não é um número válido.\nLembre-se do intervalo de números possíveis (1-10) e também de inserir apenas números inteiros.\n");
            
        }while(numeroInserido<1 || numeroInserido>10 || inputFlag);

        //Padrão A
        System.out.println("Padrão A");
        System.out.println();
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
        System.out.println("Padrão B");
        System.out.println();
        for (int i = numeroInserido; i >=1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

        //Padrão C
        System.out.println("Padrão C");
        System.out.println();
        for (int i = 0; i < numeroInserido; i++) {
            numeroMostrar = 1;
            for (int x=numeroInserido-i; x>1; x--) { //Imprime espaços por linha antes dos numeros
                System.out.print("  ");
            }
            for (int j = 0; j <=i; j++) {
                System.out.print(numeroMostrar+" ");
                numeroMostrar++;
            }
            System.out.println();
        }
    }

    public static void adivinha() {
        int totalTentativas=0,tentativa=-1;

        Random randomgen = new Random();
        int numeroGerado=randomgen.nextInt(1001);
        System.out.println(" ---------/ Jogo Da Adivinha /--------- \n");
        System.out.println("O programa gerou um numero aleatório entre 0 e 1000.");
        System.out.println("Tente adivinhá-lo!");
        System.out.println("DEBUG: N="+numeroGerado);

        do {
            System.out.print("A sua tentativa -> ");
            try {
                tentativa = Integer.parseInt(userInput.nextLine());
                totalTentativas++;
                if(tentativa>numeroGerado) System.out.println(tentativa+" é maior que o número gerado!");
                else if(tentativa<numeroGerado) System.out.println(tentativa+" é menor que o número gerado!");
                else System.out.println("Acertou em cheio! "+tentativa+" é o número gerado!\nForam precisas "+totalTentativas+" tentativas.");

            } catch (Exception e) {
                inputFlag = true;
            }

            if(tentativa<0 || tentativa>1000 || inputFlag) System.out.println("Erro: "+tentativa+" não é uma tentativa válida.\nLembre-se do intervalo de números possíveis (0-1000) e também de inserir apenas números inteiros.\n");
        } while((tentativa!=numeroGerado) || inputFlag);

    }

    public static void sudoku() {
        Random rand = new Random();
        int[][] matriz = new int[9][9];

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                matriz[x][y]=rand.nextInt(9)+1;

                    for (int z = 0; z < 9; z++){
                        if(z!=y){
                            if (matriz[x][y]==matriz[x][z]){
                                matriz[x][y]=rand.nextInt(9)+1;
                                z=0;
                            }
                        }
                        if (z!=x){
                            if (matriz[x][y]==matriz[z][y]){
                                matriz[x][y]=rand.nextInt(9)+1;
                                z=0;
                            }
                        }
                    }
                }
            }
        System.out.println(Arrays.deepToString(matriz));
        }
    }

