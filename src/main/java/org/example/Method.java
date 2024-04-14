package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Method {


    /**
     * Получение целочисленого значения от пользователя
     * Если ввели не число, возвращает -1
     * @return возвращает целое число int
     */
    public static Integer answer() {
        int result;
        try {
            Scanner sc = new Scanner(System.in);
            result = sc.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
        return result;
    }

}