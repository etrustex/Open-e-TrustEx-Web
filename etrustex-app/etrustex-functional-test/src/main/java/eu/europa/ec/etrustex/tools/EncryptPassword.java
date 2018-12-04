package eu.europa.ec.etrustex.tools;

import eu.europa.ec.etrustex.webaccess.webstart.common.view.ui.common.DialogUtil;
import eu.europa.ec.etrustex.webaccess.security.CryptoService;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.Base64;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class EncryptPassword {

    private JFrame ownerFrame;

    private static CryptoService cryptoService;

    static {
        EtxSecurityProvider.init();
        cryptoService = EtxSecurityProvider.getInstance().getCryptoService();
    }

    private PublicKey publicKey;

    public static void main(String[] args) throws Exception {
        EncryptPassword encryptPassword = new EncryptPassword();
        encryptPassword.show();
    }

    public EncryptPassword() throws Exception {
        try {
            publicKey = loadKey("./../resources/b4PartyRequestPublicKey.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (publicKey == null) {
            JOptionPane pane = new JOptionPane("Could not load the public key from key file",
                                   JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
            JDialog dialog = DialogUtil.makeModal(pane.createDialog(ownerFrame, "Public Key Loader"));
            dialog.pack();
            dialog.setLocationRelativeTo(ownerFrame);
            dialog.setVisible(true);

            System.exit(-1);
        }

        ownerFrame = new JFrame("Encrypt Password for Node");
        ownerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ownerFrame.setResizable(false);

        //add widgets
        addWidgetsToContainer();

        show();
    }

    private void addWidgetsToContainer() {

        JPanel hashPasswordPanel = new JPanel();
        hashPasswordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        hashPasswordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        hashPasswordPanel.setLayout(new BoxLayout(hashPasswordPanel, BoxLayout.Y_AXIS));

        hashPasswordPanel.add(new JLabel("Password:"));
        hashPasswordPanel.add(getYSeparator(3));

        final JTextField passwordField = new JTextField(10);
        //        passwordField.setEchoChar('*');
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        hashPasswordPanel.add(passwordField);

        hashPasswordPanel.add(getYSeparator(10));

        //the hashed password
        hashPasswordPanel.add(new JLabel("Encrypted Password:"));
        hashPasswordPanel.add(getYSeparator(3));

        //the challenge response text
        final JTextField encryptedPasswordResult = new JTextField(20);
        encryptedPasswordResult.setEditable(false);
        encryptedPasswordResult.setAlignmentX(Component.LEFT_ALIGNMENT);
        encryptedPasswordResult.addMouseListener(new CopyContextualMenu());
        hashPasswordPanel.add(encryptedPasswordResult);
        hashPasswordPanel.add(getYSeparator(10));

        JPanel hashButtonPanel = new JPanel();
        hashButtonPanel.setLayout(new BoxLayout(hashButtonPanel, BoxLayout.X_AXIS));
        hashButtonPanel.add(Box.createHorizontalGlue());
        hashButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        hashButtonPanel.add(encryptButton);
        hashPasswordPanel.add(hashButtonPanel);

        JButton button = new JButton("Close");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerFrame.dispatchEvent(new WindowEvent(ownerFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        hashPasswordPanel.add(button);

        ownerFrame.add(hashPasswordPanel);

        //link things together
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = passwordField.getText();
                if (pass.isEmpty()) {
                    encryptedPasswordResult.setText("");
                } else {
                    try {
                        byte[] encryptedContent = cryptoService.asymmetricEncrypt(publicKey, pass.getBytes());
                        encryptedPasswordResult.setText("<encrypted>" + new String(Hex.encodeHex(encryptedContent)) + "</encrypted>");
                    } catch (Exception ex) {
                        encryptedPasswordResult.setText("Unable to encrypt the password: " + ex.getMessage());
                    }
                }
            }
        });
    }

    public void show() {
        ownerFrame.pack();
        ownerFrame.setLocationRelativeTo(ownerFrame);
        ownerFrame.setVisible(true);
    }

    public static Component getYSeparator(int height) {
        return Box.createVerticalStrut(height);
    }

    private static PublicKey loadKey(String certificateFile) throws Exception {
        byte[] base64EncodedPublicKey = readFileContent(certificateFile);

        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(base64EncodedPublicKey)));
    }

    private static byte[] readFileContent(String filePath) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        InputStream certInputStream = new FileInputStream(filePath);
        while ((length = certInputStream.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, length);
        }

        return baos.toByteArray();
    }
}
