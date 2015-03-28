package br.ufpa.spider.pe.view.execution.gui;

import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxConstants;

public class InteractiveCanvas extends mxInteractiveCanvas {

    static {
        putShape(mxConstants.SHAPE_CONNECTOR, new ConnectorShape());
    }

}
