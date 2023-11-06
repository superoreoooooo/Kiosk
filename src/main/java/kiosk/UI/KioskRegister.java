package kiosk.UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;
import kiosk.user.KioskUser;
import kiosk.user.KioskUserUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class KioskRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblRegister;
	private JTextField tfNum;
	private JTextField tfName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskRegister frame = new KioskRegister();
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
	public KioskRegister() {
		setResizable(false);
		setMinimumSize(new Dimension(600, 900));
		setSize(new Dimension(600, 900));
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
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
		
		JPanel RegisterP = new JPanel();
		RegisterP.setLayout(null);
		RegisterP.setBounds(12, 235, 562, 616);
		contentPane.add(RegisterP);
		
		lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 48));
		lblRegister.setBounds(12, 10, 538, 89);
		RegisterP.add(lblRegister);
		
		JPanel Register = new JPanel();
		Register.setBounds(12, 109, 538, 497);
		RegisterP.add(Register);
		Register.setLayout(new GridLayout(4, 2, 20, 20));
		
		JPanel num = new JPanel();
		num.setLayout(null);
		Register.add(num);
		
		JLabel lbl_num = new JLabel("Phone number");
		lbl_num.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_num.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lbl_num.setBounds(12, 10, 514, 39);
		num.add(lbl_num);
		
		tfNum = new JTextField();
		tfNum.setColumns(10);
		tfNum.setBounds(12, 60, 514, 39);
		num.add(tfNum);
		
		JPanel name = new JPanel();
		name.setLayout(null);
		Register.add(name);
		
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lbl_name.setBounds(12, 10, 514, 39);
		name.add(lbl_name);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(12, 60, 514, 39);
		name.add(tfName);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number = tfNum.getText();
				String name = tfName.getText();
				
				if (number != null && number != "" && name != null && name != "") {
					final String REGEX = "[0-9]+";
					if (!number.matches(REGEX)) {
						JOptionPane.showMessageDialog(null, "Failed to register\nCheck your Phone number.", "KIOSK", JOptionPane.WARNING_MESSAGE);
						return;
					}
					KioskUser user = KioskData.register(number, name);
					if (user != null) {
						KioskData.user = user;
						JOptionPane.showMessageDialog(null, "Register complete!", "KIOSK", JOptionPane.INFORMATION_MESSAGE);
						KioskUserUtil.addUser(user);
						Kiosk.open();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to register\nPhone number duplicated.", "KIOSK", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Failed to register\nCheck your Phone number and Name.", "KIOSK", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRegister.setBackground(new Color(38, 83, 156));
		btnRegister.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 53));
		Register.add(btnRegister);
		
		bg.add(lblHome);
	}

}
