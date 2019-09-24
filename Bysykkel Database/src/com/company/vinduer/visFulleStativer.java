package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.concurrent.Flow;

public class visFulleStativer extends JFrame implements ActionListener {

    Kontroll k = Kontroll.getInstance();

    ResultSet rs;
    DefaultTableModel tabellinnhold;
    JTable tabell;
    JScrollPane skroll;
    JButton tilbake;

    private String[] kolonne = {"StativID:", "Sted:"};
    private final Object[][] defaulttabell = new Object[][]{{null,null},{null,null}};

    public visFulleStativer(){
        setTitle("Fullestativer");
        setLayout(new FlowLayout());
        JPanel tabellpanel = new JPanel();
        tabellinnhold = new DefaultTableModel(defaulttabell,kolonne);
        tabell = new JTable(tabellinnhold);
        skroll = new JScrollPane(tabell);
        tabellpanel.add(skroll);
        add(tabellpanel, BorderLayout.CENTER);
        tilbake = new JButton("Tilbake til hovedvindu");
        tilbake.addActionListener(this);
        add(tilbake);
        fylltabell(rs);
        pack();
    }

    public Object[][] lagListe(){
        Object[][] innhold = new Object[10][3];
        int teller = 0;
        try{
            ResultSet liste = k.visAlleSykkelstativer(); // DETTE ER IKKE DEN METODEN FRA KONTROLL SOM SKAL BRUKES.
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
        return innhold;
    }

    public void fylltabell(ResultSet rs){
        Object[][] liste = lagListe();
        tabell.setModel(new DefaultTableModel(liste,kolonne));
    }

    public void visVindu(){
        setLocation(500,300);
        setSize(100,300);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            setVisible(false);
        }
    }
}
