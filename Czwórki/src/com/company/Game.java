package com.company;

import java.util.Scanner;

public class Game {
    static String[][] array = new String[6][7];





    public static String[][] clearArray(String[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                array[i][j] = " ";
            }
        }
        return array;
    }

    public static void Area(String[][] array) {
        array = colorsCoins(array);
        System.out.println("|" + array[5][0] + "|" + array[5][1] + "|" + array[5][2] + "|" + array[5][3] + "|" + array[5][4] + "|" + array[5][5] + "|" + array[5][6] + "|" );
        System.out.println("|" + array[4][0] + "|" + array[4][1] + "|" + array[4][2] + "|" + array[4][3] + "|" + array[4][4] + "|" + array[4][5] + "|" + array[4][6] + "|" );
        System.out.println("|" + array[3][0] + "|" + array[3][1] + "|" + array[3][2] + "|" + array[3][3] + "|" + array[3][4] + "|" + array[3][5] + "|" + array[3][6] + "|" );
        System.out.println("|" + array[2][0] + "|" + array[2][1] + "|" + array[2][2] + "|" + array[2][3] + "|" + array[2][4] + "|" + array[2][5] + "|" + array[2][6] + "|" );
        System.out.println("|" + array[1][0] + "|" + array[1][1] + "|" + array[1][2] + "|" + array[1][3] + "|" + array[1][4] + "|" + array[1][5] + "|" + array[1][6] + "|" );
        System.out.println("|" + array[0][0] + "|" + array[0][1] + "|" + array[0][2] + "|" + array[0][3] + "|" + array[0][4] + "|" + array[0][5] + "|" + array[0][6] + "|" );
    }

    public static void game() {
        clearArray(array);
        while(true){
            Area(array);
            playerRedMove(array);
            if(checkingWIN(array)){
                System.out.println("Koniec gry! wygrał gracz czerwony !");
                Area(array);
                System.exit(0);
            }else if(checkingForRemis(array)){
                System.out.println("REMIS KONIEC GRY !");
                Area(array);
                System.exit(0);
            }
            Area(array);
            playerYellowMove(array);
            if(checkingWIN(array)){
                System.out.println("Koniec gry! wygrał gracz żółty !");
                Area(array);
                System.exit(0);
            }else if(checkingForRemis(array)){
                System.out.println("REMIS KONIEC GRY !");
                Area(array);
                System.exit(0);
            }
            }
    }


    public static String[][] playerRedMove(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColors.BLUE_BOLD + "Umieść czerwony żeton w kolumnie (0 - 6): " + ConsoleColors.RESET);
        int column;
        while (true) {
            String coin;
            try {
                coin = scanner.nextLine();
                column = Integer.parseInt(coin);
                int row = emptyCell(array, column);
                array[row][column] = ConsoleColors.RED + "C" + ConsoleColors.RESET;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nie ma takiej kolumny");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Możesz wybrać jedynie kolumny od 0 do 6.");
            }
        }
        return array ;
    }

    public static String[][] playerYellowMove(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColors.BLUE_BOLD + "Umieść żółty żeton w kolumnie (0 - 6): " + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW);
        int column;
        while (true) {
            String coin;
            try {
                coin = scanner.nextLine();
                column = Integer.parseInt(coin);
                int row = emptyCell(array, column);
                array[row][column] = "Ż";
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nie ma takiej kolumny jak ");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wybrałeś kolumne spoza zakresu 0-6, lub wybrałeś kolumnę w której nie ma już miejsca.");
            }
        }
        System.out.print(ConsoleColors.RESET);
        return array;
    }

    public static String[][] colorsCoins(String[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if(array[i][j].equals("C")){
                    array[i][j] = ConsoleColors.RED + "C" + ConsoleColors.RESET;
                }
                else if(array[i][j].equals("Ż")){
                    array[i][j] = ConsoleColors.YELLOW + "Ż" + ConsoleColors.RESET;
                }
            }
        }
        return array;
    }


    public static int emptyCell(String[][] area, int column) {
        for(int i = 0 ; i < area.length; i++) {
            if(area[i][column].equals(" ")){
                return i;
            }
        }
        return -1;
    }

    public static boolean checkingWIN(String[][] area) {
        for (int row = 0; row < area.length; row++) {
            for (int column = 0; column < area[row].length - 4; column++) {
                if (!area[row][column].equals(" ") && area[row][column].equals(area[row][column + 1]) && area[row][column].equals(area[row][column + 2]) && area[row][column].equals(area[row][column + 3])) {
                    return true;
                }
            }
        }

        for (int row = 0; row < area.length - 4; row++) {
            for (int column = 0; column < area[row].length; column++) {
                if (!area[row][column].equals(" ") && area[row][column].equals(area[row + 1][column]) && area[row][column].equals(area[row + 2][column]) && area[row][column].equals(area[row + 3][column])) {
                    return true;
                }
            }
        }

        for (int row = 0; row < area.length - 4; row++) {
            for (int column = 0; column < area[row].length - 4; column++) {
                if (!area[row][column].equals(" ") && area[row][column].equals(area[row + 1][column + 1]) && area[row][column].equals(area[row + 2][column + 2]) && area[row][column].equals(area[row + 3][column + 3])) {
                    return true;
                }
            }
        }

        for (int row = area.length - 1 ; row > area.length - 4; row--) {
            for (int column = 0; column < area[row].length - 4; column++) {
                if (!area[row][column].equals(" ") && area[row][column].equals(area[row - 1][column + 1]) && area[row][column].equals(area[row - 2][column + 2]) && area[row][column].equals(area[row - 3][column + 3])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkingForRemis(String[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if (!array[i][j].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }





}
