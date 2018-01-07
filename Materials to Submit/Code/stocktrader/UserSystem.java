
package stocktrader;
import java.util.HashMap;
import java.util.Map;
/**
 * The UserSystem class mainly deals with 
 * data coming in from the GUI in regards to user management.
 * It gets current users, checks administrators, handles login 
 * and logout functions, and some basic money functions like
 * give and take.
 * @author Gray-1
 */
public class UserSystem implements java.io.Serializable{


    private User currentUser = new User(); //The user that is currently logged in to the system.
    private Map <String,User> userMap = new HashMap <String,User>();// A mapping of usernames to User objects for easy storage and retrieval.
   
    /**
     *This method gets the current user, and returns said value
     * @return The current User object.
     */
    public User getCurrentUser()
    {
      
        
        return currentUser;
    }
    
    // This returns the currently logged in user

    /**
     *This method initializes the administrator when the program starts,
         * in order to ensure that there is always a user with admin permissions.
     * @param user The user to escalate.
     * @return The user that has been made an admin.
     */
    public User makeAdmin(User user)
    {
       
        //Check to see if it is a valid user by searching for the User associated
        //with that username, then comparing that with what was passed in.
        if ((this.findUser(user.getUserName()) == user))
        {
            //create a temporary user to make the changes
            User tempUser = user;
            
            //Make that user an admin
            tempUser.makeAdmin();
            
            //update that user in the user map
            userMap.put(tempUser.getUserName(), tempUser);
            
            //return the updated user
            return userMap.get(tempUser.getUserName());
        }
        else
        {
            //the user that was passed in was not valid, so return null
            return null;
        }
    }
    // this sets the system to the administrator

    /**
     *This method determines if the current user is an administrator
         * by calling checkAdmin from the User class.
     * @return Returns true if the current user is an admin.
     */
    public boolean isAdmin()
    {
        
        return currentUser.checkAdmin();
    }
    // this calls checkAdmin to determine if the system is running as admin

    /**
     *This method searches the userMap for a given username.
     * @param username Search for a User object by username.
     * @return The User found.
     */
    public User findUser(String username)
    {
        
        return userMap.get(username);
    }
    // this searches the hashmap for the given username

    /**
     *This method adds a user to the hashmap, used in creating a new user.
     * @param user Add the passed-in user to the system.
     */
    public void createNewUser(User user)
    {
        
        userMap.put(user.getUserName(), user);
    }
    // this adds the new user to the hashmap

    /**
     *This method searches the userMap for the entered username, then logs
         * in if that user is found.
     * @param user The User to login to the system.
     * @return The User that was logged in; the new current user.
     */
    public User UserLogin(User user)
    {
        
        //Check to see if it is a valid user by searching for the User associated
        //with that username, then comparing that with what was passed in.
        if (this.findUser(user.getUserName()) == user)
        {
            //If it is valid, then set the new user as the currently logged-in user
            currentUser = user;
            
            //Return that User for ease of use
            return currentUser;
        }
        else
        {
            return null;
        }
        
    }
    // this logs in the user and sets the system to normal user

    /**
     *This method logs out the user by resetting the current user back 
         * to empty.
     */
    public void UserLogout()
    {
        
        //Resets it to the default value of an empty user
        currentUser = new User();
    }
    // this logs out the user, returning the system to defaut

    /**
     *This method deletes the given username from the userMap, then 
         * runs the logout method, setting the current user to empty.
     * @param username Delete a user with this username.
     */
    public void deleteUser(String username)
    {  
        
        userMap.remove(username);
        UserLogout();
    }
    // this deletes the given user from the hashmap

    /**
     *This method is used by the administrator to remove money from
         * a user's total when buying a stock.
     * @param money Total
     * @param newMoney amount to subtract from the total.
     */
    public void takeMoney(float money, float newMoney)
    {
        
        if(currentUser.checkAdmin() == true)
        {
          
            float currentmoney = money - newMoney;
        }
        
    }
    // this removes the specifiec amount of money from a user

    /**
     *This method does the same as takeMoney, but subtracts instead of adds.
     * @param money Total
     * @param newMoney amount to subtract from the total.
     */
    public void giveMoney(float money, float newMoney)
    {
        
        if(currentUser.checkAdmin() == true)
        {
            float currentmoney = money + newMoney;
        }
            
    }
    // this adds a specifiec amount of money to a user
    
}