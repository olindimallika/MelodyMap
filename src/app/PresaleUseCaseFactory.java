package app;

import entity.UserFactory;
import entity.UserModelFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.presale.PresaleController;
import interface_adapter.presale.PresalePresenter;
import interface_adapter.presale.PresaleViewModel;
import use_case.presale_date.*;
import view.PresaleView;

import javax.swing.*;
import java.io.IOException;

public class PresaleUseCaseFactory {

    private PresaleUseCaseFactory() {}

    public static PresaleView create(
            ViewManagerModel viewManagerModel, PresaleViewModel presaleViewModel, PresaleDataAccess userDataAccessObject) {

        try {
            PresaleController presaleController = createUserPresaleUseCase(viewManagerModel, presaleViewModel, userDataAccessObject);
            return new PresaleView(presaleController, presaleViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static PresaleController createUserPresaleUseCase(ViewManagerModel viewManagerModel,
                                                              PresaleViewModel presaleViewModel,
                                                              PresaleDataAccess userDataAccessObject) throws IOException {
        PresaleOutputBoundary presaleOutputBoundary = new PresalePresenter(viewManagerModel, presaleViewModel);

        UserFactory userFactory = new UserModelFactory();

        PresaleInputBoundary userPresaleInteractor = new PresaleInteractor(
                userDataAccessObject, presaleOutputBoundary, userFactory);

        return new PresaleController(userPresaleInteractor);

    }

}
