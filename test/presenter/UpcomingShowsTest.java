package presenter;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_concerts.ShowConcertsViewModel;
import interface_adapter.upcoming_shows.UpcomingPresenter;
import interface_adapter.upcoming_shows.UpcomingViewModel;

import org.junit.jupiter.api.Test;

import use_case.upcoming_shows.UpcomingOutputData;

import java.util.LinkedHashMap;

public class UpcomingShowsTest {
    @Test
    void successTest() {

        LinkedHashMap<String, String> outputConcerts = new LinkedHashMap<>();
        outputConcerts.put("KESmas - Kes The Band Live in Concert", "https://www.ticketmaster.ca/kesmas-kes-the-band-live-in-toronto-ontario-12-21-2023/event/10005F6AD6584CEE");
        outputConcerts.put("Big Wreck", "https://www.ticketmaster.ca/big-wreck-toronto-ontario-12-22-2023/event/10005F09E92D43C5");
        outputConcerts.put("Sam Roberts Band - The Adventures Of Ben Blank Tour", "https://www.ticketmaster.ca/sam-roberts-band-the-adventures-of-toronto-ontario-02-09-2024/event/10005F27983B20F5");
        outputConcerts.put("+++ Crosses - Familiar World Tour", "https://www.ticketmaster.ca/crosses-familiar-world-tour-toronto-ontario-02-16-2024/event/10005F4BB0A81953");
        outputConcerts.put("The Strumbellas", "https://www.ticketmaster.ca/the-strumbellas-toronto-ontario-02-24-2024/event/10005F53C4B31DC1");
        outputConcerts.put("Chelsea Cutler - The Beauty Is Everywhere Tour", "https://www.ticketmaster.ca/chelsea-cutler-the-beauty-is-everywhere-toronto-ontario-02-27-2024/event/10005F6DD7711EC8");
        outputConcerts.put("Two Door Cinema Club", "https://www.ticketmaster.ca/two-door-cinema-club-toronto-ontario-03-03-2024/event/10005F32E0933DBF");
        outputConcerts.put("The Kooks", "https://www.ticketmaster.ca/the-kooks-toronto-ontario-03-04-2024/event/10005F4586C21145");
        outputConcerts.put("Silversun Pickups", "https://www.ticketmaster.ca/silversun-pickups-toronto-ontario-03-05-2024/event/10005F539601125B");
        outputConcerts.put("JP Saxe - A Grey Area Tour", "https://www.ticketmaster.ca/jp-saxe-a-grey-area-tour-toronto-ontario-03-09-2024/event/10005F0FB1293C1D");
        outputConcerts.put("Waterparks", "https://www.ticketmaster.ca/waterparks-toronto-ontario-03-11-2024/event/10005F4A35B554D9");
        outputConcerts.put("Alkaline Trio - Blood, Hair, And Eyeballs The Tour", "https://www.ticketmaster.ca/alkaline-trio-blood-hair-and-eyeballs-toronto-ontario-03-12-2024/event/10005F4B4A994791");
        outputConcerts.put("The Rural Alberta Advantage", "https://www.ticketmaster.ca/the-rural-alberta-advantage-toronto-ontario-03-15-2024/event/10005F3D90E91D3B");
        outputConcerts.put("Jeff Rosenstock", "https://www.ticketmaster.ca/jeff-rosenstock-toronto-ontario-03-20-2024/event/10005F63C4A9325F");
        outputConcerts.put("Jeff Rosenstock - MOVED TO HISTORY", "https://www.ticketmaster.ca/jeff-rosenstock-moved-to-history-toronto-ontario-03-20-2024/event/10005EB3B4122EEE");
        outputConcerts.put("Stardew Valley: Festival Of Seasons", "https://www.ticketmaster.ca/stardew-valley-festival-of-seasons-toronto-ontario-03-15-2024/event/10005F46B39622AA");
        outputConcerts.put("Disney in Concert: Pirates of the Caribbean - Curse of the Black Pearl", "https://www.ticketmaster.ca/disney-in-concert-pirates-of-the-toronto-ontario-05-18-2024/event/10005F76BDB01927");
        outputConcerts.put( "Eagles - The Long Goodbye", "https://www.ticketmaster.ca/eagles-the-long-goodbye-toronto-ontario-03-13-2024/event/10005F5732708A10");


        UpcomingOutputData upcomingOutputData = new UpcomingOutputData(outputConcerts);

        UpcomingViewModel upcomingViewModel = new UpcomingViewModel();

        ShowConcertsViewModel showConcertsViewModel = new ShowConcertsViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // creating the presenter
        UpcomingPresenter presenter = new UpcomingPresenter(viewManagerModel, showConcertsViewModel, upcomingViewModel);

        // invoking the presenter by passing in the output data
        presenter.prepareSuccessView(upcomingOutputData);
    }
}
