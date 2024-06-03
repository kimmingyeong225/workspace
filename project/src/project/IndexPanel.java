package project;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// 로그인

public class IndexPanel extends JPanel {

	// 백그라운드 이미지 컴포넌트
	private Image backgroundImage;
	private JPanel backgeroundPanel;

	// 보더 컴포넌트
	private JPanel borderPanel;

	// ip 컴포넌트
	private JPanel ipPanel;
	private JLabel ipLabel;
	private JTextField inputIp;

	// port 컴포넌트
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;

	// id 컴포넌트
	private JPanel idPanel;
	private JLabel idLabel;
	private JTextField inputId;

	// 로그인 버튼
	private JButton loginBtn;

	private ClientService clientService;

	public IndexPanel(ClientService clientService) {
		this.clientService = clientService;
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		// 백그라운드 이미지 컴포넌트
		backgroundImage = new ImageIcon("").getImage();
		backgeroundPanel = new JPanel();

		// 보더 컴포넌트
		borderPanel = new JPanel();

		// IP 컴포넌트
		ipPanel = new JPanel();
		ipLabel = new JLabel("HOST IP");
		inputIp = new JTextField(10);

		// RORT 컴포넌트
		portPanel = new JPanel();
		portLabel = new JLabel("PORT NUMBER");
		inputPort = new JTextField(10);

		// ID 컴포넌트
		idPanel = new JPanel();
		idLabel = new JLabel("ID");
		inputId = new JTextField(10);

		// 로그인 버튼
		loginBtn = new JButton("LOGIN");

	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);
		
		// 백그라운드 이미지 패널
		backgeroundPanel.setSize(getWidth(), getHeight());
		backgeroundPanel.setLayout(null);
		add(backgeroundPanel);
		
		// 보더 컴포넌트
		borderPanel.setBounds(100, 60, 190, 380);
		borderPanel.setLayout(null);
		borderPanel.setBackground(Color.white);
		borderPanel.setBorder(new TitledBorder(new LineBorder(Color.pink)));
		
		// IP 컴포넌트
		ipPanel.setBounds(30, 40, 120, 100);
		ipPanel.setBackground(new Color(0, 0, 0, 0));
		ipPanel.add(ipLabel);
		ipPanel.add(inputIp);
		borderPanel.add(ipPanel);
		
		// RORT 컴포넌트
		portPanel.setBounds(30, 140, 120, 100);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		borderPanel.add(portPanel);
		
		// IP 컴포넌트
		ipPanel.setBounds(30, 140, 120, 100);
		ipPanel.setBackground(new Color(0, 0, 0, 0));
		ipPanel.add(ipLabel);
		ipPanel.add(inputId);
		borderPanel.add(ipPanel);
		
		// LoginBth 컴포넌트
		loginBtn.setBackground(Color.darkGray);
		loginBtn.setBounds(30, 340, 120, 20);
		borderPanel.add(loginBtn);
		
		// 테스트 코드
		
		
		
	}

	private void initListener() {

	}

}
