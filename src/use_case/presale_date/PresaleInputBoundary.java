package use_case.presale_date;

import java.io.IOException;

public interface PresaleInputBoundary {
    void execute(PresaleInputData presaleInputData) throws IOException;

}
