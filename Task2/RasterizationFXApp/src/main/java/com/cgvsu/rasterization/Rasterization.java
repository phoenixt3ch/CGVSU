package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import static java.lang.Math.*;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color)
    {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }

    private static void plot(GraphicsContext graphicsContext, double x, double y, double c, Color color) {
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        pixelWriter.setColor((int) x, (int) y, color.deriveColor(0, 1, 1, c));
    }

    private static int ipart(double x) {
        return (int) x;
    }

    private static double fpart(double x) {
        return x - floor(x);
    }

    private static double rfpart(double value) {
        return 1.0 - fpart(value);
    }

    public static void drawLine(GraphicsContext graphicsContext, double x0, double y0, double x1, double y1, Color color) {

        boolean steep = abs(y1 - y0) > abs(x1 - x0);
        if (steep) {
            double temp = x0;
            x0 = y0;
            y0 = temp;
            temp = x1;
            x1 = y1;
            y1 = temp;
        }

        if (x0 > x1) {
            double temp = x0;
            x0 = x1;
            x1 = temp;
            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        double dx = x1 - x0;
        double dy = y1 - y0;
        double gradient = dy / dx;

        double xend = round(x0);
        double yend = y0 + gradient * (xend - x0);
        double xgap = rfpart(x0 + 0.5);
        double xpxl1 = xend;
        double ypxl1 = ipart(yend);

        if (steep) {
            plot(graphicsContext, ypxl1, xpxl1, rfpart(yend) * xgap, color);
            plot(graphicsContext, ypxl1 + 1, xpxl1, fpart(yend) * xgap, color);
        } else {
            plot(graphicsContext, xpxl1, ypxl1, rfpart(yend) * xgap, color);
            plot(graphicsContext, xpxl1, ypxl1 + 1, fpart(yend) * xgap, color);
        }

        double intery = yend + gradient;

        double xend1 = round(x1);
        double yend1 = y1 + gradient * (xend1 - x1);
        double xgap1 = fpart(x1 + 0.5);
        double xpxl2 = xend1;
        double ypxl2 = ipart(yend1);

        if (steep) {
            plot(graphicsContext, ypxl2, xpxl2, rfpart(yend1) * xgap1, color);
            plot(graphicsContext, ypxl2 + 1, xpxl2, fpart(yend1) * xgap1, color);
        } else {
            plot(graphicsContext, xpxl2, ypxl2, rfpart(yend1) * xgap1, color);
            plot(graphicsContext, xpxl2, ypxl2 + 1, fpart(yend1) * xgap1, color);
        }

        for (double x = xpxl1 + 1; x <= xpxl2 - 1; x++) {
            if (steep) {
                plot(graphicsContext, ipart(intery), x, rfpart(intery), color);
                plot(graphicsContext, ipart(intery) + 1, x, fpart(intery), color);
            } else {
                plot(graphicsContext, x, ipart(intery), rfpart(intery), color);
                plot(graphicsContext, x, ipart(intery) + 1, fpart(intery), color);
            }
            intery += gradient;
        }
    }
}
