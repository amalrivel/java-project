package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.time.*;
import java.time.temporal.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import com.toedter.calendar.JDateChooser;

public class Countdown extends JFrame {
    private JLabel submitDateLabel = new JLabel("Submit Date:");
    private JDateChooser submitDateChooser = new JDateChooser();
    private JLabel resultLabel = new JLabel("Silahkan pilih tanggal");
    public void init() {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        this.setLayout(new GridLayout(10, 2, 10, 10));
        
        submitDateChooser.setMinSelectableDate(new Date());

        submitDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    Date startDate = submitDateChooser.getDate();
                    if (startDate == null) {
                        resultLabel.setText("Silahkan pilih tanggal");
                    } else {
                        Date submitDate = submitDateChooser.getDate();

                        LocalDate newsubmitDate = submitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate deadlineDate = newsubmitDate.plusYears(1);
                        LocalDate now = LocalDate.now();

                        long diff = ChronoUnit.DAYS.between(now, deadlineDate);
                        resultLabel.setText("Deadline: " + diff);
                    }
                }
            }
        });

        this.add(submitDateLabel);
        this.add(submitDateChooser);
        this.add(resultLabel);

        this.setTitle("Form Sederhana");
        this.setSize(boardWidth, boardHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

