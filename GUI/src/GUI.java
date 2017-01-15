import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
	//versioning related
	private static final long serialVersionUID = 1L;
	// JWidget thingies
	private JPanel panel;                
   private JLabel messageLabel;       
   private JTextField minutesTextField;     
   private JButton calcButton;
   // window dimensions
   private final int WINDOW_WIDTH = 700;  
   private final int WINDOW_HEIGHT = 320;
   
   private final JRadioButton day = new JRadioButton("Day");
   private final JRadioButton evening = new JRadioButton("Evening");
   private final JRadioButton offPeak = new JRadioButton("offPeak");
    
   private final ButtonGroup group = new ButtonGroup();

 

   public GUI() {
      
      setTitle("Minutes Converter");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
      buildPanel();
      add(panel);
      setVisible(true);
   }
   
   private void buildPanel() {
      
	   // text label with text field
      messageLabel = new JLabel("Enter the number of minutes: ");
      minutesTextField = new JTextField(10);
      
      // Calc button with action listener
      calcButton = new JButton("Calculate");
      calcButton.addActionListener(new CalcButtonListener());
      
      // A panel to put stuff in
      panel = new JPanel();

      // some ugly colors
      panel.setBackground(Color.DARK_GRAY);
      messageLabel.setForeground(Color.WHITE);
      minutesTextField.setBackground(Color.LIGHT_GRAY);
      minutesTextField.setForeground(Color.BLACK);
      calcButton.setBackground(Color.LIGHT_GRAY);
      calcButton.setForeground(Color.BLACK);
      
      // add everything
      panel.add(messageLabel);
      panel.add(minutesTextField);
      panel.add(calcButton);

      group.add(day);
      group.add(evening);
      group.add(offPeak);
      // day selected as default radiobox
      day.setSelected(true);
      panel.add(day);
      panel.add(evening);
      panel.add(offPeak);

   }
   
   // calculate the rates
   private class CalcButtonListener implements ActionListener {
      
	   public double getRate(){
		   // depends on which radiobutton is selected
		   if (day.isSelected()) return 0.07;
		   else if(evening.isSelected()) return 0.12;
		   else return 0.05;
	   }

      public void actionPerformed(ActionEvent e) {
         
    	  String input;  // to hold user input
         double miles;  // the number of miles

         input = minutesTextField.getText();
         if (input.equals("")){
        	 JOptionPane.showMessageDialog(null, "Please enter a value!"); 
        	 return;
         }
         miles = Double.parseDouble(input) * getRate();
         if (miles<0){
        	 JOptionPane.showMessageDialog(null, "Please enter a valid value!"); 
        	 return;
         }
         JOptionPane.showMessageDialog(null, input +
            " minutes totals $" + miles + "");
      }
   }
   
   public static void main(String[] args) {  
      new GUI();
   }
}