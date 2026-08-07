package com.example.service.display.enums;

public enum BACKGROUND_COLOR {
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
