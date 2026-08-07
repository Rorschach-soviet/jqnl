package com.example.service.display;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.service.display.printer.Printer;
import static com.example.service.display.printer.Printer.FOREGROUND_COLOR.BLUE;
import static com.example.service.display.printer.Printer.FOREGROUND_COLOR.BRIGHT_RED;
import static com.example.service.display.printer.Printer.FOREGROUND_COLOR.BRIGHT_YELLOW;
import static com.example.service.display.printer.Printer.print;
import static com.example.service.display.printer.Printer.println;

public class DisplayService {
    private static final DisplayService INSTANCE;
    static{INSTANCE = new DisplayService();}
    private DisplayService(){}
    public static DisplayService $(){return INSTANCE;}

    private final Scanner scanner = new Scanner(System.in);



    public void mainMenu(){
        //print("ESTO ES UNA PRUEBA!!",FOREGROUND_COLOR.BLUE);
        //print("RESP: ",FOREGROUND_COLOR.BRIGHT_WHITE,BACKGROUND_COLOR.BLACK);
        //this.scanner.nextLine();
        println("    /$$$$$  /$$$$$$  /$$   /$$ /$$      ",BRIGHT_YELLOW);
        println("   |__  $$ /$$__  $$| $$$ | $$| $$      ",BRIGHT_YELLOW);
        println("      | $$| $$  \\ $$| $$$$| $$| $$      ",BRIGHT_YELLOW);
        println("      | $$| $$  | $$| $$ $$ $$| $$      ",BRIGHT_YELLOW);
        println(" /$$  | $$| $$  | $$| $$  $$$$| $$      ",BRIGHT_YELLOW);
        println("| $$  | $$| $$/$$ $$| $$\\  $$$| $$      ",BRIGHT_YELLOW);
        println("|  $$$$$$/|  $$$$$$/| $$ \\  $$| $$$$$$$$",BRIGHT_YELLOW);
        println(" \\______/  \\____ $$$|__/  \\__/|________/",BRIGHT_YELLOW);
        println("                \\__/                    ",BRIGHT_YELLOW);

        println("");

        int cifras=1;
        println("Cantidad de cifras:");
        print("1) ",BLUE); print("Una cifra\t");
        print("2) ",BLUE); print("Dos cifras\t");
        print("3) ",BLUE); print("Tres cifras\t");
        print("4) ",BLUE); println("Cuatro cifras\t");
        boolean isValid = false;
        int temp=0;
            while(!isValid){
                try{
                    temp = cifras;
                    print("(default: "+ cifras +"): ",BLUE);cifras = scanner.nextShort();
                    if(cifras < 1 || cifras > 4){
                        cifras = temp;
                        print("Opciones [1 - 4] ",BRIGHT_RED);
                    }else{
                        isValid=true;
                    }
                }catch(InputMismatchException e){
                    print("Dato erroneo! ",BRIGHT_RED);
                    scanner.next();
                }
            }
        
        int quiniela = 1;
        println("\nQuiniela:");
        print("1) ",BLUE); print("BUENOS AIRES\t");
        print("2) ",BLUE); print("CIUDAD\t");
        print("3) ",BLUE); print("SANTA FE\t");
        print("4) ",BLUE); print("CORDOBA\t");
        print("5) ",BLUE); println("ENTRE RIOS\t");
        
        isValid = false;
        temp=0;
            while(!isValid){
                try{
                    temp = quiniela;
                    print("(default: "+ quiniela +"): ",BLUE);quiniela = scanner.nextShort();
                    if(quiniela < 1 || quiniela > 5){
                        quiniela = temp;
                        print("Opciones [1 - 5] ",BRIGHT_RED);
                    }else{
                        isValid=true;
                    }
                }catch(InputMismatchException e){
                    print("Dato erroneo! ",BRIGHT_RED);
                    scanner.next();
                }
            }
        println("cifras: "+ cifras,Printer.FOREGROUND_COLOR.GREEN);
        println("quiniela: "+ quiniela,Printer.FOREGROUND_COLOR.GREEN);
    }
    


}
