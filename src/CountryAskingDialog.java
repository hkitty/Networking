import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CountryAskingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public String run() {
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setModal(true);
			setVisible(true);
			return comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
	}

	/**
	 * Create the dialog.
	 */
	public CountryAskingDialog() {
		setBounds(100, 100, 302, 149);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblEnterCountry = new JLabel("Enter country: ");
			lblEnterCountry.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblEnterCountry);
		}
		{
			String[] countryStrings = {"Canada","Switzerland","Cyprus","Danish","Estonia",
			"United Kingdom","Hong Kong","Hungary","Iceland","Japan","South Korea", 
			"Lithuania","Latvia","Malta","Norway","New Zeland","Poland","Romania","Sweden",
			"Singapore","Slovenia","Slovakia","Turkey","United States","South Africa"};
			
			comboBox = new JComboBox(countryStrings);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = 	new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
