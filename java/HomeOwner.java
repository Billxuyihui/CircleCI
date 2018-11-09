package com.example.yolobob.tempapp;

/**
 * @Description : Class HomeOwner, inheriting UserAccount
 * @author      : Group ##
 */
public class HomeOwner extends UserAccount {

    /**
     * Default constructor
     */
    public HomeOwner(){
        super();
    }

    /**
     * Constructor with all the argument for homeowner
     * @param userName
     * @param ID
     * @param password
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     */
    public HomeOwner(String userName,
                     int ID,
                     String password,
                     String firstName,
                     String lastName,
                     String dateOfBirth)
    {
        super(userName, ID, password, firstName, lastName, dateOfBirth);

    }

    /**
     * Constructor taking himself as parameter
     * @param ho
     */
    public HomeOwner(HomeOwner ho){
        super(ho);
    }

    /**
     * ========================================== GETTERS ==========================================
     */

    /**
     * return the level of the account
     * @return int, 2 = Homeowner, 1 = ServiceProvider, 0 = admin
     */
    public int getAccountPermissionLevel(){
        return(1);
    }

    /**
     * Return the first name of the user
     * @return string
     */

    /**
     * ========================================== SETTERS ==========================================
     */

    /**
     * =================================== Data base handler ======================================
     */

    //======================================/!\IMPORTANT /!\=======================================
    //This need to be completed by someone of the group. It is the database part of the class
    //All Query were made for SQLite, change it for FIREBASE if we decide to use FIREBASE
    //======================================/!\IMPORTANT /!\=======================================



}
