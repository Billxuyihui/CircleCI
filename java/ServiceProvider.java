package com.example.yolobob.tempapp;

/**
 * @Description : Class ServiceProvider, inheriting UserAccount
 * @author      : Group ##
 */


public class ServiceProvider extends UserAccount {

    //Service type is a string for the moment, but could be implement as class
    private ServiceType serviceType;

    private double hourlyRate;

    /**
     * Default Constructor
     */
    public ServiceProvider(){
        super();
        this.serviceType = new ServiceType();
        this.hourlyRate = 0;
    }

    /**
     * Full constructor
     * @param userName String
     * @param ID INT
     * @param password String
     * @param serviceType String/serviceType
     * @param hourlyRate HourlyRate
     */
    public ServiceProvider(String userName,
                           int ID,
                           String password,
                           String firstName,
                           String lastName,
                           String dateOfbirth,
                           ServiceType serviceType,
                           double hourlyRate){
        super(userName, ID, password, firstName, lastName, dateOfbirth);
        this.serviceType = serviceType;
        this.hourlyRate = hourlyRate;

    }

    /**
     * Constructor that take himself (serviceProvider) as param
     * @param sp ServiceProvider
     */
    public ServiceProvider(ServiceProvider sp){
        super(sp);
        this.serviceType = sp.getServiceType();
        this.hourlyRate = sp.getHourlyRate();
    }


    /**
     * ========================================== GETTERS ==========================================
     */

    /**
     * Return the service name
     * @return String
     */


    public ServiceType getServiceType(){
        return(this.serviceType);
    }

    /**
     * Return the hourly rate
     * @return double
     */
    public double getHourlyRate(){
        return(this.hourlyRate);
    }


    /**
     * ========================================== SETTERS ==========================================
     */


    public void setServiceType(ServiceType serviceType){
        this.serviceType = serviceType;
    }

    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    /**
     * =================================== Data base handler ======================================
     */

    //======================================/!\IMPORTANT /!\=======================================
    //This need to be completed by someone of the group. It is the database part of the class
    //All Query were made for SQLite, change it for FIREBASE if we decide to use FIREBASE
    //======================================/!\IMPORTANT /!\=======================================

    public boolean updateProvider(ServiceProvider serviceProvider){
        this.updateAccount(serviceProvider);
        String updateQuery =    "UPDATE ServiceProvider SET ";
        boolean updated = false;
        int counter = 0;//counter to see if we add a ,

        //Try / catch because when connection to database there is possible problem
        //Can handle connection problem, etc.
        try{
            //Code to udpate
            if(serviceProvider.getHourlyRate() != this.hourlyRate)
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " hourlyRate=\"" + serviceProvider.getHourlyRate() + "\"";
            //If counter is 0 then put empty string because it is the first
            //Else put "," to seperate
            if(serviceProvider.getServiceType().getServiceTypeID() != this.serviceType.getServiceTypeID())
                updateQuery = updateQuery + ((counter++<1)?"":",")  + " serviceProviderID=\"" + serviceProvider.getServiceType().getServiceTypeID() + "\"";

            updateQuery = updateQuery + " WHERE id=" + this.getID();
            //Do the query
        }
        catch(Exception ex)//Error in database, lost connection or something
        {
            return(false);
        }

        return(false);
    }

}


