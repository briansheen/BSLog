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
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        if(locDate == null){
            return null;
        }
//        java.util.Date date = java.util.Date.from(locDateTime.atZone(ZoneId.systemDefault()).toInstant());

//        return new java.sql.Timestamp(date.getTime());
        return Date.valueOf(locDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        if(sqlDate ==  null){
            return null;
        }
        return sqlDate.toLocalDate();
//        java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
//        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}