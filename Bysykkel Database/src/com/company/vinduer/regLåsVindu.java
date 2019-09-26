package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class regLåsVindu extends JFrame implements ActionListener {

    Kontroll k = Kontroll.getInstance();
    JLabel lbl_stativID, lbl_laasnr;
    JComboBox stativIDcombo;
    JButton lagre, tilbake;

    PreparedStatement stm;
    ResultSet result;
    Connection conn;

    String[] message ={"hei", "på", "deg"};

    public regLåsVindu(){
        setTitle("Registrer ny lås");
        setLayout(new GridLayout(5,2));

        lbl_stativID = new JLabel("Hvilket stativ skal låsen være på?");
        add(lbl_stativID);

        stativIDcombo = new JComboBox();
        fillCombo();
        add(stativIDcombo);


        lbl_laasnr = new JLabel("Låsnr genereres automatisk");
        add(lbl_laasnr);

        lagre = new JButton("lagre");
        lagre.addActionListener(this);
        add(lagre);

        tilbake = new JButton("tilbake");
        tilbake.addActionListener(this);
        add(tilbake);
    }

    public boolean visVindu(){
        setVisible(true);
        setSize(300,300);
        setLocation(500,700);
        return true;
    }

    public void fillCombo(){
        try{
            result = k.visAlleSykkelstativer();
            while(result.next()){
                String stativID = result.getString("StativID");
                stativIDcombo.addItem(stativID);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            setVisible(false);
        }else{
            int låsnr = k.genererLåsnr();


            k.registrerNyLås((String) stativIDcombo.getSelectedItem(), låsnr);







        }

    }
}

