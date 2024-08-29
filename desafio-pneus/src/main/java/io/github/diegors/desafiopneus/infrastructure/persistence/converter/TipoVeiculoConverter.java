package io.github.diegors.desafiopneus.infrastructure.persistence.converter;

import io.github.diegors.desafiopneus.domain.model.enums.TipoVeiculo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoVeiculoConverter implements AttributeConverter<TipoVeiculo, String> {

    @Override
    public String convertToDatabaseColumn(TipoVeiculo tipoVeiculo) {
        return tipoVeiculo != null ? tipoVeiculo.name() : null; // Converte o enum para string
    }

    @Override
    public TipoVeiculo convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? TipoVeiculo.valueOf(dbData.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para TipoVeiculo: " + dbData, e);
        }
    }
}
