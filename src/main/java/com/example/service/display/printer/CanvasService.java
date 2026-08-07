package com.example.service.display.printer;

public class CanvasService {
    private static CanvasService INSTANCE = null;
    private CanvasService(){}
    public static CanvasService $(){
        if(INSTANCE ==null){
            INSTANCE = new CanvasService();
        }
        return INSTANCE;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    public void rect(){
        char[][] board = new char[10][10];

        for(int i =0; i < board.length;i++){
            board[i] = ".".repeat(10).toCharArray();
        }

        for(int i =0; i < board.length;i++){
            System.err.println(new String(board[i]));
        }

        
    }


}
