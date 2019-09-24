package com.company;

import javax.swing.*;
import java.sql.*;


public class Kontroll {
    private String myURL = "jdbc:mysql://localhost:3306/bysykkel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&use";
    private String myDriver = "com.mysql.jdbc.Driver";
    private ResultSet result;
    private Statement stm;
    private Connection conn;

    public void lagForbindelse() {
        try {
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myURL, "root", "Lottis12");
        }catch (Exception e){
            e.getMessage();
        }
    }


    public static Kontroll single_instance = null;

    public static Kontroll getInstance(){
        if(single_instance == null){
            single_instance = new Kontroll();
        }
        return single_instance;
    }

    public int genererStativID(){
        Object[][] innhold = new Object[10000][2];
        int teller = 0;
        try{
            ResultSet liste = visAlleSykkelstativer();
            while(liste.next()){
                String stativID = liste.getString(1);
                String sted = liste.getString(2);
                innhold[teller][0] = stativID;
                innhold[teller][1] = sted;
                teller++;
            }
        }catch (Exception e){
            e.getMessage();
        }
        return teller + 1;
    }


    public void regNyttSykkelstativ(int stativID, String sted){
        String sql = "INSERT INTO sykkelstativ VALUES("+ stativID + ",'" + sted + "');";
        try {
            stm = conn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Lagret");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Beklager noe gikk galt: \n" + e.getMessage());

        }
    }

    public ResultSet visAlleSykkelstativer() throws Exception{
        lagForbindelse();
        result = null;
        try{
            String sql = "SELECT * FROM sykkelstativ";
            stm = conn.createStatement();
            result = (ResultSet) stm.executeQuery(sql);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println(result);
        return (ResultSet) result;
    }

    public boolean finnsykkelstativ(int stativID){
        Object[][] innhold = new Object[10000][2];
        int teller = 0;
        boolean stativ_finnes;
        try{
            ResultSet liste = visAlleSykkelstativer();
            while(liste.next()){
                String funnet_stativID = liste.getString(1);
                String sted = liste.getString(2);
                innhold[teller][0] = stativID;
                innhold[teller][1] = sted;
                teller++;

                if(funnet_stativID.equals(stativID)){
                    stativ_finnes = true;
                }else{
                    stativ_finnes = false;
                }
                return stativ_finnes;
            }

        }catch(Exception e){e.getMessage();}
        return false;
    }

    public void registrerNyLås(int stativID, int låsID){
        String sql = "INSERT INTO lås VALUES("+ stativID + ",'" + låsID + "');";
        try{
            stm = conn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Lagret");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Noe gikk galt:\n" + e.getMessage());
        }
    }

    public ResultSet visAlleLåser(){
        lagForbindelse();
        result = null;
        try{
            String sql = "SELECT * FROM lås";
            stm = conn.createStatement();
            result = (ResultSet) stm.executeQuery(sql);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println(result);
        return (ResultSet) result;
    }

    public int genererLåsnr(){
        Object[][] innhold = new Object[10000][2];
        int teller = 0;
        try{
            ResultSet liste = visAlleLåser();
            while (liste.next()){
                String stativID = liste.getString(1);
                String lås = liste.getString(2);
                innhold[teller][0] = stativID;
                innhold[teller][1] = lås;
                teller++;
            }
        }catch (Exception e){
            e.getMessage();
        }
        return teller + 1;
    }



}
