package com.company;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static char[][]map;
    public static final int SIZE = 3;
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static final char DOT_EMPTY = '*';
    public static Scanner sc = new Scanner(System.in);
    public  static Random random = new Random();
    public static int simbol;



    public static void main(String[] args) {
        System.out.println("Сейчас начнётся игра крестики-нолики против компьютера.");
        System.out.println("Вам предоставляется выбрать символ которым вы будете играть.");
        System.out.println("Чтобы выбрать сивол \"Х\" нажмите 1, чтобы выбрать символ \"0\" намите 0");
        simbol = sc.nextInt();
        if (simbol == 1) {
            System.out.println("Вы играете символом Х и делаете ход первым.");
            initMap();
            printMap();
            while (true) {

                human();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Вы победили!");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья.");
                    break;
                }
                mashine();
                printMap();
                if (checkWin(DOT_0)) {
                    System.out.println("Победил компьютер.");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья.");
                    break;
                }
            }
        }
        if (simbol == 0) {
            System.out.println("Вы выбрали символ 0, первым ходит компьютер.");
            initMap();
            printMap();
            while (true) {
                mashine();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил компьютер.");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья.");
                    break;
                }
                human();
                printMap();
                if (checkWin(DOT_0)) {
                    System.out.println("Вы победили!");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья.");
                    break;
                }
            }
        }
    }

    public static boolean checkWin(char sim){
        for(int i = 0; i < SIZE; i++) {
            int x = 0, y = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == sim) x++;
                if (map[j][i] == sim) y++;
                if (x == 3 || y == 3) return true;
            }
        }
        int t = 0, u = 0;
        for(int i = 0; i < SIZE; i++) {
            if(map[i][i] == sim) t++;
            if(map[i][2-i] == sim) u++;
            if(t==3 || u==3) return true;

        }
        return false;
    }

    public static boolean draw(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void mashine(){
        int x, y;

        do{
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }while (!correct(x, y));
        System.out.println("Компьютер поставил свой символ в точку " + (x+1) + " " + (y+1));
        if(simbol==0) map[y][x] = DOT_X;
        else map[y][x] = DOT_0;
    }

    public static void human() {
        int x, y;

        do {
            System.out.println("Введите координаты символа в формате Х-столбец Y-строка");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }while(!correct(x,y));
        if(simbol==1) map[y][x] = DOT_X;
        else map[y][x] = DOT_0;
    }


    public static boolean correct(int x, int y){
        if(x<0 || x>=SIZE || y<0 || y>=SIZE) return false;
        if(map[y][x] == DOT_EMPTY)return true;
        return false;
    }

    public static void initMap(){
        map = new char[SIZE][SIZE];
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap(){
        for(int i=0; i <=SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i<SIZE;i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j<SIZE;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
