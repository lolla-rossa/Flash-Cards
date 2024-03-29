package LogOn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartForm extends JFrame {
    private JButton ChooseProfileButton;
    private JButton AddUserButton;
    private JPanel panel;
    private JFrame frame;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();


    public StartForm() {
        frame = this;
        this.setLocation(screenSize.width / 2 - 80, screenSize.height / 2 - 35);
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ChooseProfileButton.addActionListener(new ChoseProfileButtonListener());
        this.AddUserButton.addActionListener(new AddUserButtonListener());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.setAlignmentX(0.5f);
        panel.setMaximumSize(new Dimension(161, 69));
        ChooseProfileButton = new JButton();
        ChooseProfileButton.setText("Войти");
        panel.add(ChooseProfileButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AddUserButton = new JButton();
        AddUserButton.setText("Добавить пользователя");
        panel.add(AddUserButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }


    public class ChoseProfileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            frame.setVisible(false);
            IdentityForm idForm = new IdentityForm();
            idForm.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    frame.setVisible(true);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
            idForm.pack();
            idForm.setVisible(true);
        }
    }

    public class AddUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            frame.setVisible(false);
            NewProfileForm newProfile = new NewProfileForm();
            newProfile.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    frame.setVisible(true);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    frame.setVisible(true);
                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
            newProfile.pack();
        }
    }
}

