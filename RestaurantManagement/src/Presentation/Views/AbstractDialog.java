package Presentation.Views;

import javax.swing.*;

public abstract class AbstractDialog extends JFrame {

    protected JPanel contentPanel;
    protected JButton confirmButton;
    protected JButton cancelButton;

    protected JLabel titleLabel;

    public AbstractDialog() {

        this.setSize(Constants.Dialogs.WIDTH, Constants.Dialogs.HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.Colors.ToolbarColor));
        this.setResizable(false);
        this.setVisible(true);

        contentPanel = new JPanel();
        contentPanel.setBackground(Constants.Colors.BackgroundColor);
        contentPanel.setBounds(0, 0, Constants.Dialogs.WIDTH, Constants.Dialogs.HEIGHT);
        contentPanel.setLayout(null);
        this.add(contentPanel);

        titleLabel = new JLabel();
        titleLabel.setBounds(175, 15, 300, 40);
        titleLabel.setFont(Constants.Fonts.LabelsFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        contentPanel.add(titleLabel);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(Constants.Fonts.ButtonsFont);
        confirmButton.setBackground(Constants.Colors.ButtonsColor);
        confirmButton.setBounds(175, 375, 100, 40);
        contentPanel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Constants.Fonts.ButtonsFont);
        cancelButton.setBackground(Constants.Colors.ButtonsColor);
        cancelButton.setBounds(375, 375, 100, 40);
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
        contentPanel.add(cancelButton);
    }
}
