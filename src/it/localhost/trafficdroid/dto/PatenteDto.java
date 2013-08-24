package it.localhost.trafficdroid.dto;

public class PatenteDto extends BaseDto {
	private String numeoPatente;
	private String saldo;
	private String scadenzaPatente;

	public PatenteDto(boolean success, String numeoPatente, String saldo, String scadenzaPatente) {
		super(success);
		this.numeoPatente = numeoPatente;
		this.saldo = saldo;
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
