package Presentation.Views;

import javax.swing.*;

public abstract class MessageDialog extends JFrame {

    protected JPanel contentPanel;

    protected JLabel titleLabel;

    public MessageDialog() {
        this.setSize(Constants.Dialogs.DeleteDialogWidth, Constants.Dialogs.DeleteDialogHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.Colors.ToolbarColor));
        this.setResizable(false);
        this.setVisible(true);

        contentPanel = new JPanel();
        contentPanel.setBounds(0, 0, Constants.Dialogs.DeleteDialogWidth, Constants.Dialogs.DeleteDialogHeight);
        contentPanel.setLayout(null);
        contentPanel.setBackground(Constants.Colors.BackgroundColor);
        this.add(contentPanel);

        titleLabel = new JLabel();
        titleLabel.setBounds(50, 25, 200, 50);
        titleLabel.setFont(Constants.Fonts.LabelsFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        contentPanel.add(titleLabel);
    }
}
