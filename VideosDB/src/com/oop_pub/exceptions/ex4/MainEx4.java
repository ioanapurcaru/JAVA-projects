package com.oop_pub.exceptions.ex4;

import java.util.EnumSet;

public class MainEx4 {

    public static void main(final String[] args) {
        LoggerBase logger1 = new ConsoleLogger(LogLevel.all());
        LoggerBase logger2 = logger1.setNext(new EmailLogger(EnumSet.of(LogLevel.FunctionalMessage,
                LogLevel.FunctionalError)));
        LoggerBase logger3 = logger2.setNext(new FileLogger(EnumSet.of(LogLevel.Warning, LogLevel.Error)));



        logger1.message("Se execută metoda ProcessOrder()", LogLevel.Debug);
        logger1.message("Comanda a fost procesată cu succes", LogLevel.Info);
        logger1.message("Datele despre adresa clientului lipsesc din baza de date a filialei", LogLevel.Warning);
        logger1.message("Detele despre adresa clientului lipsesc din baza de date a organizație", LogLevel.Error);
        logger1.message("Nu se poate procesa comanda #Comanda1 datată pe 25.11.2018 pentru clientul #Clientul1",
                LogLevel.FunctionalError);
        logger1.message("Comandă procesată", LogLevel.FunctionalMessage);

        /*
         * The output must look like below:
         *
         * [Console] Se execută metoda ProcessOrder()
         * [Console] Comanda a fost procesată cu succes
         * [Console] Datele despre adresa clientului lipsesc din baza de date a filialei
         * [File] Datele despre adresa clientului lipsesc din baza de date a filialei
         * [Console] Detele despre adresa clientului lipsesc din baza de date a organizație
         * [File] Detele despre adresa clientului lipsesc din baza de date a organizație
         * [Console] Nu se poate procesa comanda #Comanda1 datată pe 25.11.2018 pentru clientul #Clientul1
         * [Email] Nu se poate procesa comanda #Comanda1 datată pe 25.11.2018 pentru clientul #Clientul1
         * [Console] Comandă procesată
         * [Email] Comandă procesată
         *
         */
    }
}
