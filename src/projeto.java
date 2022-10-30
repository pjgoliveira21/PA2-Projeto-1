import java.util.Random;
import java.util.Scanner;
public class projeto {
    //Códigos para permitir cores
    static String AZUL="\u001B[34m";
    static String RESET="\u001B[0m";
    static String VERMELHO = "\u001B[31m";

    //Variável scanner para entrada do utilizador pelo terminal
    static Scanner userInput = new Scanner(System.in);

    //Variável para permitir geração de numeros aleatórios a partir da biblioteca java.util.Random
    static Random randomgen = new Random();
    public static void main(String[] args) {
        System.out.println("\n ---------------------------------------------------");
        System.out.println("| Bem-vindo ao 1º Projeto de Programação do Grupo 5 |");
        System.out.println("|       Paulo Oliveira     e     Rafael Cosme       |");
        System.out.println(" ---------------------------------------------------");
        boolean sair=false;
        do {
            //O switch receberá o valor retornado pelo menu (escolha do utilizador já validada)
            switch (menu()) {
                case 1 : {
                    padroes();
                    break;
                }
                case 2 : {
                    adivinha();
                    break;
                }
                case 3 : {
                    sudoku();
                    break;
                }
                case 4 : {
                    //O utilizador escolheu sair do programa
                    //é apresentada a mensagem de saída e acionada a flag de fim de programa
                    System.out.println("Obrigado por ter utilizado o programa, preesione ENTER para sair.");
                    userInput.nextLine();
                    sair=true;
                    break;
                }
            }
        } while(!sair);
    }
    public static int menu() {
        int escolhaMenu;

        System.out.println("\n --------/ "+AZUL+"Menu Principal"+RESET+" /--------\n");
        System.out.println(AZUL+"(1)"+RESET+" Desenho de padrões");
        System.out.println(AZUL+"(2)"+RESET+" Jogo do adivinha");
        System.out.println(AZUL+"(3)"+RESET+" Sudoku simplificado");
        System.out.println(AZUL+"(4)"+RESET+" Sair do programa\n");

        do {
            System.out.print("Escolha: ");
            //A escolha do utilizador é guardada na respetiva variável após passar pela função para validar o input
            escolhaMenu=ProjetoUtils.processarInput(userInput.nextLine());

            //Se o utilizador escolher uma opção fora dos limites do menu
            if(escolhaMenu<1 || escolhaMenu>4) {
                //No caso do utilizador inserir um espaço em branco, apenas ignora e prossegue a pedir um input novo
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
        System.out.println("\n --------/ "+AZUL+"Desenho de Padrões"+RESET+" /--------\n");
        do {
            System.out.print("Insira um numero entre 1 e 10: ");

            //O numero inserido pelo utilizador é guardada na respetiva variável após passar pela função para validar o input
            numeroInserido=ProjetoUtils.processarInput(userInput.nextLine());

            if(numeroInserido<1 || numeroInserido>10) {
                //No caso do utilizador inserir um espaço em branco, apenas ignora e prossegue a pedir um input novo
                if(numeroInserido==-2) continue;
                System.out.println(VERMELHO+"Número inválido" +
                                "\nLembre-se do intervalo de números possíveis (1-10) e também de inserir apenas números inteiros."
                                +RESET);
                continue;
            }
            break;

        }while(true);

        //Padrão A
        System.out.println("\n ----/ "+AZUL+"Padrão A"+RESET+" /----");
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
        System.out.println("\n ----/ "+AZUL+"Padrão B"+RESET+" /----");
        for (int i = numeroInserido; i >=1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

        System.out.println();

        //Padrão C
        System.out.println("\n ----/ "+AZUL+"Padrão C"+RESET+" /----");
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

        System.out.println("\n --------/ "+AZUL+"Jogo da Adivinha"+RESET+" /--------\n");
        System.out.println("O programa gerou um numero aleatório entre 0 e 1000.");
        System.out.println("Tente adivinhá-lo!");

        do {
            System.out.print(AZUL+"-> "+RESET);
            //O numero inserido pelo utilizador é guardada na respetiva variável após passar pela função para validar o input
            tentativa=ProjetoUtils.processarInput(userInput.nextLine());

            //Inserido espaço em branco
            if(tentativa==-2) continue;

            if(tentativa<0 || tentativa>1000) {
                System.out.print(VERMELHO+"Número inválido" +
                        "\nLembre-se do intervalo de números possíveis (0-1000) e também de inserir apenas números inteiros."
                        +RESET);
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
        System.out.println("\n ---------/ "+AZUL+"Sudoku Simplificado"+RESET+" /--------- \n");

        //Gerar array e guardá-lo na respetiva variável
        int[][] matriz = ProjetoUtils.gerarArray();
        //Mostrar tabela
        ProjetoUtils.mostrarArray(matriz);

        System.out.println("\nEscolha agora uma posição que contenha o numero 0, e siga os passos para o preencher");
        while(true) {

            System.out.print("Insira a coluna: ");
            int y=ProjetoUtils.processarInput(userInput.nextLine())-1;

            System.out.print("\nInsira a linha: ");
            int x=ProjetoUtils.processarInput(userInput.nextLine())-1;

            if (x < 0 || x > 8 || y < 0 || y > 8) {
                System.out.println(VERMELHO+"Posição inválida\n" +
                        "Lembre-se do intervalo de posições possíveis \"(1-9)\" e também de inserir apenas números inteiros."+RESET);
                continue;
            }

            else if (matriz[x][y] != 0) {
                System.out.println(VERMELHO+"A posição que inseriu não contém um 0 (contém " + matriz[x][y] + ")"+RESET);
                continue;
            }

            else {
                //Obter numeros possiveis da posição pretendida
                int[] possibilidades = ProjetoUtils.obterPossiveis(matriz, x, y);

                //Se a função retornar um vetor nulo (pré-programada para se comportar como tal se não existirem numeros possiveis)
                if (possibilidades.length == 0) {
                    System.out.println("A posição referida não contém numeros possiveis.");
                    continue;
                }

                //Se existirem numeros possiveis
                else {
                    System.out.print("Numeros possiveis: ");

                    //Listar numeros possiveis
                    for (int i = 0; i < 9; i++) {
                        if(possibilidades[i]!=0) System.out.print(possibilidades[i]+" ");
                    }

                    while(true) {
                        System.out.print("\nInsira o número possivel que deseja escolher: ");

                        //O numero inserido pelo utilizador é guardada na respetiva variável após passar pela função para validar o input
                        int numEscolhido = ProjetoUtils.processarInput(userInput.nextLine());

                        //Espaço em branco
                        if (numEscolhido == -2) continue;

                        //String não numérica
                        if (numEscolhido == -1) System.out.println(VERMELHO + "Insira um número válido!" + RESET);

                        else {
                            //Flag
                            boolean existe = false;
                            //Verificar se o numero escolhido existe dentro do array
                            for (int i = 0; i < 9; i++) {
                                if (numEscolhido == possibilidades[i] && numEscolhido != 0) {
                                    existe = true;
                                    break;
                                }
                            }
                            if (existe) {
                                //Finalmente aplicar o numero à tabela final
                                matriz[x][y] = numEscolhido;
                                System.out.println("Aqui está apresentada a tabela final com o número modificado sendo destacado a azul:\n");
                                //Exibir a tabela final
                                ProjetoUtils.mostrarArray(matriz, x, y);
                                break;
                            } else {
                                System.out.println(VERMELHO + "O número que inseriu não é um numero possivel." + RESET);
                            }
                        }
                    }
                }
            }
            break;
        }
    }
}