package eu.europa.ec.etrustex.tools;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class CopyContextualMenu extends MouseAdapter {

    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            showPopup(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            showPopup(e);
    }

    private void showPopup(MouseEvent e){
        final Component component = e.getComponent();
        if (component instanceof JTextComponent) {

            JTextComponent textComponent = (JTextComponent) component;

            if (textComponent.isEnabled()) {
                textComponent.requestFocusInWindow();
                textComponent.selectAll();

                // open the menu with the copy/paste items
                JPopupMenu filePopupMenu = new JPopupMenu();

                JMenuItem menuItem = new JMenuItem("Copy");
                Action actions[] = textComponent.getActions();
                menuItem.addActionListener(findAction(actions, DefaultEditorKit.copyAction));
                filePopupMenu.add(menuItem);

                filePopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }

        }
    }

    public static Action findAction(Action actions[], String key) {
        Map<String, Action> commands = new HashMap<>();
        for (Action action : actions) {
            commands.put((String) action.getValue(Action.NAME), action);
        }
        return commands.get(key);
    }

}
