package use_case.upcoming_shows;

import entity.UserFactory;

public class UpcomingInteractor implements UpcomingInputBoundary{
    final UpcomingDataAccess userDataAccessObject;
    final UpcomingOutputBoundary upcomingPresenter;
    final UserFactory userFactory;

    public UpcomingInteractor(UpcomingDataAccess userDataAccessInterface,
                              UpcomingOutputBoundary upcomingOutputBoundary,
                              UserFactory userFactory){
        this.userDataAccessObject = userDataAccessInterface;
        this.upcomingPresenter = upcomingOutputBoundary;
        this.userFactory = userFactory;
    }

    public void execute(UpcomingInputData upcomingInputData){

    }
}
