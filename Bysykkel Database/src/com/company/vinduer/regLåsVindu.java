package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class regLåsVindu extends JFrame implements ActionListener {

    Kontroll k = Kontroll.getInstance();

    JLabel lbl_stativID, lbl_laasnr;
    JTextField ent_stativID, ent_laasnr;
    JButton lagre, tilbake;

    public regLåsVindu(){
        setTitle("Registrer ny lås");
        setLayout(new GridLayout(3,3));

        lbl_stativID = new JLabel("Hvilket stativ skal låsen være på?");
        add(lbl_stativID);
        ent_laasnr = new JTextField();
        add(ent_laasnr);

        lbl_laasnr = new JLabel("låsnr genereres automatisk");
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
        setSize(500,400);
        setLocation(500,700);
        pack();
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            setVisible(false);
        }else{

            int låsnr = k.genererLåsnr();
            int stativID = Integer.parseInt(ent_stativID.getText());

            if(!k.finnsykkelstativ(stativID)){
                JOptionPane.showMessageDialog(null,"Stativ finnes ikke");
            }else{
                k.registrerNyLås(låsnr, stativID);
            }

        }
    }
}
