package com.oop_pub.exceptions.ex2_3;

import java.util.List;

public class MainEx2 {
    public static void main(String[] args) {
        Calculator calculator = new DoubleCalculator();

        System.out.println(calculator.add(2d, 3d));
        System.out.println(calculator.divide(9d, 4d));
        System.out.println(calculator.average(List.of(1d, 2d, 3d, 4d)));

        try {
            System.out.println(calculator.add(null, 2d));
            System.out.println(calculator.divide(null, 4d));
            System.out.println(calculator.add(Double.POSITIVE_INFINITY - 1, 1d));
        } catch (Calculator.NullParameterException | Calculator.OverflowException | Calculator.UnderflowException e) {
            e.printStackTrace();
        }

        //3a) Datorita faptului ca toate clasele interne extind clasa RunTimeException atunci acestea sunt de tipul unchecked
        //3b) Nu ar fi un mecanism destul de util pentru un utilizator deoarece nu sunt implementate toate operatiile aritmetice
        // si nu sunt tratate toate cazurile de eroare (Ex. impartirea la 0)
    }
}
