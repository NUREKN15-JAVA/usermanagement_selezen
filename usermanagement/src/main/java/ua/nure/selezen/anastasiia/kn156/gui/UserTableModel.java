package ua.nure.selezen.anastasiia.kn156.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.selezen.anastasiia.kn156.User;
import ua.nure.selezen.anastasiia.kn156.util.Messages;

public class UserTableModel extends AbstractTableModel {

	private static final String[] COLUMN_NAMES = { Messages.getString("UserTableModel.id"), //$NON-NLS-1$
			Messages.getString("UserTableModel.first_name"), Messages.getString("UserTableModel.last_name") }; //$NON-NLS-1$ //$NON-NLS-2$
	private static final Class[] COLUMN_CLASSES = { Long.class, String.class, String.class };
	private List users = null;

	/*
	 * public UserTableModel(Collection<User> collection){ this.users = new
	 * ArrayList(collection); }
	 */

	public UserTableModel(Collection<User> findAll) {
		super();
		this.users = new ArrayList(findAll);
	}

	@Override
	public int getRowCount() {

		return users.size();
	}

	@Override
	public int getColumnCount() {

		return COLUMN_NAMES.length;
	}

	public Class getColumnClass(int columnIndex) {

		return COLUMN_CLASSES[columnIndex];
	}

	public String getColumnName(int column) {

		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

	public User getUser(int index) {
        return (User) users.get(index);
    }

    public void addUsers(Collection users) {
        this.users.addAll(users);
        
    }

    public void clearUsers() {
        this.users = new ArrayList();
    }
}
