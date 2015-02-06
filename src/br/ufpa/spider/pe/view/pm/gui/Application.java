//TODO Colocar nome em todos os componentes de interface gráfica.
//TODO Colocar título em todos componentes que podem receber um título.
//TODO Colocar tooltiptext em todos os componentes da interface gráfica.
//TODO Traduzir demais rótulos mostrados pela classe JFileChooser.
//TODO Mudar ícones do barra de titulos (JFileChooser e View).
package br.ufpa.spider.pe.view.pm.gui;

import java.util.EventObject;
import javax.swing.JOptionPane;
import org.jdesktop.application.SingleFrameApplication;

public class Application extends SingleFrameApplication {

    public Application() {
        addExitListener(new ExitListener() {

            @Override
            public boolean canExit(EventObject event) {
                    return true;
            }

            @Override
            public void willExit(EventObject event) {
            }

        });
    }

    View view;

    @Override
    protected void startup() {
      
    }

    public static Application getApplication() {
        return org.jdesktop.application.Application.getInstance(Application.class);
    }

    public static void main(String[] args) {
        launch(Application.class, args);
    }

}
