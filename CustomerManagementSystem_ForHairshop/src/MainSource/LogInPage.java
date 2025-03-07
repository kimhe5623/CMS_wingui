/* 로그인 페이지 */
/* main 함수 클래스 */
package MainSource;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import CMS_MainPage.MainPage;

public class LogInPage {
	private String RID = "admin", RPW = "1234";	// ID, PW
	int width = 800, height = 490;
	private String ID, PW;

	public LogInPage() {
		JFrame LogInPageWindow = new JFrame("미용실 고객관리 시스템 - 로그인");
		
		LogInPageWindow.setBounds(300,200,width,height);
		LogInPageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Box left = Box.createVerticalBox();	// 왼쪽 디자인
		
		ImageIcon hairshopImage = new ImageIcon("C:\\Users\\user\\Desktop\\미용실 고객관리 시스템\\CMS_picture/LogInPageImage.jpg");
		left.add(new JLabel(hairshopImage));
		
		Box right = Box.createVerticalBox();
		right.add(new JLabel("아이디"));
		JTextField ID_tf = new JTextField(30);	ID_tf.setMaximumSize(ID_tf.getPreferredSize());
		right.add(ID_tf);
		right.add(new JLabel("비밀번호"));
		JPasswordField PW_tf = new JPasswordField(30);	PW_tf.setMaximumSize(PW_tf.getPreferredSize());
		right.add(PW_tf);
		JButton LogIn = new JButton("로그인");
		right.add(LogIn);
		
		Box top = Box.createHorizontalBox();
		top.add(left);
		top.add(new JLabel("                    "));
		top.add(right);

		Container content = LogInPageWindow.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(top,BorderLayout.NORTH);
		LogInPageWindow.setVisible(true);
		
		// 로그인 버튼을 누를 때 action
		LogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID = ID_tf.getText(); PW = PW_tf.getText();
				if(ID.equals(RID) && PW.equals(RPW)) {	// 성공적으로 로그인 할 경우
					ID = null; PW = null;	// ID, PW 변수 초기화

					new MainPage(LogInPageWindow);	// 고객관리시스템 홈 화면 출력
					
				}
				else {
					JOptionPane.showMessageDialog(null, "로그인 정보가 정확하지 않습니다.","로그인 정보 오류"
							, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	public static void main(String [] args) {
		new LogInPage();
	}
}
