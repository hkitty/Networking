import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainForm {

	private JFrame frame;
	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	public void run()
	{
		frame.setVisible(true);
		CountryAskingDialog dlg = new CountryAskingDialog();
		dlg.run();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
