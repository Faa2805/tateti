package ar.edu.unlam.pb1.dominio;

public class Tablero {
    private static final int FILAS = 3;
    private static final int COLUMNAS = 3;
    private char[][] tablero = new char[FILAS][COLUMNAS];

    public void inicializar() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                tablero[fila][columna] = '-';
            }
        }
    }

    public void mostrar() {
        System.out.println("-------------");
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                System.out.print("| " + tablero[fila][columna] + " ");
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    public boolean esMovimientoValido(int fila, int columna) {
        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS || tablero[fila][columna] != '-') {
            System.out.println("Movimiento no válido. Intenta de nuevo.");
            return false;
        }
        return true;
    }

    public void realizarMovimiento(Jugador jugador, int fila, int columna) {
        tablero[fila][columna] = jugador.getSimbolo();
    }

    public boolean hayGanador(Jugador jugador) {
        // Verificar filas y columnas
        for (int i = 0; i < FILAS; i++) {
            if (((tablero[i][0] == jugador.getSimbolo() || tablero[i][0] == jugador.getSimbolo()) && 
                 (tablero[i][1] == jugador.getSimbolo() || tablero[i][1] == jugador.getSimbolo()) && 
                 (tablero[i][2] == jugador.getSimbolo() || tablero[i][2] == jugador.getSimbolo())) ||
                ((tablero[0][i] == jugador.getSimbolo() || tablero[0][i] == jugador.getSimbolo()) && 
                 (tablero[1][i] == jugador.getSimbolo() || tablero[1][i] == jugador.getSimbolo()) && 
                 (tablero[2][i] == jugador.getSimbolo() || tablero[2][i] == jugador.getSimbolo()))) {
                return true;
            }
        }

        // Verificar diagonales
        return ((tablero[0][0] == jugador.getSimbolo() || tablero[0][0] == jugador.getSimbolo()) && 
                (tablero[1][1] == jugador.getSimbolo() || tablero[1][1] == jugador.getSimbolo()) && 
                (tablero[2][2] == jugador.getSimbolo() || tablero[2][2] == jugador.getSimbolo())) ||
               ((tablero[0][2] == jugador.getSimbolo() || tablero[0][2] == jugador.getSimbolo()) && 
                (tablero[1][1] == jugador.getSimbolo() || tablero[1][1] == jugador.getSimbolo()) && 
                (tablero[2][0] == jugador.getSimbolo() || tablero[2][0] == jugador.getSimbolo()));
    }

    public boolean tableroLleno() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (tablero[fila][columna] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean juegoTerminado(Jugador jugador) {
        if (hayGanador(jugador)) {
            // Si hay un ganador, el juego ha terminado
            return true;
        }

        if (tableroLleno()) {
            // Si el tablero está lleno y no hay ganador, el juego ha terminado en empate
            System.out.println("¡Empate! El tablero está lleno.");
            return true;
        }

        // El juego no ha terminado
        return false;
    }
}
