package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;
import ar.edu.unlam.pb1.dominio.*;

public class TaTeTi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = new Tablero();
        tablero.inicializar();
        tablero.mostrar();

        Jugador jugadorX = new Jugador('X');
        Jugador jugadorO = new Jugador('O');

        while (!tablero.juegoTerminado(jugadorX) && !tablero.juegoTerminado(jugadorO)) {
            jugar(tablero, jugadorX, scanner);
            tablero.mostrar();
            if (tablero.hayGanador(jugadorX)) {
                System.out.println("¡Jugador X ha ganado!");
                break;
            }

            if (tablero.tableroLleno()) {
                System.out.println("¡Empate! El tablero está lleno.");
                break;
            }

            jugar(tablero, jugadorO, scanner);
            tablero.mostrar();
            if (tablero.hayGanador(jugadorO)) {
                System.out.println("¡Jugador O ha ganado!");
                break;
            }
        }

        scanner.close();
    }

    private static void jugar(Tablero tablero, Jugador jugador, Scanner scanner) {
        int fila = 0, columna = 0;

        do {
            System.out.print("Jugador " + jugador.getSimbolo() + ", ingresa fila (1-3) y columna (1-3): ");
            try {
                fila = scanner.nextInt() - 1;
                columna = scanner.nextInt() - 1;
            } catch (Exception e) {
                System.out.println("Entrada no válida. Ingresa números válidos.");
                scanner.nextLine(); 
                continue;
            }

            if (!tablero.esMovimientoValido(fila, columna)) {
                System.out.println("Movimiento no válido. Intenta de nuevo.");
            }

        } while (!tablero.esMovimientoValido(fila, columna));

        tablero.realizarMovimiento(jugador, fila, columna);
    }
}
