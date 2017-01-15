import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i=0; i<4; i++){
			int z=Grid.tileSize;
			g.drawRect(0 + (z*(i % 2)), 0 + (z * (i / 2)), z, z);
			if(Grid.states[i]) g.drawOval(0 + (z*(i % 2)), 0 + (z * (i / 2)), z, z);
		}
  }
}
