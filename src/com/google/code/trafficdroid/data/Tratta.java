package com.google.code.trafficdroid.data;

public class Tratta implements Comparable<Tratta>
{
	private String tratta;
	private String velocitaSx;
	private String velocitaDx;
	
	public Tratta()
	{
	}

	public Tratta(String tratta, String velocitaSx, String velocitaDx)
	{
		this.tratta = tratta;
		this.velocitaSx = velocitaSx;
		this.velocitaDx = velocitaDx;
	}

	public String getTratta()
	{
		return tratta;
	}

	public void setTratta(String tratta)
	{
		this.tratta = tratta;
	}

	public String getVelocitaSx()
	{
		return velocitaSx;
	}

	public void setVelocitaSx(String velocitaSx)
	{
		this.velocitaSx = velocitaSx;
	}

	public String getVelocitaDx()
	{
		return velocitaDx;
	}

	public void setVelocitaDx(String velocitaDx)
	{
		this.velocitaDx = velocitaDx;
	}

	@Override
	public int compareTo(Tratta another)
	{
		return 0;
	}
}
