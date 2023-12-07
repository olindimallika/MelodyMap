package interface_adapter.presale;

import use_case.presale_date.PresaleInputBoundary;
import use_case.presale_date.PresaleInputData;

import java.io.IOException;

public class PresaleController {
    final PresaleInputBoundary userPresaleUseCaseInteractor;
    public PresaleController(PresaleInputBoundary userPresaleUseCaseInteractor){
        this.userPresaleUseCaseInteractor = userPresaleUseCaseInteractor;
    }

    public void execute(String postalCode, String favouriteArtist) throws IOException {
        PresaleInputData presaleInputData = new PresaleInputData(postalCode, favouriteArtist);

        userPresaleUseCaseInteractor.execute(presaleInputData);
    }

}



// Below is original with string fav artists.
//public class PresaleController {
//    final PresaleInputBoundary userPresaleUseCaseInteractor;
//
//    public PresaleController(PresaleInputBoundary userPresaleUseCaseInteractor){
//        this.userPresaleUseCaseInteractor = userPresaleUseCaseInteractor;
//    }
//
//
//    public void execute(String postalCode, String favouriteArtist) throws IOException {
//        PresaleInputData presaleInputData = new PresaleInputData(postalCode, favouriteArtist);
//
//        userPresaleUseCaseInteractor.execute(presaleInputData);
//    }
//
//}
