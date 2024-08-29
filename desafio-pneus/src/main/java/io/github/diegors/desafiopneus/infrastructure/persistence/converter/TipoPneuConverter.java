package io.github.diegors.desafiopneus.infrastructure.persistence.converter;

import io.github.diegors.desafiopneus.domain.model.enums.TipoPneu;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPneuConverter implements AttributeConverter<TipoPneu, String> {

    @Override
    public String convertToDatabaseColumn(TipoPneu tipoPneu) {
        return tipoPneu != null ? tipoPneu.name() : null; // Converte o enum para string
    }

    @Override
    public TipoPneu convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? TipoPneu.valueOf(dbData.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para TipoPneu: " + dbData, e);
        }
    }
}
