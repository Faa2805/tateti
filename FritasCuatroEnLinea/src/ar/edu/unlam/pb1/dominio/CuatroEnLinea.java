package ar.edu.unlam.pb1.dominio;

import java.util.Random;

public class CuatroEnLinea {

	private static final int FILAS = 6;
	private static final int COLUMNAS = 7;
	private static final char VACIAS = '-';
	private String nombreJugador1 = "";
	private String nombreJugador2 = "";
	private final String MAQUINA = "PC";
	char simboloJugador1;
	char simboloJugador2;
	private Jugador jugadorActual;
	private boolean finalizo = false;
	private boolean empate = false;


	private char[][] tablero;
	private Jugador[] jugadores;

	// constructor partida dos jugadores
	public CuatroEnLinea(String jugador1, String jugador2) {
		this.jugadores = new Jugador[2];
		this.nombreJugador1 = jugador1;
		this.nombreJugador2 = jugador2;
		this.jugadores[0] = new Jugador(nombreJugador1, 'X');
		this.jugadores[1] = new Jugador(nombreJugador2, 'O');
		// this.ganador = null;
		this.finalizo = false;
		this.empate = false;
		// this.turno = "";
		this.jugadorActual = this.obtenerJugadorRandom();

		tablero = new char[FILAS][COLUMNAS];

		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				tablero[fila][columna] = VACIAS;
			}
		}
	}

	// constructor partida un jugador
	public CuatroEnLinea(String jugador1) {
		this.jugadores = new Jugador[2];
		this.nombreJugador1 = jugador1;
		this.nombreJugador2 = MAQUINA;
		this.jugadores[0] = new Jugador(nombreJugador1, 'X');
		this.jugadores[1] = new Jugador(MAQUINA, 'O');
		this.finalizo = false;
		this.empate = false;
		this.jugadorActual = this.obtenerJugadorRandom();

		tablero = new char[FILAS][COLUMNAS];

		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				tablero[fila][columna] = VACIAS;
			}
		}
	}

	public void mostrarTablero() {

		for (int fila = FILAS - 1; fila >= 0; fila--) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				System.out.print(tablero[fila][columna] + " ");
			}
			mensaje("");
		}
		mensaje("1 2 3");
	}

	public boolean hayGanador() {
		// verificar si completo horizontal
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS - 3; columna++) {
				if (tablero[fila][columna] != VACIAS && tablero[fila][columna] == tablero[fila][columna + 1]
						&& tablero[fila][columna] == tablero[fila][columna + 2]
						&& tablero[fila][columna] == tablero[fila][columna + 3]) {
					return true;
				}
			}
		}

		// verificar si completo vertical
		for (int fila = 0; fila < FILAS - 3; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (tablero[fila][columna] != VACIAS && tablero[fila][columna] == tablero[fila + 1][columna]
						&& tablero[fila][columna] == tablero[fila + 2][columna]
						&& tablero[fila][columna] == tablero[fila + 3][columna]) {
					return true;
				}
			}
		}

		// verificar si completo diagonal (izquierda a derecha)
		for (int fila = 0; fila < FILAS - 3; fila++) {
			for (int columna = 0; columna < COLUMNAS - 3; columna++) {
				if (tablero[fila][columna] != VACIAS && tablero[fila][columna] == tablero[fila + 1][columna + 1]
						&& tablero[fila][columna] == tablero[fila + 2][columna + 2]
						&& tablero[fila][columna] == tablero[fila + 3][columna + 3]) {
					return true;
				}
			}
		}

		// verificar si completo diagonal (derecha a izquierda)
		for (int fila = 0; fila < FILAS - 3; fila++) {
			for (int columna = 3; columna < COLUMNAS; columna++) {
				if (tablero[fila][columna] != VACIAS && tablero[fila][columna] == tablero[fila + 1][columna - 1]
						&& tablero[fila][columna] == tablero[fila + 2][columna - 2]
						&& tablero[fila][columna] == tablero[fila + 3][columna - 3]) {
					return true;
				}
			}
		}

		return false;
	}

	public String getJugadorActual() {
		return jugadorActual.getNombreJugador();
	}
	
	public char getSimboloActual() {
		return jugadorActual.getSimboloJugador();
	}
	

	public int pedirColumna(int columna) {
		return columna;
	}
	
	public int generarColumnaRandom() {
		int columnaRandom = (int) (Math.random() * 7) +1;

		return columnaRandom;
	}


	// que jugador comienza de forma aleatoria
	public Jugador obtenerJugadorRandom() {
		Random random = new Random();
		Jugador jugadorRandom = null;

		int numero = random.nextInt(9 - 0);

		if (this.esPar(numero)) {
			jugadorRandom = this.jugadores[0];
			mensaje("\nComienza " + jugadores[0].getNombreJugador() + " con el símbolo "
					+ jugadores[0].getSimboloJugador());
		} else if (!this.esPar(numero)) {
			jugadorRandom = this.jugadores[1];
			mensaje("\nComienza " + jugadores[1].getNombreJugador() + " con el símbolo "
					+ jugadores[1].getSimboloJugador());
		} else {
			mensaje("Ocurrió un problema");
		}

		return jugadorRandom;
	}

	public Jugador cambiarJugador() {
		if (this.jugadorActual == jugadores[0]) {
			jugadorActual = jugadores[1];
		} else {
			jugadorActual = jugadores[0];
		}

		return jugadorActual;
	}

	public boolean ubicarFicha(int columna) {
		if (columna < 0 || columna >= COLUMNAS || tablero[FILAS - 1][columna] != VACIAS) {
			// mensaje("ERROR. Columna inexistente");
			return false;
		}

		for (int fila = 0; fila < FILAS; fila++) {
			if (tablero[fila][columna] == VACIAS) {
				tablero[fila][columna] = jugadorActual.getSimboloJugador();
				return true;
			}
		}
		return false;
	}

	public boolean juegoFinalizado(CuatroEnLinea juegoActual, int columna) {
		
			// Colocar la ficha del jugador en la columna seleccionada
			if (juegoActual.ubicarFicha(columna)) {
				// Verificar si el jugador actual ha ganado
				if (juegoActual.hayGanador()) {
					juegoActual.mostrarTablero();
					mensaje("\033[32m ¡El jugador " + juegoActual.getJugadorActual() + " ha ganado! \u001B[0m");
					mensaje("──────▄▀▄─────▄▀▄\r\n"
							+ "─────▄█░░▀▀▀▀▀░░█▄\r\n"
							+ "─▄▄──█░░░░░░░░░░░█──▄▄\r\n"
							+ "█▄▄█─█░░▀░░┬░░▀░░█─█▄▄█");
					this.finalizo = true;
					return this.finalizo;
				} else if (juegoActual.tableroLleno() && !juegoActual.hayGanador()) { 
					// Verificar si el tablero está lleno (empate)
					mensaje("¡Empate!");
					mensaje("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢲⡜⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡘⠇⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⠼⠁⠀⠀⠀⠀⠀⠀⠀⢀⡴⠛⠉⡆⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⠀⣠⠋⠀⠀⢠⡇⠈⠧⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⢀⡴⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡀⠀⠀⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠒⢛⣛⣓⠲⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⣸⠁⠀⠀⠀⢀⣀⡤⠔⣶⡦⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⡏⠒⢤⣰⠃⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢫⠉⣷⠚⠉⠉⡀⡀⠝⢿⣥⠄⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⡇⠀⠀⣽⢀⠈⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣁⡄⡀⡏⣇⠀⠛⠁⠉⠀⢹⣿⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠰⠇⠀⠀⣿⣮⣆⡀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⡀⣽⠁⠉⠋⠀⠤⠶⠀⣸⣿⠃⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠹⣽⣻⡹⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠘⠀⡱⢧⣀⡛⡰⠂⣠⣦⡳⡾⠃⠀⠀⠀\r\n"
							+ "⢠⡎⠭⠭⣭⣉⡏⠀⠀⠀⠀⠀⠀⠀⠀⢹⣆⠀⠀⣸⡦⠄⠀⠚⠉⠉⠓⢲⠀⠀⠀⠀⢀⠞⢄⣄⡼⠁⠀⠀⠈⠉⠉⠉⠁⠀⠙⣆⠀⠀⠀\r\n"
							+ "⠀⠳⣄⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⣏⠈⣳⣤⡇⠀⠀⣴⣷⣖⡦⢄⠀⠀⠀⣠⠔⣁⢀⡠⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⠀⠀\r\n"
							+ "⠀⠀⠈⢦⡀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠉⢐⣅⣆⡏⠳⣦⢽⠾⢷⡭⠤⠖⡶⠉⢀⡶⠗⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠀\r\n"
							+ "⠀⠀⠀⠀⢹⠀⠀⠀⡞⢿⣿⣿⡷⠀⠰⠷⣄⡇⠈⠀⠀⠈⣹⠟⠼⠧⠤⠜⠓⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄\r\n"
							+ "⠀⠀⠀⠀⠈⣇⠀⠀⠧⠼⠛⣫⣖⢶⡓⠒⠉⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣇\r\n"
							+ "⠀⠀⠀⠀⠀⠘⢦⡀⠀⠀⢾⢿⣿⡷⠃⠀⠀⠀⢀⡼⠁⠀⠀⠀⠀⠀⠀⢀⡤⢖⡚⢩⣭⠉⣩⣭⠉⡝⠲⢤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠙⠦⣄⣀⠀⠀⢀⣀⣀⡠⠴⡞⡀⠀⠀⠀⠀⢀⡤⠞⠙⢮⠫⣆⢰⠏⠀⠹⢽⢀⢳⣽⣼⣯⠓⢦⡀⠀⠀⠀⠀⠀⠀⡇\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣽⡱⠷⢳⣏⠜⣷⣡⠇⣀⡤⠞⠉⠀⠀⠀⣀⣇⠿⠁⣿⣾⣦⢾⣎⢸⠿⠗⠛⠀⠈⠙⣦⠀⠀⠀⠀⣸⠁\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢾⣛⣿⣟⣾⣦⣝⡏⠁⠀⠀⠀⠀⢀⡞⠁⠸⡄⣧⢿⣘⠋⢿⠼⠏⡆⠀⠀⠀⠀⠀⠈⢧⠀⠀⣰⠃⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠉⠉⠀⢀⣽⠀⠀⠀⠀⠀⢠⠏⠀⠀⠀⢧⣻⡈⣏⣦⣶⣿⣼⠃⠀⠀⠀⠀⠀⠀⠈⣧⡴⠁⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⣄⠀⠮⡇⠀⠀⠀⢀⣠⣏⠀⠀⠀⠀⠸⠿⠯⠿⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠒⡇⠀⠀⢠⠏⠀⠈⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣏⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⣯⣸⣼⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⠀⠀⠀⠀⠀⠉⠓⠦⠤⣄⣀⣀⡤⠤⠞⠃⠀⠀⠀⠀⢠⠏⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
							+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠑⠒⠒⠒⠒⠒⠒⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
					this.empate = true;
					this.finalizo = true;
					return this.finalizo;
				} else {
					// Cambiar al siguiente jugador
					juegoActual.cambiarJugador();
					this.finalizo = false;
					return this.finalizo;
				}
			} else {
				mensaje("ERROR. Columna inexistente");
			}
			this.finalizo = false;
			return 	this.finalizo;
	}

	public boolean hayEmpate() {
		return this.empate;
	}

	public boolean getJuegoFinalizado() {
		boolean finaliza = this.finalizo;
		return finaliza;
	}

	public boolean tableroLleno() {

		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (tablero[fila][columna] == VACIAS) {
					return false;
				}
			}
		}

		return true;
	}

	private static void mensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private boolean esPar(int numero) {
		return numero % 2 == 0;
	}

}
