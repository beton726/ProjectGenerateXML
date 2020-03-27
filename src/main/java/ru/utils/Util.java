package ru.utils;

public class Util {
    // Генерация nameId
    public static String generateWord() {
        String[] dict = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9"};
        StringBuilder word = new StringBuilder("1-");
        while (word.length() < 7)
            word.append(dict[(int)(Math.random() * dict.length)]);
        return word.toString();
    }
    // Генерация возраста
    public static String generateAge() {
        return String.valueOf((int)(Math.random()*99)+1);
    }
    // Генерация роста
    public static String generateHigh() {
        return String.valueOf((int)(Math.random()*121)+100);
    }
    // Генерация названия файла XML
    public static String generateNameXML() {
        return "\\account" + (int)(Math.random()*100000000) + ".xml";
    }
}
