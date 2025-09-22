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
                
                javax.swing.JTabbedPane tabs = new javax.swing.JTabbedPane();
                
                javax.swing.JPanel panelAdd = new javax.swing.JPanel();
                panelAdd.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

                panelAdd.add(new javax.swing.JLabel("Event Description:"));
                panelAdd.add(new javax.swing.JTextField(20));

                panelAdd.add(new javax.swing.JLabel("Forward E-mail:"));
                panelAdd.add(new javax.swing.JTextField(20));

                panelAdd.add(new javax.swing.JLabel("Date:"));
                panelAdd.add(new javax.swing.JTextField(15));

                panelAdd.add(new javax.swing.JLabel("Frequency:"));
                javax.swing.JRadioButton daily = new javax.swing.JRadioButton("Daily");
                javax.swing.JRadioButton weekly = new javax.swing.JRadioButton("Weekly");
                javax.swing.JRadioButton monthly = new javax.swing.JRadioButton("Monthly");

                javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
                group.add(daily);
                group.add(weekly);
                group.add(monthly);

                javax.swing.JPanel freqPanel = new javax.swing.JPanel();
                freqPanel.add(daily);
                freqPanel.add(weekly);
                freqPanel.add(monthly);
                panelAdd.add(freqPanel);

                panelAdd.add(new javax.swing.JCheckBox("Alarm"));

                javax.swing.JPanel btnPanel = new javax.swing.JPanel();
                btnPanel.add(new javax.swing.JButton("Save"));
                btnPanel.add(new javax.swing.JButton("Clean"));
                panelAdd.add(btnPanel);

                tabs.addTab("Event Register", panelAdd);


                
                
                
                javax.swing.JPanel panelList = new javax.swing.JPanel();
                panelList.add(new javax.swing.JScrollPane(new javax.swing.JList<String>()));
                tabs.addTab("Event List", panelList);

                javax.swing.JPanel panelRemove = new javax.swing.JPanel();
                panelRemove.add(new javax.swing.JScrollPane(new javax.swing.JList<String>()));
                panelRemove.add(new javax.swing.JButton("Eliminar"));
                tabs.addTab("Remove Event", panelRemove);

  
                javax.swing.JPanel panelGuest = new javax.swing.JPanel();
                panelGuest.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
                
                panelGuest.add(new javax.swing.JLabel("Enter Name:"));
                panelGuest.add(new javax.swing.JTextField(20));
                
                panelGuest.add(new javax.swing.JLabel("Enter Cell Phone Number:"));
                panelGuest.add(new javax.swing.JTextField(15));
                
                panelGuest.add(new javax.swing.JLabel("Gender:"));
                javax.swing.JRadioButton Male = new javax.swing.JRadioButton("Male");
                javax.swing.JRadioButton Female = new javax.swing.JRadioButton("Female");
                
                javax.swing.ButtonGroup groupgender = new javax.swing.ButtonGroup();
                groupgender.add(Male);
                groupgender.add(Female);
                
                javax.swing.JPanel GenderPanel = new javax.swing.JPanel();
                GenderPanel.add(Male);
                GenderPanel.add(Female);
                panelGuest.add(GenderPanel);
                
                panelGuest.add(new javax.swing.JLabel("Enter Birthdate:"));
                panelGuest.add(new javax.swing.JTextField(15));
                
                panelGuest.add(new javax.swing.JLabel("Address:"));
                panelGuest.add(new javax.swing.JTextField(15));
                
                panelGuest.add(new javax.swing.JCheckBox("Accepts T&C"));
                
                panelGuest.add(new javax.swing.JButton("Registrar invitado"));
                tabs.addTab("Register", panelGuest);

                // Agregar pestañas al frame
                mainFrame.setContentPane(tabs);
	}
}