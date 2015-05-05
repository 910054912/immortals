package com.core;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by Mr.Peabody on 2015/4/8.
 */
public class IdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        String dateId = Long.toString(System.currentTimeMillis(), 36);


        Double numIds = Math.random();
        String numIds3 = numIds.toString().substring(2, 8);
        String numId = Integer.toString(Integer.parseInt(numIds3), 36);

        StringBuilder stringBuilder = new StringBuilder(16);

        stringBuilder
                //.append(fillWithZero(branchId, 4))
                .append(fillWithZero(dateId, 8))
                .append(fillWithZero(numId, 8));

        return stringBuilder.toString();
    }

    private String fillWithZero(String str, Integer length) {

        StringBuilder stringBuilder = new StringBuilder(length);

        stringBuilder.append(str);
        if (str.length() > length) {
            stringBuilder.deleteCharAt(length);
        } else if (str.length() < length) {
            int tempLength = length - str.length();
            for (int i = 0; i < tempLength; i++) {
                stringBuilder.insert(0, "0");
            }
        }

        return stringBuilder.toString();
    }
}
