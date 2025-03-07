package Designer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import All_Customer.AddCustomerDialog;
import All_Customer.All_Customer_info;
import All_Customer.DeleteCustomer;
import All_Customer.DetailsOfCustomer;
import All_Customer.personal_Service_history;
import MainSource.DB_Connection;

import java.sql.*;

public class allDesigner {

	JFrame allDesignerP = null;
	private int width = 1000, height = 500;
	private Font font1 = new Font("monospace", Font.BOLD, 15);
	private Font font2 = new Font("monospace", Font.BOLD, 20);
	private Object Designer[][], WorkingDay[][];
	private int D_size, W_size;
	private String DesignerColumns[] = { "아이디", "이름", "연락처", "직급" };
	private String WorkingColumns[] = { "아이디", "이름", "근무 요일", "근무 시간" };
	private JTable designerTab, workingTab;

	/* 생성자 about Designer */
	public allDesigner(JFrame BeforePage) {
		allDesignerP = new JFrame("디자이너 조회");
		BeforePage.setVisible(false);
		allDesignerP.setBounds(300, 200, width, height);
		allDesignerP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyactionListener listener = new MyactionListener();

		/* 화면 디자인 */
		// 메뉴버튼 -> TopPanel
		JPanel TopPanel = new JPanel();
		TopPanel.setLayout(new BorderLayout());
		JButton menuBtn = new JButton("메뉴");
		menuBtn.setFont(font2);
		menuBtn.addActionListener(listener);
		TopPanel.add(menuBtn, BorderLayout.WEST);

		// 근무자 조회 버튼 -> panel1_3
		JPanel Day = new JPanel();
		JButton day[] = new JButton[7];
		day[0] = new JButton("월");
		day[4] = new JButton("금");
		day[1] = new JButton("화");
		day[5] = new JButton("토");
		day[2] = new JButton("수");
		day[6] = new JButton("일");
		day[3] = new JButton("목");

		for (int i = 0; i < 7; i++) {
			day[i].addActionListener(listener);
			Day.add(day[i]);
		}

		JButton allDesigner = new JButton("전체 조회");
		Day.add(allDesigner);

		TopPanel.add(Day, BorderLayout.EAST);

		Search();
		// 테이블 -> ScrollPane
		DefaultTableModel defaultDesignerM = new DefaultTableModel(Designer, DesignerColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		designerTab = new JTable(defaultDesignerM);
		JScrollPane jScrollPane = new JScrollPane(designerTab);
		designerTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택

		MyMouseAdapter mouselistener = new MyMouseAdapter();
		designerTab.addMouseListener(mouselistener);

		// 추가 & 삭제 버튼 -> panel2
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(width, height / 10));

		JButton insertBtn = new JButton("디자이너 추가");
		insertBtn.setFont(font1);
		insertBtn.addActionListener(listener);
		bottomPanel.add(insertBtn);

		JButton deleteBtn = new JButton("디자이너 삭제");
		deleteBtn.setFont(font1);
		deleteBtn.addActionListener(listener);
		bottomPanel.add(deleteBtn);

		Container Contentpane = allDesignerP.getContentPane();
		// panel1, jScrollPane , panel2 -> Frame
		Contentpane.add(TopPanel, BorderLayout.NORTH);
		Contentpane.add(jScrollPane, BorderLayout.CENTER);
		Contentpane.add(bottomPanel, BorderLayout.SOUTH);

		allDesignerP.setVisible(true);
	}

	/* 생성자 about WorkingDay */
	public allDesigner(JFrame BeforePage, String whatDay) {
		allDesignerP = new JFrame("요일 별 근무자 조회");
		BeforePage.setVisible(false);
		allDesignerP.setBounds(300, 200, width, height);
		allDesignerP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyactionListener listener = new MyactionListener();

		/* 화면 디자인 */
		// 메뉴버튼 -> TopPanel
		JPanel TopPanel = new JPanel();
		TopPanel.setLayout(new BorderLayout());
		JButton menuBtn = new JButton("메뉴");
		menuBtn.setFont(font2);
		menuBtn.addActionListener(listener);
		TopPanel.add(menuBtn, BorderLayout.WEST);

		// 근무자 조회 버튼 -> panel1_3
		JPanel Day = new JPanel();
		JButton day[] = new JButton[7];
		day[0] = new JButton("월");
		day[4] = new JButton("금");
		day[1] = new JButton("화");
		day[5] = new JButton("토");
		day[2] = new JButton("수");
		day[6] = new JButton("일");
		day[3] = new JButton("목");

		for (int i = 0; i < 7; i++) {
			day[i].addActionListener(listener);
			Day.add(day[i]);
		}

		JButton allDesigner = new JButton("전체 조회");
		Day.add(allDesigner);
		allDesigner.addActionListener(listener);

		TopPanel.add(Day, BorderLayout.EAST);
		JLabel wd = null;
		TopPanel.add(wd = new JLabel("  " + whatDay + "요일 근무 디자이너"), BorderLayout.CENTER);
		wd.setFont(font2);

		Search(whatDay);
		// 테이블 -> ScrollPane
		DefaultTableModel defaultWorkingM = new DefaultTableModel(WorkingDay, WorkingColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		workingTab = new JTable(defaultWorkingM);
		JScrollPane jScrollPane = new JScrollPane(workingTab);
		workingTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택

		MyMouseAdapter mouselistener = new MyMouseAdapter();
		workingTab.addMouseListener(mouselistener);

		// 추가 & 삭제 버튼 -> panel2
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(width, height / 10));

		JButton insertBtn = new JButton("근무자 추가");
		insertBtn.setFont(font1);
		insertBtn.addActionListener(listener);
		bottomPanel.add(insertBtn);

		JButton deleteBtn = new JButton("근무자 삭제");
		deleteBtn.setFont(font1);
		deleteBtn.addActionListener(listener);
		bottomPanel.add(deleteBtn);

		Container Contentpane = allDesignerP.getContentPane();
		// panel1, jScrollPane , panel2 -> Frame
		Contentpane.add(TopPanel, BorderLayout.NORTH);
		Contentpane.add(jScrollPane, BorderLayout.CENTER);
		Contentpane.add(bottomPanel, BorderLayout.SOUTH);

		allDesignerP.setVisible(true);

	}

