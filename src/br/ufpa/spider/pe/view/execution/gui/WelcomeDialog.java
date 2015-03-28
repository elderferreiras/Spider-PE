package br.ufpa.spider.pe.view.execution.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class WelcomeDialog extends javax.swing.JDialog {

    public enum ClickedButton {

        CLOSE, HELP, NEW, OPEN;

    }

    public WelcomeDialog(java.awt.Frame parent) {
        
        super(parent);
        
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        buttonDescriptionLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.execution.gui.Application.class).getContext().getResourceMap(WelcomeDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        newButton.setIcon(resourceMap.getIcon("newButton.icon")); // NOI18N
        newButton.setText(resourceMap.getString("newButton.text")); // NOI18N
        newButton.setBorderPainted(false);
        newButton.setContentAreaFilled(false);
        newButton.setDefaultCapable(false);
        newButton.setFocusPainted(false);
        newButton.setName("newButton"); // NOI18N
        newButton.setPreferredSize(new java.awt.Dimension(100, 100));
        newButton.setRolloverIcon(resourceMap.getIcon("newButton.rolloverIcon")); // NOI18N
        newButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anyButtonMouseExited(evt);
            }
        });
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        closeButton.setText(resourceMap.getString("closeButton.text")); // NOI18N
        closeButton.setMaximumSize(new java.awt.Dimension(75, 23));
        closeButton.setMinimumSize(new java.awt.Dimension(75, 23));
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        helpButton.setIcon(resourceMap.getIcon("helpButton.icon")); // NOI18N
        helpButton.setBorderPainted(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setDefaultCapable(false);
        helpButton.setFocusPainted(false);
        helpButton.setName("helpButton"); // NOI18N
        helpButton.setPreferredSize(new java.awt.Dimension(100, 100));
        helpButton.setRolloverIcon(resourceMap.getIcon("helpButton.rolloverIcon")); // NOI18N
        helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anyButtonMouseExited(evt);
            }
        });
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        openButton.setIcon(resourceMap.getIcon("openButton.icon")); // NOI18N
        openButton.setBorderPainted(false);
        openButton.setContentAreaFilled(false);
        openButton.setDefaultCapable(false);
        openButton.setFocusPainted(false);
        openButton.setName("openButton"); // NOI18N
        openButton.setPreferredSize(new java.awt.Dimension(100, 100));
        openButton.setRolloverIcon(resourceMap.getIcon("openButton.rolloverIcon")); // NOI18N
        openButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anyButtonMouseExited(evt);
            }
        });
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        buttonDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonDescriptionLabel.setText(resourceMap.getString("buttonDescriptionLabel.text")); // NOI18N
        buttonDescriptionLabel.setBorder(new javax.swing.border.LineBorder(resourceMap.getColor("buttonDescriptionLabel.border.lineColor"), 1, true)); // NOI18N
        buttonDescriptionLabel.setName("buttonDescriptionLabel"); // NOI18N

        logoLabel.setIcon(resourceMap.getIcon("logoLabel.icon")); // NOI18N
        logoLabel.setText(resourceMap.getString("logoLabel.text")); // NOI18N
        logoLabel.setName("logoLabel"); // NOI18N

        separator.setName("separator"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDescriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(separator, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(285, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openButton, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(helpButton, 0, 0, Short.MAX_VALUE)
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        clickedButton = ClickedButton.NEW;
        dispose();
    }//GEN-LAST:event_newButtonActionPerformed

    private void newButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newButtonMouseEntered
        buttonDescriptionLabel.setText("Inicia uma nova modelagem de processos.");
    }//GEN-LAST:event_newButtonMouseEntered

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        clickedButton = ClickedButton.OPEN;
        dispose();
    }//GEN-LAST:event_openButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void openButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openButtonMouseEntered
        buttonDescriptionLabel.setText(
                "Abre uma modelagem de processos existente.");
    }//GEN-LAST:event_openButtonMouseEntered

    private void anyButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anyButtonMouseExited
        buttonDescriptionLabel.setText("Clique em uma das figuras para começar.");
    }//GEN-LAST:event_anyButtonMouseExited

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        clickedButton = ClickedButton.HELP;
        dispose();
}//GEN-LAST:event_helpButtonActionPerformed

    private void helpButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseEntered
        buttonDescriptionLabel.setText("Mostra o conteúdo da ajuda.");
}//GEN-LAST:event_helpButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonDescriptionLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton openButton;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables

    private ClickedButton clickedButton = ClickedButton.CLOSE;

    public ClickedButton getClickedButton() {
        return clickedButton;
    }

}
