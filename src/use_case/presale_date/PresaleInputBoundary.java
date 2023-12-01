package use_case.presale_date;

import java.io.IOException;

import use_case.presale_date.PresaleInputData;


public interface PresaleInputBoundary {
    void execute(PresaleInputData presaleInputData) throws IOException;

}
