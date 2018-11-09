





package com.example.yolobob.tempapp;

/**
 * @Description : Class User
 *              : Also every subclass contain getAccountPermissionLevel, 2 = HomeOwner, 1 = ServiceProvider, 0 = Admin
 * @author      : Group ##
 */

public class UserAccount  {


    private String userName, password, firstName, lastName, dateOfBirth;
    private int ID,permLevel ;



    /**
     * Default constructor
     */
    public UserAccount(){
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = "";
		this.permLevel = -1;
		this.ID = -1;
    }

    /**
     * Constructor taking Username, ID, userLevel
     * @param userName String
     * @param ID int
     * @param password string
     */
    public UserAccount(String userName,
                       int ID,
                       String password,
                       String firstName,
                       String lastName,
                       String dateOfBirth,
					   int permLevel){
        this.userName = userName;
        this.ID = ID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
		this.permLevel = permLevel;
    }

    /**
     * Constructor taking himself as param.
     * @param ua
     */
    public UserAccount(UserAccount ua){
        this.userName = ua.getUserName();
        this.password = ua.getPassword();
        this.ID = ua.getID();
        this.firstName = ua.getFirstName();
        this.lastName = ua.getLastName();
        this.dateOfBirth = ua.getDateOfBirth();
		this.permLevel = ua.getPermLevel();
    }
    /**
     * ========================================== GETTERS ==========================================
     */

    /**
     * Return the user password
     * @return String
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * return the password, might remove it because it is very sensitive data
     * @return String
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * return the ID
     * @return Int
     */
    public int getID(){
        return this.ID;
    }

    /**
     * Return the first name of the user
     * @return string
     */
    public String getFirstName(){
        return(this.firstName);
    }

    /**
     * Return the last name of the user
     * @return string
     */
    public String getLastName(){
        return(this.lastName);
    }

    /**
     * Return the name of the user
     * @return string
     */
    public String getDateOfBirth(){
        return(this.dateOfBirth);
    }

	public int getPermLevel(){
		return(this.permLevel);
	}

    /**
     * ========================================== SETTERS ==========================================
     * This class is about setter, but theses setter are made from the database
     */
    /**
     * Set username (string)
     * @param userName String
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * Set password (string)
     * @param password String
     */

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //Added a setter for ID by Anbo 
    public void setID(int id){
        this.ID = id;
    }

	public void setPermLevel(int permLevel){
		this.permLevel = permLevel;
	}
    /**
     * =================================== Data base handler ======================================
     */

    //======================================/!\IMPORTANT /!\=======================================
    //This need to be completed by someone of the group. It is the database part of the class
    //All Query were made for SQLite, change it for FIREBASE if we decide to use FIREBASE
    //======================================/!\IMPORTANT /!\=======================================

    /**
     * Insert the user in the database
     * @return
     */
    public boolean insertUser(){

        //Look if the user exist already.
        boolean existAlready = false;
        if(!this.userName.equals("") && !this.password.equals(""))
            existAlready = tryConnection(this.userName,this.password);
        if(existAlready == true)
            return (false);

        //ID cant be set BY the program, it has to be pull out from database
        //Compare the userName and the password in the database then return the ID
        //SQLite Query, need to change the name of variable for the proper field
        String insertUserQuery =    "INSERT INTO UserAccount (userName, password, firstName, lastName, dateOfBirth)" +
                " VALUES(\"" +
                this.userName + "\",\"" +
                this.password  + "\",\"" +
                this.firstName  + "\",\"" +
                this.lastName  + "\",\"" +
                this.dateOfBirth  + "\")";

        //Try / catch because when connection to database there is possible problem
        try {
            //Make sure there is an username and password
            if(!this.userName.equals("") && !this.password.euqals(""){
                //This has to be changed for database handler
                boolean result = false;
                
                if(result)//Make sure that it got pushed properly
                {
                    //Get the ID from the database after the push was succesful
                    if(tryConnection(this.userName,this.password))
                        return(true);//Connection successful
                }
            }
        }
        //If something went wrong with the database(lost connection or other thing)
        catch(Exception ex)
        {
            return(false);
        }
        return(false);//Connection unsuccessful
    }

    /**
     *  Will try to connect with the username and password pass as param
     * Then it will return The ID
     * @param userName
     * @param password
     * @return true if the connection was successful
     */
    public boolean tryConnection(String userName, String password){
        //ID cant be set BY the program, it has to be pull out from database
        //Compare the userName and the password in the database then return the ID
        //SQLite Query, need to change the name of variable for the proper field
        String getUserQuery =    "SELECT userID FROM UserAccount WHERE userName=\"" + userName +
                "\" and userPassword=\"" + password + "\"";

        //Try / catch because when connection to database there is possible problem
        try {
            //This has to be changed for database handler
            boolean resultExist = true;//Get the result of database
            int result = 0;

            if(resultExist)//Look if there is 1 result, if so put the ID of the user in ID
            {
                this.ID = result;
                this.userName = userName;
                this.password = password;
                return(true);//Connection successful
            }
        }
        //If something went wrong with the database(lost connection or other thing)
        catch(Exception ex)
        {
            return(false);
        }
        return(false);//Connection unsuccessful
    }


    /**
     * Delete user from database with ID
     * @return if the delete worked
     */
    public boolean deleteUser() {

        //The query to send to SQLite to update a password (have to change for the proper field name
        //
        String deleteUserQuery =    "DELETE FROM UserAccount WHERE id=" + this.ID;
        boolean updated = false;

        //Try / catch because when connection to database there is possible problem
        //Can handle connection problem, etc.
        try{
            //Code to delete the user



            if(updated){//If the updated worked
                this.userName = "";
                this.password = "";

                return(true);//return true
            }
        }
        catch(Exception ex)//Error in database, lost connection or something
        {
            return(false);
        }

        return(false);
    }

    /**
     * Update the account with another User account, have to create userAccount from textfield
     * and send it as param, it will compare and update the userAccount
     * @param userAccount
     * @return
     */
    public boolean updateAccount(UserAccount userAccount){

        String updateQuery =    "UPDATE UserAccount SET ";
        boolean updated = false;
        int counter = 0;//counter to see if we add a ,

        //Try / catch because when connection to database there is possible problem
        //Can handle connection problem, etc.
        try{
            //Code to udpate
            if(!userAccount.getFirstName().equals(this.firstName))
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " firstName=\"" + userAccount.getFirstName() + "\"";
            //If counter is 0 then put empty string because it is the first
            //Else put "," to seperate
            if(!userAccount.getLastName().euqals(this.lastName))
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " lastName=\"" + userAccount.getLastName() + "\"";

            if(!userAccount.getDateOfBirth().euqals(this.dateOfBirth))
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " dateOfBirth=\"" + userAccount.getDateOfBirth() + "\"";

            if(!userAccount.getPassword().euqals(this.password))
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " password=\"" + userAccount.getPassword() + "\"";

            if(!userAccount.getUserName().euqals(this.userName))
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " userName=\"" + userAccount.getUserName() + "\"";


            updateQuery = updateQuery + " WHERE id=" + this.ID;
            //Do the query
        }
        catch(Exception ex)//Error in database, lost connection or something
        {
            return(false);
        }

        return(false);
    }

}
