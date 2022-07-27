import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
/*
 * -주요 컴포넌트 -
 * userNamelbl: 해당 코디셋을 작성한 유저의 이름이 들어가는 라벨
 * backBtn : [뒤로가기]버튼 . 메인페이지로 돌아간다.
 * likeBtn : [좋아요] 버튼. 좋아요 테이블에 db가 전달되어야 함.
 * getBtn[0-4] : [담기] 버튼 -> 누르면 해당 옷 db가 현재 사용자의 장바구니 db에 담긴다. 
 * 
 * */
public class LookCodiset extends JDialog {
	int count;
	
	
	//MainFrame3를 owner 로 쓴다. 
	public LookCodiset(JFrame owner){
		super(owner,true);
		//제일 큰 프레임
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		////////////top
		JPanel topPnl = new JPanel();
		topPnl.setBounds(0, 5, 834, 35);
		topPnl.setLayout(null);//좌표값으로 위치를 정해주려면 기본 layout설정을 변경해주어야 하기 때문에 setLayout(null로 지정하여 주어야 한다)
		
		//유저이름 들어가는 라벨
		JLabel userNamelbl = new JLabel("유저이름");
		userNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		userNamelbl.setLocation(543, 3);
		userNamelbl.setSize(100, 25);
		JLabel lbl = new JLabel("님의 코디셋");
		lbl.setBounds(672, 5, 100, 25);
		lbl.setHorizontalAlignment(SwingConstants.LEFT);
		//이미지 아이콘
		//[뒤로가기] 버튼
		ImageIcon back = new ImageIcon(".\\img\\back.png");//이미지 경로지정
		JButton backBtn = new JButton(back);
		backBtn.setBounds(24, 2, 38, 27);
		backBtn.setPreferredSize(new Dimension(32, 32));
		backBtn.setContentAreaFilled(false);//버튼 안 색 채우기 안함
		backBtn.setBorderPainted(false);//버튼 외각선 안 보이게
		backBtn.setFocusPainted(false);// 버튼 눌렀을 때 외각선 안 보이게
		
		//[좋아요]버튼
		ImageIcon like = new ImageIcon(".\\img\\likeImg.png");//이미지 경로지정
		ImageIcon like2 = new ImageIcon(".\\img\\like2.png");//이미지 경로지정
		JButton likeBtn = new JButton(like);
		likeBtn.setBounds(784, 2, 38, 27);
		likeBtn.setPreferredSize(new Dimension(32, 32));
		likeBtn.setContentAreaFilled(false);//버튼 안 색 채우기 안함
		likeBtn.setBorderPainted(false);//버튼 외각선 안 보이게
		likeBtn.setFocusPainted(false);// 버튼 눌렀을 때 외각선 안 보이게
		count =0;
		likeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("좋아요 버튼 눌렀음");
				++count;
				if (count%2==0) {
					likeBtn.setIcon(like);
		            System.out.println("좋아요 취소 -> 좋아요 db에 데이터 삭제");
		        }else {
		        	System.out.println("좋아요 버튼 클릭 -> 좋아요 db에 데이터 삽입");
		        	likeBtn.setIcon(like2);
		        }
			}
		});
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setVisible(true);
				dispose();
			
				System.out.println("뒤로가기 버튼 눌렀음");
			}
		});
		
		topPnl.add(backBtn);
		topPnl.add(userNamelbl);
		topPnl.add(lbl);
		topPnl.add(likeBtn);
		/////////////
		
		
		
		// middle
		JPanel middlePnl = new JPanel();
		middlePnl.setBounds(0, 40, 834, 737);
		middlePnl.setLayout(new GridLayout(0, 2));// 세로로 두 개로 나눔
		//중간-왼쪽
		JPanel leftPnlCodiset = new JPanel();
		leftPnlCodiset.setBorder(new LineBorder(Color.pink,2));//영역 볼려고 테두리 설정해두었음
		middlePnl.add(leftPnlCodiset);
		
		//중간- 오른쪽
		JPanel rightPnl = new JPanel();
		//5개의 구성요소가 있어야 함. 
		//배열에다 구성요소 하나하나 담는다. 
		List<JPanel> component = new ArrayList<JPanel>();
		List<JLabel> photo = new ArrayList<JLabel>();
		List<JLabel> text = new ArrayList<JLabel>();
		List<JButton> getBtn = new ArrayList<JButton>();
		
		
		for(int i =0; i<5;i++) {
			JPanel component2 = new JPanel();
			component.add(component2);
			JLabel photo2 = new JLabel("/사진/ ");
			photo.add(photo2);
			JLabel text2 = new JLabel("정보가 들어가는 부분");
			text.add(text2);
			JButton getBtn2 = new JButton("담기");
			getBtn.add(getBtn2);
			getBtn.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("눌렀지롱~~");
					
				}
			});
			component2.add(photo2);
			component2.add(text2);
			component2.add(getBtn2);
			component2.setBorder(new LineBorder(Color.CYAN,2));
			component.add(component2);
			rightPnl.add(component2);
		}
		rightPnl.setLayout(new GridLayout(5,0));
		
		middlePnl.add(leftPnlCodiset);
		middlePnl.add(rightPnl);
		/////
		
		
		
		
		
		//구성요소 더하기
		pnl.add(topPnl);
		pnl.add(middlePnl);
		
		add(pnl);
		BoxLayout allLayout = new BoxLayout(pnl,BoxLayout.Y_AXIS);
		setSize(850,850);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
	}
}

