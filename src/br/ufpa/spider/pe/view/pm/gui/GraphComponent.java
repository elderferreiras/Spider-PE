package br.ufpa.spider.pe.view.pm.gui;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxGraph;

public class GraphComponent extends mxGraphComponent {

    public GraphComponent(mxGraph graph) {
        super(graph);
    }

    @Override
    public mxInteractiveCanvas createCanvas() {
        return new InteractiveCanvas();
    }

}
