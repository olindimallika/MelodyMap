package use_case.presale_date;

import java.io.IOException;

/**
 * Represents the input boundary for the Presale use case.
 * This interface defines the contract for executing the Presale use case.
 */
public interface PresaleInputBoundary {

    /**
     * Executes the Presale use case with the provided input data.
     *
     * @param presaleInputData The input data for the Presale use case.
     * @throws IOException If an I/O error occurs during the execution.
     */
    void execute(PresaleInputData presaleInputData) throws IOException;

}
