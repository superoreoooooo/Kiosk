package kiosk.UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kiosk.KioskData;
import kiosk.brand.Brand;
import kiosk.brand.BrandUtil;
import kiosk.menu.Menu;
import kiosk.user.order.Order;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KioskCount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCount;
	private int count = 1;
	private static JFrame jf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jf = new KioskCount(null);
					jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setCount(int i) {
		count += i;
		if (count <= 0) {
			count = 0;
		}
		lblCount.setText(String.valueOf(count));
	}

	/**
	 * Create the frame.
	 */
	public KioskCount(Menu menu) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				KioskSelMenu.isOpen = false;
			}
		});
		setAlwaysOnTop(true);
		if (menu == null) return;
		jf = this;
		setResizable(false);
		setMinimumSize(new Dimension(400, 300));
		setSize(new Dimension(400, 300));
		setTitle("KIOSK");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCntTxt = new JLabel("Count");
		lblCntTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblCntTxt.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 18));
		contentPane.add(lblCntTxt, BorderLayout.NORTH);
		
		JLabel lblMinus = new JLabel("");
		lblMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCount(-1);
			}
		});
		lblMinus.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\minus.png"));
		contentPane.add(lblMinus, BorderLayout.WEST);
		
		JLabel lblPlus = new JLabel("");
		lblPlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCount(1);
			}
		});
		lblPlus.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\plus.png"));
		contentPane.add(lblPlus, BorderLayout.EAST);
		
		JPanel Count = new JPanel();
		contentPane.add(Count, BorderLayout.CENTER);
		Count.setLayout(null);
		
		lblCount = new JLabel("1");
		lblCount.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 29));
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(12, 54, 170, 72);
		Count.add(lblCount);
		
		JPanel confirm = new JPanel();
		FlowLayout flowLayout = (FlowLayout) confirm.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Brand brand1 : BrandUtil.getBrands()) {
                    if (brand1.getMenus().contains(menu)) {
                        KioskData.orders.add(new Order(brand1, menu, count));
                        break;
                    }
                }
				KioskSelMenu.isOpen = false;
				KioskSelMenu.set();
				jf.dispose();
				/** //폐기
				List<Order> orders = new ArrayList<>();
				for (Order o : KioskData.orders) {
					if (orders.size() == 0) {
						orders.add(o);
					}
					for (Order os : orders) {
						if (os.getMenu().equals(o.getMenu())) {
							os.setCount(os.getCount() + o.getCount());
						} else {
							orders.add(o);
						}
					}
				}
				
				KioskData.orders = orders;

				for (Order o : KioskData.orders) {
					System.out.println(o.getBrand().getBrandName() + o.getMenu().getName() + o.getCount());
				}*/
			}
		});
		confirm.setBackground(new Color(38, 83, 165));
		contentPane.add(confirm, BorderLayout.SOUTH);
		
		JLabel lblAddToCart = new JLabel("Add To Cart");
		lblAddToCart.setForeground(new Color(255, 255, 255));
		lblAddToCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddToCart.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 18));
		confirm.add(lblAddToCart);
	}
}
