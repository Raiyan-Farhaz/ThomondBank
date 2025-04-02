package gui;

import javax.swing.*;

public class ThomondBankGUI {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane1;
    private JPanel BankOfficer;
    private JPanel ATMUser;
    private JTextField AtmtextField;
    private JLabel EnterAccount;
    private JButton createNewAccountButton;
    private JButton changeAIRButton;
    private JButton changeOverDraftLimitButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ThomondBankGUI");
        frame.setContentPane(new ThomondBankGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

