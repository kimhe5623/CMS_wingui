/* 날짜 입력받는 Panel - 생일 입력받을 때 사용 */
package All_Customer;


import java.awt.FlowLayout;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class datePanel {

	public JPanel panel = new JPanel();
	public JTextField tf1, tf2, tf3;
	public String dateString = null;
	public Date To = null;
	
	// 디폴트 생성자
	public datePanel() {

		tf1 = new JTextField(5);
		tf2 = new JTextField(3);
		tf3 = new JTextField(3);

		panel.setLayout(new FlowLayout());
		panel.add(tf1);
		panel.add(new JLabel("년"));
		panel.add(tf2);
		panel.add(new JLabel("월"));
		panel.add(tf3);
		panel.add(new JLabel("일"));
		

	}
	
	// Date가 인자일 경우
	public datePanel(Date date) {
		
		System.out.println(date);
		String dateString = date.toString();
		String year = dateString.substring(0,4), month = dateString.substring(5,7), day = dateString.substring(8,10);
		
		tf1 = new JTextField(5);	tf1.setText(year.toString());
		tf2 = new JTextField(3);	tf2.setText(month.toString());
		tf3 = new JTextField(3);	tf3.setText(day.toString());

		panel.setLayout(new FlowLayout());
		panel.add(tf1);
		panel.add(new JLabel("년"));
		panel.add(tf2);
		panel.add(new JLabel("월"));
		panel.add(tf3);
		panel.add(new JLabel("일"));
		

	}

	public JPanel getPanel() {
		return panel;
	}
	public boolean isNull() {	// 입력한 값이 null인지 여부 확인
		if(tf1.getText().isEmpty() || tf2.getText().isEmpty()
				|| tf3.getText().isEmpty()) return true;
		else			 return false;
	}

	public String getDate() {
		
		dateString = tf1.getText() + tf2.getText() + tf3.getText();
		return dateString;	// 받아온 날짜를 Date로 리턴
		
	}
}