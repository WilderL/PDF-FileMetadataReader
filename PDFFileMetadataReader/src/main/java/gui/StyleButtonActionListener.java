
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Clase que representa la modificacion de los estilos en un jPane.
 */
public class StyleButtonActionListener implements ActionListener {
    private JTextPane textPane;
    private String styleName;
    private SimpleAttributeSet currentStyle;

    public StyleButtonActionListener(JTextPane textPane, String styleName, SimpleAttributeSet currentStyle) {
        this.textPane = textPane;
        this.styleName = styleName;
        this.currentStyle = currentStyle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (styleName) {
        case "bold":
        StyleConstants.setBold(currentStyle, !StyleConstants.isBold(currentStyle));
            break;
        case "italic":
            StyleConstants.setItalic(currentStyle, !StyleConstants.isItalic(currentStyle));
            break;
        case "underline":
            StyleConstants.setUnderline(currentStyle, !StyleConstants.isUnderline(currentStyle));
            break;
        }
        textPane.setCharacterAttributes(currentStyle, false);
        }
    }
