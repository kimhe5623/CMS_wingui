/* 고객 정보 추가 다이얼로그*/
package All_Customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MainSource.DB_Connection;


public class AddCustomerDialog {
	JDialog addCustomerD = null;
	JFrame DialogOwner = null;
	private JTextField tf[] = new JTextField[10];
	private datePanel Birth = new datePanel();
	
	private JButton AddBtn = new JButton("추가");
	private JButton CancelBtn = new JButton("취소");
	private String tfName[] = { "*고객 아이디", "*이름", "성별", "*연락처", "*생일" };
	private int width = 400, height = 300;
	
	String Values[] = new String[5];

	public AddCustomerDialog(JFrame frame, String title) {
		DialogOwner = frame;
		addCustomerD = new JDialog(DialogOwner);	// frame: 다이얼로그 주인
		addCustomerD.setTitle(title);	// title: 다이얼로그 title
		addCustomerD.setBounds(600,300,width,height);
		tf[0] = new JTextField(6);
		tf[1] = new JTextField(10);
		tf[2] = new JTextField(2);
		tf[3] = new JTextField(15);

		
		// 다이얼로그 디자인
		Box BoxForTf[] = new Box[5];
		for(int i=0;i<5;i++) BoxForTf[i] = Box.createVerticalBox();		
		for(int i=0;i<5;i++) BoxForTf[i].add(new JLabel(tfName[i]));
		BoxForTf[0].add(tf[0]);	BoxForTf[1].add(tf[1]);
		BoxForTf[2].add(tf[2]);	BoxForTf[3].add(tf[3]);
		BoxForTf[4].add(Birth.getPanel());
		
		JPanel insertPanel = new JPanel();	// 입력 패널
		insertPanel.setLayout(new FlowLayout());
		for(int i =0;i<5;i++) insertPanel.add(BoxForTf[i]);
		
		JPanel BtnPanel = new JPanel();	// 버튼 패널
		BtnPanel.setLayout(new FlowLayout());
		BtnPanel.add(AddBtn);
		BtnPanel.add(CancelBtn);
		
		addCustomerD.setLayout(new BorderLayout());
		addCustomerD.add(insertPanel, BorderLayout.CENTER);	// 입력 패널 추가
		addCustomerD.add(BtnPanel, BorderLayout.SOUTH);	// 버튼 패널 추가
		addCustomerD.add(new JLabel("* 가 표시된 것은 필수 입력 정보 입니다."), BorderLayout.NORTH);	//
		
		MyActionListener listener = new MyActionListener();
		AddBtn.addActionListener(listener);
		CancelBtn.addActionListener(listener);
		
		addCustomerD.setVisible(true);
	}
	/* 추가 함수 */
	public void AddNewCustomerDB() {
		Connection conn;
		String sql = "INSERT INTO CUSTOMER VALUES(?,?,?,?,to_date(?,'yyyymmdd'),null,'NEW',0,0)";
		PreparedStatement psmt = null;
		
		new DB_Connection();
		conn = DB_Connection.conn;
		try {
			psmt = conn.prepareStatement(sql);
			for(int i=0;i<5;i++) psmt.setString(i+1, Values[i]);	// 쿼리문 셋팅
			
			psmt.executeUpdate();			// 쿼리문 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DB_Connection.Disconnection(psmt);
		DB_Connection.Disconnection();
		
		JOptionPane.showMessageDialog(null, "추가되었습니다.","추가 완료"
				, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/* 리스너 함수 구현 */
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton pressedBtn = (JButton)e.getSource();
			// 추가 버튼을 누르면 //
			if(pressedBtn.getText().equals("추가")) {	 
				
				if(tf[0].getText().isEmpty() || tf[1].getText().isEmpty()
						|| tf[3].getText().isEmpty() || Birth.isNull() ) {	// 필수 입력 요소가 누락된 경우
					JOptionPane.showMessageDialog(null, "필수 입력 정보를 확인하세요.","필수 입력 요소 누락"
							, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for(int i=0;i<4;i++) Values[i] = tf[i].getText();
				
				Values[4] = Birth.getDate();	// Date 값 받아온 후, Birth의 From은 null로 초기화
				
				System.out.println(Values[4]);
				
				for(int i=0;i<4;i++)	tf[i].setText(null);	// 초기화
				
				AddNewCustomerDB();
				addCustomerD.setVisible(false);
				new All_Customer_info(DialogOwner);
			}
			 // 취소 버튼을 누르면 //
			else if(pressedBtn.getText().equals("취소")) {
				addCustomerD.setVisible(false);
			}
			
		}
	}
	

}
