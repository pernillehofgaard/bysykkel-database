package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class regSykkelstativ extends JDialog implements ActionListener {
    private boolean ok = true;
    private JButton avslutt, lagre;
    private JLabel lbl_stativID, lbl_sted;
    private JTextField ent_sted;

    Kontroll k = Kontroll.getInstance();


    public regSykkelstativ(){
        lbl_stativID = new JLabel("StativID genereres automatisk");
        add(lbl_stativID);

        lbl_sted = new JLabel("Sted:");
        add(lbl_sted);
        ent_sted = new JTextField();
        add(ent_sted);

        lagre = new JButton("Lagre");
        add(lagre);
        lagre.addActionListener(this);

        avslutt = new JButton("Avslutt");
        add(avslutt);
        avslutt.addActionListener(this);
    }

    public boolean visVindu(){
        setLayout(new GridLayout(5,1));
        setVisible(true);
        setSize(400,400);
        setLocation(700,400);
        pack();
        return ok;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lagre){

            int stativID = k.genererStativID();
            String sted = ent_sted.getText();

            k.regNyttSykkelstativ(stativID, sted);

        }else{
            setVisible(false);
        }
    }
}
