package me.maploop.jsonconverter.util;

public class ALog
{
    private static final String PREFIX = "AtlasAdminPanel";

    public static void warn(Object o) {
        System.out.println("[" + PREFIX + "] [WARNING] : " + o);
    }

    public static void info(Object o) {
        System.out.println("[" + PREFIX + "] [INFO] : " + o);
    }

    public static void severe(Object o) {
        System.out.println("[ERROR] " + "[" + PREFIX + "] [ERROR] : " + o);
    }
}
