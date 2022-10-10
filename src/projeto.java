import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class projeto {
    static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        int menuChoice;
        do {
            System.out.println();
            System.out.println("(1) Desenho de padrões");
            System.out.println("(2) Jogo do adivinha");
            System.out.println("(3) Sudoku simplificado");
            System.out.print("-> ");

            menuChoice = Integer.parseInt(userInput.nextLine());

            if (menuChoice == 1) padroes();
            else if (menuChoice == 2) adivinha();
            else if (menuChoice == 3) sudoku();
            else System.out.println("Escolha inválida!");

        }while(menuChoice<1 || menuChoice>3);
    }
    public static void padroes() {
        int numeroInserido,numeroMostrar;
        do {
            System.out.println();
            System.out.print("Insira um numero entre 1 e 10: ");
            numeroInserido = Integer.parseInt(userInput.nextLine());
            if(numeroInserido<1 || numeroInserido>10) System.out.println("Numero inválido!");
            
        }while(numeroInserido<1 || numeroInserido>10);

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

