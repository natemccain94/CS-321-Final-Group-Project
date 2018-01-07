/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import stocktrader.Iterator;
import stocktrader.StockList;
import stocktrader.StockQuote;

/**
 * View and control the Trade Center data and functionality.
 * @author Shawn
 */
public class TradeCenterPanel extends DisplayPanel {
    
    /**
     * Default, unparameterized constructor.
     */
    public TradeCenterPanel()
    {
        initComponents();
    }

    /**
     * Creates new form TradeCenterPanel. Parameterized with a UserSystem.
     * @param userSystem User system passed in from MainView.
     */
    public TradeCenterPanel(UserSystem userSystem) {
        
        userSys = userSystem;
        
        initComponents();
    }
    
    /**
     * Checks the latest prices of all known stocks and returns a list.
     * @return List of prices represented as Floats.
     */
    public ArrayList<Float> checkPrices()
    {
        ArrayList<Float> prices = new ArrayList<Float>();
        
        Iterator stocksIterator = stocks.stepThrough();
        
        //Loop through rows by getting each Stock in StockList
        for(int j=0; j< stocks.getListSize(); j++)
        {
            if (stocksIterator.hasNext() == true)
            {
                StockQuote stock = (StockQuote)stocksIterator.next();
                prices.add(stock.get_realtime_price());
            }
        }
        
        return prices;
    }
    
    /**
     * Returns the list of all known stocks.
     * @return An OwnedStock of all known stocks.
     */
    public OwnedStock getStockList()
    {
        return stocks;
    }
    
    /**
     * Perform the logic for finding a new stock on the market and adding it to the table.
     */
    public void findStock()
    {
        String symbol = searchTextField.getText();
        
        //check if max number of stocks have been reached
        if (stocks.getListSize() < stocksTable.getRowCount())
        {
            //check to ensure that the new stock does not already exist in the list of stocks
            if (stocks.searchStock(symbol) != null)
            {
                    searchTextField.setText("EXISTS");
                    //return false;
            }

            try
            {

                if (stocks.addStockIfFound(symbol.toUpperCase()) == false)
                {
                    searchTextField.setText("INVALID");

                    //return false;
                }
            } catch(IOException e) 
            {
                e.printStackTrace();
            }

            this.performUpdate();
        }
        else
        {
            searchTextField.setText("MAX");
            findSymbolButton.setEnabled(false);
            
        }   
    }
    
    /**
     * Performs the logic for updating the table.
     */
    private void performUpdate()
    {
        if (isUpdating == false)
        {
            isUpdating = true;
            findSymbolButton.setEnabled(false);
            
            this.populateStockList();

        
            isUpdating = false;
            findSymbolButton.setEnabled(true);
        }
    }
    
