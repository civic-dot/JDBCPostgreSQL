package ui;


import model.Battle;
import model.BattleMember;
import model.Country;
import model.Warship;
import org.jdatepicker.impl.JDatePickerImpl;
import repository.BattleMemberRep;
import repository.BattleRep;
import repository.CountryRep;
import repository.WarshipRep;
import table_model.BattleMemberTModel;
import table_model.BattleTModel;
import table_model.CountryTModel;
import table_model.WarshipTModel;
import utils.GUIUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.util.stream.IntStream;


public class SeaBattleGUI extends JFrame {
    private JPanel countryPanel;
    private JTable table1;
    private static JDialog dialogCountryInsert;
    private static JDialog dialogCountryUpdate;
    private static JDialog dialogWarshipInsert;
    private static JDialog dialogWarshipUpdate;
    private static JDialog dialogBattleInsert;
    private static JDialog dialogBattleUpdate;
    private static JDialog dialogBattleMemberUpdate;
    private static JDialog dialogBattleMemberInsert;
    private static JTextField textFieldInsertCountryName;
    private static JTextField textFieldInsertCountrySide;
    private static JTextField textFieldInsertWarshipName;
    private static JTextField textFieldInsertWarshipClass;
    private static JDatePickerImpl textFieldInsertWarshipDecommissionDate;


    private JButton updateButton;
    private JButton insertButton;
    private JButton selectButton;
    private JButton deleteButton;
    private JButton confirmUpdateBattleButton;
    private JButton confirmBattleMemberInsertButton;
    private JButton confirmBattleMemberUpdateButton;
    private JButton confirmInsertBattleButton;
    private JButton confirmCountryInsertButton;
    private int rowCount;
    private JComboBox <String> comboBox1;
    private JScrollPane scrollPane1;
    private JLabel infoLabel;
    private static DefaultTableModel model;
    private int[] rowIdArray;
    private JTextField textFieldUpdateCountryName;
    private JTextField textFieldUpdateWarshipName;
    private JTextField textFieldUpdateCountrySide;
    private JTextField textFieldInsertBattleName;
    private JDatePickerImpl textFieldInsertBattleDate;
    private JTextField textFieldUpdateBattleMemberShipName;
    private JTextField textFieldUpdateWarshipClass;
    private JTextField textFieldInsertBattleMemberResult;
    private JTextField textFieldUpdateBattleName;
    private JTextField textFieldUpdateBattleMemberName;
    private JDatePickerImpl textFieldUpdateWarshipCommissionDate;
    private JDatePickerImpl textFieldUpdateWarshipDecommissionDate;
    private JDatePickerImpl textFieldUpdateBattleDate;
    private JComboBox <String> textFieldInsertBattleMemberName;
    private JComboBox <String> textFieldInsertBattleMemberShipName;
    private JButton confirmCountryUpdateButton;
    private JButton confirmWarshipInsertButton;
    private JButton confirmWarshipUpdateButton;
    private JDatePickerImpl textFieldInsertWarshipCommissionDate;
    private JTextField textFieldUpdateBattleMemberResult;
    private JTextField textFieldUpdateBattleMemberId;
    private final BattleRep battleRep;
    private final Connection connection;
    private final WarshipRep warshipRep;
    private final BattleMemberTModel battleMemberTModel;
    private final BattleTModel battleTModel;
    private final WarshipTModel warshipTModel;
    private final CountryTModel countryTModel;


