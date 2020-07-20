package Presentation.Views;

import javax.swing.*;

public class ErrorDialog extends MessageDialog {

    private JButton okButton;

    public ErrorDialog(String s) {
        super();

        this.titleLabel.setText(s);

        okButton = new JButton("Ok");
        okButton.setBounds(100, 125, 100, 30);
        okButton.setBackground(Constants.Colors.ButtonsColor);
        okButton.setFont(Constants.Fonts.SmallFont);
        okButton.addActionListener(e -> {
            this.dispose();
        });
        this.contentPanel.add(okButton);

    }
}
