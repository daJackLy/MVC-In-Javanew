package views;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controllers.HomeController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements View
{
	@SuppressWarnings("unused")
	private HomeController homeController;
	private JFrame mainFrame;
	private final static int MAIN_FRAME_WIDTH = 800;
	private final static int MAIN_FRAME_HEIGHT = 500;
	private final static int MAIN_FRAME_X = 100;
	private final static int MAIN_FRAME_Y = 100;
	
	public HomeView(HomeController homeController, JFrame mainFrame)
	{
		this.homeController = homeController;
		this.mainFrame = mainFrame;
		
		make_mainFrame();
	}
	
	@Override
	public void update(Model model, Object data) 
	{}
	
	private void make_mainFrame()
	{
		mainFrame.setOpacity(1.0f);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));
		mainFrame.setTitle("MVC-in-Java");
	}
}