    public SeaBattleGUI(Connection connection) {
        super("Sea Battles");
        battleMemberTModel = new BattleMemberTModel();
        countryTModel = new CountryTModel();
        battleTModel = new BattleTModel();
        warshipTModel = new WarshipTModel();
        this.connection = connection;
        warshipRep = new WarshipRep();
        battleRep = new BattleRep();
        setVisible(true);
        setBounds(500, 300, 800, 600);
        model = new DefaultTableModel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(countryPanel);
        CountryRep countryRep = new CountryRep();
        Country country = new Country();
        BattleMemberRep battleMemberRep = new BattleMemberRep();
        BattleMember battleMember = new BattleMember();
        Battle battle = new Battle();
        Warship warship = new Warship();
        initButtons();

        insertButton.addActionListener(e -> {
            switch (comboBox1.getSelectedIndex()) {
                case 0:
                    createDialogBattleInsert();
                    dialogBattleInsert.setVisible(true);
                    break;
                case 1:
                    createDialogBattleMemberInsert();
                    dialogBattleMemberInsert.setVisible(true);
                    break;
                case 2:
                    createDialogWarshipInsert();
                    dialogWarshipInsert.setVisible(true);
                    break;
                case 3:
                    createDialogCountryInsert();
                    dialogCountryInsert.setVisible(true);
                    break;
            }

        });
        confirmBattleMemberInsertButton.addActionListener(e -> {
            battleMember.setBattleName((String) (textFieldInsertBattleMemberName.getModel().getSelectedItem()));
            battleMember.setName((String) textFieldInsertBattleMemberShipName.getModel().getSelectedItem());
            battleMember.setResult(textFieldInsertBattleMemberResult.getText());
            rowCount = battleMemberRep.insertSQL(connection, battleMember);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogBattleMemberInsert.setVisible(false);
            model = battleMemberTModel.getModel(connection, battleMemberRep, model);
            updateUI();
        });
        confirmWarshipInsertButton.addActionListener(e -> {
            warship.setName(textFieldInsertWarshipName.getText());
            warship.setClas(textFieldInsertWarshipClass.getText());
            warship.setCommissionDate((Date) textFieldInsertWarshipCommissionDate.getModel().getValue());
            warship.setDecommissionDate((Date) textFieldInsertWarshipDecommissionDate.getModel().getValue());
            rowCount = warshipRep.insertSQL(connection, warship);
            infoLabel.setText("Было вставлено " + rowCount);
            dialogWarshipInsert.setVisible(false);
            model = warshipTModel.getModel(connection, warshipRep, model);
            updateUI();
        });
        confirmCountryInsertButton.addActionListener(e -> {
            country.setName(textFieldInsertCountryName.getText());
            country.setSide(textFieldInsertCountrySide.getText());
            rowCount = countryRep.insertSQL(connection, country);
            infoLabel.setText("Было вставлено " + rowCount);
            dialogCountryInsert.setVisible(false);
            model = countryTModel.getModel(connection, countryRep, model);
            updateUI();
        });
        confirmInsertBattleButton.addActionListener(e -> {
            battle.setBattleName(textFieldInsertBattleName.getText());
            battle.setBattleDate((Date) textFieldInsertBattleDate.getModel().getValue());
            rowCount = battleRep.insertSQL(connection, battle);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogBattleInsert.setVisible(false);
            model = battleTModel.getModel(connection, battleRep, model);
            updateUI();
        });

        confirmWarshipUpdateButton.addActionListener(e -> {
            warship.setName(textFieldUpdateWarshipName.getText());
            warship.setClas(textFieldUpdateWarshipClass.getText());
            warship.setCommissionDate((Date) textFieldUpdateWarshipCommissionDate.getModel().getValue());
            warship.setDecommissionDate((Date) textFieldUpdateWarshipDecommissionDate.getModel().getValue());
            rowCount = warshipRep.updateSQL(connection, warship);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogWarshipUpdate.setVisible(false);
            model = warshipTModel.getModel(connection, warshipRep, model);
            updateUI();
        });
        confirmCountryUpdateButton.addActionListener(e -> {
            country.setName(textFieldUpdateCountryName.getText());
            country.setSide(textFieldUpdateCountrySide.getText());
            rowCount = countryRep.updateSQL(connection, country);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogCountryUpdate.setVisible(false);
            model = countryTModel.getModel(connection, countryRep, model);
            updateUI();

        });
        confirmUpdateBattleButton.addActionListener(e -> {
            battle.setBattleName(textFieldUpdateBattleName.getText());
            battle.setBattleDate((Date) textFieldUpdateBattleDate.getModel().getValue());
            rowCount = battleRep.updateSQL(connection, battle);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogBattleUpdate.setVisible(false);
            model = battleTModel.getModel(connection, battleRep, model);
            updateUI();
        });
        confirmBattleMemberUpdateButton.addActionListener(e -> {
            battleMember.setId(Integer.parseInt(textFieldUpdateBattleMemberId.getText()));
            battleMember.setBattleName(textFieldUpdateBattleMemberName.getText());
            battleMember.setName(textFieldUpdateBattleMemberShipName.getText());
            battleMember.setResult(textFieldUpdateBattleMemberResult.getText());
            rowCount = battleMemberRep.updateSQl(connection, battleMember);
            infoLabel.setText("Было обновлено " + rowCount);
            dialogBattleMemberUpdate.setVisible(false);
            model = battleMemberTModel.getModel(connection, battleMemberRep, model);
            updateUI();
        });
        deleteButton.addActionListener(e -> {
            rowIdArray = table1.getSelectedRows();
            if (table1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Выберите строку");

            } else {
                switch (comboBox1.getSelectedIndex()) {
                    case 0:
                        rowCount = battleRep.deleteRowsSQL(connection, getNameFromTable(rowIdArray));
                        infoLabel.setText("Было удалено " + rowCount + " строк");
                        model = battleTModel.getModel(connection, battleRep, model);
                        break;
                    case 1:
                        rowCount = battleMemberRep.deleteRowsSQL(connection, getIdFromTable(rowIdArray));
                        infoLabel.setText("Было удалено " + rowCount + " строк");
                        model = battleMemberTModel.getModel(connection, battleMemberRep, model);
                        break;
                    case 2:
                        rowCount = warshipRep.deleteRowsSQL(connection, getNameFromTable(rowIdArray));
                        infoLabel.setText("Было удалено " + rowCount + " строк");
                        model = warshipTModel.getModel(connection, warshipRep, model);
                        break;
                    case 3:
                        rowCount = countryRep.deleteRowsSQL(connection, getNameFromTable(rowIdArray));
                        infoLabel.setText("Было удалено " + rowCount + " строк");
                        model = countryTModel.getModel(connection, countryRep, model);
                        break;
                }
            }
            updateUI();

        });

        updateButton.addActionListener(e -> {
            if (table1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Выберите строку");

            } else {
                switch (comboBox1.getSelectedIndex()) {
                    case 0:
                        createDialogBattleUpdate();
                        dialogBattleUpdate.setVisible(true);
                        break;
                    case 1:
                        createDialogBattleMemberUpdate();
                        textFieldUpdateBattleMemberName.setText((String) table1.getValueAt(table1.getSelectedRow(), 3));
                        textFieldUpdateBattleMemberId.setText(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                        textFieldUpdateBattleMemberShipName.setText((String) table1.getValueAt(table1.getSelectedRow(), 2));
                        dialogBattleMemberUpdate.setVisible(true);
                        break;
                    case 2:
                        createDialogWarshipUpdate();
                        dialogWarshipUpdate.setVisible(true);
                        break;
                    case 3:
                        createDialogCountryUpdate();
                        dialogCountryUpdate.setVisible(true);
                        break;
                }
            }

        });
        selectButton.addActionListener(e -> {
            switch (comboBox1.getSelectedIndex()) {
                case 0:
                    model = battleTModel.getModel(connection, battleRep, model);
                    break;
                case 1:
                    model = battleMemberTModel.getModel(connection, battleMemberRep, model);
                    break;
                case 2:
                    model = warshipTModel.getModel(connection, warshipRep, model);
                    break;
                case 3:
                    model = countryTModel.getModel(connection, countryRep, model);
                    break;
            }
            updateUI();
        });
        updateUI();
    }

    private void updateUI() {
        table1.setModel(model);
        table1.setRowHeight(25);
        countryPanel.updateUI();
    }

    private Collection<String> getNameFromTable(int... rowIdArray) {
        Collection<String> resultName = new HashSet<>();
        Arrays.stream(rowIdArray)
                .mapToObj(rowId -> model.getValueAt(rowId, 0).toString())
                .forEach(resultName::add);
        return resultName;
    }

    private Collection<Integer> getIdFromTable(int... rowIdArray) {
        Collection<Integer> resultId = new HashSet<>();
        IntStream.of(rowIdArray)
                .map(rowId -> Integer.parseInt(model.getValueAt(rowId, 0).toString()))
                .forEach(resultId::add);
        return resultId;
    }


    public void createDialogCountryInsert() {
        dialogCountryInsert = new JDialog();
        dialogCountryInsert.setLayout(null);
        dialogCountryInsert.setTitle("Вставка строки");
        dialogCountryInsert.setBounds(640, 400, 240, 250);
        textFieldInsertCountryName = new JTextField();
        textFieldInsertCountrySide = new JTextField();
        JLabel labelInsertCountryName = new JLabel("Введите название страны:");
        JLabel labelInsertCountrySide = new JLabel("Укажите (Союзник/Страна НБ)");
        confirmCountryInsertButton.setBounds(25, 150, 150, 25);
        labelInsertCountryName.setBounds(10, 10, 200, 25);
        textFieldInsertCountryName.setBounds(10, 40, 200, 25);
        labelInsertCountrySide.setBounds(10, 70, 200, 25);
        textFieldInsertCountrySide.setBounds(10, 100, 200, 25);
        dialogCountryInsert.add(confirmCountryInsertButton);
        dialogCountryInsert.add(labelInsertCountryName);
        dialogCountryInsert.add(textFieldInsertCountryName);
        dialogCountryInsert.add(labelInsertCountrySide);
        dialogCountryInsert.add(textFieldInsertCountrySide);

    }

    public void createDialogCountryUpdate() {
        dialogCountryUpdate = new JDialog();
        dialogCountryUpdate.setLayout(null);
        dialogCountryUpdate.setTitle("Обновление строки");
        dialogCountryUpdate.setBounds(640, 400, 310, 260);
        textFieldUpdateCountryName = new JTextField();
        textFieldUpdateCountrySide = new JTextField();

        JLabel labelUpdateCountryName = new JLabel("Введите название страны:");
        JLabel labelUpdateCountrySide = new JLabel("Введите новое значение (Союзник/Страна НБ)");
        confirmCountryUpdateButton.setBounds(25, 150, 150, 25);
        labelUpdateCountryName.setBounds(10, 10, 200, 25);
        textFieldUpdateCountryName.setBounds(10, 40, 200, 25);
        labelUpdateCountrySide.setBounds(10, 70, 280, 25);
        textFieldUpdateCountrySide.setBounds(10, 100, 200, 25);
        dialogCountryUpdate.add(confirmCountryUpdateButton);
        dialogCountryUpdate.add(labelUpdateCountryName);
        dialogCountryUpdate.add(textFieldUpdateCountryName);
        dialogCountryUpdate.add(labelUpdateCountrySide);
        dialogCountryUpdate.add(textFieldUpdateCountrySide);

    }

    public void createDialogWarshipInsert() {
        dialogWarshipInsert = new JDialog();
        dialogWarshipInsert.setLayout(null);
        dialogWarshipInsert.setTitle("Вставить строку");
        dialogWarshipInsert.setBounds(640, 400, 280, 350);
        textFieldInsertWarshipName = new JTextField();
        textFieldInsertWarshipName.setBounds(10, 40, 200, 25);
        textFieldInsertWarshipClass = new JTextField();
        textFieldInsertWarshipClass.setBounds(10, 100, 200, 25);
        JLabel labelInsertWarshipName = new JLabel("Введите название корабля:");
        labelInsertWarshipName.setBounds(10, 10, 200, 25);
        JLabel labelInsertWarshipClass = new JLabel("Введите класс корабля:");
        labelInsertWarshipClass.setBounds(10, 70, 280, 25);
        JLabel labelInsertWarshipCommissionDate = new JLabel("Введите дату ввода в эксплуатацию:");
        labelInsertWarshipCommissionDate.setBounds(10, 130, 280, 25);
        textFieldInsertWarshipCommissionDate = GUIUtils.createDatePicker();
        textFieldInsertWarshipCommissionDate.setBounds(10, 160, 200, 25);
        textFieldInsertWarshipDecommissionDate = GUIUtils.createDatePicker();
        textFieldInsertWarshipDecommissionDate.setBounds(10, 220, 200, 25);
        JLabel labelInsertWarshipDecommissionDate = new JLabel("Введите дату вывода из эксплуатации:");
        labelInsertWarshipDecommissionDate.setBounds(10, 190, 280, 25);
        confirmWarshipInsertButton.setBounds(25, 270, 150, 25);

        dialogWarshipInsert.add(labelInsertWarshipDecommissionDate);
        dialogWarshipInsert.add(textFieldInsertWarshipCommissionDate);
        dialogWarshipInsert.add(labelInsertWarshipCommissionDate);
        dialogWarshipInsert.add(textFieldInsertWarshipDecommissionDate);
        dialogWarshipInsert.add(confirmWarshipInsertButton);
        dialogWarshipInsert.add(labelInsertWarshipName);
        dialogWarshipInsert.add(textFieldInsertWarshipName);
        dialogWarshipInsert.add(labelInsertWarshipClass);
        dialogWarshipInsert.add(textFieldInsertWarshipClass);
    }

    public void createDialogWarshipUpdate() {
        dialogWarshipUpdate = new JDialog();
        dialogWarshipUpdate.setLayout(null);
        dialogWarshipUpdate.setTitle("Обновить строку");
        dialogWarshipUpdate.setBounds(640, 400, 280, 350);
        textFieldUpdateWarshipName = new JTextField();
        textFieldUpdateWarshipName.setBounds(10, 40, 200, 25);
        textFieldUpdateWarshipClass = new JTextField();
        textFieldUpdateWarshipClass.setBounds(10, 100, 200, 25);
        JLabel labelUpdateWarshipName = new JLabel("Введите название корабля:");
        labelUpdateWarshipName.setBounds(10, 10, 200, 25);
        JLabel labelUpdateWarshipClass = new JLabel("Введите класс корабля:");
        labelUpdateWarshipClass.setBounds(10, 70, 280, 25);
        JLabel labelUpdateWarshipCommissionDate = new JLabel("Введите дату ввода в эксплуатацию:");
        labelUpdateWarshipCommissionDate.setBounds(10, 130, 280, 25);
        textFieldUpdateWarshipCommissionDate = GUIUtils.createDatePicker();
        textFieldUpdateWarshipCommissionDate.setBounds(10, 160, 200, 25);
        textFieldUpdateWarshipDecommissionDate = GUIUtils.createDatePicker();
        textFieldUpdateWarshipDecommissionDate.setBounds(10, 220, 200, 25);
        JLabel labelUpdateWarshipDecommissionDate = new JLabel("Введите дату вывода из эксплуатации:");
        labelUpdateWarshipDecommissionDate.setBounds(10, 190, 280, 25);
        confirmWarshipUpdateButton.setBounds(25, 270, 150, 25);

        dialogWarshipUpdate.add(labelUpdateWarshipDecommissionDate);
        dialogWarshipUpdate.add(textFieldUpdateWarshipCommissionDate);
        dialogWarshipUpdate.add(labelUpdateWarshipCommissionDate);
        dialogWarshipUpdate.add(textFieldUpdateWarshipDecommissionDate);
        dialogWarshipUpdate.add(confirmWarshipUpdateButton);
        dialogWarshipUpdate.add(labelUpdateWarshipName);
        dialogWarshipUpdate.add(textFieldUpdateWarshipName);
        dialogWarshipUpdate.add(labelUpdateWarshipClass);
        dialogWarshipUpdate.add(textFieldUpdateWarshipClass);
    }

    public void createDialogBattleInsert() {
        dialogBattleInsert = new JDialog();
        dialogBattleInsert.setLayout(null);
        dialogBattleInsert.setTitle("Вставка строки");
        dialogBattleInsert.setBounds(640, 400, 240, 250);
        textFieldInsertBattleName = new JTextField();
        textFieldInsertBattleDate = GUIUtils.createDatePicker();
        JLabel labelInsertBattleName = new JLabel("Введите название сражения:");

        JLabel labelInsertBattleDate = new JLabel("Введите дату сражения");
        confirmInsertBattleButton.setBounds(25, 150, 150, 25);
        labelInsertBattleName.setBounds(10, 10, 200, 25);
        textFieldInsertBattleName.setBounds(10, 40, 200, 25);
        labelInsertBattleDate.setBounds(10, 70, 200, 25);
        textFieldInsertBattleDate.setBounds(10, 100, 200, 25);
        dialogBattleInsert.add(confirmInsertBattleButton);
        dialogBattleInsert.add(labelInsertBattleName);
        dialogBattleInsert.add(textFieldInsertBattleName);
        dialogBattleInsert.add(labelInsertBattleDate);
        dialogBattleInsert.add(textFieldInsertBattleDate);

    }

    public void createDialogBattleUpdate() {
        dialogBattleUpdate = new JDialog();
        dialogBattleUpdate.setLayout(null);
        dialogBattleUpdate.setTitle("Обновление строки");
        dialogBattleUpdate.setBounds(640, 400, 240, 250);
        textFieldUpdateBattleName = new JTextField();
        textFieldUpdateBattleDate = GUIUtils.createDatePicker();
        JLabel labelUpdateBattleName = new JLabel("Введите новое название сражения:");
        JLabel labelUpdateBattleDate = new JLabel("Введите новую дату сражения");
        confirmUpdateBattleButton.setBounds(25, 150, 150, 25);
        labelUpdateBattleName.setBounds(10, 10, 200, 25);
        textFieldUpdateBattleName.setBounds(10, 40, 200, 25);
        labelUpdateBattleDate.setBounds(10, 70, 200, 25);
        textFieldUpdateBattleDate.setBounds(10, 100, 200, 25);
        dialogBattleUpdate.add(confirmUpdateBattleButton);
        dialogBattleUpdate.add(labelUpdateBattleName);
        dialogBattleUpdate.add(textFieldUpdateBattleName);
        dialogBattleUpdate.add(labelUpdateBattleDate);
        dialogBattleUpdate.add(textFieldUpdateBattleDate);

    }

    // TODO нормално заполнит данные таблицы
    public void createDialogBattleMemberUpdate() {
        dialogBattleMemberUpdate = new JDialog();
        dialogBattleMemberUpdate.setLayout(null);
        dialogBattleMemberUpdate.setTitle("Обновить строку");
        dialogBattleMemberUpdate.setBounds(640, 400, 280, 350);
        textFieldUpdateBattleMemberName = new JTextField();
        textFieldUpdateBattleMemberName.setEnabled(false);
        textFieldUpdateBattleMemberName.setBounds(10, 100, 200, 25);
        JLabel labelUpdateBatlleMemberId = new JLabel("Введите id участника сражения:");
        labelUpdateBatlleMemberId.setBounds(10, 10, 200, 25);
        textFieldUpdateBattleMemberId = new JTextField();
        textFieldUpdateBattleMemberId.setEnabled(false);
        textFieldUpdateBattleMemberId.setBounds(10, 40, 280, 25);
        JLabel labelUpdateBattleMemberName = new JLabel("Введите название сражения:");
        labelUpdateBattleMemberName.setBounds(10, 70, 280, 25);
        JLabel labelUpdateBattleMemberShipName = new JLabel("Введите название корабля:");
        labelUpdateBattleMemberShipName.setBounds(10, 130, 280, 25);
        textFieldUpdateBattleMemberShipName = new JTextField();
        textFieldUpdateBattleMemberShipName.setEnabled(false);
        textFieldUpdateBattleMemberShipName.setBounds(10, 170, 200, 25);
        textFieldUpdateBattleMemberResult = new JTextField();
        textFieldUpdateBattleMemberResult.setBounds(10, 220, 200, 25);
        JLabel labelUpdateBattleMemberResult = new JLabel("Введите результат сражения:");
        labelUpdateBattleMemberResult.setBounds(10, 190, 280, 25);
        confirmBattleMemberUpdateButton.setBounds(25, 270, 150, 25);

        dialogBattleMemberUpdate.add(labelUpdateBattleMemberResult);
        dialogBattleMemberUpdate.add(labelUpdateBatlleMemberId);
        dialogBattleMemberUpdate.add(textFieldUpdateBattleMemberId);
        dialogBattleMemberUpdate.add(labelUpdateBattleMemberShipName);
        dialogBattleMemberUpdate.add(textFieldUpdateBattleMemberResult);
        dialogBattleMemberUpdate.add(textFieldUpdateBattleMemberShipName);
        dialogBattleMemberUpdate.add(confirmBattleMemberUpdateButton);
        dialogBattleMemberUpdate.add(labelUpdateBattleMemberName);
        dialogBattleMemberUpdate.add(textFieldUpdateBattleMemberName);
    }

    public void createDialogBattleMemberInsert() {
        dialogBattleMemberInsert = new JDialog();
        dialogBattleMemberInsert.setLayout(null);
        dialogBattleMemberInsert.setTitle("Вставить строку");
        dialogBattleMemberInsert.setBounds(640, 400, 280, 350);
        textFieldInsertBattleMemberName = new JComboBox<>();
        textFieldInsertBattleMemberShipName = new JComboBox<>();
        List<String> battleMemberName = battleRep.selectBattleNames(connection);
        for (String item : battleMemberName) {
            textFieldInsertBattleMemberName.addItem(item);
        }
        List<String> battleMemberShipName = warshipRep.selectShipNames(connection);
        for (String item : battleMemberShipName) {
            textFieldInsertBattleMemberShipName.addItem(item);
        }
        textFieldInsertBattleMemberName.setBounds(10, 100, 200, 25);
        JLabel labelInsertBattleMemberName = new JLabel("Введите название сражения:");
        labelInsertBattleMemberName.setBounds(10, 70, 280, 25);
        JLabel labelInsertBattleMemberShipName = new JLabel("Введите название корабля:");
        labelInsertBattleMemberShipName.setBounds(10, 130, 280, 25);

        textFieldInsertBattleMemberShipName.setBounds(10, 160, 200, 25);
        textFieldInsertBattleMemberResult = new JTextField();
        textFieldInsertBattleMemberResult.setBounds(10, 220, 200, 25);
        JLabel labelInsertBattleMemberResult = new JLabel("Введите результат сражения:");
        labelInsertBattleMemberResult.setBounds(10, 190, 280, 25);

        confirmBattleMemberInsertButton.setBounds(25, 270, 150, 25);

        dialogBattleMemberInsert.add(labelInsertBattleMemberResult);
        dialogBattleMemberInsert.add(labelInsertBattleMemberShipName);
        dialogBattleMemberInsert.add(textFieldInsertBattleMemberResult);
        dialogBattleMemberInsert.add(textFieldInsertBattleMemberShipName);
        dialogBattleMemberInsert.add(confirmBattleMemberInsertButton);
        dialogBattleMemberInsert.add(labelInsertBattleMemberName);
        dialogBattleMemberInsert.add(textFieldInsertBattleMemberName);
    }

    private void createUIComponents() {
        table1 = new JTable();
        countryPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        infoLabel = new JLabel();
        table1.setDefaultEditor(Object.class, null);
    }

    private void initButtons() {
        String buttonName = "Confirm";
        confirmCountryUpdateButton = new JButton(buttonName);
        confirmUpdateBattleButton = new JButton(buttonName);
        confirmBattleMemberInsertButton = new JButton(buttonName);
        confirmCountryInsertButton = new JButton(buttonName);
        confirmWarshipInsertButton = new JButton(buttonName);
        confirmWarshipUpdateButton = new JButton(buttonName);
        confirmInsertBattleButton = new JButton(buttonName);
        confirmBattleMemberUpdateButton = new JButton(buttonName);
    }
}
