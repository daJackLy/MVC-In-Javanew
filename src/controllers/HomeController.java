package controllers;

import java.awt.Component;
import java.io.File;
import javax.swing.JMenuItem;
import core.Controller;
import views.HomeView;

public class HomeController extends Controller{
    private HomeView homeView;
    
    @Override
    public void run()
    {
            // Initializes HomeView
            homeView = new HomeView(this, mainFrame);
            addView("HomeView", homeView);

            // Displays the program window
            mainFrame.setVisible(true);
    }
}
