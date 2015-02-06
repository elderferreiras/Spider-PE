package br.ufpa.spider.pe.view.pm.gui;

import br.ufpa.spider.pe.view.pm.logic.ModellingController;
import br.ufpa.spider.pe.view.pm.model.Modelling;
import br.ufpa.spider.pe.view.pm.model.Relationship;
import br.ufpa.spider.pe.view.pm.model.StereotypesGroup;

public class RelationshipPropertiesPanel extends javax.swing.JPanel {

    private int relationshipId;

    public RelationshipPropertiesPanel() {
        initComponents();
    }

     public void setRelationship(Relationship relationship) {
        relationshipId = relationship.getId();
        labelTextField.setText(relationship.getLabel());
        descriptionTextArea.setText(relationship.getDescription());
        objectivesTextArea.setText(relationship.getObjectives());
        additionalInformationsTextArea.setText(relationship.getAdditionalInformations());
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : relationship.getStereotypesIds()) {
            stringBuilder.append(Modelling.getModelling().getStereotype(i).getName());
            stringBuilder.append("\n");
        }
        stereotypesTextArea.setText(stringBuilder.toString());
        stereotypesButton.setEnabled(false);
        for(StereotypesGroup stereotypesGroup : Modelling.getModelling().getStereotypesGroups()) {
            if(stereotypesGroup.getType().equalsToComponentOrRelationshipType(relationship.getType())) {
                stereotypesButton.setEnabled(true);
                break;
            }
        }
    }

    @Override
    public boolean requestFocusInWindow() {
        if (super.requestFocusInWindow()) {
            if (labelTextField.requestFocusInWindow()) {
                labelTextField.setSelectionStart(0);
                labelTextField.setSelectionEnd(labelTextField.getText().length());
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLabel = new javax.swing.JLabel();
        labelTextField = new javax.swing.JTextField();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        objetivesScrollPane = new javax.swing.JScrollPane();
        objectivesTextArea = new javax.swing.JTextArea();
        stereotypesButton = new javax.swing.JButton();
        additionalInformationsScrollPane = new javax.swing.JScrollPane();
        additionalInformationsTextArea = new javax.swing.JTextArea();
        additionalInformationsLabel = new javax.swing.JLabel();
        objectivesButton = new javax.swing.JButton();
        descriptionLabel = new javax.swing.JLabel();
        stereotypesLabel = new javax.swing.JLabel();
        objectivesLabel = new javax.swing.JLabel();
        stereotypesScrollPane = new javax.swing.JScrollPane();
        stereotypesTextArea = new javax.swing.JTextArea();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.ufpa.spider.pe.view.pm.gui.Application.class).getContext().getResourceMap(RelationshipPropertiesPanel.class);
        labelLabel.setText(resourceMap.getString("labelLabel.text")); // NOI18N
        labelLabel.setName("labelLabel"); // NOI18N

        labelTextField.setName("labelTextField"); // NOI18N
        labelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTextFieldActionPerformed(evt);
            }
        });
        labelTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                labelTextFieldFocusLost(evt);
            }
        });

        descriptionScrollPane.setName("descriptionScrollPane"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(resourceMap.getFont("descriptionTextArea.font")); // NOI18N
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        descriptionTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTextAreaFocusLost(evt);
            }
        });
        descriptionScrollPane.setViewportView(descriptionTextArea);

        objetivesScrollPane.setName("objetivesScrollPane"); // NOI18N

        objectivesTextArea.setColumns(20);
        objectivesTextArea.setEditable(false);
        objectivesTextArea.setFont(resourceMap.getFont("objectivesTextArea.font")); // NOI18N
        objectivesTextArea.setLineWrap(true);
        objectivesTextArea.setRows(5);
        objectivesTextArea.setName("objectivesTextArea"); // NOI18N
        objetivesScrollPane.setViewportView(objectivesTextArea);

        stereotypesButton.setText(resourceMap.getString("stereotypesButton.text")); // NOI18N
        stereotypesButton.setName("stereotypesButton"); // NOI18N
        stereotypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stereotypesButtonActionPerformed(evt);
            }
        });

        additionalInformationsScrollPane.setName("additionalInformationsScrollPane"); // NOI18N

        additionalInformationsTextArea.setColumns(20);
        additionalInformationsTextArea.setFont(resourceMap.getFont("additionalInformationsTextArea.font")); // NOI18N
        additionalInformationsTextArea.setLineWrap(true);
        additionalInformationsTextArea.setRows(5);
        additionalInformationsTextArea.setName("additionalInformationsTextArea"); // NOI18N
        additionalInformationsTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                additionalInformationsTextAreaFocusLost(evt);
            }
        });
        additionalInformationsScrollPane.setViewportView(additionalInformationsTextArea);

        additionalInformationsLabel.setText(resourceMap.getString("additionalInformationsLabel.text")); // NOI18N
        additionalInformationsLabel.setName("additionalInformationsLabel"); // NOI18N

        objectivesButton.setText(resourceMap.getString("objectivesButton.text")); // NOI18N
        objectivesButton.setName("objectivesButton"); // NOI18N
        objectivesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectivesButtonActionPerformed(evt);
            }
        });

        descriptionLabel.setText(resourceMap.getString("descriptionLabel.text")); // NOI18N
        descriptionLabel.setName("descriptionLabel"); // NOI18N

        stereotypesLabel.setText(resourceMap.getString("stereotypesLabel.text")); // NOI18N
        stereotypesLabel.setName("stereotypesLabel"); // NOI18N

        objectivesLabel.setText(resourceMap.getString("objectivesLabel.text")); // NOI18N
        objectivesLabel.setName("objectivesLabel"); // NOI18N

        stereotypesScrollPane.setName("stereotypesScrollPane"); // NOI18N

        stereotypesTextArea.setColumns(20);
        stereotypesTextArea.setEditable(false);
        stereotypesTextArea.setFont(resourceMap.getFont("stereotypesTextArea.font")); // NOI18N
        stereotypesTextArea.setLineWrap(true);
        stereotypesTextArea.setRows(5);
        stereotypesTextArea.setName("stereotypesTextArea"); // NOI18N
        stereotypesScrollPane.setViewportView(stereotypesTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLabel)
                        .addGap(10, 10, 10)
                        .addComponent(labelTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                    .addComponent(objetivesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(objectivesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(objectivesButton))
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stereotypesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stereotypesButton))
                    .addComponent(stereotypesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(additionalInformationsLabel)
                    .addComponent(additionalInformationsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLabel)
                    .addComponent(labelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectivesLabel)
                    .addComponent(objectivesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objetivesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stereotypesLabel)
                    .addComponent(stereotypesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stereotypesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalInformationsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalInformationsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateRelationship() {
        Relationship relationship = Modelling.getModelling().getRelationshipToUpdate(ModellingController.getElementShowingPropertiesId());
        if (relationshipId == relationship.getId()) {
            relationship.setLabel(labelTextField.getText());
            relationship.setDescription(descriptionTextArea.getText());
            relationship.setObjectives(objectivesTextArea.getText());
            relationship.setAdditionalInformations(additionalInformationsTextArea.getText());
            if (!ModellingController.commitRelationship(relationship)) {
                setRelationship(Modelling.getModelling().getRelationship(relationship.getId()));
            }
        }
    }
    
    private void labelTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_labelTextFieldFocusLost
        updateRelationship();
}//GEN-LAST:event_labelTextFieldFocusLost

    private void labelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTextFieldActionPerformed
        updateRelationship();
    }//GEN-LAST:event_labelTextFieldActionPerformed

    private void descriptionTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusLost
        updateRelationship();
}//GEN-LAST:event_descriptionTextAreaFocusLost

    private void additionalInformationsTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_additionalInformationsTextAreaFocusLost
        updateRelationship();
}//GEN-LAST:event_additionalInformationsTextAreaFocusLost

    private void objectivesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectivesButtonActionPerformed
        /*
    	View view = (View) Application.getApplication().getMainView();
        view.getListDialog().reset();
        view.getListDialog().setTitle("Definir lista de objetivos");
        view.getListDialog().setItemsText("Lista de objetivos:");
        view.getListDialog().setNewItemText("Objetivo:");
        view.getListDialog().setData(Modelling.getModelling().getRelationshipToUpdate(ModellingController.getElementShowingPropertiesId()).getObjectives());
        view.showListDialog();
        if (view.getListDialog().getClickedButton() == ListDialog.ClickedButton.OK) {
            objectivesTextArea.setText(view.getListDialog().getData());
        }
        updateRelationship();
        */
}//GEN-LAST:event_objectivesButtonActionPerformed

    private void stereotypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stereotypesButtonActionPerformed
        /*
        View view = (View) Application.getApplication().getMainView();
        view.showElementStereotypesDialog();
        */
    }//GEN-LAST:event_stereotypesButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel additionalInformationsLabel;
    private javax.swing.JScrollPane additionalInformationsScrollPane;
    private javax.swing.JTextArea additionalInformationsTextArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel labelLabel;
    private javax.swing.JTextField labelTextField;
    private javax.swing.JButton objectivesButton;
    private javax.swing.JLabel objectivesLabel;
    private javax.swing.JTextArea objectivesTextArea;
    private javax.swing.JScrollPane objetivesScrollPane;
    private javax.swing.JButton stereotypesButton;
    private javax.swing.JLabel stereotypesLabel;
    private javax.swing.JScrollPane stereotypesScrollPane;
    private javax.swing.JTextArea stereotypesTextArea;
    // End of variables declaration//GEN-END:variables

}
