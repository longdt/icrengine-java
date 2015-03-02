package com.eprotea.icrengine;

public class Result {
	private double amount;
	private double confidence;
	
	public Result(double amount, double confidence) {
		this.amount = amount;
		this.confidence = confidence;
	}

	public double getAmount() {
		return amount;
	}

	public double getConfidence() {
		return confidence;
	}
}
