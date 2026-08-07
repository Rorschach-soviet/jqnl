package com.example.stats.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.persistance.DBService;
import com.example.scrapper.enums.QUINIELA;
import com.example.scrapper.enums.SORTEO;
import com.example.stats.enums.QUERY_NUMBER_TYPE;
import com.example.stats.model.CriterioSorteo;
import com.example.stats.model.ResultDto;

public class StatsRepository {
    private static StatsRepository INSTANCE = null;
    private StatsRepository() throws SQLException{
        this.db = DBService.$();

        this.db.getConnection().setAutoCommit(false);
        this.stmt = this.db.getConnection().createStatement();
    }
    public static StatsRepository $(){
        try{
            if(INSTANCE == null){
                INSTANCE = new StatsRepository();
            }
            return INSTANCE;
        }catch(SQLException e){
            System.getLogger(StatsRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        }
        return null;
    }
    public static void destroy(){
        INSTANCE = null;
    }

    private final DBService db;
    private final Statement stmt;

    public List<ResultDto> getSalidos(QUERY_NUMBER_TYPE number_type,QUINIELA quiniela, SORTEO sorteo, int pos, LocalDate desde, LocalDate hasta){
        List<ResultDto> result = new ArrayList<>();
        String params = String.format(SALIDOS_QUERY, number_type.text(), quiniela.code(), sorteo.code(),pos,desde,hasta);

        try {
            ResultSet rs = this.stmt.executeQuery(params);
            while(rs.next()){
                result.add(new ResultDto(rs.getInt(2),rs.getInt(1)));
            }
        } catch (SQLException ex) {
            System.getLogger(StatsRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    public List<ResultDto> getSalidos(QUERY_NUMBER_TYPE number_type, CriterioSorteo criterio){
        List<ResultDto> result = new ArrayList<>();
        String params = String.format(FILTERED_QUERY, number_type.text(), criterio);

        try {
            ResultSet rs = this.stmt.executeQuery(params);
            while(rs.next()){
                result.add(new ResultDto(rs.getInt(2),rs.getInt(1)));
            }
        } catch (SQLException ex) {
            System.getLogger(StatsRepository.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    private final String SALIDOS_QUERY="""
                                       select numero, count(cant) as cantidad from (\r
                                       select d.%s as numero, count(j.id) as cant from jugadas j\r
                                       \tinner join numeros d\r
                                       \ton d.numero_id = j.numero \r
                                       where j.quiniela=%d \r
                                       \tand j.sorteo=%d \r
                                       \tand j.pos = %d \r
                                       \tand j.fecha between '%s' and '%s' \r
                                       group by numero order by numero\r
                                       )\r
                                       group by numero order by cantidad desc""";

        private final String FILTERED_QUERY="""
                                       select numero, count(cant) as cantidad from (\r
                                       select d.%s as numero, count(j.id) as cant from jugadas j\r
                                       \tinner join numeros d\r
                                       \ton d.numero_id = j.numero \r
                                       %s \r
                                       group by numero order by numero\r
                                       )\r
                                       group by numero order by cantidad desc""";

}
