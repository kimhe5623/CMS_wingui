/* 메뉴 페이지 */
package CMS_MainPage;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import All_Customer.All_Customer_info;
import Birth_customer.BirthCustomerPage;
import Designer.allDesigner;

public class MainPage {
	int width = 800, height = 490;
	private Font font1 = new Font("monospace", Font.BOLD, 15);
	private Font font2 = new Font("monospace", Font.BOLD, 20);
	JFrame MainPageWindow = null;

	public MainPage(JFrame BeforePage) {
		BeforePage.setVisible(false);
		// 화면 생성
		MainPageWindow = new JFrame("미용실 고객관리 시스템 - 메인 화면");
		MainPageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPageWindow.setBounds(300, 200, width, height);
		String MenuStr[] = { "1. 고객 정보", "2. 생일 고객 조회", "3. 디자이너 정보", "4. 날짜별 거래 내역", "5. 등급 기준 정보", "6. 서비스/미용용품 정보" };
		// 버튼 생성
		JButton MenuBtn[] = new JButton[6];
		for (int i = 0; i < 6; i++) {
			MenuBtn[i] = new JButton(MenuStr[i]);
			MenuBtn[i].setFont(font1);
		}

		// 화면 구성
		JPanel panel1 = new JPanel(); // menu, 1,2,3
		panel1.setLayout(new GridLayout(4, 1, 5, 5));
		JLabel Menu = new JLabel("  # 메뉴");
		Menu.setFont(font2);
		panel1.add(Menu);
		for (int i = 0; i < 3; i++)
			panel1.add(MenuBtn[i]);

		JPanel panel2 = new JPanel(); // "", 4,5,6
		panel2.setLayout(new GridLayout(4, 1, 5, 5));
		panel2.add(new JLabel(""));
		for (int i = 3; i < 6; i++)
			panel2.add(MenuBtn[i]);

		// 컴포넌트 배치 & 추가
		MainPageWindow.setLayout(new GridLayout(1, 3, 5, 5));
		ImageIcon hairshopImage = new ImageIcon(
				"C:\\Users\\user\\Desktop\\미용실 고객관리 시스템\\CMS_picture/MainPageImage.jpg");
		MainPageWindow.add(new JLabel(hairshopImage));
		MainPageWindow.add(panel1);
		MainPageWindow.add(panel2);
		MainPageWindow.setVisible(true);
		
		MyactionListener listener = new MyactionListener();
		MenuBtn[0].addActionListener(listener);
		MenuBtn[1].addActionListener(listener);
		MenuBtn[2].addActionListener(listener);
	}

	// MyActionListener 작성
	class MyactionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String MenuStr[] = { "1. 고객 정보", "2. 생일 고객 조회", "3. 디자이너 정보", 
						"4. 날짜별 거래 내역", "5. 등급 기준 정보", "6. 서비스/미용용품 정보" };
			JButton pressed = (JButton) e.getSource();
			if(pressed.getText().equals(MenuStr[0]))	// 1. 고객 정보 버튼을 클릭할 경우
				new All_Customer_info(MainPageWindow);
			else if(pressed.getText().equals(MenuStr[1]))	// 2. 생일 고객 조회 버튼을 클릭한 경우
				new BirthCustomerPage(MainPageWindow);
			else if(pressed.getText().equals(MenuStr[2]))	// 3. 디자이너 조회 버튼을 클릭한 경우
				new allDesigner(MainPageWindow);

		}
	}
}
