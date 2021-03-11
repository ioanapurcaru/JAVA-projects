package com.oop_pub.exceptions.ex2_3;

import java.util.Collection;

public class DoubleCalculator implements Calculator {
    @Override
    public Double add(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException("Parametru null");
        }
        double suma = nr1 + nr2;
        if (suma == Double.POSITIVE_INFINITY) {
            throw new OverflowException("Suma prea mare");
        }
        if (suma == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException("Suma prea mica");
        }
        return nr1 + nr2;
    }

    @Override
    public Double divide(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException("Parametru null");
        }

        return nr1 / nr2;
    }

    @Override
    public Double average(Collection<Double> numbers) {

        double suma = numbers.stream()
                .mapToDouble(n -> n)
                .sum();

        if (suma == Double.POSITIVE_INFINITY) {
            throw new OverflowException("Suma prea mare");
        }
        if (suma == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException("Suma prea mica");
        }

        return suma / numbers.size();
    }


}
