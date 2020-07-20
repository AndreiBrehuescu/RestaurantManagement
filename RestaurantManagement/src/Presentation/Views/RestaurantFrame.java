package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.MenuItem;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class RestaurantFrame extends JFrame implements Observer {

    protected static JPanel contentPanel;
    protected JPanel toolbarPanel;
    protected static JPanel waiterActionsPanel;
    protected static JPanel adminActionsPanel;

    protected static JButton adminButton;
    protected static JButton waiterButton;
    protected static JButton chefButton;
    protected static JButton homePageButton;

    protected static JButton addNewItemButton;
    protected static JButton deleteItemButton;
    protected static JButton editItemButton;

    protected static JButton createOrderButton;
    protected static JButton computePriceButton;
    protected static JButton generateBillButton;

    protected static JLabel menuLabel;
    protected static JLabel orderLabel;

    protected static JLabel welcomeLabel;
    protected static JLabel roleLabel;
    
    protected static JTable table;
    protected static JScrollPane scrollPane; 
    
    protected static JTable chefTable;
    protected static JScrollPane chefScrollPane;
    
    private static ArrayList<MenuItem> prepareItems;

    public static JScrollPane getScrollPane() {
    	return scrollPane;
    }
    
    public static void setScrollPane(JScrollPane sp) {
    	scrollPane = sp;
    }
    
    public static JPanel getContentPanel() {
    	return contentPanel;
    }
    
    public static void setTable(JTable newTable) {
    	table = newTable;
    }
    
    public static JTable getTable() {
    	return table;
    }
    
    public RestaurantFrame(String title) {
    	prepareItems = new ArrayList<MenuItem>();
    	
        this.setTitle(title);
        this.setSize(Constants.Frames.WIDTH, Constants.Frames.HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        toolbarPanel = new JPanel();
        toolbarPanel.setBackground(Constants.Colors.ToolbarColor);
        toolbarPanel.setBounds(0, 0, Constants.Frames.WIDTH, 50);
        toolbarPanel.setVisible(true);
        toolbarPanel.setLayout(null);
        this.add(toolbarPanel);

        adminButton = new JButton("Administrator");
        adminButton.setBounds(0, 0, 150, 50);
        adminButton.setFont(Constants.Fonts.ButtonsFont);
        adminButton.setBackground(Constants.Colors.ToolbarColor);
        toolbarPanel.add(adminButton);

        waiterButton = new JButton("Waiter");
        waiterButton.setBounds(150, 0, 150, 50);
        waiterButton.setBackground(Constants.Colors.ToolbarColor);
        waiterButton.setFont(Constants.Fonts.ButtonsFont);
        toolbarPanel.add(waiterButton);

        chefButton = new JButton("Chef");
        chefButton.setBounds(300, 0, 150, 50);
        chefButton.setBackground(Constants.Colors.ToolbarColor);
        chefButton.setFont(Constants.Fonts.ButtonsFont);
        toolbarPanel.add(chefButton);

        homePageButton = new JButton("Home Page");
        homePageButton.setBounds(750, 0, 150, 50);
        homePageButton.setBackground(Constants.Colors.ButtonsColor);
        homePageButton.setFont(Constants.Fonts.ButtonsFont);
        toolbarPanel.add(homePageButton);

        contentPanel = new JPanel();
        contentPanel.setBackground(Constants.Colors.BackgroundColor);
        contentPanel.setBounds(0, 50, Constants.Frames.WIDTH, Constants.Frames.HEIGHT - 50);
        contentPanel.setLayout(null);
        this.add(contentPanel);

        adminActionsPanel = new JPanel();
        adminActionsPanel.setBackground(Constants.Colors.BackgroundColor);
        adminActionsPanel.setBounds(0, 0, Constants.Frames.WIDTH, 50);
        adminActionsPanel.setLayout(null);
        adminActionsPanel.setVisible(false);
        contentPanel.add(adminActionsPanel);

        addNewItemButton = new JButton("Add New Item");
        addNewItemButton.setBounds(0, 0, 300, 50);
        addNewItemButton.setBackground(Constants.Colors.ButtonsColor);
        addNewItemButton.setFont(Constants.Fonts.ButtonsFont);
        adminActionsPanel.add(addNewItemButton);

        editItemButton = new JButton("Edit Existing Item");
        editItemButton.setBounds(300, 0, 300, 50);
        editItemButton.setBackground(Constants.Colors.ButtonsColor);
        editItemButton.setFont(Constants.Fonts.ButtonsFont);
        adminActionsPanel.add(editItemButton);

        deleteItemButton = new JButton("Delete Existing Item");
        deleteItemButton.setBounds(600, 0, 300, 50);
        deleteItemButton.setBackground(Constants.Colors.ButtonsColor);
        deleteItemButton.setFont(Constants.Fonts.ButtonsFont);
        adminActionsPanel.add(deleteItemButton);

        waiterActionsPanel = new JPanel();
        waiterActionsPanel.setBackground(Constants.Colors.BackgroundColor);
        waiterActionsPanel.setBounds(0, 0, Constants.Frames.WIDTH, 50);
        waiterActionsPanel.setLayout(null);
        waiterActionsPanel.setVisible(false);
        contentPanel.add(waiterActionsPanel);

        createOrderButton = new JButton("New Order");
        createOrderButton.setBounds(0, 0, 300, 50);
        createOrderButton.setFont(Constants.Fonts.ButtonsFont);
        createOrderButton.setBackground(Constants.Colors.ButtonsColor);
        waiterActionsPanel.add(createOrderButton);

        computePriceButton = new JButton("Compute Price");
        computePriceButton.setBounds(300, 0, 300, 50);
        computePriceButton.setBackground(Constants.Colors.ButtonsColor);
        computePriceButton.setFont(Constants.Fonts.ButtonsFont);
        waiterActionsPanel.add(computePriceButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(600, 0, 300, 50);
        generateBillButton.setBackground(Constants.Colors.ButtonsColor);
        generateBillButton.setFont(Constants.Fonts.ButtonsFont);
        waiterActionsPanel.add(generateBillButton);

        menuLabel = new JLabel("Menu");
        menuLabel.setFont(Constants.Fonts.LabelsFont);
        menuLabel.setForeground(Constants.Colors.BLACK);
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        menuLabel.setVerticalAlignment(JLabel.CENTER);
        menuLabel.setBounds(200, 75, 500, 40);
        menuLabel.setVisible(false);
        contentPanel.add(menuLabel);

        orderLabel = new JLabel("Orders");
        orderLabel.setFont(Constants.Fonts.LabelsFont);
        orderLabel.setForeground(Constants.Colors.BLACK);
        orderLabel.setHorizontalAlignment(JLabel.CENTER);
        orderLabel.setVerticalAlignment(JLabel.CENTER);
        orderLabel.setBounds(200, 75, 500, 40);
        orderLabel.setVisible(false);
        contentPanel.add(orderLabel);

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(Constants.Fonts.LabelsFont);
        welcomeLabel.setForeground(Constants.Colors.TextColor);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setBounds(200, 200, 500, 40);
        welcomeLabel.setVisible(true);
        contentPanel.add(welcomeLabel);

        roleLabel = new JLabel("Please choose your role :)");
        roleLabel.setFont(Constants.Fonts.LabelsFont);
        roleLabel.setForeground(Constants.Colors.TextColor);
        roleLabel.setHorizontalAlignment(JLabel.CENTER);
        roleLabel.setVerticalAlignment(JLabel.CENTER);
        roleLabel.setBounds(200, 260, 500, 40);
        roleLabel.setVisible(true);
        contentPanel.add(roleLabel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(75, 125, 750, 350);
        contentPanel.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        scrollPane.setVisible(false);
        
        chefScrollPane = new JScrollPane();
        chefScrollPane.setBounds(75, 125, 750, 350);
        contentPanel.add(chefScrollPane);
        
        chefTable = new JTable();
        chefScrollPane.setViewportView(chefTable);
        chefScrollPane.setVisible(false);
        
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		prepareItems = (ArrayList<MenuItem>) arg;	
	}

    public static JButton getAdminButton() {
        return adminButton;
    }

    public static JButton getWaiterButton() {
        return waiterButton;
    }

    public static JButton getChefButton() {
        return chefButton;
    }

    public static JButton getHomePageButton() {
        return homePageButton;
    }

    public static JPanel getAdminActionsPanel() {
        return adminActionsPanel;
    }

    public static JPanel getWaiterActionsPanel() {
        return waiterActionsPanel;
    }

    public static JButton getAddNewItemButton() {
        return addNewItemButton;
    }

    public static JButton getCreateOrderButton() {
        return createOrderButton;
    }

    public static JButton getComputePriceButton() {
        return computePriceButton;
    }

    public static JButton getGenerateBillButton() {
        return generateBillButton;
    }

    public static JLabel getMenuLabel() {
        return menuLabel;
    }

    public static JLabel getOrderLabel() {
        return orderLabel;
    }

    public static JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public static JLabel getRoleLabel() {
        return roleLabel;
    }

    public void setAdminButtonActionListener(ActionListener actionListener) {
        adminButton.addActionListener(actionListener);
    }

    public void setWaiterButtonActionListener(ActionListener actionListener) {
        waiterButton.addActionListener(actionListener);
    }

    public void setHomePageButtonActionListener(ActionListener actionListener) {
        homePageButton.addActionListener(actionListener);
    }

    public void setAddNewItemButtonActionListener(ActionListener actionListener) {
        addNewItemButton.addActionListener(actionListener);
    }

    public void setEditItemButtonActionListener(ActionListener actionListener) {
        editItemButton.addActionListener(actionListener);
    }

    public void setDeleteItemButtonActionListener(ActionListener actionListener) {
        deleteItemButton.addActionListener(actionListener);
    }
    
    public void setComputePriceButtonActionListenet(ActionListener actionListener) {
    	computePriceButton.addActionListener(actionListener);
    }
    
    public void setGenerateBillButtonActionListener(ActionListener actionListener) {
    	generateBillButton.addActionListener(actionListener);
    }
    
    public void setNewOrderButtonActionListener(ActionListener actionListener) {
    	createOrderButton.addActionListener(actionListener);
    }
    
    public void setChefButtonActionListener(ActionListener actionListener) {
    	chefButton.addActionListener(actionListener);
    }
    
    public static ArrayList<MenuItem> getPrepareItems() {
    	return prepareItems;
    }
    
    public static void setChefTable(JTable table) {
    	chefTable = table;
    }
    
    public static void setChefScrollTable(JScrollPane scrollPane) {
    	chefScrollPane = scrollPane;
    }
    
    public static JScrollPane getChefScrollPane() {
    	return chefScrollPane;
    }
    public static JTable getChefTable() {
    	return chefTable;
    }
}
