import java.util.Scanner;



class Calc {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите пример через пробел (римские числа принимаются только в верхнем регистре)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(parse(input));

    }

    static String parse(String input) throws Exception {
        int a;
        int b;
        String operation;
        String result;
        boolean isRoman;
        String[] scInput = input.split(" ");
        if (scInput.length != 3) {
            throw new Exception("Должно быть два числа и математический знак");
        }
        operation = detectOperation(input);
        if (operation == null) {
            throw new Exception("Ошибка ввода");
        }
        if (Roman.isRoman(scInput[0]) && Roman.isRoman(scInput[2])) {
            a = Roman.convertToArabian(scInput[0]);
            b = Roman.convertToArabian(scInput[2]);
            isRoman = true;
        } else if (!Roman.isRoman(scInput[0]) && !Roman.isRoman(scInput[2])) {
            a = Integer.parseInt(scInput[0]);
            b = Integer.parseInt(scInput[2]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (a > 10 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");

        }
        int arabian = calc(a, b, operation);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");}

            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }


    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operation) {

        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

}
class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}








