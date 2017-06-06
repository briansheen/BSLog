package com.example.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime locDateTime) {
        if(locDateTime == null){
            return null;
        }
        java.util.Date date = java.util.Date.from(locDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return new java.sql.Date(date.getTime());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date sqlDate) {
        if(sqlDate ==  null){
            return null;
        }
        java.util.Date date = new java.util.Date(sqlDate.getTime());
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}