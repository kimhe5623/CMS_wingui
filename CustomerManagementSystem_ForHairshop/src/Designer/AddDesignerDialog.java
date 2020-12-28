package Designer;

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

public class AddDesignerDialog {
	
	JDialog addDesignerD = null;
	JFrame DialogOwner = null;
	private JTextField tf[] = new JTextField[10];
	
	private JButton AddBtn = new JButton("추가");
	private JButton CancelBtn = new JButton("취소");
	private String tfName[] = { "*디자아너 아이디", "*이름", "*연락처", "*직급" };
	private int width = 400, height = 300;
	
	String Values[] = new String[5];
	
	public AddDesignerDialog(JFrame frame, String title) {
		DialogOwner = frame;
		addDesignerD = new JDialog(DialogOwner);	// frame: 다이얼로그 주인
		addDesignerD.setTitle(title);	// title: 다이얼로그 title
		addDesignerD.setBounds(600,300,width,height);
		tf[0] = new JTextField(6);
		tf[1] = new JTextField(10);
		tf[2] = new JTextField(15);
		tf[3] = new JTextField(8);

		
		// 다이얼로그 디자인
		Box BoxForTf[] = new Box[4];
		for(int i=0;i<4;i++) BoxForTf[i] = Box.createVerticalBox();		
		for(int i=0;i<4;i++) BoxForTf[i].add(new JLabel(tfName[i]));
		for(int i=0;i<4;i++) BoxForTf[i].add(tf[i]);
		
		JPanel insertPanel = new JPanel();	// 입력 패널
		insertPanel.setLayout(new FlowLayout());
		for(int i =0;i<4;i++) insertPanel.add(BoxForTf[i]);
		
		JPanel BtnPanel = new JPanel();	// 버튼 패널
		BtnPanel.setLayout(new FlowLayout());
		BtnPanel.add(AddBtn);
		BtnPanel.add(CancelBtn);
		
		addDesignerD.setLayout(new BorderLayout());
		addDesignerD.add(insertPanel, BorderLayout.CENTER);	// 입력 패널 추가
		addDesignerD.add(BtnPanel, BorderLayout.SOUTH);	// 버튼 패널 추가
		addDesignerD.add(new JLabel("* 가 표시된 것은 필수 입력 정보 입니다."), BorderLayout.NORTH);	//
		
		MyActionListener listener = new MyActionListener();
		AddBtn.addActionListener(listener);
		CancelBtn.addActionListener(listener);
		
		addDesignerD.setVisible(true);

		
	}
	/* 추가 함수 */
	public void AddNewDesignerDB() {
		Connection conn;
		String sql = "INSERT INTO Designer VALUES(?,?,?,?)";
		PreparedStatement psmt = null;
		
		new DB_Connection();
		conn = DB_Connection.conn;
		try {
			psmt = conn.prepareStatement(sql);
			for(int i=0;i<4;i++) psmt.setString(i+1, Values[i]);	// 쿼리문 셋팅
			
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
						|| tf[2].getText().isEmpty() || tf[3].getText().isEmpty()) {	// 필수 입력 요소가 누락된 경우
					JOptionPane.showMessageDialog(null, "필수 입력 정보를 확인하세요.","필수 입력 요소 누락"
							, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for(int i=0;i<4;i++) Values[i] = tf[i].getText();
				
				//System.out.println(Values[4]);
				
				for(int i=0;i<4;i++)	tf[i].setText(null);	// 초기화
				
				AddNewDesignerDB();
				addDesignerD.setVisible(false);
				new allDesigner(DialogOwner);
			}
			 // 취소 버튼을 누르면 //
			else if(pressedBtn.getText().equals("취소")) {
				addDesignerD.setVisible(false);
			}
			
		}
	}
}
