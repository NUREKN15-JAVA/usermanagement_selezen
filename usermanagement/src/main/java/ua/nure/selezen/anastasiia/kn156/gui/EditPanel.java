package ua.nure.selezen.anastasiia.kn156.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.selezen.anastasiia.kn156.User;
import ua.nure.selezen.anastasiia.kn156.db.DatabaseExeption;
import ua.nure.selezen.anastasiia.kn156.util.Messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;





public class EditPanel extends JPanel implements ActionListener {

	private User user;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private MainFrame parent;
	private JPanel fieldsPanel;
	private JPanel buttonsPanel;
	private JButton okButton;
	private JButton cancelButton;

	public EditPanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("editPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldsPanel(), BorderLayout.NORTH);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);

	}

	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.add(getOkButton());
			buttonsPanel.add(getCancelButton());
		}
		return buttonsPanel;
	}

	private JPanel getFieldsPanel() {
		if (fieldsPanel == null) {
			fieldsPanel = new JPanel(new GridLayout(3, 2));
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.first_name"), getFirstNameField());
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.last_name"), getLastNameField());
			addLabeledField(fieldsPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField());
		}
		return fieldsPanel;
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel"));
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(Messages.getString("EditPanel.ok"));
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	protected void doAction(ActionEvent e) {

		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				Date date = format.parse(getDateOfBirthField().getText());
				user.setDateOfBirthday(date);
				parent.getDao().update(user);
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			} catch (DatabaseExeption e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

			setVisible(false);
			clearFields();
			parent.showBrowsePanel();
		}
		if ("cancel".equalsIgnoreCase(e.getActionCommand())) {
			setVisible(false);
			clearFields();
			parent.showBrowsePanel();
		}

	}

	private void clearFields() {
		getFirstNameField().setText("");
		getLastNameField().setText("");
		getDateOfBirthField().setText("");
		getFirstNameField().setBackground(Color.white);
		getLastNameField().setBackground(Color.white);
		getDateOfBirthField().setBackground(Color.white);
	}

	public void setUser(User user) {
		DateFormat format = DateFormat.getDateInstance();
		this.user = user;
		getFirstNameField().setText(user.getFirstName());
		getLastNameField().setText(user.getLastName());
		getDateOfBirthField().setText(format.format(user.getDateOfBirthday()));
	}

	private JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); //$NON-NLS-1$
		}
		return firstNameField;
	}

	private JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
		}
		return lastNameField;
	}

	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
		}
		return dateOfBirthField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
	            doAction(e);
	       
	        clearFields();
	        this.setVisible(false);
	        parent.showBrowsePanel();
	        
	}

}
