package kiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import kiosk.UI.KioskHome;
import kiosk.UI.KioskLogin;
import kiosk.UI.KioskRegister;
import kiosk.user.KioskUser;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Kiosk extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JPanel panel;
	public static KioskHome kioskHome;
	public static KioskLogin kioskLogin;
	public static KioskRegister kioskRegister;
	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnHelp;
	private static Kiosk frame;
	public static KioskUser user;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Kiosk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void swapToMainWindow() {
		kioskHome = new KioskHome();
		kioskHome.setVisible(true);
		frame.dispose();
	}
	
	public static void open() {
		if (kioskHome != null) {
			kioskHome.dispose();
		}
		if (KioskHome.kh != null) {
			KioskHome.kh.dispose();
		}
		if (kioskLogin != null) {
			kioskLogin.dispose();
		}
		if (kioskRegister != null) {
			kioskRegister.dispose();
		}
		if (KioskHome.frame != null) {
			KioskHome.frame.dispose();
		}
		
		frame = new Kiosk();
		frame.setVisible(true);
	}
	
	public static void swapToLoginWindow() {
		kioskLogin = new KioskLogin();
		kioskLogin.setVisible(true);
		frame.dispose();
	}
	
	public static void swapToRegisterWindow() {
		kioskRegister = new KioskRegister();
		kioskRegister.setVisible(true);
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	public Kiosk() {
		KioskData.init();
		frame = this;
		setResizable(false);
		setMinimumSize(new Dimension(600, 900));
		setSize(new Dimension(600, 900));
		setTitle("Welcome!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 584, 861);
		contentPane.add(panel);
		panel.setVisible(true);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 584, 430);
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\logo.png"));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 90));
		panel.add(lblNewLabel);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(12, 440, 306, 324);
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setBackground(new Color(38, 83, 156));
		btnOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (KioskData.user == null) {
					JOptionPane.showMessageDialog(null, "You are not logged in.", "KIOSK", JOptionPane.WARNING_MESSAGE);
				}
				swapToMainWindow();
				frame.dispose();
			}
		});
		
		btnOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrder.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 86));
		panel.add(btnOrder);
		
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (KioskData.user != null) {
					JOptionPane.showMessageDialog(null, "You are already logged in.\nPlease logout first.", "KIOSK", JOptionPane.WARNING_MESSAGE);
				} else {
					swapToLoginWindow();
				}
			}
		});
		btnLogin.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 45));
		btnLogin.setBackground(new Color(170, 208, 71));
		btnLogin.setBounds(330, 440, 242, 161); 
		panel.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				swapToRegisterWindow();
			}
		});
		btnRegister.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 30));
		btnRegister.setBackground(new Color(0, 122, 133));
		btnRegister.setBounds(330, 611, 242, 94);
		panel.add(btnRegister);
		
		btnHelp = new JButton("Help");
		btnHelp.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 30));
		btnHelp.setBackground(new Color(27, 188, 240));
		btnHelp.setBounds(330, 715, 242, 49);
		panel.add(btnHelp);
		
		JLabel lblLoginStatus = new JLabel("Not logged in");
		lblLoginStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginStatus.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lblLoginStatus.setBackground(new Color(255, 255, 255));
		lblLoginStatus.setBounds(12, 774, 306, 77);
		panel.add(lblLoginStatus);
		
		btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (KioskData.user != null) {
					KioskData.user = null;
					JOptionPane.showMessageDialog(null, "You are now logged out.", "KIOSK", JOptionPane.INFORMATION_MESSAGE);
					KioskData.orders = new ArrayList<>();
					lblLoginStatus.setText("Not logged in");
				} else {
					JOptionPane.showMessageDialog(null, "You are not logged in!", "KIOSK", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 30));
		btnLogout.setBackground(new Color(77, 32, 122));
		btnLogout.setBounds(330, 774, 242, 77);
		panel.add(btnLogout);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, lblNewLabel, btnOrder, btnLogin, btnRegister, btnHelp, lblLoginStatus, btnLogout}));
		
		if (KioskData.user != null) {
			lblLoginStatus.setText("Logged in : " + KioskData.user.getName());
		}
	}
}
