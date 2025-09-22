package views;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
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
        
        private javax.swing.JButton btnSelectAll, btnRemove;

        private javax.swing.JTable eventTable;
        private javax.swing.JTable removeTable;
        private javax.swing.table.DefaultTableModel eventTableModel;
        private javax.swing.table.DefaultTableModel removeTableModel;



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
                    updateRemoveTable();

                    txtDescription.setText("");
                    txtEmail.setText("");
                    txtDate.setText("");
                    chkAlarm.setSelected(false);
                    group.clearSelection();
                });
                
                btnClean.addActionListener(e -> {
                    txtDescription.setText("");
                    txtEmail.setText("");
                    txtDate.setText("");

                    group.clearSelection();

                    chkAlarm.setSelected(false);
                });


                tabs.addTab("Event Register", panelAdd);

                
                
                javax.swing.JPanel panelList = new javax.swing.JPanel();
                String[] columnNamesEventList = {"Description", "Date", "Frequency", "Email", "Alarm"};
                eventTableModel = new javax.swing.table.DefaultTableModel(columnNamesEventList, 0);
                eventTable = new javax.swing.JTable(eventTableModel);
                panelList.setLayout(new java.awt.BorderLayout());
                panelList.add(new javax.swing.JScrollPane(eventTable), java.awt.BorderLayout.CENTER);
                tabs.addTab("Event List", panelList);
                


                javax.swing.JPanel panelRemove = new javax.swing.JPanel();
                String[] columnNamesRemoveEvent = {"Description", "Date", "Frequency", "Email", "Alarm", "Selected"};
                removeTableModel = new javax.swing.table.DefaultTableModel(columnNamesRemoveEvent, 0) {
                    @Override
                    public Class<?> getColumnClass(int column) {
                        if (column == 5) return Boolean.class;
                        return String.class;
                    }

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return column == 5;
                    }
                };

                removeTable = new JTable(removeTableModel);
                removeTable.setFillsViewportHeight(true);
                
                panelRemove.setLayout(new java.awt.BorderLayout());
                panelRemove.add(new javax.swing.JScrollPane(removeTable), java.awt.BorderLayout.CENTER);
                
                updateRemoveTable();
                
                btnSelectAll = new javax.swing.JButton("Select All");
                btnSelectAll.addActionListener(e -> {
                    for (int i = 0; i < removeTableModel.getRowCount(); i++) {
                        removeTableModel.setValueAt(true, i, 5);
                    }
                });

                btnRemove = new javax.swing.JButton("Delete Selected");
                btnRemove.addActionListener(e -> {
                    for (int i = removeTableModel.getRowCount() - 1; i >= 0; i--) {
                        Boolean selected = (Boolean) removeTableModel.getValueAt(i, 5);
                        if (selected) {
                            eventController.removeEvent(i);
                            removeTableModel.removeRow(i);
                        }
                    }
                });
                
                javax.swing.JPanel btnPanelRemove = new javax.swing.JPanel();
                btnPanelRemove.add(btnSelectAll);
                btnPanelRemove.add(btnRemove);
                panelRemove.add(btnPanelRemove, java.awt.BorderLayout.SOUTH);
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
                
                panelGuest.add(new javax.swing.JButton("Register "));
                tabs.addTab("Register", panelGuest);

                mainFrame.setContentPane(tabs);
	}
        
        private void updateEventList() {
            eventTableModel.setRowCount(0);
            for (models.Event e : eventController.getEvents()) {
                Object[] row = {
                    e.getDescription(),
                    e.getDate(),
                    e.getFrequency(),
                    e.getEmail(),
                    e.getAlarm()
                };
                eventTableModel.addRow(row);
            }
        }
        
        private void updateRemoveTable() {
            removeTableModel.setRowCount(0);
            for (models.Event e : eventController.getEvents()) {
                Object[] row = {
                    e.getDescription(),
                    e.getDate(),
                    e.getFrequency(),
                    e.getEmail(),
                    e.getAlarm(),
                    e.getSelected()
            };
            removeTableModel.addRow(row);
        }
}


        
    public HomeView(HomeController homeController, EventController eventController, JFrame mainFrame) {
        this.homeController = homeController;
        this.eventController = eventController;
        this.mainFrame = mainFrame;

        make_mainFrame();
    }
}


