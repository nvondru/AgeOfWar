/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author nicolasvondru
 */
public class SkillAnimation extends Transition{
    private final ImageView unitImage;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SkillAnimation(
            ImageView unitImage, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int unitWidth,   int unitHeight) {
        this.unitImage = unitImage;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = unitWidth;
        this.height    = unitHeight;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            unitImage.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}
