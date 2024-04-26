import javax.swing.JFrame;
public class GameFrame extends JFrame {
	public GameFrame(){
		Board board = new Board();
		this.setTitle("Java Gaming");
		setSize(1800,920);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(board);
        setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame();
        
	}

}
