package kiosk.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;
import kiosk.user.KioskUser;

public class KioskLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblLogin;
	private JTextField tfNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskLogin frame = new KioskLogin();
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
	public KioskLogin() {
		setResizable(false);
		setMinimumSize(new Dimension(600, 900));
		setSize(new Dimension(600, 900));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\logo_225.png"));
		logo.setBackground(new Color(38, 83, 156));
		logo.setBounds(0, 0, 584, 225);
		contentPane.add(logo);
		
		JPanel bg = new JPanel();
		bg.setLayout(null);
		bg.setBackground(new Color(38, 83, 156));
		bg.setBounds(0, 0, 584, 225);
		contentPane.add(bg);
		
		JLabel lblHome = new JLabel("");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Kiosk.open();
			}
		});
		lblHome.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\home.png"));
		lblHome.setBounds(482, 125, 90, 90);
		bg.add(lblHome);
		
		JPanel LoginP = new JPanel();
		LoginP.setLayout(null);
		LoginP.setBounds(12, 235, 562, 616);
		contentPane.add(LoginP);
		
		lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 48));
		lblLogin.setBounds(12, 10, 538, 89);
		LoginP.add(lblLogin);
		
		JPanel Login = new JPanel();
		Login.setBounds(12, 109, 538, 497);
		LoginP.add(Login);
		Login.setLayout(new GridLayout(4, 2, 20, 20));
		
		JPanel num = new JPanel();
		num.setLayout(null);
		Login.add(num);
		
		JLabel lbl_num = new JLabel("Phone number");
		lbl_num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_num.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lbl_num.setBounds(12, 10, 514, 39);
		num.add(lbl_num);
		
		tfNum = new JTextField();
		tfNum.setColumns(10);
		tfNum.setBounds(12, 60, 514, 39);
		num.add(tfNum);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number = tfNum.getText();
				
				if (number != null && number != "") {
					KioskUser user = KioskData.login(number);
					if (user != null) {
						KioskData.user = user;
						JOptionPane.showMessageDialog(null, "Login complete!\n Mileage : " + user.getMileage(), "KIOSK", JOptionPane.INFORMATION_MESSAGE);
						Kiosk.open();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to login!\nCheck your Phone number.", "KIOSK", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Failed to login\nCheck your Phone number.", "KIOSK", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBackground(new Color(38, 83, 156));
		btnNewButton.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 53));
		Login.add(btnNewButton);

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
