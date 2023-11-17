package use_case.upcoming_shows;

public class UpcomingInputData {
    final private String postalCode;

    public UpcomingInputData(String postalCode){
        this.postalCode = postalCode;
    }

    String getPostalCode(){
        return postalCode;
    }

}
