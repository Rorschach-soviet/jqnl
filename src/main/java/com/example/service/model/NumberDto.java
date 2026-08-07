package com.example.service.model;

public class NumberDto {
    private final int number4c;
    private final String numberStr4c;

    private final int number3c;
    private final String numberStr3c;

    private final int number2c;
    private final String numberStr2c;

    private final int number1c;
    private final int decena;
    private final int centena;
    private final int unidadMil;

    private final int capi;
    private final String capiStr;
    private boolean capicua=false;

    public NumberDto(int number){
        if(number > 9999){
            String temp = String.valueOf(number);
            this.number4c = Integer.parseInt(temp.substring(temp.length()-4));
        }else{
            this.number4c = number;
        }
        this.numberStr4c = String.format("%04d", this.number4c);

        this.numberStr3c = this.numberStr4c.substring(this.numberStr4c.length()-3);
        this.number3c = Integer.parseInt(this.numberStr3c);

        this.numberStr2c = this.numberStr3c.substring(this.numberStr3c.length()-2);
        this.number2c = Integer.parseInt(this.numberStr2c);

        this.number1c = Integer.parseInt(this.numberStr2c.substring(this.numberStr2c.length()-1));
        this.decena = Integer.parseInt(this.numberStr2c.substring(0, 1));
        this.centena = Integer.parseInt(this.numberStr3c.substring(0, 1));
        this.unidadMil = Integer.parseInt(this.numberStr4c.substring(0, 1));
        this.capiStr = String.valueOf(this.number1c) + String.valueOf(this.decena);
        this.capi = Integer.parseInt(this.capiStr);
        if(this.capi == this.number2c){
            this.capicua = true;
        }
    }

    @Override
    public String toString(){
        return "number4c: " +this.number4c +
        "\nnumberStr4c: " +this.numberStr4c +
        "\nnumber3c: " +this.number3c +
        "\nnumberStr3c: " +this.numberStr3c +
        "\nnumber2c: " +this.number2c +
        "\nnumberStr2c: " +this.numberStr2c +
        "\nnumber1c: " +this.number1c +
        "\ndecena: " +this.decena +
        "\ncentena: " +this.centena +
        "\nunidadMil: " +this.unidadMil+
        "\ncapi: " + this.capi +
        "\ncapiStr: " + this.capiStr +
        "\nes capicua: " + this.capicua;
    }

    public String toSql(){
        String template = "INSERT OR IGNORE INTO NUMEROS(numero_id,numberStr4c,number3c,numberStr3c,number2c,numberStr2c,number1c,decena,centena,unidadMil,capi,capiStr,capicua)VALUES(%d,'%s',%d,'%s',%d,'%s',%d,%d,%d,%d,%d,'%s',%d);";
        return String.format(template, this.number4c,this.numberStr4c,this.number3c,this.numberStr3c,this.number2c,this.numberStr2c,this.number1c,this.decena,this.centena,this.unidadMil,this.capi,this.capiStr,this.capicua?1:0);
    }

    public int getNumber4c() {
        return number4c;
    }

    public String getNumberStr4c() {
        return numberStr4c;
    }

    public int getNumber3c() {
        return number3c;
    }

    public String getNumberStr3c() {
        return numberStr3c;
    }

    public int getNumber2c() {
        return number2c;
    }

    public String getNumberStr2c() {
        return numberStr2c;
    }

    public int getNumber1c() {
        return number1c;
    }

    public int getDecena() {
        return decena;
    }

    public int getCentena() {
        return centena;
    }

    public int getUnidadMil() {
        return unidadMil;
    }

    public int getCapi() {
        return capi;
    }

    public String getCapiStr() {
        return capiStr;
    }

    public boolean isCapicua() {
        return capicua;
    }
}
