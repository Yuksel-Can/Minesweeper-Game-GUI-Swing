package mayinTarlasiGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MayinTarlasi implements MouseListener{
	
	private int rowSize=10;
	private int colSize=10;
	private int size=rowSize*colSize;
	private int mineCount=size/12;
	private int openButton=0;
	Random random = new Random();
	JFrame frame;
	Button[][] board= new Button[rowSize][colSize];
	
	public MayinTarlasi() {
		
		run();
	}
	
	public MayinTarlasi(int rowSize, int colSize,int mineCount) {
		super();
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.mineCount = mineCount;
		
		run();
		
	}

	public void run() {
		frame = new JFrame("Mayýn Tarlasý");
		frame.setSize(rowSize*50,colSize*50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(rowSize,colSize));
		
		for(int row=0; row < board.length; row++ ) {
			for(int col = 0; col < board[0].length; col++) {
				Button btn = new Button(row,col);
				
				frame.add(btn);
				btn.addMouseListener(this);
				board[row][col] = btn;
			}
		}
		System.out.println("Oyun Oluþturuldu");
		generateMine();
		check(board);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);	//screen centering
		frame.setVisible(true);
	}
	public void generateMine(){
		
		int i = 0;
		int randRow, randCol;
		while (i<mineCount) {
			randRow = random.nextInt(rowSize);
			randCol = random.nextInt(rowSize);
			if(board[randRow][randCol].isMine() == false) {
				board[randRow][randCol].setMine(true);
				i++;
			}
		}
		System.out.println("Mayýnlar Yerleþtirildi");
	}

	public void check(Button btns[][]) {
		for(int row=0;row<btns.length;row++) {
			for(int col=0; col<btns[0].length;col++) {
				checkMine(row, col);
			}
		}
	}
	
	public void checkMine(int row, int col) {
		int warning=0;
		for(int i =-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				
				if(row+i>=0 && col+j>=0 && board.length >row+i && board[0].length>col+j) {
					if(board[row+i][col+j].isMine()) {
						warning++;
					}
				}
			}
		}
		if(warning !=0) {
			board[row][col].setCount(warning);
		}else {
			board[row][col].setCount(0);;
		}
	}
	
	public void printMap() {
		for(int row=0; row < board.length; row++ ) {
			for(int col = 0; col < board[0].length; col++) {
				
				if(board[row][col].isMine() == true){
					board[row][col].setIcon(new ImageIcon("C:\\Users\\can\\eclipse-workspace\\mayinTarlasiGUI\\src\\mayinTarlasiGUI\\bombmini.png"));
					
				}
			}
		}
	}
	
	public void open(int row, int col) {
		if(row<0 || row >= board.length || col<0 || col >= board[0].length  || board[row][col].isEnabled()==false) {
			return;
			
		}else if(board[row][col].getCount() !=0) {
			board[row][col].setEnabled(false);
			board[row][col].setText(String.valueOf(board[row][col].getCount()));
			openButton++;
			
		}else {
			openButton++;
			board[row][col].setEnabled(false);
			for(int i =-1;i<2;i++) {
				for(int j=-1;j<2;j++) {
					if(i==0&j==0) {
						continue;
					}
					open(row+i, col+j);
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Button btn = (Button) e.getComponent();	//týklanan buttonu verir
		if(e.getButton()==1) {			//sol týk týklandý
			btn.setIcon(null);
			if(btn.isMine()) {
				printMap();
				btn.setEnabled(false);
				btn.setBackground(Color.RED);
				int input = JOptionPane.showOptionDialog(null, "Mayýna bastýnýz, Tekrar Oynmak Ýster misiniz?", "Oyun Bitti",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
				if(input == JOptionPane.OK_OPTION) {
					frame.dispose();
					MayinTarlasi mt = new MayinTarlasi();
				}else {
					//frame.dispose();
				}
				
			}else{		//mayýnsýz alan
				
				if(btn.getCount()==0) {
					open(btn.getRow(), btn.getCol());
				}else {
					openButton++;
					btn.setEnabled(false);
					btn.setText(String.valueOf(btn.getCount()));
				}
				if(openButton==size-mineCount) {
					int input = JOptionPane.showOptionDialog(null, "TEBRÝKLER, OYUNU KAZANDINIZ Tekrar Oynmak Ýster misiniz?", "Oyun Kazanýldý",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(input == JOptionPane.OK_OPTION) {
						frame.dispose();
						MayinTarlasi mt = new MayinTarlasi();
					}
				}
			}
			
		}else if(e.getButton()==3) {		//sað týk týklandý
			if(!btn.isFlag() && btn.isEnabled()) {
				btn.setIcon(new ImageIcon("C:\\Users\\can\\eclipse-workspace\\mayinTarlasiGUI\\src\\mayinTarlasiGUI\\flagmini.jpg"));
				btn.setFlag(true);
				
			}else if(btn.isFlag()){
				btn.setIcon(null);
				btn.setFlag(false);
			}
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
