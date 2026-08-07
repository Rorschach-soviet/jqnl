package com.example.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.example.service.model.NumberDto;

public class DBService {
    private static final DBService INSTANCE;
    static{INSTANCE = new DBService();}
    private DBService(){}
    public static DBService $(){return INSTANCE;}

    private final String URL = "jdbc:sqlite:db/jqnl.db";
    private Connection conn;


    public void connect(){
        System.out.println( "Conectando a la DB" );
        try{
            //creamos la carpeta si no existe
            Files.createDirectories(Paths.get("db"));
        }catch(IOException e){
            System.out.println("no se pudo cerrar la carpeta db. \n" + e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false);

            Statement stmt = this.conn.createStatement();

            getDataFromResource().forEach(sql -> {
                    try{
                        if(!(sql.trim()).isEmpty()){
                            stmt.addBatch(sql);
                        }
                    }catch(SQLException e){
                        System.out.println("error al insertar el batch. \n" + e.getMessage());
                    }
                }
            );
            stmt.executeBatch();
            this.conn.commit();
            ResultSet rs = stmt.executeQuery("select count(id) from numeros;");
            if(rs.getInt(1) < 10000){
                createTableData();
            }

            System.out.println( "Conectado!!" );
        } catch (SQLException e) {
            System.out.println("error al conectar a la BD: " + e.getMessage());
        }
    }

    public Connection getConnection(){
        return this.conn;
    }

    public void close(){
        try{
          conn.close();  
        }catch(SQLException e){
            System.out.println("no se pudo cerrar la db. \n" + e.getMessage());
        }
    }

    private List<String> getDataFromResource() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.sql")) {
            if (is == null) {
                throw new IllegalArgumentException("archivo config.sql no encontrado.");
            }
            List<String> result = Arrays.asList(new String(is.readAllBytes()).split(";"));

            return result;
        }catch(IOException e){
            System.out.println("error al leer el archivo. \n" + e.getMessage());
        }
        return null;
    }

    private void createTableData() throws SQLException{
        Statement stmt = this.conn.createStatement();
        this.conn.setAutoCommit(false);
        System.out.println("Insertando datos...");

        for(int i =0;i<10000;i++){
            stmt.addBatch(new NumberDto(i).toSql());
        }

        stmt.executeBatch();
        this.conn.commit();
        System.out.println("Datos insertados!");
    }
}
