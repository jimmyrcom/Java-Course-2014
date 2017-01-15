// draw a 4x4 grid and draw circles on it if clicked and remove them if clicked again
// James Ruska
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JApplet;
import javax.swing.JPanel;


public class Grid extends JApplet {
	public static boolean[] states = new boolean[4];
	static final int tileSize= 75;
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		new Grid();
	}
	public Grid(){
		// set the title
		//super("4x4 Grid");
		setLayout(new BorderLayout());
		setSize(300,300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// has to be a custom jpanel or else can't rewrite paintComponent()
		// tried several times to do it without a jpanel and it couldn't clear
		// the screen when I wanted to repaint
		JPanel mine=new MyPanel();
		mine.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent e) {
		    	Point p = e.getPoint();
		    	
		    	// draw the squares.
		        for (int i=0; i<4; i++){
		        	int minX=0 + (tileSize*(i % 2));
		        	int maxX=minX+tileSize;
		        	int minY=0 + (tileSize * (i / 2));
		        	int maxY=minY+tileSize;
		        	// see if you clicked in one of the squares
		        	if (p.x > minX && p.x < maxX && p.y > minY && p.y < maxY){
		        		System.out.println(i);
		        		//set flags if you did
		        		Grid.states[i]=!Grid.states[i];
				        // repaint
				        repaint();
		        	}
		        }
			}

			@Override
			public void mouseClicked(MouseEvent e){}
			@Override
			public void mouseEntered(MouseEvent e){}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e){}

		});
		add(mine,BorderLayout.CENTER);
		setVisible(true);
	}
}
