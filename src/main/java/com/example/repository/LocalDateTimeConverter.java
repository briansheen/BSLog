package com.example.repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        if(locDateTime == null){
            return null;
        }
//        java.util.Date date = java.util.Date.from(locDateTime.atZone(ZoneId.systemDefault()).toInstant());

//        return new java.sql.Timestamp(date.getTime());
        return Timestamp.valueOf(locDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        if(sqlTimestamp ==  null){
            return null;
        }
        return sqlTimestamp.toLocalDateTime();
//        java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
//        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}