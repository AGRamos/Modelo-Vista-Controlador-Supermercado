/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Aplicacion;

import java.util.Random;

/**
 *
 * @author Rosalia
 */
public class NewClass {

    public static String pasarTablero(char[][] tableroCargado) {
        char[][] tablero = tableroCargado;
        String t = new String();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                t += tablero[i][j];
            }
        }
        return t;

    }

    public static char[][] cargarTablero(int col, int filas) {
        int tablero[][] = new int[col][filas];

        Random rd = new Random();
        int numAleatorio1 = rd.nextInt(col);
        int numAleatorio2 = rd.nextInt(col);

        for (int i = 0; i < 5; i++) {
            tablero[numAleatorio1][numAleatorio2] = 1;
            numAleatorio1 = rd.nextInt(col);
            numAleatorio2 = rd.nextInt(col);
        }

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != 1) {
                    tablero[i][j] = 0;
                }

            }
        }

        char tableroChar[][] = new char[col][filas];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == 0) {
                    tableroChar[i][j] = 'A';
                } else {
                    tableroChar[i][j] = 'B';
                }
            }
        }
        return tableroChar;
    }
}
