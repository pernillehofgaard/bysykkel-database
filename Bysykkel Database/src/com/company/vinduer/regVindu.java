package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class regVindu extends JDialog implements ActionListener {
    private JLabel hva;
    private JButton sykkel, stativ, lås, tilbake;
    private boolean ok = true;

    Kontroll k = Kontroll.getInstance();
    regSykkelVindu regSykkel = new regSykkelVindu();
    regSykkelstativ regStativ = new regSykkelstativ();
    regLåsVindu registrerLås = new regLåsVindu();

    public regVindu(){
        hva = new JLabel("Hva vil du registrere");
        add(hva);

        sykkel = new JButton("Registrer ny sykkel");
        add(sykkel);
        sykkel.addActionListener(this);

        stativ = new JButton("Registrer nytt sykkelstativ");
        add(stativ);
        stativ.addActionListener(this);

        lås = new JButton("Registrer ny lås");
        add(lås);
        lås.addActionListener(this);

        tilbake = new JButton("Tilbake");
        add(tilbake);
        tilbake.addActionListener(this);
    }

    public boolean visVindu(){
        setVisible(true);
        setLayout(new GridLayout(5,4));
        setSize(350,350);
        setLocation(550,300);
        return ok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == sykkel){
            regSykkel.visVindu();
        }else if(e.getSource() == stativ){
            regStativ.visVindu();
        }else if(e.getSource() == tilbake){
            setVisible(false);
        }else if(e.getSource() == lås){
            registrerLås.visVindu();
        }
    }
}
