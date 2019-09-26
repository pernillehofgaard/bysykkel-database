package com.company;

import javax.swing.*;
import java.sql.*;


public class Kontroll {
    private String myURL = "jdbc:mysql://localhost:3306/bysykkel?useSSL=false";
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

    /*
    public boolean finnsykkelstativ(int stativID){
        lagForbindelse();
        Object[][] innhold = new Object[10000][2];
        int teller = 0;
        boolean funnet = false;
        try{
            ResultSet liste = visAlleSykkelstativer();
            while(liste.next()) {
                int eksisterende_stativ = liste.getInt(1);
                String eksisterende_sted = liste.getString(2);
                innhold[teller][0] = eksisterende_stativ;
                innhold[teller][1] = eksisterende_sted;
                teller++;

                if (eksisterende_stativ == stativID) {
                    funnet = true;
                }
            }
        }catch(Exception e){e.getMessage();}
        if(funnet == true){
            return true;
        }else{
            return false;
        }
        
    }*/

    public void registrerNyLås(String stativID, int låsID){

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

    public ResultSet visAlleSykler(){
        try{
            ResultSet result;
            String sql = "SELECT* FROM sykkel;";

            stm = conn.createStatement();
            result = stm.executeQuery(sql);

        }catch (Exception e){
            e.getMessage();
        }
        return result;
    }

    public int genererSykkelID(){
        Object[][] innhold = new Object[10000][4];
        int teller = 0;
        try{
            ResultSet liste = visAlleSykler();
            while(liste.next()){
                String sykkelID = liste.getString("SykkelID");
                String Startdato = liste.getString("Startdato");
                String stativID = liste.getString("StativID");
                String låsnr = liste.getString("Låsnr");
                innhold[teller][0] = sykkelID;
                innhold[teller][1] = Startdato;
                innhold[teller][2] = stativID;
                innhold[teller][3] = låsnr;

            }
        }catch (Exception e){
            e.getMessage();
        }
        return teller;
    }

    public void registrerNySykkel(int sykkelID, String startdato, String stativID, int låsnr){
        try {
            String sql = "INSERT INTO sykkel VALUES(" + sykkelID + "','" + startdato + "','" + stativID + ",'" + låsnr + "')";

            stm = conn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Lagret");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Noe gikk galt: \n " + e.getMessage());
        }
    }





}
