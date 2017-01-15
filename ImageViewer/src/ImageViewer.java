// An image viewer in java.
// I had to ask a lot of questions to the java IRC in freenode to get this one to work well
// James Ruska
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;


public class ImageViewer {
    JPanel gui;
    JLabel imageCanvas;

    // load up a new image to the imageCanvas
    public void setImage(Image image) {
        imageCanvas.setIcon(new ImageIcon(image));
    }

    public void initComponents() {
        if (gui==null) { 
        	// set a border
            gui = new JPanel(new BorderLayout());
            gui.setBorder(new EmptyBorder(5,5,5,5));
            imageCanvas = new JLabel();

            // Set image orientation
            JPanel imageCenter = new JPanel(new GridBagLayout());
            imageCenter.add(imageCanvas);
            // Add some spiffy scroll components
            JScrollPane imageScroll = new JScrollPane(imageCenter);
            // set size
            imageScroll.setPreferredSize(new Dimension(600,600));
            // add it to the gui
            gui.add(imageScroll, BorderLayout.CENTER);
        }
    }

    public Container getGui() {
        initComponents();
        return gui;
    }

  
    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {

            public void run() {
            	// setup an image viewer and add a menu bar to main GUI for file -> open
            	final ImageViewer viewer = new ImageViewer();
            	JMenuBar menuBar = new JMenuBar();
            	JMenu menu = new JMenu("File");
            	menu.setMnemonic(KeyEvent.VK_F);
            	JMenuItem menuItem = new JMenuItem("Open",KeyEvent.VK_O);
            	// set an action listener for what happens when I select Open
            	menuItem.addActionListener(new ActionListener(){
            			public void actionPerformed(ActionEvent e){
            				JFileChooser fileChooser = new JFileChooser();
            				int status = fileChooser.showOpenDialog(null);
            				if (status == JFileChooser.APPROVE_OPTION)
            				{
            				  File selectedFile =
            				          fileChooser.getSelectedFile();
            				  String filename = selectedFile.getPath();
            				  // use the image selected in the image viewer component
            				  viewer.setImage(new ImageIcon(filename).getImage());
            				}
            			}
            			});
            	// add everything and set some default opts
            	menu.add(menuItem);
            	menuBar.add(menu);

            	JFrame f = new JFrame("Image Viewer");
            	f.setJMenuBar(menuBar);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setContentPane(viewer.getGui());
                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);

            }
        };
        SwingUtilities.invokeLater(r);
    }

}
