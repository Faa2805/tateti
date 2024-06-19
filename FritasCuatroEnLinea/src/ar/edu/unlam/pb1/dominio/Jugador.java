package ar.edu.unlam.pb1.dominio;

public class Jugador {
	//Datos jugadores
	private String nombreJugador = "";
	private char simboloJugador;

		
	public Jugador(String nombreJugador, char simboloJugador) {
		this.nombreJugador = nombreJugador;
		this.simboloJugador = simboloJugador;

	}
	
	public String getNombreJugador() {
		return nombreJugador;
	}

	public char getSimboloJugador() {
		return simboloJugador;
	}

}
