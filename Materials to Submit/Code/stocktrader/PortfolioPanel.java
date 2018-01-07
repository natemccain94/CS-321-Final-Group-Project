/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import stocktrader.Iterator;
import stocktrader.StockQuote;

/**
 * Displays the Portfolio for the current user.
 * @author Shawn
 */
public class PortfolioPanel extends DisplayPanel {

    /**
     * Creates new form PortfolioPanel
     */
    public PortfolioPanel() {
        initComponents();
        populateStockList();
    }
    
    /**
     * Populate the table with all of the stocks.
     */
    public void populateStockList() {
        //Check Filter. If Buy Orders, switch to buyOrders table model
        String filter = filterComboBox.getSelectedItem().toString();


        if (filter.equals("Currently Owned"))
        {
            //disable appropriate buttons
            cancelButton.setEnabled(false);
            buyMoreButton.setEnabled(false);
            sellMoreButton.setEnabled(false);
            
            //Update the list of stocks
            stocks = userSys.getCurrentUser().getPortfolio().getStocks();

            Iterator stocksIterator = stocks.stepThrough();
            //StockList.StepThrough stocksIterator = stocks.stepThrough();

            TableModel model = stocksTable.getModel();
            
            if (stocks.getListSize() > 0)
            {
                //Loop through rows by getting each Stock in StockList
                for(int j=0; j< stocks.getListSize(); j++)
                {
                    if (stocksIterator.hasNext() == true)
                    {
                        StockQuote stock = (StockQuote)stocksIterator.next();

                        model.setValueAt(stock.getName(), j, 0);
                        model.setValueAt(stock.getSymbol(), j, 1);
                        model.setValueAt(stock.getDaysLow(), j, 2);
                        model.setValueAt(stock.getDaysHigh(), j, 3);
                        model.setValueAt(stock.getYearLow(), j, 4);
                        model.setValueAt(stock.getYearHigh(), j, 5);
                        model.setValueAt(stock.getOpen(), j, 6);
                        model.setValueAt(stock.get_timeStamp(), j, 7);
                        model.setValueAt(stock.get_realtime_price(), j, 8);
                        model.setValueAt(stock.get_change_percent(), j, 9);
                        model.setValueAt(stocks.getQuantity(stock.getName()), j, 10); 

                    }
                }
            }
            else
            {
                //empty list of stocks
                //Loop through rows by getting each Stock in StockList
                for(int j=0; j< model.getRowCount(); j++)
                {
                    model.setValueAt(new String(), j, 0);
                    model.setValueAt(new String(), j, 1);
                    model.setValueAt(new String(), j, 2);
                    model.setValueAt(new String(), j, 3);
                    model.setValueAt(new String(), j, 4);
                    model.setValueAt(new String(), j, 5);
                    model.setValueAt(new String(), j, 6);
                    model.setValueAt(new String(), j, 7);
                    model.setValueAt(new String(), j, 8);
                    model.setValueAt(new String(), j, 9);
                    model.setValueAt(new String(), j, 10);
                    model.setValueAt(new String(), j, 11);
                    model.setValueAt(new String(), j, 12);

                }
            }
        }
        else
        {
            //User has selected one of the Orders filters
            ArrayList<Order> orders = userSys.getCurrentUser().getPortfolio().getOrders();
            
            TableModel model = stocksTable.getModel();
            
            //If looking for buys, iterate through buys and update table
            
            //Clear the list first
            //Loop through rows by getting each Stock in StockList
            for(int j=0; j< model.getRowCount(); j++)
            {
                model.setValueAt(new String(), j, 0);
                model.setValueAt(new String(), j, 1);
                model.setValueAt(new String(), j, 2);
                model.setValueAt(new String(), j, 3);
                model.setValueAt(new String(), j, 4);
                model.setValueAt(new String(), j, 5);
                model.setValueAt(new String(), j, 6);
                model.setValueAt(new String(), j, 7);
                model.setValueAt(new String(), j, 8);
                model.setValueAt(new String(), j, 9);
                model.setValueAt(new String(), j, 10);
                model.setValueAt(new String(), j, 11);
                model.setValueAt(new String(), j, 12);

            }
            
            //Now add the orders
            
            //keeps track of orders added so that sale and buy orders are not printed on correct rows
            int ordersAdded = 0;
            
            if (filter.equals("Pending Buys"))
            {
                
                //disable appropriate buttons
                cancelButton.setEnabled(true);
                buyMoreButton.setEnabled(true);
                sellMoreButton.setEnabled(false);
                
                
                //Loop through rows by getting each order in orders
                for(int j=0; j < orders.size(); j++)
                {
                    Order order = orders.get(j);

                    if (order.Buy() == true)
                    {
                        StockQuote stock = order.getStockQuote();

                        model.setValueAt(stock.getName(), ordersAdded, 0);
                        model.setValueAt(stock.getSymbol(), ordersAdded, 1);
                        model.setValueAt(stock.getDaysLow(), ordersAdded, 2);
                        model.setValueAt(stock.getDaysHigh(), ordersAdded, 3);
                        model.setValueAt(stock.getYearLow(), ordersAdded, 4);
                        model.setValueAt(stock.getYearHigh(), ordersAdded, 5);
                        model.setValueAt(stock.getOpen(), ordersAdded, 6);
                        model.setValueAt(stock.get_timeStamp(), ordersAdded, 7);
                        model.setValueAt(stock.get_realtime_price(), ordersAdded, 8);
                        model.setValueAt(stock.get_change_percent(), ordersAdded, 9);
                        model.setValueAt(order.getQuantity(), ordersAdded, 10);
                        model.setValueAt(order.getMinPrice(), ordersAdded, 11);
                        model.setValueAt(order.getMaxPrice(), ordersAdded, 12);
                        
                        ordersAdded++;

                    }
                }
            }
            //Looking for sales, iterate through sales and update table
            else
            {
                
                //disable appropriate buttons
                cancelButton.setEnabled(true);
                buyMoreButton.setEnabled(false);
                sellMoreButton.setEnabled(true);
                
                //Loop through rows by getting each Stock in StockList
                for(int j=0; j< orders.size(); j++)
                {
                    Order order = orders.get(j);

                    if (order.Buy() == false)
                    {
                        StockQuote stock = order.getStockQuote();

                        model.setValueAt(stock.getName(), ordersAdded, 0);
                        model.setValueAt(stock.getSymbol(), ordersAdded, 1);
                        model.setValueAt(stock.getDaysLow(), ordersAdded, 2);
                        model.setValueAt(stock.getDaysHigh(), ordersAdded, 3);
                        model.setValueAt(stock.getYearLow(), ordersAdded, 4);
                        model.setValueAt(stock.getYearHigh(), ordersAdded, 5);
                        model.setValueAt(stock.getOpen(), ordersAdded, 6);
                        model.setValueAt(stock.get_timeStamp(), ordersAdded, 7);
                        model.setValueAt(stock.get_realtime_price(), ordersAdded, 8);
                        model.setValueAt(stock.get_change_percent(), ordersAdded, 9);
                        model.setValueAt(order.getQuantity(), ordersAdded, 10);
                        model.setValueAt(order.getMinPrice(), ordersAdded, 11);
                        model.setValueAt(order.getMaxPrice(), ordersAdded, 12);
                        
                        ordersAdded++;
                    }
                }
            }
        }
        
        this.repaint();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stocksScrollPane = new javax.swing.JScrollPane();
        stocksTable = new javax.swing.JTable();
        optionsPanel = new javax.swing.JPanel();
        filterLabel = new javax.swing.JLabel();
        filterComboBox = new javax.swing.JComboBox<>();
        selectedLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        buyMoreButton = new javax.swing.JButton();
        quantityTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        optionsSeparator = new javax.swing.JSeparator();
        sellMoreButton = new javax.swing.JButton();

        stocksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Symbol", "Days Low", "Days High", "Year Low", "Year High", "Open", "Time Stamp", "Realtime Price", "Realtime Δ %", "Quantity", "Min Price", "Max Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stocksTable.setColumnSelectionAllowed(true);
        stocksTable.getTableHeader().setReorderingAllowed(false);
        stocksScrollPane.setViewportView(stocksTable);
        stocksTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        optionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        filterLabel.setText("Filter:");

        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Currently Owned", "Pending Buys", "Pending Sales" }));
        filterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboBoxActionPerformed(evt);
            }
        });

        selectedLabel.setText("Currently Selected:");

        cancelButton.setText("Cancel Order");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        buyMoreButton.setText("Buy More");
        buyMoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyMoreButtonMouseClicked(evt);
            }
        });

        quantityLabel.setText("Quantity:");

        sellMoreButton.setText("Sell More");
        sellMoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellMoreButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionsSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyMoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantityTextField)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterLabel)
                            .addComponent(selectedLabel)
                            .addComponent(quantityLabel))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(sellMoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(optionsSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedLabel)
                .addGap(18, 18, 18)
                .addComponent(quantityLabel)
                .addGap(15, 15, 15)
                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buyMoreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellMoreButton)
                .addContainerGap(320, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stocksScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stocksScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buyMoreButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyMoreButtonMouseClicked
        
        Integer quantity = Integer.parseInt(quantityTextField.getText());
        
        //debug
        System.out.print("\n" + quantity.toString() + "\n");
        
        if (quantity > 0)
        {
        
            int row  = stocksTable.getSelectedRow();
            
            //if a row is selected
            if (row != -1)
            {

                String symbol = stocksTable.getModel().getValueAt(row, 1).toString();
                
                System.out.print("\n" + symbol + "\n");
                
                Portfolio port = userSys.getCurrentUser().getPortfolio();
                
                ArrayList<Order> orders = userSys.getCurrentUser().getPortfolio().getOrders();
                
                for(int j=0; j < orders.size(); j++)
                {
                    Order order = orders.get(j);
                    
                    System.out.print("\n" + order.getStockQuote().getSymbol() + "\n");
                    
                    if (order.getStockQuote().getSymbol().equals(symbol))
                    {
                        order.setQuantity(order.getQuantity() + quantity);
                    }
                    
                }

                port.setOrders(orders);
                
                userSys.getCurrentUser().setPortfolio(port);

                populateStockList();
            }
        }
    }//GEN-LAST:event_buyMoreButtonMouseClicked

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        Integer quantity = Integer.parseInt(quantityTextField.getText());
        
        //debug
        System.out.print("\n" + quantity.toString() + "\n");

        int row  = stocksTable.getSelectedRow();

        //if a row is selected
        if (row != -1)
        {

            String symbol = stocksTable.getModel().getValueAt(row, 1).toString();

            System.out.print("\n" + symbol + "\n");

            Portfolio port = userSys.getCurrentUser().getPortfolio();

            ArrayList<Order> orders = userSys.getCurrentUser().getPortfolio().getOrders();

            for(int j=0; j < orders.size(); j++)
            {
                Order order = orders.get(j);

                System.out.print("\n" + order.getStockQuote().getSymbol() + "\n");

                if (order.getStockQuote().getSymbol().equals(symbol))
                {
                    orders.remove(order);
                }

            }

            port.setOrders(orders);

            userSys.getCurrentUser().setPortfolio(port);

            populateStockList();
        }
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void filterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboBoxActionPerformed
        populateStockList();
    }//GEN-LAST:event_filterComboBoxActionPerformed

    private void sellMoreButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellMoreButtonMouseClicked
        Integer quantity = Integer.parseInt(quantityTextField.getText());
        
        //debug
        System.out.print("\n" + quantity.toString() + "\n");
        
        if (quantity > 0)
        {
        
            int row  = stocksTable.getSelectedRow();
            
            //if a row is selected
            if (row != -1)
            {

                String symbol = stocksTable.getModel().getValueAt(row, 1).toString();
                
                System.out.print("\n" + symbol + "\n");
                
                Portfolio port = userSys.getCurrentUser().getPortfolio();
                
                ArrayList<Order> orders = userSys.getCurrentUser().getPortfolio().getOrders();
                
                for(int j=0; j < orders.size(); j++)
                {
                    Order order = orders.get(j);
                    
                    System.out.print("\n" + order.getStockQuote().getSymbol() + "\n");
                    
                    if (order.getStockQuote().getSymbol().equals(symbol))
                    {
                        order.setQuantity(order.getQuantity() + quantity);
                    }
                    
                }

                port.setOrders(orders);
                
                userSys.getCurrentUser().setPortfolio(port);

                populateStockList();
            }
        }
    }//GEN-LAST:event_sellMoreButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyMoreButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JSeparator optionsSeparator;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JLabel selectedLabel;
    private javax.swing.JButton sellMoreButton;
    private javax.swing.JScrollPane stocksScrollPane;
    private javax.swing.JTable stocksTable;
    // End of variables declaration//GEN-END:variables

    private OwnedStock stocks = new OwnedStock();//List of owned stocks and corresponding quantities
}
