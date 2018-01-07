/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocktrader;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.io.*;


/**
 * Frame for Stock Trader that displays all of the Model data and provides
 * control access to it.
 * @author Shawn
 */
public class MainView extends javax.swing.JFrame {
    /**
     * Creates new form MainView
     */
    public MainView() {       
        
        //make things look pretty
        this.setExtendedState(this.MAXIMIZED_BOTH); 
        
        //initialize the generated components
        initComponents();
        
        //Run the load method to load previous data.
        load();
        
        //now that displayPanel has been initialized, set the value for displayLayout
        displayLayout = (CardLayout) displayPanel.getLayout();
        
        //initialize the display panel's components
        setupDisplayPanel();
        
        //Set the Virtual Wallet 
        currentUser.setMoney(currentUser.getMoney());
        
        //Set virtual wallet display
        newsLabel.setText("Virtual Wallet: $" + currentUser.getMoney());
    }
    
    /**
     * Loads previous session's data from StockTrader.ser in the current working directory.
     */
    private void load()
    {
        UserSystem u = null;
      try {
         FileInputStream fileIn = new FileInputStream("StockTrader.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         u = (UserSystem) in.readObject();
         in.close();
         fileIn.close();
         
         userSys = u;
         currentUser = userSys.getCurrentUser();
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Save file not found.");
         c.printStackTrace();
         return;
      }
      
        
    }
    
    /**
     * Performs all logic necessary to perform an update.
     * Updates the TradeCenter with most recent data from Yahoo. Then updates
     *  the portfolio as described below.
     *Updates the Portofolio's display of owned stock, buy orders, and sell orders
     *First it updates the User System. This updates and buy orders or sell orders
     *  that may have been made in the Trade Center.
     *Then, it checks the tradeCenter to compare current stock prices to the min/max
     *  in each Order.
     *If min/max conditions are met, then it adds the Order's StockQuote
     *  to the list of bought stock.
     *  It only does this if the user has enough money.
     * If the min/max conditions are met, but it is a sale order,
     *  Then, first, check if the user has that stock,
     *  if they do, then make the transaction.
     *Finally, it updates the portfolio's table with the new data.
     *NOTE: If the current panel IS the portfolio, then some of this is redundant.
     * @return A boolean that returns false if update fails.
     */
    private boolean performUpdate()
    {
        //get the most recent UserSystem from the currentPanel
        userSys = currentPanel.getUserSystem();
        currentUser = userSys.getCurrentUser();
        
        //Updates the Portofolio's display of owned stock, buy orders, and sell orders
        //First it updates the User System. This updates and buy orders or sell orders
        //  that may have been made in the Trade Center.
        //Then, it checks the tradeCenter to compare current stock prices to the min/max
        // in each Order.
        //If min/max conditions are met, then it adds the Order's StockQuote
        //  to the list of bought stock.
        //  It only does this if the user has enough money.
        // If the min/max conditions are met, but it is a sale order,
        //  Then, first, check if the user has that stock,
        //  if they do, then make the transaction.
        //Finally, it updates the portfolio's table with the new data.
        //NOTE: If the current panel IS the portfolio, then some of this is redundant.
        
        //check TradeCenter's prices
        ArrayList<Float> prices = tradeCenter.checkPrices();
        
        OwnedStock stocks = tradeCenter.getStockList();
        
        ArrayList<Order> orders = currentUser.getPortfolio().getOrders();
        
        Iterator stocksIterator = stocks.stepThrough();
        
        //Loop through rows by getting each Stock in StockList
        for(int i=0; i < stocks.getListSize(); i++)
        {
            if (stocksIterator.hasNext() == true)
            {
                StockQuote stock = (StockQuote)stocksIterator.next();
                
                //if current stock in tradeCenter is also in Portfolio's orders
                for (int j = 0; j < orders.size(); j++)
                {
                   Order order = orders.get(j);
                   
                   //compare stock symbols
                   if (stock.getSymbol().equals(order.getStockQuote().getSymbol()))
                   {
                       //check if price of that stock is min < price < max
                       if (stock.get_realtime_price() > order.getMinPrice() && stock.get_realtime_price() < order.getMaxPrice())
                       {
                           System.out.print("\nMoney: " + currentUser.getMoney());
                           System.out.print("\nPrice: " + (stock.get_realtime_price() * order.getQuantity()) + "\n");
                           
                           //check if the order is buy or sell
                           if (order.Buy() == true)
                           {
                           
                                //check if the user can afford the purchase
                                if (currentUser.getMoney() >= (stock.get_realtime_price() * order.getQuantity()))
                                {
                                    Portfolio port = currentUser.getPortfolio();
                                    
                                    //copy the stock
                                    StockQuote stockCopy = stock;

                                    //add it to portfolio's list of stocks in stated quantity
                                     port.addStock(stockCopy);
                                    
                                     
                                    //old quantity
                                    Integer oldQuant = port.getStocks().getQuantity(stock.getName());
                                    
                                    //if oldQuant is null, that means that the user owns none of the stock
                                    if (oldQuant == null)
                                    {
                                        oldQuant = 0;
                                    }

                                    //set the new quantity of the stock as current quantity + new quantity
                                    port.getStocks().setQuantity(stock, oldQuant +  (int) order.getQuantity());

                                    //remove the Order from the portfolio
                                    port.removeOrder(order);

                                    //take the appropriate amount from the user's wallet
                                    currentUser.setMoney(currentUser.getMoney() - (stock.get_realtime_price() * order.getQuantity()));
                                }
                           }
                           //it is a sale order
                           else
                           {
                               //does the user already own that amount of stock?
                               
                               Portfolio port = currentUser.getPortfolio();
                               
                               OwnedStock ownedStocks = port.getStocks();
                               
                               //iterate through OWNED stock
                               Iterator ownedStocksIterator = ownedStocks.stepThrough();
                               
                               for (int k = 0; k < ownedStocks.getListSize(); k ++)
                               {
                                   System.out.print("IN!!!");
                                   
                                   if (ownedStocksIterator.hasNext() == true)
                                    {
                                        StockQuote ownedStock = (StockQuote)ownedStocksIterator.next();
                                        
                                        System.out.print("IN!!!");
                                        
                                        //check if they're the same stock
                                        if (ownedStock.getSymbol().equals(order.getStockQuote().getSymbol()))
                                        {
                                            System.out.print("OUT!!!");
                                            
                                            
                                            Integer oldQuant = port.getStocks().getQuantity(stock.getName());
                                            
                                            //if oldQuant is null, that means that the user owns none of the stock
                                            //check if there is enough stock to sell
                                            if(oldQuant!= null && (oldQuant >= order.getQuantity()))
                                            {
                                                //copy the stock
                                                StockQuote stockCopy = ownedStock;

                                                 //add it to portfolio's list of stocks in stated quantity
                                                currentUser.getPortfolio().addStock(stockCopy);

                                                //set the quantity of the purchase
                                                currentUser.getPortfolio().getStocks().setQuantity(ownedStock, (oldQuant - (int) order.getQuantity()));

                                                 //remove the Order from the portfolio
                                                 currentUser.getPortfolio().removeOrder(order);

                                                 //take the appropriate amount from the user's wallet
                                                 currentUser.setMoney(currentUser.getMoney() + (stock.get_realtime_price() * order.getQuantity()));
                                            }
                                            
                                        }
                                        
                                    }
                                   
                               }
                           }
                       }
                   }
                }
            }
        }
        
        portfolio.setUserSystem(userSys);
        portfolio.populateStockList();
        
        //do stuff
        tradeCenter.populateStockList();

        //update virtual wallet display
        newsLabel.setText("Virtual Wallet: $" + currentUser.getMoney());
        
        //reset updateButton
        isUpdating = false;
        updateButton.setText("Update");
        updateButton.setEnabled(true);
        
        return true;
    }
    
    /**
     * UNUSED! Switches the display panel to the login screen
     */
    public void switchToLogin() {
        
        /*
        removeCardFromDisplayPanel(login);
        login = new stocktrader.LoginPanel();
        login.setUserSystem(userSys);
        addCardToDisplayPanel(login, "login");
        changeDisplayPanel("login");
        currentPanel = new stocktrader.LoginPanel();
        */
    }
    

    /**
     * Switches the display panel to the portfolio screen
     */
    public void switchToPortfolio() {
        
        UserSystem userSys = currentPanel.getUserSystem();
        
        portfolio.setUserSystem(userSys);
        
        changeDisplayPanel("portfolio");
        currentPanel = portfolio;
    }
    
    /**
     * Switches the display panel to the Trade Center screen
     */
    public void switchToTradeCenter() {
        
        //get current user system of currentPanel
        
        //set current user systesm of tradeCenter as updated user system
        
        UserSystem userSys = currentPanel.getUserSystem();
        
        tradeCenter.setUserSystem(userSys);
        
        changeDisplayPanel("tradeCenter");
        
        currentPanel = tradeCenter;
    }
    
    /**
     * UNUSED! Switches the current panel to the UserPanel.
     */
    public void switchToUser() {
        
        /*
        UserSystem userSys = currentPanel.getUserSystem();
        
        userPanel.setUserSystem(userSys);
        
        //get any changes to UserSystem
        
        changeDisplayPanel("userPanel");
        
        currentPanel = userPanel;
        */
    }
    
    /**
     *Adds all of the cards to the deck of the display panel and then changes
     * to login. Used whenever a login occurs.
     */
    private void setupDisplayPanel()
    {
        //Set the UserSystem of the panels
        //login.setUserSystem(userSys);
        portfolio.setUserSystem(userSys);
        tradeCenter.setUserSystem(userSys);
        //userPanel.setUserSystem(userSys);
        
        //Add the cards to the display panel's list
        //addCardToDisplayPanel(login, "login");
        addCardToDisplayPanel(portfolio, "portfolio");
        addCardToDisplayPanel(tradeCenter, "tradeCenter");
        //addCardToDisplayPanel(userPanel, "userPanel");
        changeDisplayPanel("tradeCenter");
        currentPanel = tradeCenter;
    }
    
    /**
     * Changes visible panel for the displayPanel
     * @param cardname Name of the card in the deck to display.
     */
    private void changeDisplayPanel(String cardname)
    {
        displayLayout.show(displayPanel, cardname);
        
        revalidate();
        repaint();
    }
    
    /**
     * Adds a JPanel to the display panel
     * @param panel Card to be added.
     * @param cardname Index of card to be added.
     */
    private void addCardToDisplayPanel(javax.swing.JPanel panel, String cardname)
    {
        displayPanel.add(panel, cardname);
    }
    
    /**
     * Removes a JPanel from the Display Panel
     * @param panel Card to be removed.
     */
    private void removeCardFromDisplayPanel(javax.swing.JPanel panel)
    {
        displayLayout.removeLayoutComponent(panel);
    }
    
    /**
     * Called from LoginPanel when the user clicks 'Login'.
     */
    public void onLogin() {
        //update the current user
        
        //change the text on the user button
        
        //update the wallet thing in the top
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newsLabel = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();
        portfolioButton = new javax.swing.JToggleButton();
        tradeCenterButton = new javax.swing.JToggleButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        newsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newsLabel.setText("Virtual Wallet: $0");
        newsLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        displayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        displayPanel.setLayout(new java.awt.CardLayout());

        updateButton.setText("Update");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        portfolioButton.setText("Portfolio");
        portfolioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                portfolioButtonMouseClicked(evt);
            }
        });

        tradeCenterButton.setText("Trade Center");
        tradeCenterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeCenterButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newsLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portfolioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tradeCenterButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateButton)
                        .addComponent(saveButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(portfolioButton)
                        .addComponent(tradeCenterButton)))
                .addGap(18, 18, 18)
                .addComponent(newsLabel)
                .addGap(18, 18, 18)
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
       //user Data Handler to poll for any updates to stockData

        //check all orders for current user and see if criteria are met
        
        //sentinal to guard from event hell
        if (isUpdating == false)
        {
            isUpdating = true;
            updateButton.setText("Updating...");
            updateButton.setEnabled(false);
            
            performUpdate();

        }
            
    }//GEN-LAST:event_updateButtonMouseClicked

    private void tradeCenterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeCenterButtonActionPerformed

        //userButton.setSelected(false);
        portfolioButton.setSelected(false);

        switchToTradeCenter();
    }//GEN-LAST:event_tradeCenterButtonActionPerformed

    private void portfolioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portfolioButtonMouseClicked
        tradeCenterButton.setSelected(false);
        //userButton.setSelected(false);

        switchToPortfolio();
    }//GEN-LAST:event_portfolioButtonMouseClicked

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        
        try {
         FileOutputStream fileOut =
         new FileOutputStream("StockTrader.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(userSys);
         out.close();
         fileOut.close();
      }catch(IOException i) {
         i.printStackTrace();
      }
    }//GEN-LAST:event_saveButtonMouseClicked

    /**
     * Main program to start the Stock Trader.
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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stocktrader.MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel displayPanel;
    private javax.swing.JLabel newsLabel;
    private javax.swing.JToggleButton portfolioButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JToggleButton tradeCenterButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    //This code section is for the non-generated variable definitions
    
    //used to track updating state
    private boolean isUpdating = false; //Event flag that prevents multiple thread collision when updating.
    
    //Used for keeping track of Users
    private UserSystem userSys = new UserSystem(); // The User System that contains all of the current user data.
    private User currentUser = userSys.getCurrentUser(); //The Current User object.

    //Create several Panels and switch between them when appropriate
    private stocktrader.PortfolioPanel portfolio = new stocktrader.PortfolioPanel(); // The current Portfolio panel.
    private stocktrader.TradeCenterPanel tradeCenter = new stocktrader.TradeCenterPanel(); // The current Trade Center Panel.
    //private stocktrader.UserPanel userPanel = new stocktrader.UserPanel(); // The current User Panel.
    //private stocktrader.LoginPanel login = new stocktrader.LoginPanel(); //The current login panel.
    private CardLayout displayLayout; //User to hold all of the display panels and switch between them.
    private DisplayPanel currentPanel; //Holds the currently displayed panel.
}