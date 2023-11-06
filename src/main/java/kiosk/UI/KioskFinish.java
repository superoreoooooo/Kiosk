package kiosk.UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kiosk.Kiosk;
import kiosk.KioskData;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KioskFinish extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JLabel lblwaitingnumber;
	private static JLabel Mileage;
	private JFrame jf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskFinish frame = new KioskFinish();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void set() {
		final int[] t = {0};
		KioskData.orders.forEach(order -> t[0] += (order.getCount() * order.getMenu().getPrice()));
		lblwaitingnumber.setText(String.format("%04d", KioskData.count++));
		if (KioskData.user != null) {
			KioskData.user.setMileage(t[0] / 10);
			
			Mileage.setText(KioskData.user.getName() + "'s Mileage : " + KioskData.user.getMileage());
		}
	}
	
	public KioskFinish() {
		jf = this;
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\logo.png"));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 90));
		lblNewLabel.setBounds(0, 0, 584, 430);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 440, 562, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Order Finished!");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 38));
		lblNewLabel_1.setBounds(12, 10, 538, 99);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(38, 83, 165));
		panel_1.setBounds(12, 10, 538, 99);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 119, 538, 99);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Waiting Number");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 31));
		lblNewLabel_2.setBounds(12, 10, 326, 79);
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(350, 10, 176, 79);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		lblwaitingnumber = new JLabel("000");
		lblwaitingnumber.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 32));
		lblwaitingnumber.setBounds(12, 10, 152, 59);
		panel_3.add(lblwaitingnumber);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 228, 538, 173);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 362, 173);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		Mileage = new JLabel("Not logged in.");
		Mileage.setHorizontalAlignment(SwingConstants.CENTER);
		Mileage.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 26));
		Mileage.setBounds(12, 10, 338, 153);
		panel_5.add(Mileage);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(363, 0, 175, 173);
		panel_4.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jf.dispose();
				KioskData.user = null;
				KioskData.orders = new ArrayList<>();
				Kiosk k = new Kiosk();
				k.setVisible(true);
			}
		});
		lblNewLabel_4.setBounds(12, 10, 153, 153);
		panel_6.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\exit.png"));
		
		set();
	}
}
