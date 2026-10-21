package com.gerenciamento.obra.entities.enums;


public enum StatusGasto {
	
	PENDENTE(50), PAGO(51), CANCELADO(52);

	
	private int code;

	private StatusGasto(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StatusGasto valueOf(int code) {
		for (StatusGasto value : StatusGasto.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("codigo do status_gasto invalido!");
	}
}
