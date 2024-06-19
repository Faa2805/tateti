package ar.edu.unlam.pb1.interfaz;
//import ar.edu.unlam.pb1.dominio.MenuJuego;
//import ar.edu.unlam.pb1.dominio.Jugador;
import ar.edu.unlam.pb1.dominio.CuatroEnLinea;
import java.util.Scanner;

public class PruebaCuatroEnLinea {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		boolean salirDelJuego = false;
		boolean opcionCorrecta = false;

		do {
			mensaje("\033[35m \n【4】　【E】【N】　【L】【I】【N】【E】【A】 \u001B[0m");
			mensaje("\n¡Bienvenido!");
			mensaje("--------------------------");
			mensaje("Ingrese el numero correspondiente a jugadores: ");
			mostrarMenu();
			int opcionElegida = teclado.nextInt();

			switch (opcionElegida) {
			case 1:
				opcionCorrecta = true;
				// Pedimos nombre del jugador
				String nombreJugador = ingresarPalabra("\nIngrese el nombre del jugador: ");

				// Creamos el juego
				CuatroEnLinea juegoUnJugador = new CuatroEnLinea(nombreJugador);

				do {
					jugarUnJugador(juegoUnJugador);
				} while ((!juegoUnJugador.hayGanador() || !juegoUnJugador.hayEmpate()) && !juegoUnJugador.getJuegoFinalizado());
				
				
				mensaje("\nDesea volver a jugar del juego? (true/false): ");
				boolean reiniciarJuego = teclado.nextBoolean();
				if (reiniciarJuego == true) {
					opcionCorrecta = false;
					salirDelJuego = false;
				} else {
					mensaje("\nSaliendo del juego.");
					opcionCorrecta = true;
					salirDelJuego = true;

				}
				
				break;
			case 2:
				opcionCorrecta = true;
				// Pedimos nombre de los dos jugadores
				String nombreJugador1 = ingresarPalabra("\nIngrese el nombre del jugador 1: ");
				String nombreJugador2 = ingresarPalabra("\nIngrese el nombre del jugador 2: ");

				// Creamos el juego
				CuatroEnLinea juegoDosJugadores = new CuatroEnLinea(nombreJugador1, nombreJugador2);
				
				do {
					jugar(juegoDosJugadores);
				} while ((!juegoDosJugadores.hayGanador() || !juegoDosJugadores.hayEmpate()) && !juegoDosJugadores.getJuegoFinalizado());
							

				mensaje("\nDesea volver a jugar? (true/false): ");
				boolean reiniciarJuego2 = teclado.nextBoolean();
				if (reiniciarJuego2 == true) {
					opcionCorrecta = false;
					salirDelJuego = false;
				} else {
					mensaje("\nSaliendo del juego.");
					opcionCorrecta = true;
					salirDelJuego = true;

				}
				

				break;
			case 3:
				mostrarInstruccion();
				salirDelJuego = false;
				break;
			case 4:
				mensaje("Saliendo del juego");
				salirDelJuego = true;

				break;
			default:
				mensaje("\u001B[31m La opción ingresada no es valida \u001B[0m" + "\n");
				opcionCorrecta = false;

				break;
			}
			
		} while (!salirDelJuego && !opcionCorrecta);
		//}
	}

	// modo de juego de dos jugadores
	private static void jugar(CuatroEnLinea juegoDosJugadores) {
		boolean finJuego = false;
		int columna;

		while (!finJuego) {

			if ((!juegoDosJugadores.hayGanador() || !juegoDosJugadores.hayEmpate()) && !juegoDosJugadores.getJuegoFinalizado()) {
				// Mostrar el tablero
				juegoDosJugadores.mostrarTablero();
				// Obtener la columna elegida por el jugador
				mensaje("Ingrese el numero de la columna donde desea colocar su ficha: ");
				columna = teclado.nextInt();
				juegoDosJugadores.pedirColumna(--columna);
				finJuego = juegoDosJugadores.juegoFinalizado(juegoDosJugadores, columna);
			} 

			if ((!juegoDosJugadores.hayGanador() || !juegoDosJugadores.hayEmpate()) && !juegoDosJugadores.getJuegoFinalizado()) {
				mensaje("\nJuega " + juegoDosJugadores.getJugadorActual() + " con el símbolo "
						+ juegoDosJugadores.getSimboloActual());
			} else {
				break;
			}
		}
	}

	// modo de juego de un jugador
	private static void jugarUnJugador(CuatroEnLinea juegoUnJugador) {
		boolean finJuego = false;
		int columna;

		while (!finJuego) {

			if (juegoUnJugador.getJugadorActual() != "PC") {

				if ((!juegoUnJugador.hayGanador() || !juegoUnJugador.hayEmpate()) && !juegoUnJugador.getJuegoFinalizado()) {
					// Mostrar el tablero
					juegoUnJugador.mostrarTablero();
					// Obtener la columna elegida por el jugador
					mensaje("Ingrese el numero de la columna donde desea colocar su ficha: ");
					columna = teclado.nextInt();
					juegoUnJugador.pedirColumna(--columna);
					finJuego = juegoUnJugador.juegoFinalizado(juegoUnJugador, columna);
				}

				if ((!juegoUnJugador.hayGanador() || !juegoUnJugador.hayEmpate()) && !juegoUnJugador.getJuegoFinalizado()) {
					mensaje("\nJuega " + juegoUnJugador.getJugadorActual() + " con el símbolo "
							+ juegoUnJugador.getSimboloActual());
				} else {
					finJuego = true;
					break;
				}

			} else {
				if ((!juegoUnJugador.hayGanador() || !juegoUnJugador.hayEmpate()) && !juegoUnJugador.getJuegoFinalizado()) {
				juegoUnJugador.mostrarTablero();
				// Obtener la columna random del jugador pc
				columna = juegoUnJugador.generarColumnaRandom();
				juegoUnJugador.pedirColumna(--columna);
				finJuego = juegoUnJugador.juegoFinalizado(juegoUnJugador, columna);
				}
				
				if ((!juegoUnJugador.hayGanador() || !juegoUnJugador.hayEmpate()) && !juegoUnJugador.getJuegoFinalizado()) {				
				mensaje("\nJuega " + juegoUnJugador.getJugadorActual() + " con el símbolo "
						+ juegoUnJugador.getSimboloActual());
				} else {
					finJuego = true;
					break;
				}
			}
			
		}
	}

	private static void mostrarInstruccion() {
		mensaje("\nInstrucciones: \n El objetivo de este juego consiste en colocar cuatro fichas \n en una fila contínua vertical, horizontal o diagonalmente. \n Ambos jugadores sitúan sus fichas (una por movimiento) en el tablero, eligiendo el número de la COLUMNA. \n La regla para colocarlas consiste en que estas siempre \"caen hasta abajo\". \n Es decir una ficha puede ser colocada bien en la parte inferior de una columna o bien sobre otra de alguna otra columna.\n");

	}

	private static void mostrarMenu() {
		mensaje("-------MENU-------");
		mensaje("1. Un Jugador");
		mensaje("2. Dos Jugadores");
		mensaje("3. Instrucciones de juego");
		mensaje("4. Salir");
	}

	private static String ingresarPalabra(String mensaje) {
		mensaje(mensaje);
		return teclado.next();
	}

	private static void mensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
