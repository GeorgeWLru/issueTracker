package ru.georgewl.epam.it.persistence;

/**
 * Contains methods to make getter and setter names
 * @author Yury Belozyorov, PTS
 */
public class ReflectHelper {
    
    public static String makeGetterName(String fieldName) {
        Character ch= fieldName.charAt(0);
        ch= Character.toUpperCase(ch);
        String getterName= "get"+ch+fieldName.substring(1);
        return getterName;
    }
    
    public static String makeSetterName(String fieldName) {
        Character ch= fieldName.charAt(0);
        ch= Character.toUpperCase(ch);
        String setterName= "set"+ch+fieldName.substring(1);
        return setterName;
    }
    
}
