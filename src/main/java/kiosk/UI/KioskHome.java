package kiosk.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;
import kiosk.brand.BrandUtil;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class KioskHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static KioskSelMenu frame;
	public static KioskPay f2;
	public static KioskHome kh;
	public static KioskPay kp;
	private static JScrollPane scrollPane;
	private static JLabel lblTotalPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskHome frame = new KioskHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void open() {
		this.dispose();
		
		frame = new KioskSelMenu();
		frame.setVisible(true);
	}

		
	public static void back() {
		if (frame != null) {
			frame.dispose();
		}
		kh = new KioskHome();
		kh.setVisible(true);
	}
	
	public static void openHome() {
		kh = new KioskHome();
		kh.setVisible(true);
	}
	
	public void pay() {
		if (KioskData.orders.size() == 0) {
			JOptionPane.showMessageDialog(null, "No items in cart. Please add items to cart.", "KIOSK", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			this.dispose();
			kp = new KioskPay();
			kp.setVisible(true);
		}
	}
	
	public static void set() {
		DefaultListModel lm = new DefaultListModel();
		KioskData.orders.forEach(order -> lm.addElement(order.getMenu().getName() + " " + order.getCount() + "x"));
		
		JList list = new JList(lm);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(list);
		
		final int[] t = {0};
		KioskData.orders.forEach(order -> t[0] += (order.getCount() * order.getMenu().getPrice()));
		
		lblTotalPrice.setText("Total : " + t[0] + " won");
	}

	
	/**
	 * Create the frame.
	 */
	public KioskHome() {
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
		logo.setBackground(new Color(38, 83, 156));
		logo.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\logo_225.png"));
		logo.setBounds(0, 0, 584, 225);
		getContentPane().add(logo);
		
		JPanel bg = new JPanel();
		bg.setBackground(new Color(38, 83, 156));
		bg.setBounds(0, 0, 584, 225);
		getContentPane().add(bg);
		bg.setLayout(null);
		
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
		
		JLabel lblPay = new JLabel("");
		lblPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pay();
			}
		});
		lblPay.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\pay.png"));
		lblPay.setBounds(482, 25, 90, 90);
		bg.add(lblPay);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 25, 137, 136);
		bg.add(scrollPane);
		
		lblTotalPrice = new JLabel("Total :");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setForeground(Color.WHITE);
		lblTotalPrice.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lblTotalPrice.setBounds(231, 171, 137, 44);
		bg.add(lblTotalPrice);
		
		JPanel Brands = new JPanel();
		Brands.setBounds(10, 235, 562, 616);
		getContentPane().add(Brands);
		Brands.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Click to choose brand");
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 538, 53);
		Brands.add(lblNewLabel);
		
		JPanel BrandsPanel = new JPanel();
		BrandsPanel.setBounds(12, 73, 538, 533);
		Brands.add(BrandsPanel);
		BrandsPanel.setLayout(new GridLayout(2, 2, 20, 20));
		
		JPanel Gra = new JPanel();
		Gra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KioskData.brand = BrandUtil.getBrand("Grazie");
				open();
			}
		});
		BrandsPanel.add(Gra);
		Gra.setLayout(null);
		
		JLabel img_Gra = new JLabel("");
		img_Gra.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\gra.png"));
		img_Gra.setHorizontalAlignment(SwingConstants.CENTER);
		img_Gra.setBounds(12, 10, 235, 187);
		Gra.add(img_Gra);
		
		JLabel name_Gra = new JLabel("Grazzie");
		name_Gra.setHorizontalAlignment(SwingConstants.CENTER);
		name_Gra.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		name_Gra.setBounds(12, 207, 235, 39);
		Gra.add(name_Gra);
		
		JPanel HRN = new JPanel();
		HRN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KioskData.brand = BrandUtil.getBrand("Hongdae Rice Noodles");
				open();
			}
		});
		BrandsPanel.add(HRN);
		HRN.setLayout(null);
		
		JLabel img_HRN = new JLabel("");
		img_HRN.setHorizontalAlignment(SwingConstants.CENTER);
		img_HRN.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\hrn.png"));
		img_HRN.setBounds(12, 10, 235, 187);
		HRN.add(img_HRN);
		
		JLabel name_HRN = new JLabel("Hongdae Rice Noodles");
		name_HRN.setHorizontalAlignment(SwingConstants.CENTER);
		name_HRN.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		name_HRN.setBounds(12, 207, 235, 39);
		HRN.add(name_HRN);
		
		JPanel Haz = new JPanel();
		Haz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KioskData.brand = BrandUtil.getBrand("Hazben");
				open();
			}
		});
		BrandsPanel.add(Haz);
		Haz.setLayout(null);
		
		JLabel img_Haz = new JLabel("");
		img_Haz.setHorizontalAlignment(SwingConstants.CENTER);
		img_Haz.setBounds(12, 10, 235, 187);
		img_Haz.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\haz.png"));
		img_Haz.setBounds(12, 10, 235, 187);
		Haz.add(img_Haz);
		
		JLabel name_Haz = new JLabel("Hazben");
		name_Haz.setHorizontalAlignment(SwingConstants.CENTER);
		name_Haz.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		name_Haz.setBounds(12, 207, 235, 39);
		Haz.add(name_Haz);
		
		JPanel MW = new JPanel();
		MW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KioskData.brand = BrandUtil.getBrand("Mankwon Whabap");
				open();
			}
		});
		BrandsPanel.add(MW);
		MW.setLayout(null);
		
		JLabel img_MW = new JLabel("");
		img_MW.setHorizontalAlignment(SwingConstants.CENTER);
		img_MW.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\mw.png"));
		img_MW.setBounds(12, 10, 235, 187);
		MW.add(img_MW);
		
		JLabel name_MW = new JLabel("Mankwon Whabap");
		name_MW.setHorizontalAlignment(SwingConstants.CENTER);
		name_MW.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		name_MW.setBounds(12, 207, 235, 39);
		MW.add(name_MW);
		
		set();
	}
}
