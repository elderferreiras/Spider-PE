package br.ufpa.spider.pe.view.execution.gui;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxConnectorShape;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxLine;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxUtils;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

public class ConnectorShape extends mxConnectorShape {

    @Override
    public mxPoint paintMarker(mxGraphics2DCanvas canvas,
                               List<mxPoint> points,
                               Map<String, Object> style,
                               boolean source) {
        float strokeWidth = (float) (mxUtils.getFloat(style,
                                                      mxConstants.STYLE_STROKEWIDTH, 1) * canvas.getScale());
        Object type = mxUtils.getString(style,
                                        (source) ? mxConstants.STYLE_STARTARROW
                                        : mxConstants.STYLE_ENDARROW, "");
        float size = (float) (mxUtils.getFloat(style,
                                               (source) ? mxConstants.STYLE_STARTSIZE
                                               : mxConstants.STYLE_ENDSIZE,
                                               mxConstants.DEFAULT_MARKERSIZE));
        Color color = mxUtils.getColor(style, mxConstants.STYLE_STROKECOLOR);
        canvas.getGraphics().setColor(color);

        double absSize = size * canvas.getScale();

        mxLine markerVector = getMarkerVector(points, source, absSize);
        mxPoint p0 = new mxPoint(markerVector.getX(), markerVector.getY());
        mxPoint pe = markerVector.getEndPoint();

        mxPoint offset = null;

        // Computes the norm and the inverse norm
        double dx = pe.getX() - p0.getX();
        double dy = pe.getY() - p0.getY();

        double dist = Math.max(1, Math.sqrt(dx * dx + dy * dy));
        double unitX = dx / dist;
        double unitY = dy / dist;
        double nx = unitX * absSize;
        double ny = unitY * absSize;

        // Allow for stroke width in the end point used and the
        // orthogonal vectors describing the direction of the
        // marker
        double strokeX = unitX * strokeWidth;
        double strokeY = unitY * strokeWidth;
        pe = (mxPoint) pe.clone();
        pe.setX(pe.getX() - strokeX);
        pe.setY(pe.getY() - strokeY);

        if (type.equals(mxConstants.ARROW_CLASSIC)
            || type.equals(mxConstants.ARROW_BLOCK)) {
            Polygon poly = new Polygon();
            poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
            poly.addPoint((int) Math.round(pe.getX() - nx - ny / 2), (int) Math.round(pe.getY() - ny + nx / 2));

            if (type.equals(mxConstants.ARROW_CLASSIC)) {
                poly.addPoint((int) Math.round(pe.getX() - nx * 3 / 4),
                              (int) Math.round(pe.getY() - ny * 3 / 4));
            }

            poly.addPoint((int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2));

            canvas.fillShape(poly);
            canvas.getGraphics().draw(poly);
            offset = new mxPoint(-nx - strokeX / 2.0, -ny - strokeY / 2.0);
        } else if (type.equals(mxConstants.ARROW_OPEN)) {
            nx *= 1.2;
            ny *= 1.2;

            canvas.getGraphics().draw(
                    new Line2D.Float((int) Math.round(pe.getX() - nx - ny / 2),
                                     (int) Math.round(pe.getY() - ny + nx / 2),
                                     (int) Math.round(pe.getX() - nx / 6), (int) Math.round(pe.getY() - ny / 6)));
            canvas.getGraphics().draw(
                    new Line2D.Float((int) Math.round(pe.getX() - nx / 6),
                                     (int) Math.round(pe.getY() - ny / 6), (int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2)));
            offset = new mxPoint(-nx / 2 - strokeX / 2.0, -ny / 2 - strokeY / 2.0);
        } else if (type.equals("generalization")) {
            Polygon poly = new Polygon();
            poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
            poly.addPoint((int) Math.round(pe.getX() - nx - ny / 2), (int) Math.round(pe.getY() - ny + nx / 2));
            poly.addPoint((int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2));
            canvas.getGraphics().draw(poly);
            offset = new mxPoint(-nx - strokeX / 2.0, -ny - strokeY / 2.0);
        } else if (type.equals(mxConstants.ARROW_OPEN)) {
            nx *= 1.2;
            ny *= 1.2;

            canvas.getGraphics().draw(
                    new Line2D.Float((int) Math.round(pe.getX() - nx - ny / 2),
                                     (int) Math.round(pe.getY() - ny + nx / 2),
                                     (int) Math.round(pe.getX() - nx / 6), (int) Math.round(pe.getY() - ny / 6)));
            canvas.getGraphics().draw(
                    new Line2D.Float((int) Math.round(pe.getX() - nx / 6),
                                     (int) Math.round(pe.getY() - ny / 6), (int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2)));
            offset = new mxPoint(-nx / 2 - strokeX / 2.0, -ny / 2 - strokeY / 2.0);

        } else if (type.equals(mxConstants.ARROW_OVAL)) {
            nx *= 1.2;
            ny *= 1.2;
            absSize *= 1.2;

            int cx = (int) Math.round(pe.getX() - nx / 2);
            int cy = (int) Math.round(pe.getY() - ny / 2);
            int a = (int) Math.round(absSize / 2);
            int a2 = (int) Math.round(absSize);
            Shape shape = new Ellipse2D.Float(cx - a, cy - a, a2, a2);

            canvas.fillShape(shape);
            canvas.getGraphics().draw(shape);

            offset = new mxPoint(-nx / 2 - strokeX / 2.0, -ny / 2 - strokeY / 2.0);
        } else if (type.equals(mxConstants.ARROW_DIAMOND)) {
            nx *= 1.2;
            ny *= 1.2;

            Polygon poly = new Polygon();
            poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
            poly.addPoint((int) Math.round(pe.getX() - nx / 2 - ny / 2), (int) Math.round(pe.getY() + nx / 2 - ny / 2));
            poly.addPoint((int) Math.round(pe.getX() - nx), (int) Math.round(pe.getY() - ny));
            poly.addPoint((int) Math.round(pe.getX() - nx / 2 + ny / 2), (int) Math.round(pe.getY() - ny / 2 - nx / 2));

            canvas.fillShape(poly);
            canvas.getGraphics().draw(poly);

            offset = new mxPoint(-nx / 2 - strokeX / 2.0, -ny / 2 - strokeY / 2.0);
        } else if (type.equals("aggregation")) {
            nx *= 1.2;
            ny *= 1.2;

            Polygon poly = new Polygon();
            poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
            poly.addPoint((int) Math.round(pe.getX() - nx / 2 - ny / 2), (int) Math.round(pe.getY() + nx / 2 - ny / 2));
            poly.addPoint((int) Math.round(pe.getX() - nx), (int) Math.round(pe.getY() - ny));
            poly.addPoint((int) Math.round(pe.getX() - nx / 2 + ny / 2), (int) Math.round(pe.getY() - ny / 2 - nx / 2));

            canvas.getGraphics().draw(poly);

            offset = new mxPoint(-nx - strokeX / 2.0, -ny - strokeY / 2.0);
        } else {
            // Offset for the strokewidth
            nx = dx * strokeWidth / dist;
            ny = dy * strokeWidth / dist;

            offset = new mxPoint(-strokeX / 2.0, -strokeY / 2.0);
        }

        return offset;

    }

}
