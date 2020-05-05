package rhythm_game_6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RhythmGame extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/Animal Title Image.png")).getImage();
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/Animal Game Image.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);

	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;	// 곡 선택 화면

	public RhythmGame() {
		setUndecorated(true); // 기본적으로 존재하는 메뉴바를 보이지 않게 함.
		setTitle("Rhythm Game"); // 창 제목 설정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 창 사이즈 조절
		setResizable(false); // 창 사이즈 조절 불가
		setLocationRelativeTo(null); // 창을 화면 정중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램도 종료. 이를 안 하면 창을 닫아도 프로그램은 돌아감.
		setVisible(true); // 창을 화면에 출력
		setBackground(new Color(0, 0, 0, 0)); // 배경을 하얀색으로 지정
		setLayout(null); // 배치관리자를 null로 설정. 직접 배치하겠다는 뜻.

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false); // 버튼 테두리 제거
		exitButton.setContentAreaFilled(false); // 버튼 영역 배경 표시 제거
		exitButton.setFocusPainted(false); // 버튼 선택(focus) 시 생기는 테두리 표시 안함.
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	// 마우스를 버튼 위에 올렸을 시
				exitButton.setIcon(exitButtonEnteredImage);	// 이미지 변경
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 손 모양으로 변경
				
			}
			@Override
			public void mouseExited(MouseEvent e) {	// 마우스가 버튼 위에서 나왔을 시
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	// 기본 모양으로 변경
			}
			@Override
			public void mousePressed(MouseEvent e) {	// 마우스 클릭 효과음 재생 
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);	// 해당 음악을 한 번만 실행
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);	// 1초 동안 멈추기
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(1000, 100, 145, 150);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				startButton.setVisible(false);
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();	// 이미지 교체
				isMainScreen = true;
			}
		});
		add(startButton);
		
		quitButton.setBounds(1000, 300, 145, 150);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 왼쪽 버튼 이벤트
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { 
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				// 오른쪽 버튼 이벤트
			}
		});
		add(rightButton);
		
		menuBar.setBounds(0, 0, 1280, 30); // menuBar 위치(x, y, width, height) 지정
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	// 마우스 클릭 시 클릭한 좌표 파악하기
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {	// 마우스 드래그 시 드래그한 좌표 파악하기
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);	// 드래그 한 곳에 창을 표시
			}
		});
		add(menuBar);

		Music introMusic = new Music("introMusic.mp3", true); // 곡 이름, 종료 전까지 무한 반복 = true
		introMusic.start();
	}

	public void paint(Graphics g) { // 가장 먼저 화면을 표시해주는 메소드
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 0, 0 위치에 screenImage가 그려짐.
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);	// 역동적인 이미지 그릴 때 사용.
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 200, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		paintComponents(g); // 움직이지 않는 고정적인 요소들 그릴 때 사용. add로 추가한 요소들 그릴 때 사용
		this.repaint(); // 다시 paint를 호출해 매 순간마다 화면을 표시함.
	}
}