	private void Search() {
		String Query = "SELECT Did, Dname, Dphone, Dpos FROM DESIGNER " + "ORDER BY Did asc"; // [0] 서비스 history

		// DB연결
		Connection conn = null;
		Statement stmt = null;

		conn = new DB_Connection().conn;
		try {

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query);

			Designer_info di = new Designer_info();
			Vector<Object[]> D = new Vector<Object[]>();
			while (rs.next()) {
				int i = 1;
				di.Did = rs.getString(i++);
				di.Dname = rs.getString(i++);
				di.Dphone = rs.getString(i++);
				di.Dpos = rs.getString(i++);
				D.add(di.toArray()); // 한 행씩 벡터에 담기
				System.out.println(di.Did + " " + di.Dname + " " + di.Dphone + " " + di.Dpos);
			}
			
			Designer = new Object[D.size()][];
			for (int i = 0; i < D.size(); i++)
				Designer[i] = D.get(i);

			D.removeAllElements();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	private void Search(String d) {
		String Query = "SELECT D.Did, D.Dname, D.Dphone, D.Dpos, W.Dtime FROM DESIGNER D, DESIGNER_WORKING_DAY W "
						+ "WHERE W.Dday = '"+ d +"' AND W.Did = D.Did ORDER BY Did asc"; // [0] 서비스 history

		// DB연결
		Connection conn = null;
		Statement stmt = null;

		conn = new DB_Connection().conn;
		try {

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query);

			Working_info wi = new Working_info();
			Vector<Object[]> W = new Vector<Object[]>();
			while (rs.next()) {
				int i = 1;
				wi.Did = rs.getString(i++);
				wi.Dname = rs.getString(i++);
				wi.Dphone = rs.getString(i++);
				wi.Dpos = rs.getString(i++);
				wi.Dtime = rs.getString(i++);
				W.add(wi.toArray()); // 한 행씩 벡터에 담기
				System.out.println(wi.Did + " " + wi.Dname + " " + wi.Dphone + " " + wi.Dpos+ " " + wi.Dtime);
			}
			WorkingDay = new Object[W.size()][];
			for (int i = 0; i < W.size(); i++)
				WorkingDay[i] = W.get(i);

			W.removeAllElements();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/* 리스너 함수 구현 */
	private class MyactionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String BtnStr[] = { "메뉴", "월", "화", "수", "목", "금", "토", "일", "디자이너 추가", "디자이너 삭제", "전체 조회" };
			JButton pressed = (JButton) e.getSource();
			String p = pressed.getText();

			if (p.equals(BtnStr[0])) // "메뉴"클릭
				new CMS_MainPage.MainPage(allDesignerP);

			if (p.equals(BtnStr[1]) || p.equals(BtnStr[2]) || p.equals(BtnStr[3]) || p.equals(BtnStr[4])
					|| p.equals(BtnStr[5]) || p.equals(BtnStr[6]) || p.equals(BtnStr[7])) { // "요일 중 하나" 클릭

				new allDesigner(allDesignerP, p);
			}

			if (pressed.getText().equals(BtnStr[8])) { // "추가" 클릭
				 new AddDesignerDialog(allDesignerP, "디자이너 정보 추가");
			}

			if (pressed.getText().equals(BtnStr[9])) { // "삭제" 클릭
				 new DeleteDesigner(allDesignerP, Designer, D_size);
			}
			if (pressed.getText().equals(BtnStr[10])) { // "전체 조회" 클릭
				new allDesigner(allDesignerP);
			}

		}
	}

	private class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { // 고객 정보 더블클릭
				int SelectedRow = designerTab.getSelectedRow();
				// new DetailsOfCustomer(allDesignerP, O[SelectedRow]);

			}
		}
	}
}
