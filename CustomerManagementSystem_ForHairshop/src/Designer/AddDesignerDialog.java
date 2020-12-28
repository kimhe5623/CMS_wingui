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
	
	private JButton AddBtn = new JButton("�߰�");
	private JButton CancelBtn = new JButton("���");
	private String tfName[] = { "*���ھƳ� ���̵�", "*�̸�", "*����ó", "*����" };
	private int width = 400, height = 300;
	
	String Values[] = new String[5];
	
	public AddDesignerDialog(JFrame frame, String title) {
		DialogOwner = frame;
		addDesignerD = new JDialog(DialogOwner);	// frame: ���̾�α� ����
		addDesignerD.setTitle(title);	// title: ���̾�α� title
		addDesignerD.setBounds(600,300,width,height);
		tf[0] = new JTextField(6);
		tf[1] = new JTextField(10);
		tf[2] = new JTextField(15);
		tf[3] = new JTextField(8);

		
		// ���̾�α� ������
		Box BoxForTf[] = new Box[4];
		for(int i=0;i<4;i++) BoxForTf[i] = Box.createVerticalBox();		
		for(int i=0;i<4;i++) BoxForTf[i].add(new JLabel(tfName[i]));
		for(int i=0;i<4;i++) BoxForTf[i].add(tf[i]);
		
		JPanel insertPanel = new JPanel();	// �Է� �г�
		insertPanel.setLayout(new FlowLayout());
		for(int i =0;i<4;i++) insertPanel.add(BoxForTf[i]);
		
		JPanel BtnPanel = new JPanel();	// ��ư �г�
		BtnPanel.setLayout(new FlowLayout());
		BtnPanel.add(AddBtn);
		BtnPanel.add(CancelBtn);
		
		addDesignerD.setLayout(new BorderLayout());
		addDesignerD.add(insertPanel, BorderLayout.CENTER);	// �Է� �г� �߰�
		addDesignerD.add(BtnPanel, BorderLayout.SOUTH);	// ��ư �г� �߰�
		addDesignerD.add(new JLabel("* �� ǥ�õ� ���� �ʼ� �Է� ���� �Դϴ�."), BorderLayout.NORTH);	//
		
		MyActionListener listener = new MyActionListener();
		AddBtn.addActionListener(listener);
		CancelBtn.addActionListener(listener);
		
		addDesignerD.setVisible(true);

		
	}
	/* �߰� �Լ� */
	public void AddNewDesignerDB() {
		Connection conn;
		String sql = "INSERT INTO Designer VALUES(?,?,?,?)";
		PreparedStatement psmt = null;
		
		new DB_Connection();
		conn = DB_Connection.conn;
		try {
			psmt = conn.prepareStatement(sql);
			for(int i=0;i<4;i++) psmt.setString(i+1, Values[i]);	// ������ ����
			
			psmt.executeUpdate();			// ������ ����
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DB_Connection.Disconnection(psmt);
		DB_Connection.Disconnection();
		
		JOptionPane.showMessageDialog(null, "�߰��Ǿ����ϴ�.","�߰� �Ϸ�"
				, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/* ������ �Լ� ���� */
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton pressedBtn = (JButton)e.getSource();
			// �߰� ��ư�� ������ //
			if(pressedBtn.getText().equals("�߰�")) {	 
				
				if(tf[0].getText().isEmpty() || tf[1].getText().isEmpty()
						|| tf[2].getText().isEmpty() || tf[3].getText().isEmpty()) {	// �ʼ� �Է� ��Ұ� ������ ���
					JOptionPane.showMessageDialog(null, "�ʼ� �Է� ������ Ȯ���ϼ���.","�ʼ� �Է� ��� ����"
							, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for(int i=0;i<4;i++) Values[i] = tf[i].getText();
				
				//System.out.println(Values[4]);
				
				for(int i=0;i<4;i++)	tf[i].setText(null);	// �ʱ�ȭ
				
				AddNewDesignerDB();
				addDesignerD.setVisible(false);
				new allDesigner(DialogOwner);
			}
			 // ��� ��ư�� ������ //
			else if(pressedBtn.getText().equals("���")) {
				addDesignerD.setVisible(false);
			}
			
		}
	}
}
