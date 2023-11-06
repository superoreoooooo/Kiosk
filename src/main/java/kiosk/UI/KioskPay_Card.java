package kiosk.UI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kiosk.KioskData;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KioskPay_Card extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JFrame jf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskPay_Card frame = new KioskPay_Card();
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
	public KioskPay_Card() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				KioskPay.isOpen = false;
			}
		});
		jf = this;
		setAlwaysOnTop(true);
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
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 360, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Insert Your Card");
		lblNewLabel.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 29));
		lblNewLabel.setBounds(12, 10, 336, 52);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 92, 159, 159);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCard = new JLabel("");
		lblCard.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\Kiosk\\imgs\\card.png"));
		lblCard.setBounds(0, 0, 159, 159);
		panel_1.add(lblCard);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(183, 92, 189, 159);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(38, 83, 165));
		panel_3.setBounds(12, 94, 165, 55);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Insert");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 141, 35);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblCard1 = new JLabel();
		lblCard1.setVerticalAlignment(SwingConstants.TOP);
		lblCard1.setBounds(12, 10, 165, 15);
		panel_2.add(lblCard1);
		
		JLabel lblCard2 = new JLabel();
		lblCard2.setBounds(12, 22, 165, 15);
		panel_2.add(lblCard2);
		
		JLabel lblCard3 = new JLabel();
		lblCard3.setBounds(12, 34, 165, 15);
		panel_2.add(lblCard3);
		
		JLabel lblCard4 = new JLabel();
		lblCard4.setBounds(12, 47, 80, 15);
		panel_2.add(lblCard4);
		
		JLabel lblCard5 = new JLabel();
		lblCard5.setBounds(88, 47, 89, 15);
		panel_2.add(lblCard5);
		
		JLabel lblCard6 = new JLabel();
		lblCard6.setBounds(12, 59, 57, 15);
		panel_2.add(lblCard6);
		
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_1.setText("Inserted!");
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						Thread.sleep(1000);
						lblCard1.setText("BIN : 0000 00");
						Thread.sleep(1000);
						lblCard2.setText("Data : 00 0000 000");
						Thread.sleep(1000);
						lblCard3.setText("Check Digit : 0");
						Thread.sleep(1000);
						lblCard4.setText("Requesting...");
						Thread.sleep(3000);
						lblCard5.setText("Complete!");
						Thread.sleep(1000);
						lblCard6.setText("Done!");
						Thread.sleep(2000);
						exit();
						return null;
					}
				};
				worker.execute();
			}
		});
		
		final int[] t = {0};
		KioskData.orders.forEach(order -> t[0] += (order.getCount() * order.getMenu().getPrice()));
	}
	
	public void exit() {
		KioskPay.done();
		jf.dispose();
	}
}
