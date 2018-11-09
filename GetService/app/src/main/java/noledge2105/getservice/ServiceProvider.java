package noledge2105.getservice;

/**
 * @Description : Class ServiceProvider, inheriting UserAccount
 * @author      : Group ##
 */


public class ServiceProvider extends UserAccount {

    //Service type is a string for the moment, but could be implement as class
    private String serviceType, serviceProviderID;

    private double hourlyRate;

    /**
     * Default Constructor
     */
    public ServiceProvider(){
        super();
        this.serviceType = "";
        this.serviceProviderID = "";
        this.hourlyRate = 0;
    }

    /**
     * Full constructor
     * @param userName String
     * @param userID String
     * @param password String
     * @param serviceType String/serviceType
     * @param hourlyRate HourlyRate
     */
    public ServiceProvider(String userName,
                           String userID,
                           String password,
                           String firstName,
                           String lastName,
                           String dateOfbirth,
                           String phoneNumber,
                           int permLevel,
                           String serviceProviderID,
                           String serviceType,
                           double hourlyRate){
        super(userName, userID, password, firstName, lastName, dateOfbirth, phoneNumber, permLevel);
        this.serviceType = serviceType;
        this.serviceProviderID = serviceProviderID;
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
        this.serviceProviderID =sp.getServiceProviderID();
    }


    /**
     * ========================================== GETTERS ==========================================
     */

    public String getServiceProviderID() {
        return serviceProviderID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public double getHourlyRate(){
        return(this.hourlyRate);
    }




    /**
     * ========================================== SETTERS ==========================================
     */

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    public void setServiceProviderID(String serviceProviderID) {
        this.serviceProviderID = serviceProviderID;
    }
}


