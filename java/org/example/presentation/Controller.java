package org.example.presentation;

import org.example.bll.ClientBLL;
import org.example.bll.OrderBLL;
import org.example.bll.ProductBLL;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Product;
import org.example.start.ReflectionExample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private final ClientBLL clientBLL;
    private final OrderBLL orderBLL;
    private final ProductBLL productBLL;
    private final FileWriter myWriter;


    public Controller(View view) throws IOException {
        this.clientBLL = new ClientBLL();
        this.orderBLL = new OrderBLL();
        this.productBLL = new ProductBLL();
        myWriter = new FileWriter("bill.txt");

        view.getAddClientBtn().addActionListener(e -> {
            AddClientView addClientView = new AddClientView();
            view.setVisible(false);
            addClientView.getBackBtn().addActionListener(e1 -> {
                addClientView.dispose();
                view.setVisible(true);
            });
            addClientView.getAddBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (addClientView.getAge().equals("") || addClientView.getAge().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The client age is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    clientBLL.insertClient(new Client(addClientView.getName(), addClientView.getEmail(), Integer.parseInt(addClientView.getAge())));
                    addClientView.repaint();
                }
            });

        });
        view.getAddOrderBtn().addActionListener(e -> {
            view.setVisible(false);
            AddOrderView addOrderView = new AddOrderView();
            addOrderView.getBackBtn().addActionListener(e12 -> {
                view.setVisible(true);
                addOrderView.dispose();
            });
            addOrderView.getAddBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int quantity = 0;
                    float price = 0;
                    Product product = null;
                    if (addOrderView.getProductId().equals("") || addOrderView.getProductId().equals(" ")
                            || addOrderView.getQuantity().equals("") || addOrderView.getQuantity().equals(" ")
                            || addOrderView.getClientId().equals("") || addOrderView.getClientId().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid data!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    try {
                        product = productBLL.findById(Integer.parseInt(addOrderView.getProductId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (product != null) {
                        quantity = product.getQuantity();
                    }
                    try {
                        price = productBLL.findPriceById(Integer.parseInt(addOrderView.getProductId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    quantity -= Integer.parseInt(addOrderView.getQuantity());
                    if (quantity < 0) {
                        throw new IllegalArgumentException("Not enough quantity of this product!");
                    } else {

                        orderBLL.insertOrder(new Orders(Integer.parseInt(addOrderView.getClientId()), Integer.parseInt(addOrderView.getProductId()),
                                Integer.parseInt(addOrderView.getQuantity()), (int) (price * Integer.parseInt(addOrderView.getQuantity()))));

                        if (product != null)
                            product.setQuantity(quantity);

                        productBLL.editProduct(product);
                    }
                    addOrderView.repaint();
                }
            });

        });
        view.getAddProductBtn().addActionListener(e -> {
            view.setVisible(false);
            AddProductView addProductView = new AddProductView();
            addProductView.getBackBtn().addActionListener(e13 -> {
                view.setVisible(true);
                addProductView.dispose();
            });
            addProductView.getAddBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (addProductView.getQuantity().equals("") || addProductView.getQuantity().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The quantity is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (addProductView.getPrice().equals("") || addProductView.getPrice().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The price is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    productBLL.insertProduct(new Product(addProductView.getName(), Integer.parseInt(addProductView.getQuantity()),
                            Integer.parseInt(addProductView.getPrice())));
                    addProductView.repaint();
                }
            });

        });
        view.getDeleteClientBtn().addActionListener(e -> {
            DeleteClientView deleteClientView = new DeleteClientView();
            view.setVisible(false);
            deleteClientView.getBackBtn().addActionListener(e14 -> {
                deleteClientView.dispose();
                view.setVisible(true);
            });
            deleteClientView.getDeleteBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteClientView.getID().equals("") || deleteClientView.getID().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The ID is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    clientBLL.deleteClient(Integer.parseInt(deleteClientView.getID()));
                    deleteClientView.repaint();
                }
            });
        });
        view.getDeleteOrderBtn().addActionListener(e -> {
            DeleteOrderView deleteOrderView = new DeleteOrderView();
            view.setVisible(false);
            deleteOrderView.getBackBtn().addActionListener(e15 -> {
                deleteOrderView.dispose();
                view.setVisible(true);
            });
            deleteOrderView.getDeleteBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteOrderView.getID().equals("") || deleteOrderView.getID().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The ID is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    orderBLL.deleteOrder(Integer.parseInt(deleteOrderView.getID()));
                    deleteOrderView.repaint();
                }
            });

        });
        view.getDeleteProductBtn().addActionListener(e -> {
            DeleteProductView deleteProductView = new DeleteProductView();
            view.setVisible(false);
            deleteProductView.getBackBtn().addActionListener(e16 -> {
                deleteProductView.dispose();
                view.setVisible(true);
            });
            deleteProductView.getDeleteBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteProductView.getID().equals("") || deleteProductView.getID().equals(" ")) {

                        JOptionPane.showMessageDialog(null,
                                "The ID is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    productBLL.deleteProduct(Integer.parseInt(deleteProductView.getID()));
                    deleteProductView.repaint();
                }
            });

        });
        view.getEditClientBtn().addActionListener(e -> {
            EditClientView editClientView = new EditClientView();
            view.setVisible(false);
            editClientView.getBackBtn().addActionListener(e17 -> {
                view.setVisible(true);
                editClientView.dispose();

            });
            editClientView.getEditBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editClientView.getId().equals("") || editClientView.getId().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The ID is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    if (editClientView.getNewAge().equals(" ") || editClientView.getNewAge().equals("")) {
                        JOptionPane.showMessageDialog(null,
                                "The age is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    clientBLL.editClient(new Client(Integer.parseInt(editClientView.getId()), editClientView.getNewName()
                            , editClientView.getNewEmail(), Integer.parseInt(editClientView.getNewAge())));
                    editClientView.repaint();
                }
            });

        });
        view.getEditOrderBtn().addActionListener(e -> {
            view.setVisible(false);
            EditOrderView editOrderView = new EditOrderView();
            editOrderView.getBackBtn().addActionListener(e18 -> {
                view.setVisible(true);
                editOrderView.dispose();
            });
            editOrderView.getEditBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Orders order = null;
                    Product product = null;
                    if (editOrderView.getId().equals("") || editOrderView.getId().equals(" ")
                            || editOrderView.getNewProductId().equals("") || editOrderView.getNewProductId().equals(" ")
                            || editOrderView.getNewQuantity().equals("") || editOrderView.getNewQuantity().equals(" ")
                            || editOrderView.getNewClientId().equals("") || editOrderView.getNewClientId().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "The product name is invalid!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    float price = 0;
                    try {
                        order = orderBLL.findById(Integer.parseInt(editOrderView.getId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        product = productBLL.findById(Integer.parseInt(editOrderView.getNewProductId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (order == null || product == null)
                        throw new IllegalArgumentException("InvalId Ids!");

                    int newOrderQuantity = Integer.parseInt(editOrderView.getNewQuantity()) - order.getQuantity();
                    if (newOrderQuantity > product.getQuantity()) {
                        throw new IllegalArgumentException("The quantity is not enough!");
                    }

                    try {
                        price = productBLL.findPriceById(Integer.parseInt(editOrderView.getNewProductId()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    product.setQuantity(product.getQuantity() - newOrderQuantity);
                    productBLL.editProduct(product);
                    orderBLL.editOrder(new Orders(Integer.parseInt(editOrderView.getId()), Integer.parseInt(editOrderView.getNewClientId()), Integer.parseInt(editOrderView.getNewProductId())
                            , Integer.parseInt(editOrderView.getNewQuantity()), (int) (price * Integer.parseInt(editOrderView.getNewQuantity()))));
                    editOrderView.repaint();
                }
            });

        });
        view.getEditProductBtn().addActionListener(e -> {
            EditProductView editProductView = new EditProductView();
            view.setVisible(false);
            editProductView.getBackBtn().addActionListener(e19 -> {
                view.setVisible(true);
                editProductView.dispose();
            });
            editProductView.getEditBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (editProductView.getId().equals("") || editProductView.getId().equals(" ")
                            || editProductView.getNewQuantity().equals("") || editProductView.getNewQuantity().equals(" ")
                            || editProductView.getNewPrice().equals("") || editProductView.getNewPrice().equals(" ")) {
                        JOptionPane.showMessageDialog(null,
                                "InvalId data!",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                    productBLL.editProduct(new Product(Integer.parseInt(editProductView.getId()), editProductView.getNewName(),
                            Integer.parseInt(editProductView.getNewQuantity()), Integer.parseInt(editProductView.getNewPrice())));
                    editProductView.repaint();
                }
            });

        });
        view.getShowClientsBtn().addActionListener(e -> {
            view.setVisible(false);
            ShowClientsView showClientsView = new ShowClientsView( ReflectionExample.retrieveProperties(clientBLL.printAllClients(),new Client()));
            showClientsView.getBackBtn().addActionListener(e110 -> {
                view.setVisible(true);
                showClientsView.dispose();
            });
        });
        view.getShowOrdersBtn().addActionListener(e -> {
            view.setVisible(false);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            ArrayList<Orders> orders = orderBLL.printAllOrders();
            ShowOrdersView showOrdersView = new ShowOrdersView(ReflectionExample.retrieveProperties(orderBLL.printAllOrders(),new Orders()));
            for (Orders order : orders) {
                try {
                    myWriter.write("Order Bill\n");
                    myWriter.write("Order Id: " + order.getId() + "\n");
                    myWriter.write("Client Name: " + clientBLL.findById(order.getClientId()).getName() + "\n");
                    myWriter.write("Product Name: " + productBLL.findById(order.getProductId()).getName() + "\n");
                    myWriter.write("Quantity: " + order.getQuantity() + "\n");
                    myWriter.write("Price: " + order.getPrice() + "\n");
                    myWriter.write("Data: " + formatter.format(date) + "\n");
                    myWriter.write("\n");
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }
            try {
                myWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            showOrdersView.getBackBtn().addActionListener(e111 -> {
                view.setVisible(true);
                showOrdersView.dispose();
            });

        });
        view.getShowProductsBtn().addActionListener(e -> {
            view.setVisible(false);
            ShowProductsView showProductsView = new ShowProductsView(ReflectionExample.retrieveProperties(productBLL.printAllProducts(),new Product()));
            showProductsView.getBackBtn().addActionListener(e112 -> {
                view.setVisible(true);
                showProductsView.dispose();
            });


        });
    }
}