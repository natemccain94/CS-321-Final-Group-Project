
package stocktrader;

import java.util.Random;
import stocktrader.Portfolio;

/**
 *The User class' main purpose is to stores data the can be accessed through
 * the UserSystem class or the Portfolio class.
 * @author Gray-1
 */
public class User implements java.io.Serializable{
   
   private boolean isAdmin;
   private String userName;
   private String password;
   private Portfolio portfolio;
   private float money;
   
   /**
    * Non-parameterized constructor of User.
    * Uses random values for username and password.
    */
   public User()
   {
       
        isAdmin = false;
       
        Random rand = new Random();
        Integer  n = rand.nextInt(20000) + 1;
        userName = "user" + n.toString();
        
        n = rand.nextInt(999999999) + 1;
        password = n.toString();
        
        portfolio = new Portfolio();
        
        money = 1000;
   }
   
    /**
     *This method sets the userName variable to the entered username.
     * @param username New username.
     */
    public void setUserName(String username)
   {
      
       userName = username;
   }

    /**
     *This method returns the value of the userName variable.
     * @return Current uername.
     */
    public String getUserName()
   {
      
       return userName;
   }

    /**
     *The purpose of this method is to check passwords, used in login.
     * @param passwordToCheck Check if this password is valid.
     * @return Return true if valid.
     */
    public boolean checkPassword(String passwordToCheck)
   {
       
       boolean pass;
       if(passwordToCheck.equals(password))
       {
           pass  = true;
       }
        else
           {
                pass  = false;
           }
       return pass;
   }
   // this checks the given password against the stored password

    /**
     *This method is used to add an order by using the portfolio method addOrder.
     * @param order Add an order to the User's portfolio.
     */
   public void makeOrder(Order order)
   {
       
       portfolio.addOrder(order);
   }
   // this calls the addOrder method from the portfolio class to add a new order

    /**
     *This method is similar to makeOrder, but it calls remove Order instead.
     * @param order Cancel an order in the user's portfolio.
     */
   public void cancelOrder(Order order)
   {
       
       portfolio.removeOrder(order);
   }
   // this calls the removeOrder method from the portfolio class to remove an order

    /**
     *This method is used to change a user's password.
     * @param newpassword Set the user's password to this new value.
     */
   public void changePassword(String newpassword)
   {
      
       password = newpassword;
       
   }
   // this assigns a new password over the user's old password

    /**
     *This method acquires the user's portfolio.
     * @return Get the current user's portfolio.
     */
   public Portfolio getPortfolio()
   {
       return portfolio;
   }
   // this aquires the portfolio of a user

    /**
     *This method sets a user's portfolio to be accessed later.
     * @param newportfolio The updated portfolio.
     */
   public void setPortfolio(Portfolio newportfolio)
   {
      
       portfolio = newportfolio; 
       
   }
   // this sets the portfolio to be returned with the method getPortfolio

    /**
     *This method's purpose is to check if the user is an admin, it is used
        * to determine if a user can access certain functions of the program.
     * @return True if the user is an admin.
     */
   public boolean checkAdmin()
   {
       
       if(isAdmin = true)
       {
           return isAdmin;
       }
       else 
       {
           return isAdmin;
       }
   }
   
    /**
     *This method sets the current user to have administrator permissions.
     */
    public void makeAdmin()
   {
       
       isAdmin = true;
   }
   // ths checks the state of isAdmin and determines if the system is user or admin

    /**
     *This method gets the money available to the current user.
     * @return The user's current amount of money.
     */
   public float getMoney()
   {
      
       return money;
       
   }
   // this returns the user's money

    /**
     *This method is used to give a user a certain amount of money.
     * @param getmoney New amount of money.
     */
   public void setMoney(float getmoney)
   {
       
       money = getmoney;
   }
   // this sets the amount of money available to a user
}


