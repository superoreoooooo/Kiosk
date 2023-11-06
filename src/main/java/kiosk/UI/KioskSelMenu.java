package kiosk.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;
import kiosk.brand.Brand;
import kiosk.menu.Menu;

import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class KioskSelMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel img_menu1;
	private JLabel img_menu2;
	private JLabel menu1;
	private JLabel menu2;
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
					KioskSelMenu frame = new KioskSelMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void load() {
		Brand brand = KioskData.brand;
		switch (brand.getBrandName()) {
			case "Grazie" :
				img_menu1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\gra\\mn_ia.png"));
				img_menu2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\gra\\mn_mys.png"));
				break;
			case "Hongdae Rice Noodles" :
				img_menu1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\hrn\\mn_rn.png"));
				img_menu2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\hrn\\mn_fr.png"));
				break;
			case "Hazben" :
				img_menu1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\haz\\mn_pc.png"));
				img_menu2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\haz\\mn_u.png"));
				break;
			case "Mankwon Whabap" :
				img_menu1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\mw\\mn_eg.png"));
				img_menu2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\menus\\mw\\mn_kj.png"));
				break;
			default :
				break;
		}
		menu1.setText(KioskData.brand.getMenus().get(0).getName());
		menu2.setText(KioskData.brand.getMenus().get(1).getName());
	}
	
	public void openCounter(Menu menu) {
		if (isOpen) return;
		KioskCount c = new KioskCount(menu);
		c.setVisible(true);
		isOpen = true;
	}
	
	public void pay() {
		if (KioskData.orders.size() == 0) {
			JOptionPane.showMessageDialog(null, "No items in cart. Please add items to cart.", "KIOSK", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			this.dispose();
			new KioskPay().setVisible(true);
		}
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

	/**
	 * Create the frame.
	 */
	public KioskSelMenu() {
		setResizable(false);
		setMinimumSize(new Dimension(600, 900));
		setSize(new Dimension(600, 900));
		setTitle("KIOSK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 573);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		logo.setForeground(new Color(255, 255, 255));
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
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KioskHome.back();
			}
		});
		lblBack.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\back.png"));
		lblBack.setBounds(380, 125, 90, 90);
		bg.add(lblBack);
		
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
		lblTotalPrice.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		lblTotalPrice.setForeground(new Color(255, 255, 255));
		lblTotalPrice.setBounds(231, 171, 137, 44);
		bg.add(lblTotalPrice);
		
		JPanel Brands = new JPanel();
		Brands.setLayout(null);
		Brands.setBounds(10, 235, 562, 616);
		contentPane.add(Brands);
		
		JLabel lblNewLabel = new JLabel("Click to choose item");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 48));
		lblNewLabel.setBounds(12, 10, 538, 53);
		Brands.add(lblNewLabel);
		
		JPanel BrandsPanel = new JPanel();
		BrandsPanel.setBounds(12, 73, 538, 533);
		Brands.add(BrandsPanel);
		BrandsPanel.setLayout(new GridLayout(2, 2, 20, 20));
		
		JPanel Menu1 = new JPanel();
		Menu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCounter(KioskData.brand.getMenus().get(0));
			}
		});
		Menu1.setLayout(null);
		BrandsPanel.add(Menu1);
		
		img_menu1 = new JLabel("");
		img_menu1.setHorizontalAlignment(SwingConstants.CENTER);
		img_menu1.setBounds(12, 10, 235, 187);
		Menu1.add(img_menu1);
		
		menu1 = new JLabel("Menu1Name");
		menu1.setHorizontalAlignment(SwingConstants.CENTER);
		menu1.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		menu1.setBounds(12, 207, 235, 39);
		Menu1.add(menu1);
		
		JPanel Menu2 = new JPanel();
		Menu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openCounter(KioskData.brand.getMenus().get(1));
			}
		});
		Menu2.setLayout(null);
		BrandsPanel.add(Menu2);
		
		img_menu2 = new JLabel("");
		img_menu2.setHorizontalAlignment(SwingConstants.CENTER);
		img_menu2.setBounds(12, 10, 235, 187);
		Menu2.add(img_menu2);
		
		menu2 = new JLabel("Menu2Name");
		menu2.setHorizontalAlignment(SwingConstants.CENTER);
		menu2.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		menu2.setBounds(12, 207, 235, 39);
		Menu2.add(menu2);
		
		JPanel cartList = new JPanel();
		BrandsPanel.add(cartList);
		cartList.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		BrandsPanel.add(panel_1);
		panel_1.setLayout(null);
		
		set();
		load();
	}
}
