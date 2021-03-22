import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


class Main {

    public static char firstMove = 'X';
    public static boolean gameWined = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char [][] table = new char [3][3];

        table = fillTable(table, getFillNewGame());

        printTable(table);

        for (int i = 0; i < 9; i++) {

            table = changeTable(table);

            printTable(table);

            analyseTable(table);

            if (gameWined) break;

            table = EasyChangeTable(table);

            printTable(table);

            analyseTable(table);

            if (gameWined) break;
        }



    }
    // Ввод начального поля с Х и О, анализ строки ввода и определение кто ходит первый.
    public static char [] getFill() {
        Scanner scanner = new Scanner(System.in);

        boolean check = false;
        int count = 0;
        int Xes = 0;
        int Os = 0;

        String fillString = "";
        char [] fillCharArr = new char[9];

        while (!check) {
            count = 0;
            Xes = 0;
            Os = 0;
            System.out.print("Enter the cells : > ");

            fillString = scanner.nextLine();
            fillCharArr = fillString.toCharArray();
            for (char c : fillCharArr) {
                if (c == 'X' || c == 'O' || c == '_') {
                    count++;
                    if (c == 'X') {
                        Xes++;
                    }
                    if (c == 'O') {
                        Os++;
                    }
                }
            }
            if (count == 9) check = true;
        }
        if (Os < Xes) {
            Main.firstMove = 'O';
        }
        return fillCharArr;
    }
    // Создание пустрого поля в виде массива символов
    public static char [] getFillNewGame (){
        char [] fillCharArr = new char[9];

        Arrays.fill(fillCharArr, '_');
        return fillCharArr;
    }

    public static char [][] fillTable(char[][] table, char[] symbols) {
        int count = 0;
        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[0].length; j++)
            {
                if (symbols[count] == '_')
                {
                    table[i][j] = ' ';
                    count++;
                    continue;
                }
                table[i][j] = symbols[count];
                count++;
            }
        }
        return table;
    }
    // печатает таблицу
    public static void printTable(char [][] table) {

        System.out.println("---------");

        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[0].length; j++)
            {
                if (j == 0) {
                    System.out.print("| " + table[i][j]);
                }
                if (j == 1) {
                    System.out.print(" " + table[i][j]);
                }
                if (j == 2) {
                    System.out.println(" " + table[i][j] + " |");
                }
            }
        }
        System.out.println("---------");
    }

    public static char [][] changeTable(char[][] table){
        Scanner scanner = new Scanner(System.in);


        boolean check = false;

        int x = 0;
        int y = 0;

        while (!check){
            System.out.println("Enter the coordinates: ");

            String firstNumber = scanner.next();

            if (firstNumber.length() == 1 && Character.isDigit(firstNumber.charAt(0))) {
                x = Integer.parseInt(firstNumber);
            }
            else {
                System.out.println("You should enter numbers!");
                continue;
            }

            String secondNumber = scanner.next();

            if (secondNumber.length() == 1 && Character.isDigit(secondNumber.charAt(0))) {
                y = Integer.parseInt(secondNumber);
            }
            else {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (x >= 1 && x <= 3 && y >= 1 && y <= 3)
            {
                if (table[x-1][y-1] == ' ') {
                    check = true;
                }
                else {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
            }
            else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }

        // Указать с чего начинается ход Х/О

        table[x-1][y-1] = firstMove;

        return table;
    }

    public static void analyseTable(char [][] table) {

        // x win || o win  drow
        for (int i = 0; i < table.length; i++) {
            if (table [i][0] == 'X' && table [i][0] == table [i][1] && table [i][1] == table [i][2]) {
                gameWined = true;
                System.out.println("X wins");
                return;
            }
            if (table [i][0] == 'O' && table [i][0] == table [i][1] && table [i][1] == table [i][2]) {
                gameWined = true;
                System.out.println("O wins");
                return;
            }
        }

        for (int i = 0; i < table[0].length; i++) {
            if (table [0][i] == 'X' && table [0][i] == table [1][i] && table [1][i] == table [2][i]) {
                gameWined = true;
                System.out.println("X wins");
                return;
            }
            if (table [0][i] == 'O' && table [0][i] == table [1][i] && table [1][i] == table [2][i]) {
                gameWined = true;
                System.out.println("O wins");
                return;
            }
        }

        if (table [0][0] == 'X' && table [0][0] == table [1][1] && table [1][1] == table [2][2])
        {
            gameWined = true;
            System.out.println("X wins");
            return;
        }
        else if (table [0][0] == 'O' && table [0][0] == table [1][1] && table [1][1] == table [2][2])
        {
            gameWined = true;
            System.out.println("O wins");
            return;
        }

        if (table [0][2] == 'X' && table [0][2] == table [1][1] && table [1][1] == table [2][0])
        {
            gameWined = true;
            System.out.println("X wins");
            return;
        }
        else if (table [0][2] == 'O' && table [0][2] == table [1][1] && table [1][1] == table [2][0])
        {
            gameWined = true;
            System.out.println("O wins");
            return;
        }

        int counter = 0;

        for (int i = 0; i < table.length; i++)
        {
            for (int j = 0; j < table[0].length; j++)
            {
                if (table[i][j] == 'X' || table[i][j] == 'O')
                {
                    counter++;
                }
            }
        }

        if (counter == 9)
        {
            gameWined = true;
            System.out.println("Draw");
            return;
        }
    }

    public static char [][] EasyChangeTable(char[][] table){

        Random random = new Random();

        boolean check = false;

        int x = 0;
        int y = 0;

        System.out.println("Making move level \"easy\"");

        while (!check){

            x = random.nextInt(3);
            y = random.nextInt(3);

            if (table[x][y] == ' ') {
                check = true;
            }

        }

        table[x][y] = 'O';

        return table;
    }

}