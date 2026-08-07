package com.example.service.display.enums;

public enum FOREGROUND_COLOR {
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
        BRIGHT_WHITE(97),
        RESET(0);

        private final int color;
        private FOREGROUND_COLOR(int color){
            this.color = color;
        }
        public int color(){
            return this.color;
        }
}
