/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketing.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ticketing.connections.SerialConnenction;
import ticketing.dao.PaymentDaoImpl;
import ticketing.model.Payment;

public class station extends javax.swing.JFrame {

    PaymentDaoImpl p = new PaymentDaoImpl();
    SerialConnenction serial = null;

    public station() {
        initComponents();
        serial = new SerialConnenction(null, this);
        serial.initialize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new GridBagLayout());
        add(outPanel, new GridBagConstraints());
        DateTime();
        invoice();
    }

    public void setFields(String sid, String name) {
        idBox.setText(sid);
        passengerBox.setText(name);
    }

    public void DateTime() {

        ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
        e.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // do stuff
                SwingUtilities.invokeLater(new Runnable() {

                    Date date = new Date();
                    SimpleDateFormat D = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat T = new SimpleDateFormat("hh:mm:ss a");

                    @Override
                    public void run() {
                        lblDate.setText(D.format(date));
                        lblTime.setText(T.format(date));

                    }

                });
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    public void invoice() {

        String bill = p.loadInvoice();
        invoice_L.setText(bill);
    }

    public void setFieldsempty() {
        idBox.setText("");
        passengerBox.setText("");
        typeBox.setSelectedItem("Select Payment Type");
        totBox.setText("");

    }

    public boolean validateFields(Payment pay) {
        String pid = pay.getInvoiceNo();
        String smartID = pay.getSmartId();
        String date = pay.getDate();
        String time = pay.getTime();
        String type = pay.getType();
        Double amount = pay.getAmount();

        if (pid.isEmpty() || smartID.isEmpty() || date.isEmpty() || time.isEmpty() || type.equals("Select Payment Type") || amount == null) {
            return false;

        }

        return true;
    }

    public boolean isvalidAmount(double value) {

        if (value < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void Print() {

        try {
            Payment pay = new Payment();
            pay.setInvoiceNo(invoice_L.getText());
            pay.setDate(lblDate.getText());
            pay.setTime(lblTime.getText());
            pay.setSmartId(idBox.getText());
            pay.setPassengerName(passengerBox.getText());
            pay.setType((typeBox.getSelectedItem().toString()));
            pay.setAmount(Double.parseDouble(totBox.getText()));

            if (validateFields(pay) == false) {
                JOptionPane.showMessageDialog(this, "Fields are empty");
                setFieldsempty();
            } else {

                JOptionPane.showMessageDialog(this, "Invoice created !");
                Document doc = new Document();
                File file = new File("Invoice.pdf");

                if (file.exists()) {
                    file.delete();
                }
                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(file));
                    doc.open();

                    doc.add(new Paragraph("================================================="));
                    doc.add(new Paragraph("         SMART CARD RECHARGE PAYMENT INVOICE                 "));
                    doc.add(new Paragraph("================================================="));
                    doc.add(new Paragraph("Invoice number         :" + invoice_L.getText()));
                    doc.add(new Paragraph("Isued Date             : " + lblDate.getText()));
                    doc.add(new Paragraph("Isued Time             : " + lblTime.getText()));
                    doc.add(new Paragraph("**************************************************************"));
                    doc.add(new Paragraph("Smart Card ID          :   " + idBox.getText()));
                    doc.add(new Paragraph("Passenger Name         :   " + passengerBox.getText()));

                    doc.add(new Paragraph("Payment Type : " + typeBox.getSelectedItem()));
                    doc.add(new Paragraph("****************************************************************"));
                    doc.add(new Paragraph());
                    doc.add(new Paragraph("Total(Rs:)                 : " + totBox.getText()));
                    doc.add(new Paragraph());
                    doc.add(new Paragraph());
                    doc.add(new Paragraph("================================================="));
                    doc.add(new Paragraph("================================================="));
                    doc.close();

                } catch (DocumentException ex) {
                    Logger.getLogger(station.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(station.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Please enter a number into amount field");

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Fields are empty");
            setFieldsempty();
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totBox = new javax.swing.JTextField();
        passengerBox = new javax.swing.JTextField();
        idBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        invoice_L = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        typeBox = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        outPanel.setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("                  Pay-Station  ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("PASSENGER NAME");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("RECHARGE AMOUNT (Rs:)");

        totBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totBoxActionPerformed(evt);
            }
        });

        passengerBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        idBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idBoxActionPerformed(evt);
            }
        });
        idBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idBoxKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idBoxKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("PAYMENT TYPE");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("SMART CARD ID ");

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTime.setText("time");

        invoice_L.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        invoice_L.setText("Invoice");

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDate.setText("Date");

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        typeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Payment Type", "Cash", "Credit", "Debit" }));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("LOGOUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout outPanelLayout = new javax.swing.GroupLayout(outPanel);
        outPanel.setLayout(outPanelLayout);
        outPanelLayout.setHorizontalGroup(
            outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(invoice_L, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
            .addGroup(outPanelLayout.createSequentialGroup()
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idBox, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(typeBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(passengerBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                            .addComponent(totBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        outPanelLayout.setVerticalGroup(
            outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoice_L)
                    .addComponent(lblTime)
                    .addComponent(lblDate))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(idBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(passengerBox)))
                .addGap(18, 18, 18)
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(39, 39, 39)
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void totBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totBoxActionPerformed

    private void idBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idBoxActionPerformed

    private void idBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idBoxKeyReleased

    }//GEN-LAST:event_idBoxKeyReleased

    private void idBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idBoxKeyTyped
        String id = idBox.getText();
        String name = p.fillPassenger(id);

        if (name.equals("fail")) {
            JOptionPane.showMessageDialog(this, "Please enter values to smart ID field");
            idBox.setText("");
            passengerBox.setText("");
        } else {

            passengerBox.setText(name);
        }

    }//GEN-LAST:event_idBoxKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Payment pay = new Payment();
            pay.setInvoiceNo(invoice_L.getText());
            pay.setDate(lblDate.getText());
            pay.setTime(lblTime.getText());
            pay.setSmartId(idBox.getText());
            pay.setPassengerName(passengerBox.getText());
            pay.setType((typeBox.getSelectedItem().toString()));
            pay.setAmount(Double.parseDouble(totBox.getText()));

            if (validateFields(pay) == false) {
                JOptionPane.showMessageDialog(this, "Fields are empty");
                setFieldsempty();
            } else if (!isvalidAmount(Double.parseDouble(totBox.getText()))) {
                JOptionPane.showMessageDialog(this, "Please enter an valid amount for Recharge amount field");
                totBox.setText("");
            } else {
                try {

                    if (p.checkValidSmartCard(idBox.getText()) == 1) {
                        JOptionPane.showMessageDialog(this, "Smart Card ID Not Found !");
                        setFieldsempty();

                    } else {

                        if (p.addPayment(pay) == 0) {

                            // JOptionPane.showMessageDialog(this, "Successfully added");
                            int response = JOptionPane.showConfirmDialog(this, "Payment Successfully created ! \n do you want to Print recepit?", "Confirm", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                Print();
                                invoice();
                                setFieldsempty();
                            } else {
                                invoice();
                                setFieldsempty();
                            }

                        } else if (p.addPayment(pay) == 1) {
                            JOptionPane.showMessageDialog(this, "account update error");
                            setFieldsempty();

                        } else {
                            JOptionPane.showMessageDialog(this, "Fail");
                            setFieldsempty();
                        }
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(this, "Fields are empty");
                    setFieldsempty();
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Please enter a number");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Please enter a number");
            totBox.setText("");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Fields are empty");
            setFieldsempty();
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        serial = new SerialConnenction(null, this);
        serial.initialize();
        setFieldsempty();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(station.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(station.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(station.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(station.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new station().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idBox;
    private javax.swing.JLabel invoice_L;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel outPanel;
    private javax.swing.JTextField passengerBox;
    private javax.swing.JTextField totBox;
    private javax.swing.JComboBox<String> typeBox;
    // End of variables declaration//GEN-END:variables
}
