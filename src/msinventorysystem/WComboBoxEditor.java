package msinventorysystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;

public class WComboBoxEditor implements ComboBoxEditor
{
    JTextField tf;

    public WComboBoxEditor(Color background)
    {
        tf = new JTextField();
        tf.setBackground(background);
        tf.setBorder(null);
    }

    public Component getEditorComponent()
    {
        return tf;
    }

    public void setItem(Object anObject)
    {
        if (anObject != null)
        {
            tf.setText(anObject.toString());
        }
    }

    public Object getItem()
    {
        return tf.getText();
    }

    public void selectAll()
    {
        tf.selectAll();
    }

    public void addActionListener(ActionListener l)
    {
        tf.addActionListener(l);
    }

    public void removeActionListener(ActionListener l)
    {
        tf.removeActionListener(l);
    }

}