package com.example.persistance.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.persistance.DBService;
import com.example.persistance.model.ReproDto;
import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SORTEO;


public class CommonRepository {
    private static CommonRepository INSTANCE = null;
    private CommonRepository() throws SQLException{
        this.db = DBService.$();

        this.db.getConnection().setAutoCommit(false);
        this.stmt = this.db.getConnection().createStatement();
    }
    public static CommonRepository $(){
        try{
            if(INSTANCE == null){
                INSTANCE = new CommonRepository();
            }
            return INSTANCE;
        }catch(SQLException e){
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        }
        return null;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    private final DBService db;
    private final Statement stmt;

    public void addBatch(String sql){
        try {
            this.stmt.addBatch(sql);
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void clearBatch(){
        try {
            this.stmt.clearBatch();
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void executeBatch(){
        try {
            this.stmt.executeBatch();
            this.db.getConnection().commit();
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public LocalDate getLasDate(){
        try {
            ResultSet rs = this.stmt.executeQuery("SELECT MAX(FECHA) FROM JUGADAS");
            rs.next();
            String date = rs.getString(1);
            if(date != null){
                return LocalDate.parse(date);
            }
            
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        //si la BD esta vacia trae 2016-07-31
        return LocalDate.of(2016,7, 31);
    }

    public void limpiarReprocesados(){
        try {
            this.stmt.execute("DELETE FROM REPROCESO WHERE FECHA < DATE('now', '-1 day')");
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Map<LocalDate,List<ReproDto>> getReprocesos(){
        Map<LocalDate,List<ReproDto>> result = new HashMap<>();
        try {
            ResultSet rs = this.stmt.executeQuery("SELECT ID, FECHA, QUINIELA, SORTEO FROM REPROCESO");
            while(rs.next()){
                LocalDate date = LocalDate.parse(rs.getString(2));
                if(result.get(date) == null){
                    result.put(date,new ArrayList<>());
                }
                result.get(date).add(
                    new ReproDto(
                        rs.getInt(1),
                        date,
                        QUINIELA.get(rs.getInt(3)),
                        SORTEO.get(rs.getInt(4))));
            }
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    public void borrarReprocesos(int id){
        try {
            this.stmt.execute(String.format("DELETE FROM REPROCESO WHERE ID = %d",id));
        } catch (SQLException ex) {
            System.getLogger(CommonRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
