

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;



package com.example.yolobob.tempapp;

/**
 * @Description : Class ServiceType
 * @author      : Group ##
 */

public class ServiceType  {
    private int serviceTypeID;
    private String serviceTypeName;


    /**
     * Default constructor
     */
    public ServiceType() {
        serviceTypeID = 0;
        serviceTypeName = "";
    }

    public ServiceType(int ID, String serviceTypeName){
        this.serviceTypeID = ID;
        this.serviceTypeName = serviceTypeName;
    }

    /**
     * ========================================== GETTERS ==========================================
     */
    public int getServiceTypeID() {
        return this.serviceTypeID;
    }
    public String getServiceTypeName() {
        return this.serviceTypeName;
    }

    /**
     * ========================================== SETTERS ==========================================
     */
    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    /**
     * =================================== Data base handler ======================================
     */

    //======================================/!\IMPORTANT /!\=======================================
    //This need to be completed by someone of the group. It is the database part of the class
    //All Query were made for SQLite, change it for FIREBASE if we decide to use FIREBASE
    //======================================/!\IMPORTANT /!\=======================================

    /**
     * All the requirement to update the service type name.
     * @return
     */
    public boolean updateServiceType(){
        //ID cant be set BY the program, it has to be pulled out from database
        //Compare the userName and the password in the database then return the ID
        //SQLite Query, need to change the name of variable for the proper field
        String updateQuery = "UPDATE ServiceType " +
                              "SET serviceName=\"" + this.serviceTypeName + "\" " +
                                "WHERE serviceTypeID=" + this.serviceTypeID;

        try {
            //Make sure there is an username and password
            //This has to be changed for database handler
            boolean result = false;

            if(result)//Make sure that it got pushed properly
            {
                //Get the ID from the database after the update was succesful

                this.serviceTypeID = 0;//Need to find a way to get the ID of the service type
                return(true);//all worked fine
            }
        }
        //If something went wrong with the database(lost connection or other thing)
        catch(Exception ex)
        {
            return(false);
        }
        return(false);//Didnt work

    }

    /**
     * Insert the serviceType in the database
     * @return
     */
    public boolean insertServiceType() {


        //ID cant be set BY the program, it has to be pull out from database
        //Compare the userName and the password in the database then return the ID
        //SQLite Query, need to change the name of variable for the proper field
        String insertUserQuery =    "INSERT INTO UserAccount (serviceName)" +
                " VALUES(\"" + this.serviceTypeName + "\")";

        //Try / catch because when connection to database there is possible problem
        try {
            //Make sure there is an username and password
            //This has to be changed for database handler
            boolean result = false;

            if(result)//Make sure that it got pushed properly
            {
                //Get the ID from the database after the push was succesful

                this.serviceTypeID = 0;//Need to find a way to get the ID of the service type
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
     * Delete the service type from database with ID
     * @return if the delete worked
     */
    public boolean deleteServiceType() {

        //The query to send to SQLite to update a password (have to change for the proper field name
        //
        String deleteUserQuery =    "DELETE FROM ServiceType WHERE id=" + this.serviceTypeID;
        boolean updated = false;

        //Try / catch because when connection to database there is possible problem
        //Can handle connection problem, etc.
        try{
            //Code to delete the user



            if(updated){//If the delete worked
                this.serviceTypeName = "";
                this.serviceTypeID = -1;

                return(true);//return true
            }
        }
        catch(Exception ex)//Error in database, lost connection or something
        {
            return(false);
        }

        return(false);
    }
}
