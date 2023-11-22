package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;
import java.time.temporal.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import com.toedter.calendar.JDateChooser;

public class MainFrame extends JFrame implements ActionListener {
    private JLabel nameLabel = new JLabel("Nama:");
    private JTextField nameField = new JTextField(20);
    private JLabel emailLabel = new JLabel("Email:");
    private JTextField emailField = new JTextField(20);
    private JLabel startDateLabel = new JLabel("Start Date:");
    private JDateChooser startDateChooser = new JDateChooser();
    private JLabel endDateLabel = new JLabel("End Date:");
    private JDateChooser endDateChooser = new JDateChooser();
    private JLabel submitDateLabel = new JLabel("Submit Date:");
    private JDateChooser submitDateChooser = new JDateChooser();
    private JButton submitButton = new JButton("Submit");
    private JLabel resultLabel = new JLabel("");

    public void init() {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        // Menggunakan objek this untuk merujuk ke frame utama
        this.setLayout(new GridLayout(10, 2, 10, 10));

        startDateChooser.setDateFormatString("dd/MM/yyyy");
        endDateChooser.setDateFormatString("dd/MM/yyyy");

        endDateChooser.setEnabled(false);

        // Menambahkan PropertyChangeListener ke startDateChooser
        startDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Jika properti yang berubah adalah date
                if (evt.getPropertyName().equals("date")) {
                    // Mendapatkan nilai date dari startDateChooser
                    Date startDate = startDateChooser.getDate();
                    // Jika nilai date adalah null, berarti startDateChooser belum dipilih
                    if (startDate == null) {
                        // Menonaktifkan endDateChooser
                        endDateChooser.setEnabled(false);
                    } else {
                        // Mengaktifkan endDateChooser
                        endDateChooser.setEnabled(true);
                        // Membatasi tanggal yang bisa dipilih di endDateChooser
                        // Tanggal minimal adalah hari setelah startDate
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        calendar.add(Calendar.DATE, 1);
                        endDateChooser.setMinSelectableDate(calendar.getTime());
                    }
                }
            }
        });

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

        // if (submitDateChooser.getDate() == null) {
        // resultLabel.setText("Silahkan pilih tanggal");
        // } else {
        // Date submitDate = submitDateChooser.getDate();

        // LocalDate newsubmitDate =
        // submitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // LocalDate deadlineDate = newsubmitDate.plusYears(1);
        // LocalDate now = LocalDate.now();

        // long diff = ChronoUnit.DAYS.between(now, deadlineDate);
        // resultLabel.setText("Deadline: " + diff);
        // }

        // dateChooser.setDateFormatString("dd/MM/yyyy");
        // dateChooser.setMinSelectableDate(new Date(120, 0, 1)); // 120 adalah tahun
        // 2020 - 1900
        // dateChooser.setMaxSelectableDate(new Date(123, 11, 31)); // 123 adalah tahun
        // 2023 - 1900

        // Menambahkan komponen ke frame
        this.add(nameLabel);
        this.add(nameField);
        this.add(emailLabel);
        this.add(emailField);
        this.add(startDateLabel);
        this.add(startDateChooser);
        this.add(endDateLabel);
        this.add(endDateChooser);
        this.add(submitDateLabel);
        this.add(submitDateChooser);
        this.add(submitButton);
        this.add(resultLabel);

        // Menambahkan action listener ke tombol
        submitButton.addActionListener(this);

        // Menampilkan frame
        this.setTitle("Form Sederhana");
        this.setSize(boardWidth, boardHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String email = emailField.getText();
        Date startDate = startDateChooser.getDate();
        Date endDate = endDateChooser.getDate();
        Date submitDate = submitDateChooser.getDate();

        LocalDate newsubmitDate = submitDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate deadlineDate = newsubmitDate.plusYears(1);
        LocalDate now = LocalDate.now();

        long diff = ChronoUnit.DAYS.between(now, deadlineDate);

        // Menampilkan hasil input ke label
        // resultLabel.setText("Halo, " + name + ". Email Anda adalah " + email + ".
        // Start date " + startDate
        // + ". End date " + endDate + ". Sisa hari");
        // resultLabel.setText("Deadline " + diff);
    }
}
