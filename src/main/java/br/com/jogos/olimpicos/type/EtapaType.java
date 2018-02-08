package br.com.jogos.olimpicos.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Tipo das Etapas
 * @author gabir
 *
 */
public enum EtapaType {
	ELIMINATORIAS, OITAVAS, QUARTAS, SEMIFINAL, FINAL;
	
	@JsonValue
	public String toValue() {
		return name();
	}

}
