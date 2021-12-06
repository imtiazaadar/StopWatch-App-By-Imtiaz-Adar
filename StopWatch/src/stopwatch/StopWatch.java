package stopwatch;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class StopWatch implements ActionListener{
	
	private int passedTime = 0;
	private int hour = 0, minute = 0, second = 0;
	private boolean clockRunning = false;
	private String hourformat = String.format("%02d", hour);
	private String minuteformat = String.format("%02d", minute);
	private String secondformat = String.format("%02d", second);
	private JFrame frame = new JFrame();
	private JButton startButton = new JButton("START");
	private JButton resetButton = new JButton("RESET");
	private Color labelColor = new Color(245,255,255);
	private Color introColor = new Color(96, 96, 96);
	private Color startButtonColor = new Color(76, 0, 153);
	private Color stopButtonColor = new Color(153, 0, 76);
	private JLabel timeField = new JLabel();
	private JLabel intro = new JLabel("STOPWATCH BY IMTIAZ ADAR");
	private Font font1 = new Font("DS-DIGITAL", Font.BOLD, 80);
	private Font font2 = new Font("Verdana", (Font.BOLD), 20);
	private Font font3 = new Font("Verdana", Font.BOLD, 15);
	private ImageIcon icon = new ImageIcon(getClass().getResource("clock5.png"));
	private Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			passedTime += 1000;
			hour = passedTime / 3600000;
			minute = (passedTime / 60000) % 60;
			second = (passedTime / 1000) % 60;
			hourformat = String.format("%02d", hour);
			minuteformat = String.format("%02d", minute);
			secondformat = String.format("%02d", second);
			timeField.setText(hourformat + " : " + minuteformat + " : " + secondformat);
		}
		
	});
	public StopWatch() {
		intro.setBorder(BorderFactory.createBevelBorder(2));
		intro.setFont(font3);
		intro.setBounds(153, 40, 400, 80);
		intro.setForeground(introColor);
		
		timeField.setText(hourformat + " : " + minuteformat + " : " + secondformat);
		timeField.setBorder(BorderFactory.createBevelBorder(5));
		timeField.setBackground(Color.BLACK);
		timeField.setForeground(labelColor);
		timeField.setFont(font1);
		timeField.setBounds(100, 150, 390, 80);
		timeField.setOpaque(true);
		
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.setBounds(130, 330, 150, 50);
		startButton.setBackground(startButtonColor);
		startButton.setForeground(Color.WHITE);
		startButton.setFont(font2);
		startButton.setBorder(BorderFactory.createSoftBevelBorder(2));
		//startButton.setToolTipText("Click To Start Stopwatch");
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resetButton.setBounds(283, 330, 150, 50);
		resetButton.setBackground(stopButtonColor);
		resetButton.setForeground(Color.WHITE);
		resetButton.setFont(font2);
		resetButton.setBorder(BorderFactory.createSoftBevelBorder(2));
		//resetButton.setToolTipText("Click To Reset Stopwatch");
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(intro);
		frame.add(startButton);
		frame.add(resetButton);
		frame.add(timeField);
		frame.getContentPane().setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		//frame.setSize(500, 500);
		frame.setBounds(468, 180, 580, 500);
		frame.setTitle("STOPWATCH BY IMTIAZ ADAR");
		frame.setLayout(null);
		frame.setVisible(true);
	}
	public void Start() {
		timer.start();
	}
	public void Stop() {
		timer.stop();
	}
	public void Reset() {
		timer.stop();
		passedTime = 0;
		hour = 0;
		minute = 0;
		second = 0;
		hourformat = String.format("%02d", hour);
		minuteformat = String.format("%02d", minute);
		secondformat = String.format("%02d", second);
		timeField.setText(hourformat + " : " + minuteformat + " : " + secondformat);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			if(!clockRunning) {
				clockRunning = true;
				Start();
				startButton.setText("STOP");
				//startButton.setToolTipText("Click To Reset Stopwatch");
			}
			else {
				startButton.setText("START");
				//startButton.setToolTipText("Click To Start Stopwatch");
				clockRunning = false;
				Stop();
			}
		}
		if(e.getSource() == resetButton) {
			clockRunning = false;
			startButton.setText("START");
			//startButton.setToolTipText("Click To Start Stopwatch");
			Reset();
		}
	}
}
