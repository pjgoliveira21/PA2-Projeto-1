import java.util.Random;
import java.util.Scanner;
public class projeto {
    static String RESET="\u001B[0m";
    static String VERMELHO = "\u001B[31m";
    static Scanner userInput = new Scanner(System.in);
    static Random randomgen = new Random();
    public static void main(String[] args) {
        System.out.println("\n ---------------------------------------------------");
        System.out.println("| Bem-vindo ao 1º Projeto de Programação do Grupo 5 |");
        System.out.println("|       Paulo Oliveira     e     Rafael Cosme       |");
        System.out.println(" ---------------------------------------------------");
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
    public static int menu() {
        int escolhaMenu;

        System.out.println("\n --------/ Menu Principal /--------- \n");
        System.out.println("(1) Desenho de padrões");
        System.out.println("(2) Jogo do adivinha");
        System.out.println("(3) Sudoku simplificado");
        System.out.println("(4) Sair do programa\n");


        do {
            System.out.print("Escolha: ");
            escolhaMenu=ProjetoUtils.processarInput(userInput.nextLine());

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

            numeroInserido=ProjetoUtils.processarInput(userInput.nextLine());

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
            tentativa=ProjetoUtils.processarInput(userInput.nextLine());

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
        int[][] matriz = ProjetoUtils.gerarArray();
        ProjetoUtils.mostrarArray(matriz);
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

        int[] possibilidades = ProjetoUtils.obterPossiveis(matriz,x,y);

        if(possibilidades.length==0) System.out.println("A posição referida não contém numeros possiveis.");
        else {
            System.out.println("Numeros possiveis: ");
            for (int i = 0; i < 9; i++) if(possibilidades[i]!=0) System.out.print(possibilidades[i]+" ");

            int numEscolhido;
            while(true) {
                System.out.print("Insira o número possivel que deseja escolher: ");

                numEscolhido = ProjetoUtils.processarInput(userInput.nextLine());
                if(numEscolhido==0) {
                    System.out.println("Não pode inserir zeros.");
                    continue;
                }

                //Espaço em branco
                if(numEscolhido==-2) continue;

                //String não numérica
                if(numEscolhido==-1) System.out.println(VERMELHO+"Insira um número válido!"+RESET);

                else {
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
                        ProjetoUtils.mostrarArray(matriz,x,y);
                    }
                    else {
                        System.out.println(VERMELHO+"O número que inseriu não é um numero possivel."+RESET);
                        continue;
                    }
                    break;
                }
            }
        }
    }
}