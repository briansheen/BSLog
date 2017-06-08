package com.example.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

/**
 * Created by bsheen on 6/7/17.
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime locTime) {
        if(locTime == null){
            return null;
        }
//        java.util.Date date = java.util.Date.from(locDateTime.atZone(ZoneId.systemDefault()).toInstant());

//        return new java.sql.Timestamp(date.getTime());
        return Time.valueOf(locTime);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlTime) {
        if(sqlTime ==  null){
            return null;
        }
        return sqlTime.toLocalTime();
//        java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
//        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
