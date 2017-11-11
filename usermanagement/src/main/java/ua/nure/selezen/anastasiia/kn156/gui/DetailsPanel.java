package ua.nure.selezen.anastasiia.kn156.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import ua.nure.selezen.anastasiia.kn156.User;
import ua.nure.selezen.anastasiia.kn156.util.Messages;
import ua.nure.selezen.anastasiia.kn156.db.DatabaseExeption;
import ua.nure.selezen.anastasiia.kn156.db.DaoFactoryImpl;

public class DetailsPanel extends JPanel implements ActionListener {

	private MainFrame parentFrame;
	private JPanel buttonsPanel;
	private JPanel fieldsPanel;
	private JButton backButton;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private User user;

	public DetailsPanel(MainFrame mainFrame) {
		this.parentFrame = mainFrame;
		initialize();
	}

	public void setUser(User user) {
		DateFormat format = DateFormat.getDateInstance();
		this.user = user;
		getFirstNameField().setText(user.getFirstName());
		getLastNameField().setText(user.getLastName());
		getDateOfBirthField().setText(format.format(user.getDateOfBirthday()));
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private void initialize() {
		this.setName("detailsPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldsPanel(), BorderLayout.NORTH);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.add(getBackButton());
		}
		return buttonsPanel;
	}

	private JPanel getFieldsPanel() {
		if (fieldsPanel == null) {
			fieldsPanel = new JPanel();
			fieldsPanel.setLayout(new GridLayout(3, 2));
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.first_name"), getFirstNameField());
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.last_name"), getLastNameField());
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField());
		}
		return fieldsPanel;
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setEditable(false);
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setEditable(false);
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	public JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setEditable(false);
			dateOfBirthField.setName("dateOfBirthField");
		}
		return dateOfBirthField;
	}

	private JButton getBackButton() {
		if (backButton == null) {
			backButton = new JButton();
			backButton.setText(Messages.getString("DetailsPanel.back")); //$NON-NLS-1$
			backButton.setName("backButton");
			backButton.setActionCommand("back");
			backButton.addActionListener(this);
		}
		return backButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("back".equalsIgnoreCase(e.getActionCommand())) {
			setVisible(false);
			clearFields();
			parentFrame.showBrowsePanel();
		}
	}

	private void clearFields() {
		getFirstNameField().setText("");
		getLastNameField().setText("");
	}

	public void showUser(Long id) {
		try {
			User user = parentFrame.getDao().find(id);
			getFirstNameField().setText(user.getFirstName());
			getLastNameField().setText(user.getLastName());
			getDateOfBirthField().setText(DateFormat.getDateInstance().format(user.getDateOfBirthday()));
		} catch (DatabaseExeption e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			parentFrame.showBrowsePanel();
		}
	}
}
