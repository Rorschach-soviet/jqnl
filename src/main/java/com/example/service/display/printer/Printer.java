package com.example.service.display.printer;

public class Printer {

    protected Printer(){}

    public static void print(String text){
        System.out.print(text);
    }

    public static void println(String text){
        System.out.println(text);
    }

    public static void print(String text, FOREGROUND_COLOR foreCol, BACKGROUND_COLOR bgCol){
        System.out.print(createColor(foreCol,bgCol)+text + RESET);
    }

    public static void println(String text, FOREGROUND_COLOR foreCol){
        System.out.println(createColor(foreCol,null)+text + RESET);
    }

    public static void print(String text, FOREGROUND_COLOR foreCol){
        System.out.print(createColor(foreCol,null)+text + RESET);
    }

    public static void println(String text, FOREGROUND_COLOR foreCol, BACKGROUND_COLOR bgCol){
        System.out.println(createColor(foreCol,bgCol)+text + RESET);
    }

    private static String createColor(FOREGROUND_COLOR foreCol, BACKGROUND_COLOR bgCol){
        return "\u001B["+ foreCol.color + ((bgCol == null)?"" : ";"+ bgCol.color)  + "m";
    }

    private static final String RESET = "\u001B[0m";

    public enum FOREGROUND_COLOR{
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        PURPLE(35),
        CYAN(36),
        WHITE(37),
        GRAY(90),
        BRIGHT_RED(91),
        BRIGHT_GREEN(92),
        BRIGHT_YELLOW(93),
        BRIGHT_BLUE(94),
        BRIGHT_PURPLE(95),
        BRIGHT_CYAN(96),
        BRIGHT_WHITE(97);

        private final int color;
        private FOREGROUND_COLOR(int color){
            this.color = color;
        }
        public int color(){
            return this.color;
        }
    }

    public enum BACKGROUND_COLOR{
        BG_BLACK(40),
        BG_RED(41),
        BG_GREEN(42),
        BG_YELLOW(43),
        BG_BLUE(44),
        BG_PURPLE(45),
        BG_CYAN(46),
        BG_WHITE(47);

        private final int color;
        private BACKGROUND_COLOR(int color){
            this.color = color;
        }
        public int color(){
            return this.color;
        }
    }

}
