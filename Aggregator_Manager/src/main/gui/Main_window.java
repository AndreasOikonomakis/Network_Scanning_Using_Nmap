package main.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main_window extends JFrame {
	private JTable table_clients;

	/**
	 * Launch the application.
	 */
	public static void Create_Main_Window() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_window frame = new Main_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_window() {
		setTitle("Aggregator Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 422);

//		The default resource directory for all Maven projects is src/main/resources which 
//		will end up in target/classes and in WEB-INF/classes in the WAR. 
//		The directory structure will be preserved in the process.
		ImageIcon online = getImage("/icons/online.png");
		ImageIcon offline = getImage("/icons/offline.png");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JPanel panel_clients = new JPanel();
		tabbedPane.addTab("Clients", null, panel_clients, null);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_clients = new GroupLayout(panel_clients);
		gl_panel_clients.setHorizontalGroup(gl_panel_clients.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_clients.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(16, Short.MAX_VALUE)));
		gl_panel_clients
				.setVerticalGroup(gl_panel_clients.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panel_clients.createSequentialGroup().addContainerGap(35, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		Object[][] model = { { null, online, "accept", "delete" }, { null, offline, "accept", "delete" } };
		String[] names = { "client", "status", "Accept", "Delete" };
		DefaultTableModel modelo = new DefaultTableModel(model, names) {
			@Override
			public boolean isCellEditable(int row, int column)
		    {
				return column == 1 ? false : true;
		    }
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Object.class;
				case 1:
					return ImageIcon.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				default:
					return Object.class;
				}
			}
		};
		table_clients = new JTable(modelo);
		
		/*
		 * Button row delete
		 */

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				table_clients = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				((DefaultTableModel) table_clients.getModel()).removeRow(modelRow);
			}
		};
		ButtonColumn buttonDelete = new ButtonColumn(table_clients, delete, 3);
		buttonDelete.setMnemonic(KeyEvent.VK_ENTER);
		/*
		 * Button row accept
		 */
		Action accept = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				table_clients = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				JOptionPane.showMessageDialog(null, "ok thnx");
				// ((DefaultTableModel)table_clients.getModel()).removeRow(modelRow);
			}
		};
		ButtonColumn buttonAccept = new ButtonColumn(table_clients, accept, 2);
		buttonAccept.setMnemonic(KeyEvent.VK_ENTER);
		
		scrollPane.setViewportView(table_clients);
		panel_clients.setLayout(gl_panel_clients);

		JPanel panel_client_management = new JPanel();
		tabbedPane.addTab("Management", null, panel_client_management, null);
		GroupLayout gl_panel_client_management = new GroupLayout(panel_client_management);
		gl_panel_client_management.setHorizontalGroup(
				gl_panel_client_management.createParallelGroup(Alignment.LEADING).addGap(0, 622, Short.MAX_VALUE));
		gl_panel_client_management.setVerticalGroup(
				gl_panel_client_management.createParallelGroup(Alignment.LEADING).addGap(0, 366, Short.MAX_VALUE));
		panel_client_management.setLayout(gl_panel_client_management);

		JPanel panel_results = new JPanel();
		tabbedPane.addTab("Results", null, panel_results, null);
		GroupLayout gl_panel_results = new GroupLayout(panel_results);
		gl_panel_results.setHorizontalGroup(
				gl_panel_results.createParallelGroup(Alignment.LEADING).addGap(0, 622, Short.MAX_VALUE));
		gl_panel_results.setVerticalGroup(
				gl_panel_results.createParallelGroup(Alignment.LEADING).addGap(0, 366, Short.MAX_VALUE));
		panel_results.setLayout(gl_panel_results);
		getContentPane().setLayout(groupLayout);
	}

	private ImageIcon getImage(String path) {
		java.net.URL url = Main_window.class.getResource(path);
		if (url != null)
			return (new ImageIcon(url));
		else {
			System.out.println(path);
			return null;
		}
	}

}
