package main.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import main.database.*;

public class am_gui {

	private JFrame frmAggregatorManagerLogin;
	private JPasswordField pwdPassword;
	private JTextField txtusername;
	private JButton btnLogin;
	private static am_gui window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new am_gui();
					window.frmAggregatorManagerLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public am_gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAggregatorManagerLogin = new JFrame();
		frmAggregatorManagerLogin.setTitle("Aggregator Manager Login");
		frmAggregatorManagerLogin.setBounds(100, 100, 401, 189);
		frmAggregatorManagerLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblUsername = new JLabel("Username");

		JLabel lblPassword = new JLabel("Password");

		pwdPassword = new JPasswordField();

		txtusername = new JTextField();
		txtusername.setColumns(10);

		btnLogin = new JButton("Login");
		CreateEvents();
		frmAggregatorManagerLogin.getRootPane().setDefaultButton(btnLogin);
		GroupLayout groupLayout = new GroupLayout(frmAggregatorManagerLogin.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(88)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPassword)
								.addComponent(lblUsername))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogin).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pwdPassword).addComponent(txtusername)))
				.addContainerGap(104, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(34)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblUsername).addComponent(
						txtusername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnLogin)
				.addContainerGap(44, Short.MAX_VALUE)));
		frmAggregatorManagerLogin.getContentPane().setLayout(groupLayout);
	}

	/*
	 * This method contains all of the code for creating events
	 */
	private void CreateEvents() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.print(txtusername.getText()+ " ");
				// System.out.println(pwdPassword.getPassword());
				/* convert password to sha256 */
				MessageDigest md = null;
				try {
					md = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * get bytes from Jpassword field for sha256 update function
				 */
				char[] in = pwdPassword.getPassword();
				ArrayList<Byte> list = new ArrayList<Byte>();
				for (int i = 0; i < in.length; i++) {
					byte b = (byte) in[i];
					list.add(b);
				}
				byte[] inputInByte = new byte[list.size()];
				for (int i = 0; i < list.size(); i++) {
					inputInByte[i] = list.get(i);
				}
				md.update(inputInByte);

				byte byteData[] = md.digest();

				// convert the byte to hex format
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}

				// System.out.println("Hex format : " + sb.toString());

				/*
				 * validate username and password
				 */
				if (DbConnector.Login_to_Server(txtusername.getText(), sb.toString()) == 1) {
					System.out.println("HELLO MASTER");
					window.frmAggregatorManagerLogin.setVisible(false);
					window.frmAggregatorManagerLogin.dispose();
					Main_window.Create_Main_Window();
				} else {
					System.out.println("f*ck off stranger");
					JOptionPane.showMessageDialog(null, "wrong username or password");
				}

			}
		});
	}
}
