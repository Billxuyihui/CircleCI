package noledge2105.getservice;

public class ServiceType {
    private String id;
    private String service;
    private double hourlyRate;

    public ServiceType(){

    }
    public ServiceType(String id, String service, double hourlyRate) {
        this.id = id;
        this.service = service;
        this.hourlyRate = hourlyRate;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getService() {
        return service;
    }
    public void setPrice(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public double getHourlyRate() {
        return hourlyRate;
    }

}
