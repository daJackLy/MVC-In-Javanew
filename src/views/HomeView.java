package views;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controllers.HomeController;
import controllers.EventController;
import core.Model;
import core.View;



@SuppressWarnings("serial")
public class HomeView extends JPanel implements View
{
        private javax.swing.JTextField txtDescription;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JTextField txtDate;
        private javax.swing.JRadioButton rbDaily, rbWeekly, rbMonthly;
        private javax.swing.JCheckBox chkAlarm;
        private javax.swing.JButton btnSave, btnClean;

        private javax.swing.JTable eventTable;
        private javax.swing.table.DefaultTableModel tableModel;


	private HomeController homeController;
        private EventController eventController;
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
                txtDescription = new javax.swing.JTextField(20);
                panelAdd.add(txtDescription);

                panelAdd.add(new javax.swing.JLabel("Forward E-mail:"));
                txtEmail = new javax.swing.JTextField(20);
                panelAdd.add(txtEmail);

                panelAdd.add(new javax.swing.JLabel("Date:"));
                txtDate = new javax.swing.JTextField(15);
                panelAdd.add(txtDate);
                
                panelAdd.add(new javax.swing.JLabel("Frequency:"));
                rbDaily = new javax.swing.JRadioButton("Daily");
                rbWeekly = new javax.swing.JRadioButton("Weekly");
                rbMonthly = new javax.swing.JRadioButton("Monthly");

                javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
                group.add(rbDaily);
                group.add(rbWeekly);
                group.add(rbMonthly);

                javax.swing.JPanel freqPanel = new javax.swing.JPanel();
                freqPanel.add(rbDaily);
                freqPanel.add(rbWeekly);
                freqPanel.add(rbMonthly);
                panelAdd.add(freqPanel);

                chkAlarm = new javax.swing.JCheckBox("Alarm");
                panelAdd.add(chkAlarm);

                btnSave = new javax.swing.JButton("Save");
                btnClean = new javax.swing.JButton("Clean");
                javax.swing.JPanel btnPanel = new javax.swing.JPanel();
                btnPanel.add(btnSave);
                btnPanel.add(btnClean);
                panelAdd.add(btnPanel);
                
                btnSave.addActionListener(e -> {
                String description = txtDescription.getText();
                String email = txtEmail.getText();
                String date = txtDate.getText();

                String frequency = "";
                if (rbDaily.isSelected()) frequency = "Daily";
                else if (rbWeekly.isSelected()) frequency = "Weekly";
                else if (rbMonthly.isSelected()) frequency = "Monthly";

                String alarm = chkAlarm.isSelected() ? "Yes" : "No";
                models.Event event = new models.Event(date, description, frequency, email, alarm, false);

                eventController.addEvent(event);

                updateEventList();

                txtDescription.setText("");
                txtEmail.setText("");
                txtDate.setText("");
                chkAlarm.setSelected(false);
                group.clearSelection();
                });

                tabs.addTab("Event Register", panelAdd);

                
                
                javax.swing.JPanel panelList = new javax.swing.JPanel();
                String[] columnNames = {"Description", "Date", "Frequency", "Email", "Alarm", "Selected"};
                tableModel = new javax.swing.table.DefaultTableModel(columnNames, 0); // 0 filas iniciales
                eventTable = new javax.swing.JTable(tableModel);
                panelList.setLayout(new java.awt.BorderLayout());
                panelList.add(new javax.swing.JScrollPane(eventTable), java.awt.BorderLayout.CENTER);
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

                mainFrame.setContentPane(tabs);
	}
        
        private void updateEventList() {
            tableModel.setRowCount(0);
            for (models.Event e : eventController.getEvents()) {
                Object[] row = {
                    e.getDescription(),
                    e.getDate(),
                    e.getFrequency(),
                    e.getEmail(),
                    e.getAlarm(),
                    e.getSelected()
                };
                tableModel.addRow(row);
            }
        }
        
    public HomeView(HomeController homeController, EventController eventController, JFrame mainFrame) {
        this.homeController = homeController;
        this.eventController = eventController;
        this.mainFrame = mainFrame;

        make_mainFrame();
    }
}


