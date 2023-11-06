package kiosk.UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KioskPay extends JFrame {
	public static KioskPay mframe;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JScrollPane scrollPane;
	private static JLabel lblTotalPrice;
	public static boolean isOpen = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mframe = new KioskPay();
					mframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void dispo() {
		mframe.dispose();
	}
	
	public static void done() {
		dispo();
		KioskFinish f = new KioskFinish();
		f.setVisible(true);
	}
	
	public static void set() {
		DefaultListModel lm = new DefaultListModel();
		KioskData.orders.forEach(order -> lm.addElement(order.getMenu().getName() + " " + order.getCount() + "x"));
		
		JList list = new JList(lm);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		final int[] t = {0};
		KioskData.orders.forEach(order -> t[0] += (order.getCount() * order.getMenu().getPrice()));
		
		lblTotalPrice.setText("Total : " + t[0] + " won");
		
		scrollPane.setViewportView(list);
	}
	
	public static void payCard() {
		if (isOpen) return;
		KioskPay_Card c = new KioskPay_Card();
		c.setVisible(true);
		isOpen = true;
	}
	
	public static void payOther() {
		JOptionPane.showMessageDialog(null, "Other payment is not supported yet!", "KIOSK", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Create the frame.
	 */
	public KioskPay() {
		mframe = this;
		setResizable(false);
		setMinimumSize(new Dimension(600, 900));
		setSize(new Dimension(600, 900));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		lblHome.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\home.png"));
		lblHome.setBounds(482, 125, 90, 90);
		bg.add(lblHome);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mframe.dispose();
				KioskHome.openHome();
			}
		});
		lblBack.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\back.png"));
		lblBack.setBounds(380, 125, 90, 90);
		bg.add(lblBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 233, 560, 618);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 234, 598);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(258, 10, 290, 598);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblTotalPrice = new JLabel("Total : ");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 33));
		lblTotalPrice.setBounds(0, 10, 290, 65);
		panel_1.add(lblTotalPrice);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				payCard();
			}
		});
		panel_2.setBackground(new Color(149, 197, 27));
		panel_2.setBounds(12, 85, 266, 210);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCredit = new JLabel("Credit Card");
		lblCredit.setBounds(12, 10, 242, 190);
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 42));
		lblCredit.setBackground(new Color(149, 197, 27));
		panel_2.add(lblCredit);
		
		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				payOther();
			}
		});
		panel_3.setBackground(new Color(0, 104, 133));
		panel_3.setBounds(12, 305, 266, 148);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblOther = new JLabel("Other");
		lblOther.setHorizontalAlignment(SwingConstants.CENTER);
		lblOther.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 42));
		lblOther.setBounds(12, 10, 242, 128);
		panel_3.add(lblOther);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
				
			}
		});
		panel_4.setBackground(new Color(56, 191, 239));
		panel_4.setBounds(12, 463, 266, 125);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblReset = new JLabel("Reset");
		lblReset.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 42));
		lblReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblReset.setBounds(12, 10, 242, 105);
		panel_4.add(lblReset);
		
		set();
	}
	public static void reset() {
		KioskData.orders = new ArrayList<>();
		mframe.dispose();
		Kiosk k = new Kiosk();
		k.setVisible(true);
	}
}
