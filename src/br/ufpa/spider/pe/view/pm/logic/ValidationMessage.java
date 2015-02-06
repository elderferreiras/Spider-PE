/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpa.spider.pe.view.pm.logic;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufpa.spider.pe.view.JDialog_Spider_Login;
import br.ufpa.spider.pe.view.pm.gui.Application;
import br.ufpa.spider.pe.view.pm.gui.View;

/**
 *
 * @author Specktro
 */
class ValidationMessage {

    private String message;

    private ValidationMessageType type;

    ValidationMessage(String message, ValidationMessageType type) {
        this.message = message;
        this.type = type;
    }

    String getMessage() {
        return message;
    }

    ValidationMessageType getType() {
        return type;
    }

    void show() {
        JPanel mainPanel = JDialog_Spider_Login.view.getMainPanel();
        if(type == ValidationMessageType.ERROR) {
            JOptionPane.showMessageDialog(mainPanel, message, "Erro nos dados da modelagem", JOptionPane.ERROR_MESSAGE);
        }else if(type == ValidationMessageType.CORRECTION) {
            JOptionPane.showMessageDialog(mainPanel, message, "Correção de erro nos dados da modelagem", JOptionPane.INFORMATION_MESSAGE);
        }else if(type == ValidationMessageType.OBLIGATION) {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação obrigatória", JOptionPane.INFORMATION_MESSAGE);
        }else if(type == ValidationMessageType.CONSTRAINT) {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação não permitida", JOptionPane.INFORMATION_MESSAGE);
        }else if (type == ValidationMessageType.WARNING) {
            JOptionPane.showMessageDialog(mainPanel, message, "Possível inconsistência nos dados da modelagem", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, message, "Ação automática", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
