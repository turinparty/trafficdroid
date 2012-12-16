package it.localhost.trafficdroid.dto;

public class PatenteDTO {
	private String numeoPatente;
	private String saldo;
	private String scadenzaPatente;

	public void setNumeoPatente(String numeoPatente) {
		this.numeoPatente = numeoPatente;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public void setScadenzaPatente(String scadenzaPatente) {
		this.scadenzaPatente = scadenzaPatente;
	}

	public String getNumeoPatente() {
		return numeoPatente;
	}

	public String getSaldo() {
		return saldo;
	}

	public String getScadenzaPatente() {
		return scadenzaPatente;
	}
}
