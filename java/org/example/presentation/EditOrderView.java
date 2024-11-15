package org.example.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditOrderView extends JFrame {

    private final JTextField orderId;
    private final JTextField newClientIdText;
    private final JTextField newProductIdText;
    private final JButton backBtn;
    private final JButton editBtn;
    private final JTextField newQuantityText;



    public EditOrderView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 295, 329);
        setVisible(true);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Edit Order");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setBounds(100, 10, 120, 20);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Id:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 60, 70, 20);
        contentPane.add(lblNewLabel_1);

        orderId = new JTextField();
        orderId.setBounds(120, 60, 150, 20);
        contentPane.add(orderId);
        orderId.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("New Client Id:");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1_1.setBounds(10, 100, 90, 20);
        contentPane.add(lblNewLabel_1_1);

        newClientIdText = new JTextField();
        newClientIdText.setColumns(10);
        newClientIdText.setBounds(120, 100, 150, 20);
        contentPane.add(newClientIdText);

        JLabel lblNewLabel_1_1_1 = new JLabel("New Product Id:");
        lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1_1_1.setBounds(10, 140, 110, 20);
        contentPane.add(lblNewLabel_1_1_1);

        newProductIdText = new JTextField();
        newProductIdText.setColumns(10);
        newProductIdText.setBounds(120, 140, 150, 20);
        contentPane.add(newProductIdText);

        editBtn = new JButton("Edit");
        editBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        editBtn.setBounds(105, 220, 85, 21);
        contentPane.add(editBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        backBtn.setBounds(180, 261, 85, 21);
        contentPane.add(backBtn);

        JLabel lblNewLabel_1_1_2 = new JLabel("New Quantity:");
        lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1_1_2.setBounds(10, 180, 90, 20);
        contentPane.add(lblNewLabel_1_1_2);

        newQuantityText = new JTextField();
        newQuantityText.setColumns(10);
        newQuantityText.setBounds(120, 180, 150, 20);
        contentPane.add(newQuantityText);
    }

    public JButton getBackBtn() {
        return this.backBtn;
    }

    public JButton getEditBtn() {
        return this.editBtn;
    }

    public String getId() {
        return this.orderId.getText();
    }

    public String getNewClientId() {
        return this.newClientIdText.getText();
    }

    public String getNewProductId() {
        return this.newProductIdText.getText();
    }

    public String getNewQuantity() {
        return this.newQuantityText.getText();
    }

    public void repaint() {
        this.newClientIdText.setText("");
        this.newProductIdText.setText("");
        this.newQuantityText.setText("");
        this.orderId.setText("");
    }

}
