package mayinTarlasiGUI;
import javax.swing.JButton;
public class Button extends JButton{
	
	private int row;
	private int col;
	private int count;
	private boolean isMine;
	private boolean flag;
	public Button(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.count = 0;
		this.isMine = false;
		this.flag = false;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isMine() {
		return isMine;
	}
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
