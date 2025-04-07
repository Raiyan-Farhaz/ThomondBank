package gui;

import model.Account;
import model.BankStaff;
import model.CurrentAccount;
import model.DepositAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton DepositButton;
    private JButton withdrawButton;
    private JButton logOutButton;
    private JButton CheckButton;
    private JRadioButton DepositRadioButton;
    private JRadioButton CurrentRadio;

    public static ArrayList<Account> thomondAccounts = new ArrayList<>();
    public static ArrayList<BankStaff> thomondStaff = new ArrayList<>();

    public ThomondBankGUI() {
        populateAccounts();

        // Ensure only one radio button is selected at a time
        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(DepositRadioButton);
        accountTypeGroup.add(CurrentRadio);

        // Deposit
        DepositButton.addActionListener(e -> Deposit());

        // Withdraw
        withdrawButton.addActionListener(e -> Withdraw());

        // Check balance
        CheckButton.addActionListener(e -> CheckingBalance());

        // Create new account
        createNewAccountButton.addActionListener(e -> CreatingNewAccount());

        // Change AIR
        changeAIRButton.addActionListener(e -> ChangingAIR());

        // Change overdraft
        changeOverDraftLimitButton.addActionListener(e -> ChangingOverdraftLimit());

        // Logout
        logOutButton.addActionListener(e -> JOptionPane.showMessageDialog(rootPanel, "Logging out..."));
    }

    private void Deposit() {
        try {
            int accountId = Integer.parseInt(AtmtextField.getText());
            Account account = findAccount(accountId);

            if (account != null) {
                String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
                double amount = Double.parseDouble(amountStr);
                account.deposit(amount);

                JOptionPane.showMessageDialog(rootPanel, "Deposit successful! New balance: " + account.getBalance());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Invalid amount entered.");
        }
    }
    private void Withdraw() {
        try {
            int accountId = Integer.parseInt(AtmtextField.getText());
            Account account = findAccount(accountId);

            if (account != null) {
                String amountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
                double amount = Double.parseDouble(amountStr);
                account.withdraw(amount);

                JOptionPane.showMessageDialog(rootPanel, "Withdrawal successful! New balance: " + account.getBalance());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Invalid amount entered.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(rootPanel, ex.getMessage());
        }
    }
    private void CheckingBalance() {
        int accountId = Integer.parseInt(AtmtextField.getText());
        Account account = findAccount(accountId);

        if (account != null) {
            JOptionPane.showMessageDialog(rootPanel, "Current balance: " + account.getBalance());
        }
    }



    private void CreatingNewAccount() {
        String[] options = {"Deposit", "Current"};
        int choice = JOptionPane.showOptionDialog(
                rootPanel,
                "Select Account Type:",
                "New Account",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == -1) return; // User cancelled

        try {
            String accountIdStr = JOptionPane.showInputDialog("Enter Account ID:");
            if (accountIdStr == null) return;
            int accountId = Integer.parseInt(accountIdStr);

            String custNoStr = JOptionPane.showInputDialog("Enter Customer Number:");
            if (custNoStr == null) return;
            int custNo = Integer.parseInt(custNoStr);

            double overdraftLimit = 0;
            if (choice == 1) { // Current Account
                String overdraftStr = JOptionPane.showInputDialog("Enter Overdraft Limit:");
                if (overdraftStr == null) return;
                overdraftLimit = Double.parseDouble(overdraftStr);
            }

            Account newAccount;
            if (choice == 0) {
                newAccount = new DepositAccount(accountId, custNo, 0);
            } else {
                newAccount = new CurrentAccount(accountId, custNo, 0, overdraftLimit);
            }

            thomondAccounts.add(newAccount);
            JOptionPane.showMessageDialog(rootPanel, "New Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPanel, "Invalid input. Please enter valid numbers only.");
        }
    }

    private void ChangingAIR() {
        try {
            if (DepositRadioButton.isSelected()) {
                double newAIR = Double.parseDouble(JOptionPane.showInputDialog("Enter new AIR for Deposit Accounts:"));
                DepositAccount.AIR = newAIR;
                JOptionPane.showMessageDialog(rootPanel, "Deposit Account AIR updated to: " + newAIR);
            } else if (CurrentRadio.isSelected()) {
                double newAIR = Double.parseDouble(JOptionPane.showInputDialog("Enter new AIR for Current Accounts:"));
                CurrentAccount.AIR = newAIR;
                JOptionPane.showMessageDialog(rootPanel, "Current Account AIR updated to: " + newAIR);
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Please select an account type to change AIR!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Invalid input. Please enter a valid number.");
        }
    }

    private void ChangingOverdraftLimit() {
        try {
            int accountId = Integer.parseInt(JOptionPane.showInputDialog("Enter Account ID to modify overdraft:"));
            Account account = findAccount(accountId);

            if (account instanceof CurrentAccount) {
                double newOverdraftLimit = Double.parseDouble(JOptionPane.showInputDialog("Enter new overdraft limit:"));
                ((CurrentAccount) account).setOverdraftLimit(newOverdraftLimit);
                JOptionPane.showMessageDialog(rootPanel, "Overdraft limit updated successfully!");
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Error: Account not found or not a Current Account.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPanel, "Invalid input. Please enter numbers only.");
        }
    }

    private void populateAccounts() {
        thomondAccounts.add(new DepositAccount(1, 1, 100));
        thomondAccounts.add(new DepositAccount(2, 2, 500));
        thomondAccounts.add(new DepositAccount(3, 3, 300));
        thomondAccounts.add(new DepositAccount(4, 4, 300));

        thomondAccounts.add(new CurrentAccount(5, 1, 100, 100));
        thomondAccounts.add(new CurrentAccount(6, 2, 1000, 500));
        thomondAccounts.add(new CurrentAccount(7, 4, 200, 200));
    }

    private Account findAccount(int accountId) {
        for (Account account : thomondAccounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        JOptionPane.showMessageDialog(rootPanel, "Account not found!");
        return null;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ThomondBankGUI");
        frame.setContentPane(new ThomondBankGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}