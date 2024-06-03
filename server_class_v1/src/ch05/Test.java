package ch05;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Test extends JFrame{
	private JButton button1;
	private JButton button2;
	private JLabel label;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public Test() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("test");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		button1 = new JButton("서버 실행");
		button2 = new JButton("서버 종료");
		textField = new JTextField("채팅 입력", 20);
	}
	
	private void setInitLayout() {
		setLayout(new FlowLayout());
		setVisible(true);
		
		add(button1);
		add(button2);
		add(textField);
		
		
	}

	public static void main(String[] args) {
		new Test();
	}
	
}
