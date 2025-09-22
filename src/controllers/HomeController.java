package controllers;

import java.awt.Component;
import java.io.File;
import javax.swing.JMenuItem;
import core.Controller;
import views.HomeView;
import models.Event;

public class HomeController extends Controller{
    private HomeView homeView;
    private EventController eventController;
    
    @Override
    public void run() {
        eventController = new EventController();
        homeView = new HomeView(this, eventController, mainFrame);
        addView("HomeView", homeView);

    mainFrame.setVisible(true);
}

    
    

}


