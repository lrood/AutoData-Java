import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// The public class AutoCode has 3 private fields: The controlPanel which
// contains the three buttons, the statusLabel which holds the Text and the
// mainFrame which is the JFrame.  
public class AutoData 
{

	private JFrame mainFrame;
	private JLabel statusLabel;
	private JPanel controlPanel1;
	private JPanel controlPanel2;

	
	// The constructor calls the prepare GUI method
	public AutoData()
	{
		prepareGUI();
	}

	
	// The main method uses the Autocode class constructor to create an
	// instance of AutoCode.  The showEvent method is called
	public static void main(String[] args)
	{
		AutoData scantool = new AutoData();  
		scantool.showEvent();       
	}
      
	// The class constructor calls this method to create the frame, add the
	// statusLabel and controlPanel and use a grid for the mainFrame, 
	// statusLabel, and controlPanel.  Flow Layout is used for the buttons
	// on the control panel.
	private void prepareGUI()
	{
		  mainFrame = new JFrame("Auto Data");
		  mainFrame.setSize(400,200);
		  mainFrame.setResizable(false);
		  mainFrame.setLayout(new GridLayout(3, 1));
		  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		  statusLabel = new JLabel("",JLabel.CENTER);        

		  statusLabel.setSize(350,100);
		  controlPanel1 = new JPanel();
		  controlPanel2 = new JPanel();
		  controlPanel1.setLayout(new FlowLayout());
		  controlPanel2.setLayout(new FlowLayout());

		  
		  mainFrame.add(controlPanel1);
		  mainFrame.add(controlPanel2);
		  mainFrame.add(statusLabel);
		  mainFrame.setVisible(true);  
	}

	// This method is called from the main method.  It adds the buttons to
	// the controlPanel and sets the ActionListeners with Lamda Expressions
	private void showEvent()
	{
      
      JButton ltftButton = new JButton("LTFT");
      JButton stftButton = new JButton("STFT");
      JButton aboutButton= new JButton("About");
      JButton helpButton = new JButton("Help");
      JButton b1s1Button = new JButton("B1S1");
      JButton ectButton = new JButton("ECT");
      JButton iatButton = new JButton("IAT");
      JButton b2s1Button = new JButton("B2S2");
      JButton tpButton = new JButton("TP");
      JButton rpmButton = new JButton("RPM");
      

      ltftButton.addActionListener((ActionEvent e)-> statusLabel.setText(ltft()));
      stftButton.addActionListener((ActionEvent e)-> statusLabel.setText(stft()));
      aboutButton.addActionListener((ActionEvent e)-> about());
	  helpButton.addActionListener((ActionEvent e)-> help());
      b1s1Button.addActionListener((ActionEvent e)-> statusLabel.setText(b1s1()));
      ectButton.addActionListener((ActionEvent e)-> statusLabel.setText(ect()));
      iatButton.addActionListener((ActionEvent e)-> statusLabel.setText(iat()));
      b2s1Button.addActionListener((ActionEvent e)-> statusLabel.setText(b2s1()));
      tpButton.addActionListener((ActionEvent e)-> statusLabel.setText(tp()));
	  rpmButton.addActionListener((ActionEvent e)-> statusLabel.setText(rpm()));
		
      controlPanel1.add(aboutButton);       
	  controlPanel1.add(helpButton);
      controlPanel1.add(ectButton);       
	  controlPanel1.add(iatButton);
      controlPanel1.add(tpButton);
      
      controlPanel2.add(rpmButton);
      controlPanel2.add(ltftButton);
      controlPanel2.add(stftButton);
      controlPanel2.add(b1s1Button);
      controlPanel2.add(b2s1Button);
	
      mainFrame.setVisible(true);  
	}
    
    
    // All of the following methods are called form the button action listeners
    // All of them return the parsed and translated strings to the status Label
    // About and Help do not return values and are not passed values.
    private String ltft()
    {
		String data = parse("0107");
		 String trim = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 trim = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 int integer1 = Integer.parseInt(data.trim(), 16);
			 int integer2 = integer1 - 128;
			 Double num = (integer2 * .78125);
			 trim = String.format("%.2f", num);
			 
		 }
		 
