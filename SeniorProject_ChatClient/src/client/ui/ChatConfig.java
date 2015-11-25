package client.ui;

import client.network.connections.ConnectionHandler;
import javax.swing.JOptionPane;
import shared.impl.AuthenticationRequest;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class ChatConfig extends javax.swing.JFrame implements ServerStatus {

    private static String host;
    private static Integer port;
    /**
     * Creates new form ChatConfig
     */
    public void setup(String host, Integer port) {
        ChatConfig.host = host;
        ChatConfig.port = port;
        setLookAndFeel();
        initComponents();
        //Center window on screen
        setLocationRelativeTo(null);
        //Show window
        setVisible(true);
    }

    public static String getHost() {
        return host;
    }
    
    public static Integer getPort() {
        return port;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameTextField = new javax.swing.JTextField();
        serverLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Client");
        setName("chatConfig"); // NOI18N
        setResizable(false);

        usernameTextField.setEnabled(false);
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyPressed(evt);
            }
        });

        serverLabel.setText("Server");

        usernameLabel.setText("Username");

        connectButton.setText("Connect");
        connectButton.setEnabled(false);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        statusLabel.setText("Offline");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(connectButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(serverLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusLabel)
                        .addGap(177, 177, 177))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLabel)
                    .addComponent(statusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(connectButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        requestLogin();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void usernameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyPressed
        //Shortcut enter to login
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            requestLogin();
    }//GEN-LAST:event_usernameTextFieldKeyPressed

    private void requestLogin() {
        if (!isValidUsername(getUsername()))
                JOptionPane.showMessageDialog(this, "Username cannot be null.", "Chat Client", JOptionPane.WARNING_MESSAGE);
        else {
            toggleCredentials(false);
            ConnectionHandler.getConnection().send(new AuthenticationRequest(getUsername()));
        }
    }
    
    public void login(String response) {
		if (response != null) {
			JOptionPane.showMessageDialog(this, response, "Chat Client", JOptionPane.WARNING_MESSAGE);
                        toggleCredentials(true);
		} else {
                    ChatClient client = new ChatClient();
                    client.setup(getUsername());
                    FrameHandler.launchFrame(client);
                    dispose();
                }
	}
    
    private void toggleCredentials(boolean enabled) {
		usernameTextField.setEnabled(enabled);
		connectButton.setEnabled(enabled);
	}
    
    public static Boolean isValidServer(String server) {
        return !server.isEmpty();
    }
    
    public static Boolean isValidPort(Integer port) {
        return port > 0 && port < 65536;
    }
    
    public static Boolean isValidUsername(String username) {
        return !username.isEmpty();
    }
    
    private void setLookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    private String getUsername() {
        return usernameTextField.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void handleServerStatus(boolean online) {
        statusLabel.setText(online ? "Online" : "Offline");
        toggleCredentials(online);
    }
}