    /**
     * Iterates through the StockList and populates the table with the data.
     */
    public void populateStockList() {
        
        String time = new SimpleDateFormat("MM/dd/yy' at 'HH:mm:ss, zzzz").format(new Date());
       
        try
        {
            StockList.updateStockList(stocks);
        } catch(IOException e) 
        {
            e.printStackTrace();
            return;
        }
        
        Iterator stocksIterator = stocks.stepThrough();
        //StockList.StepThrough stocksIterator = stocks.stepThrough();
        
        TableModel model = stocksTable.getModel();
        
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
                model.setValueAt(time, j, 7);
                model.setValueAt(stock.get_realtime_price(), j, 8);
                model.setValueAt(stock.get_change_percent(), j, 9);
                
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
        panelSeparator = new javax.swing.JSeparator();
        maxPriceLabel = new javax.swing.JLabel();
        maxPriceTextField = new javax.swing.JTextField();
        minLabel = new javax.swing.JLabel();
        minTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        findSymbolButton = new javax.swing.JButton();
        orderOutputLabel = new javax.swing.JLabel();

        stocksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Symbol", "Days Low", "Days High", "Year Low", "Year High", "Open", "Time Stamp", "Realtime Price", "Realtime Δ %"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stocksTable.setColumnSelectionAllowed(true);
        stocksTable.getTableHeader().setReorderingAllowed(false);
        stocksScrollPane.setViewportView(stocksTable);
        stocksTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        optionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        maxPriceLabel.setText("Max Price:");

        minLabel.setText("Min Price:");

        quantityLabel.setText("Quantity:");

        buyButton.setText("Place Buy Order");
        buyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyButtonMouseClicked(evt);
            }
        });

        sellButton.setText("Place Sell Order");
        sellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellButtonMouseClicked(evt);
            }
        });

        searchLabel.setText("Search by Symbol:");

        findSymbolButton.setText("Find Stock");
        findSymbolButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findSymbolButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchTextField)
                    .addComponent(findSymbolButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maxPriceLabel)
                            .addComponent(minLabel)
                            .addComponent(buyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sellButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maxPriceTextField)
                            .addComponent(minTextField)
                            .addComponent(quantityLabel)
                            .addComponent(quantityTextField)
                            .addComponent(searchLabel)
                            .addComponent(orderOutputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findSymbolButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderOutputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxPriceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stocksScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stocksScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void findSymbolButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findSymbolButtonMouseClicked
       
        //Perform all of the logic necessary to update.
       findStock(); 
    }//GEN-LAST:event_findSymbolButtonMouseClicked

    /**
     * Called when the Buy button is clicked. Creates a new Order and adds it to the currentUser's portfolio.
     * @param evt 
     */
    private void buyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMouseClicked

        float max = -1;
        
        float min = -1;
        
        try
        {
            max = Float.parseFloat(maxPriceTextField.getText());

            min = Float.parseFloat(minTextField.getText());
        }
        catch (java.lang.NumberFormatException e)
        {
            orderOutputLabel.setText("Max/min not provided.");
            
            return;
        }
        
        //Check bounds of min and max
        if (max > min && max > 0 && min >= 0)
        {
        
            int row = stocksTable.getSelectedRow();
            
            //Check to see if any row is selected.
            if (row != -1)
            {
                Integer quantity = 0;
                try
                {
                    //Check to see if any quantity has been set
                    quantity  = Integer.parseInt(quantityTextField.getText());
                }
                catch (java.lang.NumberFormatException e)
                {
                    orderOutputLabel.setText("Quantity invalid.");

                    return;
                }
                
                
                
                //Finally, check the quantity
                if (quantity > 0)
                {
                    
                    //All conditions passed. Make the Order.

                    TableModel model = stocksTable.getModel();

                    String symbol = model.getValueAt(row, 1).toString();

                    //Create the Order
                    Order newOrder = new Order(min, max, quantity, stocks.searchStock(symbol), true);

                    //Add the order to the user's list of Orders
                    userSys.getCurrentUser().makeOrder(newOrder);
                    
                    //Notify user.
                    orderOutputLabel.setText(quantity.toString() + " " + symbol + " stock bought.");
                }
                else
                {
                    orderOutputLabel.setText("Invalid Quantity.");
                }
            }
            else
            {
                orderOutputLabel.setText("No row selected.");
            }
        }
        else
        {
            orderOutputLabel.setText("Invalid min/max bounds.");
        }
    }//GEN-LAST:event_buyButtonMouseClicked
    
    /**
     * Called when the Sell button is clicked. Creates a new Order and adds it to the currentUser's portfolio.
     * @param evt 
     */
    private void sellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellButtonMouseClicked
        
        float max = -1;
        
        float min = -1;
        
        try
        {
            max = Float.parseFloat(maxPriceTextField.getText());

            min = Float.parseFloat(minTextField.getText());
        }
        catch (java.lang.NumberFormatException e)
        {
            orderOutputLabel.setText("Max/min not provided.");
            
            return;
        }
        
        //Check bounds of min and max
        if (max > min && max > 0 && min >= 0)
        {
        
            int row = stocksTable.getSelectedRow();
            
            //Check to see if any row is selected.
            if (row != -1)
            {
                //Check to see if any quantity has been set
                Integer quantity = Integer.parseInt(quantityTextField.getText());
                
                //Finally, check the quantity
                if (quantity > 0)
                {
                    
                    //All conditions passed. Make the Order.

                    TableModel model = stocksTable.getModel();

                    String symbol = model.getValueAt(row, 1).toString();

                    //Create the Order
                    Order newOrder = new Order(min, max, quantity, stocks.searchStock(symbol), false);

                    //Add the order to the user's list of Orders
                    userSys.getCurrentUser().makeOrder(newOrder);
                    
                    //Notify user.
                    orderOutputLabel.setText(quantity.toString() + " " + symbol + " stock sold.");
                }
                else
                {
                    orderOutputLabel.setText("Invalid Quantity.");
                }
            }
            else
            {
                orderOutputLabel.setText("No row selected.");
            }
        }
        else
        {
            orderOutputLabel.setText("Invalid min/max bounds.");
        }
    }//GEN-LAST:event_sellButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyButton;
    private javax.swing.JButton findSymbolButton;
    private javax.swing.JLabel maxPriceLabel;
    private javax.swing.JTextField maxPriceTextField;
    private javax.swing.JLabel minLabel;
    private javax.swing.JTextField minTextField;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel orderOutputLabel;
    private javax.swing.JSeparator panelSeparator;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton sellButton;
    private javax.swing.JScrollPane stocksScrollPane;
    private javax.swing.JTable stocksTable;
    // End of variables declaration//GEN-END:variables

    private OwnedStock stocks = new OwnedStock(); //List of currently owned stocks with corresponding quantities.
    
    //used to track updating state
    private boolean isUpdating = false; //Event guard flag to prevent multiple thread collision when updating.
}
