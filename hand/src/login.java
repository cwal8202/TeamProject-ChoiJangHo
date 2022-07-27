import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.rmi.server.UID;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class login extends JDialog {
	MainFrame3 mainPage = new MainFrame3();
	
	private UserDaoImpl uDI = new UserDaoImpl();
	private boolean loginCheck;
	private String loginUser_id;
	
	
	
	public login(MainFrame3 mainPage) {
		super();
		this.mainPage = mainPage;
	}

	public String getLoginUser_id() {
		return loginUser_id;
	}

	public void setLoginUser_id(String loginUser_id) {
		this.loginUser_id = loginUser_id;
	}

	public UserDaoImpl getuDI() {
		return uDI;
	}

	public void setuDI(UserDaoImpl uDI) {
		this.uDI = uDI;
	}

	public boolean isLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(boolean loginCheck) {
		this.loginCheck = loginCheck;
	}

	public login() {
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel id_lbl = new JLabel("아이디");
		id_lbl.setBounds(57, 76, 57, 34);
		getContentPane().add(id_lbl);
		
		JLabel pw_lbl = new JLabel("비밀번호");
		pw_lbl.setBounds(57, 120, 57, 34);
		getContentPane().add(pw_lbl);
		
		
////////////////////////////////////텍스트필드/////////////////////////////////////////////////		
		
		JTextField id_tf = new JTextField("아이디");
		id_tf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (id_tf.getText().equals("")) {
					id_tf.setText("아이디");
				}	
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (id_tf.getText().equals("아이디")) {
					id_tf.setText("");
				}
			}
		});
		
		id_tf.setBounds(142, 83, 116, 21);
		getContentPane().add(id_tf);
		id_tf.setColumns(10);
		
		JTextField pw_tf = new JTextField("비밀번호");
		pw_tf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (pw_tf.getText().equals("")) {
					pw_tf.setText("비밀번호");
				}	
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (pw_tf.getText().equals("비밀번호")) {
					pw_tf.setText("");
				}
			}
		});
		
		pw_tf.setColumns(10);
		pw_tf.setBounds(142, 127, 116, 21);
		getContentPane().add(pw_tf);
		
		JButton login_button = new JButton("로그인");
		login_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					System.out.println("아이디와 비밀번호 확인 들어갑니다~");
					try {
						if (id_tf.getText().equals("*MIN*") && pw_tf.getText().equals("java")) {
							System.out.println("관리자 창으로 갑니다 + 여기에 관리자창 여는 코드 쓰세요!");
							loginCheck = true;
							dispose();
						} else {
							if (uDI.login_read(id_tf.getText(), pw_tf.getText()) != null) {
								System.out.println("로그인 되었습니다");
								System.out.println("메인창으로~! + 여기에 메인창 여는 코드 쓰세요!");
								
								mainPage.user_id = id_tf.getText();	// mainpage에 아이디 값 넘겨줌
								mainPage.setVisible(true);
								dispose();
								
							} else {
								System.out.println("아이디 또는 비밀번호가 틀렸습니다");
							}
						} 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		});
		login_button.setBounds(60, 212, 97, 23);
		getContentPane().add(login_button);
		
		JButton signUp_button = new JButton("회원가입");
		signUp_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new signUp().setVisible(true);
			}
		});
		
		mainPage.loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new login().setVisible(true);				
			}
		});
		
		signUp_button.setBounds(203, 212, 97, 23);
		getContentPane().add(signUp_button);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new login().mainPage.setVisible(true);
	}
}
