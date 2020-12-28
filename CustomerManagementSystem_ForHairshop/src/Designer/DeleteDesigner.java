package Designer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import MainSource.DB_Connection;

public class DeleteDesigner {
	
	private static final int BOOLEAN_COLUMN = 0;
	private Object rawData[][];
	private Font font1 = new Font("monospace", Font.BOLD, 15);
	private JFrame deleteDesignerPage = null;
	private int width = 1000, height = 500;
	private Object columnNames[] = { "삭제 선택", "아이디","이름", "연락처", "직급" };
	private Vector<Object> v = new Vector<Object>();
	
	
	/* 생성자 */
	public DeleteDesigner(JFrame BeforePage, Object O[][], int D_num) {
		BeforePage.setVisible(false);
		rawData = new Object[D_num][5];
		for (int i = 0; i < D_num; i++)
			rawData[i][0] = false;
		for (int i = 0; i < D_num; i++)
			for (int j = 1; j < 5; j++)
				rawData[i][j] = O[i][j - 1];
		// rawData + 체크박스

		deleteDesignerPage = new JFrame("디자이너 정보삭제");
		deleteDesignerPage.setBounds(300, 200, width, height);
		deleteDesignerPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyactionListener listener = new MyactionListener();

		// 테이블 -> ScrollPane
		DefaultTableModel defaultTabM = new DefaultTableModel(rawData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == BOOLEAN_COLUMN)
					return true;
				else
					return false;
			}

			public Class getColumnClass(int column) {
				return column == BOOLEAN_COLUMN ? Boolean.class : String.class;
			}
		};
		JTable jTable = new JTable(defaultTabM);
		JScrollPane jScrollPane = new JScrollPane(jTable);

		jTable.getModel().addTableModelListener(new CheckBoxModelListener());

		// 삭제 & 취소 버튼 -> BtnPanel
		JPanel BtnPanel = new JPanel();
		BtnPanel.setPreferredSize(new Dimension(width, height / 10));

		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setFont(font1);
		BtnPanel.add(deleteBtn);

		JButton cancelBtn = new JButton("취소");
		cancelBtn.setFont(font1);
		BtnPanel.add(cancelBtn);

		Container Contentpane = deleteDesignerPage.getContentPane();
		// jScrollPane , BtnPanel -> Frame
		Contentpane.add(jScrollPane, BorderLayout.CENTER);
		Contentpane.add(BtnPanel, BorderLayout.SOUTH);

		deleteBtn.addActionListener(listener);
		cancelBtn.addActionListener(listener);

		deleteDesignerPage.setVisible(true);
	}
	
	/* 리스너 함수 구현 */
	private class MyactionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String BtnStr[] = { "삭제", "취소" };
			JButton pressed = (JButton) e.getSource();

			System.out.println(pressed.getText());

			if (pressed.getText().equals(BtnStr[0])) { // "삭제"클릭
				// 쿼리 실행
				Connection conn = null;
				PreparedStatement psmt = null;
				for (int i = 0; i < v.size(); i++) { // 삭제하기 위해 선택된 디자이너의 수만큼 반복

					String sql = "DELETE FROM Designer WHERE Did = ?";

					psmt = null;

					new DB_Connection();
					conn = DB_Connection.conn;
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, (String) v.get(i)); // 쿼리문 셋팅

						psmt.executeUpdate(); // 쿼리문 실행
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				DB_Connection.Disconnection(psmt);
				DB_Connection.Disconnection();
				
				new allDesigner(deleteDesignerPage); // 삭제 완료 후 조회 페이지
				JOptionPane.showMessageDialog(null, "삭제되었습니다.","삭제 완료"
						, JOptionPane.INFORMATION_MESSAGE);
			}

			else if (pressed.getText().equals(BtnStr[1])) // "취소"클릭
				new allDesigner(deleteDesignerPage);

		}
	}
	// 체크박스 리스너
	private class CheckBoxModelListener implements TableModelListener {
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (column == BOOLEAN_COLUMN) {
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(column);
				Boolean checked = (Boolean) model.getValueAt(row, column);
				if (checked) {
					v.add(model.getValueAt(row, column + 1));
				} else {
					v.remove(model.getValueAt(row, column + 1));
				}
			}
		}
	}
}
