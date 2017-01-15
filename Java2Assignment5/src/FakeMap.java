// Name:      James Ruska
// Section:   2152-INSY-4305-001-ADV-APPLICATION-DEVELOPMENT
// File:      Date.java

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

// just a slow version of HashMap because we haven't covered it yet.
class FakeMap implements Serializable
{

	class Entry
	{

		public String key;
		public Object value;

		public Entry(String key, Object value)
		{
			this.key = key;
			this.value = value;
		}
	}

	private ArrayList<Entry> list = new ArrayList<Entry>();

	public void put(String key, Object value)
	{
		list.add(new Entry(key, value));
	}

	public Object get(String key)
	{
		for (Entry e : list)
			if (e.key == key)
				return e.value;
		return new Object();
	}

	public void clearValues()
	{
		for (Entry e : list)
			if (e.value instanceof JTextComponent)
				((JTextComponent) e.value).setText("");
			else if (e.value instanceof JComboBox)
				((JComboBox) e.value).setSelectedIndex(0);
			else if (e.value instanceof ButtonGroup)
				((ButtonGroup) e.value).clearSelection();

		((JTextField) get("name")).requestFocusInWindow();

	}

	public String getValue(String key)
	{
		Object value = get(key);
		if (value instanceof JTextComponent)
			return ((JTextComponent) value).getText();
		else if (value instanceof JComboBox)
			return String
					.valueOf(((JComboBox<String>) value).getSelectedItem());
		else if (value instanceof ButtonGroup)
		{
			Enumeration<AbstractButton> buttons = ((ButtonGroup) value)
					.getElements();
			while (buttons.hasMoreElements())
			{
				JRadioButton button = (JRadioButton) buttons.nextElement();

				if (button.isSelected())
					return button.getText();
			}
		}
		return "";
	}
}