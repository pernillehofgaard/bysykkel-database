package com.company.vinduer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class regSykkelVindu extends JDialog implements ActionListener {
    private JLabel velkommen, dato, stativID, låsnr;
    private JTextField ent_dato;
    private JComboBox comboStativID, comboLåsnr;
    private JButton lagre, tilbake;
    private boolean ok = true;

    public regSykkelVindu(){
        velkommen = new JLabel("Registrering av ny sykkel. Vennligst oppgi følgende: ");
        add(velkommen);

        dato = new JLabel("Dagens dato: ");
        add(dato);
        ent_dato = new JTextField(6);
        add(ent_dato);

        stativID = new JLabel("StativID sykkelen skal stå i: ");
        add(stativID);
        comboStativID = new JComboBox();
        add(comboStativID);

        låsnr = new JLabel("Låsnummer til sykkelen: ");
        add(låsnr);
        comboLåsnr = new JComboBox();
        add(comboLåsnr);

        lagre = new JButton("Lagre");
        add(lagre);
        lagre.addActionListener(this);

        tilbake = new JButton("Tilbake");
        add(tilbake);
        tilbake.addActionListener(this);
    }


    public boolean visVindu(){
        setVisible(true);
        setLayout(new GridLayout(10,1));
        pack();
        setLocation(500,400);
        return ok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            setVisible(false);
        }else{

        }
    }
}
