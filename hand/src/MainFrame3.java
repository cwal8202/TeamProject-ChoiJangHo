import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.security.auth.login.LoginContext;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class MainFrame3 extends JFrame{
	String user_id;
	JButton loginBtn;
	
	public String what;
	public JLabel lbl1;
	public JLabel lbl2;
	public JLabel lbl3;
	int count;
	DAO_heart daoheart;

	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public JLabel getLbl1() {
		return lbl1;
	}


	public void setLbl1(JLabel lbl1) {
		this.lbl1 = lbl1;
	}

	//생성자 
	public MainFrame3() {
		super("메인창");
		//제일 큰 프레임
		JPanel pnl = new JPanel();
		
		//윗부분
		JPanel topPnl = new JPanel();
		JLabel lbl = new JLabel("메인화면 ");
		loginBtn = new JButton("로그인");
		JButton myBtn  = new JButton("내 정보");
		topPnl.add(lbl);
		topPnl.add(loginBtn);
		topPnl.add(myBtn);
		BoxLayout bltop = new BoxLayout(topPnl, BoxLayout.X_AXIS);// 입력패널 세로배치
		topPnl.setLayout(bltop);
		
		///////////////
		
		//중간부분
		JPanel middlePnl = new JPanel();//중간 전체 감싸는 패널
		//위 - 추천 게시글 3개 
		JPanel recommendPnl  = new JPanel();
		
		lbl1 = new JLabel("첫 번째 추천");
		lbl2 = new JLabel("두 번째 추천");
		lbl3 = new JLabel("세 번째 추천");
//		lbl1.setContentAreaFilled(false);버튼에만 가능
		lbl1.setBorder(new LineBorder(Color.CYAN,2));
		lbl2.setBorder(new LineBorder(Color.CYAN,2));
		lbl3.setBorder(new LineBorder(Color.CYAN,2));
		recommendPnl.add(lbl1);
		recommendPnl.add(lbl2);
		recommendPnl.add(lbl3);
		recommendPnl.setLayout(new GridLayout(0,3));
		
		MouseListener pageLook = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("글 누름~~");
				LookCodiset lookCodiset = new LookCodiset(MainFrame3.this);
				lookCodiset.setVisible(true);
			}
		}; 
		lbl1.addMouseListener(pageLook);	
		lbl2.addMouseListener(pageLook);	
		lbl3.addMouseListener(pageLook);	
		
		//아래 - 탭으로 넘기는  섹션
		JTabbedPane  tapPnl = new JTabbedPane();
		
		//top3 부분
		JPanel top3Pnl = new JPanel();
		top3Pnl.setBackground(Color.PINK);
		//top3를 감싸는 패널(사진,버튼을 한 셋트로 묶을 패널)
		JPanel pnltoplbl1 = new JPanel();
		JPanel pnltoplbl2 = new JPanel();
		JPanel pnltoplbl3 = new JPanel();
		pnltoplbl1.setLayout(null);//패널에 절대값으로 모양 지정할 거라서 레이아웃 널로 셋팅함. 
		pnltoplbl2.setLayout(null);
		pnltoplbl3.setLayout(null);
		//그림이 들어갈 라벨
		JLabel toplbl1 = new JLabel("top1");
		toplbl1.setBounds(0, 0, 276, 315);
		toplbl1.setBorder(new LineBorder(Color.CYAN,2));
		JLabel toplbl2 = new JLabel("top2");
		toplbl2.setBounds(0, 0, 276, 315);
		toplbl2.setBorder(new LineBorder(Color.CYAN,2));
		JLabel toplbl3 = new JLabel("top3");
		toplbl3.setBounds(0, 0, 276, 315);
		toplbl3.setBorder(new LineBorder(Color.CYAN,2));
		
		//[좋아요]버튼 이미지
		ImageIcon like = new ImageIcon(".\\img\\likeImg.png");//이미지 경로지정
		ImageIcon like2 = new ImageIcon(".\\img\\like2.png");//이미지 경로지정
		//좋아요 버튼 전부 생성 
		JButton likeBtn1= new JButton(like);
		JButton likeBtn2= new JButton(like);
		JButton likeBtn3= new JButton(like);
		settingBtn(likeBtn1);
		settingBtn(likeBtn2);
		settingBtn(likeBtn3);
		
		count =0;
		daoheart= new DAO_heart();
		
		
		
		//좋아요 버튼 두 번 누루면 취소되게 끔 
		likeBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++count;
				if (count%2==0) {
		            System.out.println("좋아요 취소 -> 좋아요 db에 데이터 삭제");
		            likeBtn1.setIcon(like);
		            try {
						daoheart.delete("lossryeong",1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		            
		        }else {
		        	System.out.println("좋아요 버튼 클릭 -> 좋아요 db에 데이터 삽입");
		        	likeBtn1.setIcon(like2);
		        	try {
						daoheart.create(user_id,1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        }
			}
		});
		likeBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++count;
				if (count%2==0) {
					likeBtn2.setIcon(like);
		            System.out.println("좋아요 취소 -> 좋아요 db에 데이터 삭제");
		        }else {
		        	System.out.println("좋아요 버튼 클릭 -> 좋아요 db에 데이터 삽입");
		        	likeBtn2.setIcon(like2);
		        }
			}
		});
		likeBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++count;
				if (count%2==0) {
					likeBtn3.setIcon(like);
		            System.out.println("좋아요 취소 -> 좋아요 db에 데이터 삭제");
		        }else {
		        	System.out.println("좋아요 버튼 클릭 -> 좋아요 db에 데이터 삽입");
		        	likeBtn3.setIcon(like2);
		        }
			}
		});
	
		
		
		//패널에 구성요소 더하기 
		pnltoplbl1.add(toplbl1);
		pnltoplbl1.add(likeBtn1);
		pnltoplbl2.add(toplbl2);
		pnltoplbl3.add(toplbl3);
		pnltoplbl2.add(likeBtn2);
		pnltoplbl3.add(likeBtn3);
		
		
		//패널에 비율로 3개 똑같이 맞춰 넣기. 
		top3Pnl.setLayout(new GridLayout(0,3));
		top3Pnl.add(pnltoplbl1);
		top3Pnl.add(pnltoplbl2);
		top3Pnl.add(pnltoplbl3);
		//탭에 top3 넣기
		tapPnl.add(top3Pnl);
		tapPnl.add("TOP3",top3Pnl);
		
		///////////
		//신상 부분 제일 큰 패널
		JPanel pnlNew = new JPanel();
		//3개 감쌀 패널(사진,버튼을 한 셋트로 묶을 패널)
		JPanel pnllblnew1 = new JPanel();
		JPanel pnllblnew2 = new JPanel();
		JPanel pnllblnew3 = new JPanel();
		pnllblnew1.setLayout(null);//패널에 절대값으로 모양 지정할 거라서 레이아웃 널로 셋팅함. 
		pnllblnew2.setLayout(null);
		pnllblnew3.setLayout(null);
		
		//[담기]버튼
		JButton getBtn1 = new JButton("담기");
		getBtn1.setBounds(109, 330, 60, 23);
		JButton getBtn2 = new JButton("담기");
		getBtn2.setBounds(109, 330, 60, 23);
		JButton getBtn3 = new JButton("담기");
		getBtn3.setBounds(109, 330, 60, 23);
		
		//사진 들어갈 라벨
		JLabel lblnew1 = new JLabel("신상1");
		lblnew1.setBounds(0, 0, 276, 315);
		lblnew1.setBorder(new LineBorder(Color.CYAN,2));
		JLabel lblnew2 = new JLabel("신상2");
		lblnew2.setBounds(0, 0, 276, 315);
		lblnew2.setBorder(new LineBorder(Color.CYAN,2));
		JLabel lblnew3 = new JLabel("신상3");
		lblnew3.setBounds(0, 0, 276, 315);
		lblnew3.setBorder(new LineBorder(Color.CYAN,2));
		
		//사진,버튼 하나씩 더해준다. 
		pnllblnew1.add(lblnew1);
		pnllblnew1.add(getBtn1);
		pnllblnew2.add(lblnew2);
		pnllblnew2.add(getBtn2);
		pnllblnew3.add(lblnew3);
		pnllblnew3.add(getBtn3);
		
		//3개의 구성요소를 비율에 맞춰 셋팅하기. 
		pnlNew.add(pnllblnew1);
		pnlNew.add(pnllblnew2);
		pnlNew.add(pnllblnew3);
		pnlNew.setLayout(new GridLayout(0,3));
		
		//tap에다가 더해준다. 
		tapPnl.add(pnlNew);
		tapPnl.add("신제품",pnlNew);
		///////////TAP부분 끝
		
		//중간부분을 가로로 2개 나눈다. 
		middlePnl.add(recommendPnl);
		middlePnl.add(tapPnl);
		middlePnl.setLayout(new GridLayout(2,0));
		////////중간 부분 끝
		
	
		
		
		// bottom
		JPanel bottomPnl = new JPanel();//bottom전체 감싸는 큰 패널
		JButton writeBtn = new JButton("글쓰기");
		bottomPnl.add(writeBtn);
		BoxLayout blbottom = new BoxLayout(bottomPnl, BoxLayout.Y_AXIS);// 입력패널 세로배치
		bottomPnl.setLayout(blbottom);
		//
		
	
		
		
		pnl.add(topPnl);
		pnl.add(middlePnl);
		pnl.add(bottomPnl);
		BoxLayout allLayout = new BoxLayout(pnl,BoxLayout.Y_AXIS);
		pnl.setLayout(allLayout);
		
		
		getContentPane().add(pnl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850,850);
	
	}
	
	
	public void settingBtn(JButton btn) {
		btn.setBounds(102, 325, 69, 28);
		btn.setPreferredSize(new Dimension(32, 32));
		btn.setContentAreaFilled(false);//버튼 안 색 채우기 안함
		btn.setBorderPainted(false);//버튼 외각선 안 보이게
		btn.setFocusPainted(false);// 버튼 눌렀을 때 외각선 안 보이게
	}
	
	public static void main(String[] args) {
		new MainFrame3().setVisible(true);
	}
}