package com.company.vinduer;

import com.company.Kontroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hovedvindu extends JFrame implements ActionListener {
    JLabel velkommen;
    JButton registrering, alleKunder, alleSyklerDato, antallKunder, kunderIkkeLeid, antallLeidForHverKunde, over100ganger, totalBeløp, ledigeSyklerIstativ, hvemLeierNå, fultStativ, avslutt;

    regVindu regVindu = new regVindu();
    regSykkelstativ regStativ = new regSykkelstativ();
    Kontroll k = Kontroll.getInstance();
    visFulleStativer visStativer = new visFulleStativer();


    public Hovedvindu(){
        setTitle("Bysykkel administrasjon");
        setLayout(new GridLayout(12,2));
        setSize(400,400);
        setLocation(500,300);

        velkommen = new JLabel("Velkommen");
        add(velkommen);

        registrering = new JButton("Registrering av sykkel, lås, sykkelstativ");
        add(registrering);
        registrering.addActionListener(this);

        alleKunder = new JButton("Vis alle kunder");
        add(alleKunder);
        alleKunder.addActionListener(this);

        alleSyklerDato = new JButton("Se alle sykler som er tatt i bruk etter en gitt dato");
        add(alleSyklerDato);
        alleSyklerDato.addActionListener(this);

        antallKunder = new JButton("Se alle kunder i Bysykkel-ordningen");
        add(antallKunder);
        antallKunder.addActionListener(this);

        kunderIkkeLeid = new JButton("Vis alle kunder som ikke har leid sykkel");
        add(kunderIkkeLeid);
        kunderIkkeLeid.addActionListener(this);

        antallLeidForHverKunde = new JButton("Antall ganger kunder har leid");
        add(antallLeidForHverKunde);
        antallLeidForHverKunde.addActionListener(this);

        over100ganger = new JButton("Vis sykler som er leid over 100 ganger");
        add(over100ganger);
        over100ganger.addActionListener(this);

        totalBeløp = new JButton("Vis totalbeløpet for hver kunde");
        add(over100ganger);
        totalBeløp.addActionListener(this);

        ledigeSyklerIstativ = new JButton("Oversikt over hvor mange ledige sykler som er tilgjengelig i hvert sykkelstativ");
        add(ledigeSyklerIstativ);
        ledigeSyklerIstativ.addActionListener(this);

        hvemLeierNå = new JButton("Se hvilke kunder som leier sykkel nå");
        add(hvemLeierNå);
        hvemLeierNå.addActionListener(this);

        fultStativ = new JButton("Se hvilke stativ som er fulle");
        add(fultStativ);
        fultStativ.addActionListener(this);

        avslutt = new JButton("Avslutt");
        add(avslutt);
        avslutt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrering){
            regVindu.visVindu();
        }else if(e.getSource() == avslutt){
            setVisible(false);
        }else if(e.getSource() == fultStativ){
            visStativer.visVindu();
        }
    }
}