		 return trim;
	}
	
	private String stft()
    {
		
		 String data = parse("0106");
		 String trim = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 trim = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 int integer1 = Integer.parseInt(data.trim(), 16);
			 int integer2 = integer1 - 128;
			 Double num = (integer2 * .78125);
			 trim = String.format("%.2f", num);
			 
		 }
		 
		 return trim;
	}
    
    private String iat()
    {
		 String data = parse("010F");
		 String temp = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
				temp = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 int integer1 = Integer.parseInt(data.trim(), 16);
			 integer1 = integer1 - 40;
			 Double num = (integer1 * 1.8) + 32;
			 temp = String.format("%.2f" , num); 
			 temp = (temp + " degrees F");
		 }
		 
		 return temp;
		
	}
	private String tp()
    {
		 String data = parse("0111");
		 String tp = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 tp = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 int integer1 = Integer.parseInt(data.trim(), 16);
		       	 int integer2 = integer1 * 100;
			 double floatNum = integer2 / 255;
			 tp = String.format("%.2f", floatNum);
			 tp = (tp + " %");
		 }
		 
		 return tp;

	}
	private String b1s1()
    {
		 String data = parse("0114");
		 String o2 = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 o2 = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 String dec = Character.toString(data.charAt(0)) + Character.toString(data.charAt(1));
			 int num1 = Integer.parseInt(dec,16);
			 float floatNum = (float)num1 / (float)200;
			 o2 = String.format("%.2f", floatNum);
			 o2 = (o2 + " Volts");
		 }
		 
		 return o2;
		
		
	}
	private String b2s1()
    {
		String data = parse("0118");
		 String o2 = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 o2 = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 String dec = Character.toString(data.charAt(0)) + Character.toString(data.charAt(1));
			 int num1 = Integer.parseInt(dec,16);
			 
			 float floatNum = (float)num1 / (float)200;
			 o2 = String.format("%.2f", floatNum);
			 o2 = (o2 + " Volts");
		 }
		 
		 return o2;
	}
	private String ect()
    {
		 String data = parse("0105");
		 String temp = "";
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
				temp = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 int integer1 = Integer.parseInt(data.trim(), 16);
			 integer1 = integer1 - 40;
			 Double num = (integer1 * 1.8) + 32;
			 temp = String.format("%.2f" , num); 
			 temp = (temp + " degrees F");
		 }
		 
		 return temp;
	}
	private String rpm()
	{
		 String data = parse("010C");
		 String rpm = "";
		 String num1 = "";
		 String num2 = "";
		 int int1 = 0;
		 int int2 = 0;
		 if (data.contains("Connections") || data.contains("NO") || data.contains("UNABLE"))
		 {
			 rpm = "Check Your Connections. Turn Ignition On.";
		 }
		 else
		 {
			 num1 = Character.toString(data.charAt(0)) + Character.toString(data.charAt(1));
			 num2 = Character.toString(data.charAt(2)) + Character.toString(data.charAt(3));
			 int1 = Integer.parseInt(num1, 16);
			 int2 = Integer.parseInt(num2, 16);
			 
			 float floatNum = (((int1 * 256) + int2)/4);
			 rpm = String.format("%.2f" , floatNum);
			 rpm = (rpm + " RPM");
		 }
		 
		 return rpm;
	}

	private void help()
    {
		JOptionPane.showMessageDialog(null, "ECT = Engine Coolant Temp\nIAT = Intake Air Temp\nTP = Throttle Position\n\n" + 
										"STFT and LTFT\n(add fuel when +)\n(subtract fuel when -)\n\n" +
										"STFT = Short Term Fuel Trim\nLTFT = Long Term FuelTrim\n\n" +
										"O2 Sensors\n(above .5 is rich)\n(below .5 is lean)\n\nB1S1 = Bank 1 O2 Sensor\nB2S2 = Bank 2 O2 Sensor");
	}
	private void about()
    {
		JOptionPane.showMessageDialog(null, "Auto Data\nVersion 1.0\nAuthor: Lauren Rood\n\nUse with ELM327 USB Interface");
	}
 
    // This method is called from each one of the button presses (except help and about)
    // The method will further prep the string that is returned from the Serial Connection class
    // The white spaces are removed and the returned String starts at the correct point.
    private String parse(String command)
    {
		SerialConnection serial = new SerialConnection();
		String shortString = "";
		String data = serial.connect(command);
		String strippedString = "";
		if (data.contains("41")) 
		{
			int index = data.indexOf("41");
			
			index = index + 6;	
			while (index < data.length())
			{
				shortString = shortString + data.charAt(index);
				index +=1;
			}
			strippedString = shortString.replaceAll(" ",""); 
	    }
	    else 
		{
			strippedString = data.trim();
		}
		return strippedString;
	}
    
}

