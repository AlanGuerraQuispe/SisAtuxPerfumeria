package com.aw.swing.g2d;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * User: Juan Carlos Vergara
 * Date: 23-nov-2007
 * Time: 17:08:24
 */
public class ShadowRenderer {
    /**
     * <p>Identifies a change to the size used to render the shadow.</p>
     * <p>When the property change event is fired, the old value and the new
     * value are provided as <code>Integer</code> instances.</p>
     */
    public static final String SIZE_CHANGED_PROPERTY = "shadow_size";

    /**
     * <p>Identifies a change to the opacity used to render the shadow.</p>
     * <p>When the property change event is fired, the old value and the new
     * value are provided as <code>Float</code> instances.</p>
     */
    public static final String OPACITY_CHANGED_PROPERTY = "shadow_opacity";

    /**
     * <p>Identifies a change to the color used to render the shadow.</p>
     */
    public static final String COLOR_CHANGED_PROPERTY = "shadow_color";

    // size of the shadow in pixels (defines the fuzziness)
    private int size = 5;

    // opacity of the shadow
    private float opacity = 0.5f;

    // color of the shadow
    private Color color = Color.BLACK;

    // notifies listeners of properties changes
    private PropertyChangeSupport changeSupport;

    /**
     * <p>Creates a default good looking shadow generator.
     * The default shadow renderer provides the following default values:
     * <ul>
     *   <li><i>size</i>: 5 pixels</li>
     *   <li><i>opacity</i>: 50%</li>
     *   <li><i>color</i>: Black</li>
     * </ul></p>
     * <p>These properties provide a regular, good looking shadow.</p>
     */
    public ShadowRenderer() {
        this(5, 0.5f, Color.BLACK);
    }

    /**
     * <p>A shadow renderer needs three properties to generate shadows.
     * These properties are:</p>
     * <ul>
     *   <li><i>size</i>: The size, in pixels, of the shadow. This property also
     *   defines the fuzzyness.</li>
     *   <li><i>opacity</i>: The opacity, between 0.0 and 1.0, of the shadow.</li>
     *   <li><i>color</i>: The color of the shadow. Shadows are not meant to be
     *   black only.</li>
     * </ul>
     * @param size the size of the shadow in pixels. Defines the fuzziness.
     * @param opacity the opacity of the shadow.
     * @param color the color of the shadow.
     */
    public ShadowRenderer(final int size, final float opacity, final Color color) {
        //noinspection ThisEscapedInObjectConstruction
        changeSupport = new PropertyChangeSupport(this);

        setSize(size);
        setOpacity(opacity);
        setColor(color);
    }

    /**
     * <p>Add a PropertyChangeListener to the listener list. The listener is
     * registered for all properties. The same listener object may be added
     * more than once, and will be called as many times as it is added. If
     * <code>listener</code> is null, no exception is thrown and no action
     * is taken.</p>
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * <p>Remove a PropertyChangeListener from the listener list. This removes
     * a PropertyChangeListener that was registered for all properties. If
     * <code>listener</code> was added more than once to the same event source,
     * it will be notified one less time after being removed. If
     * <code>listener</code> is null, or was never added, no exception is thrown
     * and no action is taken.</p>
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    /**
     * <p>Gets the color used by the renderer to generate shadows.</p>
     * @return this renderer's shadow color
     */
    public Color getColor() {
        return color;
    }

    /**
     * <p>Sets the color used by the renderer to generate shadows.</p>
     * <p>Consecutive calls to {@link #createShadow} will all use this color
     * until it is set again.</p>
     * <p>If the color provided is null, the previous color will be retained.</p>
     * @param shadowColor the generated shadows color
     */
    public void setColor(final Color shadowColor) {
        if (shadowColor != null) {
            Color oldColor = this.color;
            this.color = shadowColor;
            changeSupport.firePropertyChange(COLOR_CHANGED_PROPERTY,
                                             oldColor,
                                             this.color);
        }
    }

    /**
     * <p>Gets the opacity used by the renderer to generate shadows.</p>
     * <p>The opacity is comprised between 0.0f and 1.0f; 0.0f being fully
     * transparent and 1.0f fully opaque.</p>
     * @return this renderer's shadow opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * <p>Sets the opacity used by the renderer to generate shadows.</p>
     * <p>Consecutive calls to {@link #createShadow} will all use this opacity
     * until it is set again.</p>
     * <p>The opacity is comprised between 0.0f and 1.0f; 0.0f being fully
     * transparent and 1.0f fully opaque. If you provide a value out of these
     * boundaries, it will be restrained to the closest boundary.</p>
     * @param shadowOpacity the generated shadows opacity
     */
    public void setOpacity(final float shadowOpacity) {
        float oldOpacity = this.opacity;

        if (shadowOpacity < 0.0) {
            this.opacity = 0.0f;
        } else if (shadowOpacity > 1.0f) {
            this.opacity = 1.0f;
        } else {
            this.opacity = shadowOpacity;
        }

        changeSupport.firePropertyChange(OPACITY_CHANGED_PROPERTY,
                                         oldOpacity,
                                         this.opacity);
    }

    /**
     * <p>Gets the size in pixel used by the renderer to generate shadows.</p>
     * @return this renderer's shadow size
     */
    public int getSize() {
        return size;
    }

    /**
     * <p>Sets the size, in pixels, used by the renderer to generate shadows.</p>
     * <p>The size defines the blur radius applied to the shadow to create the
     * fuzziness.</p>
     * <p>There is virtually no limit to the size. The size cannot be negative.
     * If you provide a negative value, the size will be 0 instead.</p>
     * @param shadowSize the generated shadows size in pixels (fuzziness)
     */
    public void setSize(final int shadowSize) {
        int oldSize = this.size;

        if (shadowSize < 0) {
            this.size = 0;
        } else {
            this.size = shadowSize;
        }

        changeSupport.firePropertyChange(SIZE_CHANGED_PROPERTY,
                                         new Integer(oldSize),
                                         new Integer(this.size));
    }

    /**
     * <p>Generates the shadow for a given picture and the current properties
     * of the renderer.</p>
     * <p>The generated image dimensions are computed as following:</p>
     * <pre>
     * width  = imageWidth  + 2 * shadowSize
     * height = imageHeight + 2 * shadowSize
     * </pre>
     * @param image the picture from which the shadow must be cast
     * @return the picture containing the shadow of <code>image</code>
     */
    public BufferedImage createShadow(final BufferedImage image) {
        // Written by Sesbastien Petrucci
        int shadowSize = size * 2;

        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();

        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;

        int left = size;
        int right = shadowSize - left;

        int yStop = dstHeight - right;

        int shadowRgb = color.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int historyIdx;

        int aSum;

        BufferedImage dst = new BufferedImage(dstWidth, dstHeight,
                                              BufferedImage.TYPE_INT_ARGB);

        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];

        GraphicsUtil.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);

        int lastPixelOffset = right * dstWidth;
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;

        int[] hSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }

        int[] vSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        int srcOffset;

        // horizontal pass : extract the alpha mask from the source picture and
        // blur it into the destination picture
        for (int srcY = 0, dstOffset = left * dstWidth; srcY < srcHeight; srcY++) {

            // first pixels are empty
            for (historyIdx = 0; historyIdx < shadowSize; ) {
                aHistory[historyIdx++] = 0;
            }

            aSum = 0;
            historyIdx = 0;
            srcOffset = srcY * srcWidth;

            // compute the blur average with pixels from the source image
            for (int srcX = 0; srcX < srcWidth; srcX++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;   // store the alpha value only
                                                    // the shadow color will be added in the next pass

                aSum -= aHistory[historyIdx]; // substract the oldest pixel from the sum

                // extract the new pixel ...
                a = srcBuffer[srcOffset + srcX] >>> 24;
                aHistory[historyIdx] = a;   // ... and store its value into history
                aSum += a;                  // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // blur the end of the row - no new pixels to grab
            for (int i = 0; i < shadowSize; i++) {

                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;

                // substract the oldest pixel from the sum ... and nothing new to add !
                aSum -= aHistory[historyIdx];

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        // vertical pass
        for (int x = 0, bufferOffset = 0; x < dstWidth; x++, bufferOffset = x) {

            aSum = 0;

            // first pixels are empty
            for (historyIdx = 0; historyIdx < left;) {
                aHistory[historyIdx++] = 0;
            }

            // and then they come from the dstBuffer
            for (int y = 0; y < right; y++, bufferOffset += dstWidth) {
                int a = dstBuffer[bufferOffset] >>> 24;         // extract alpha
                aHistory[historyIdx++] = a;                     // store into history
                aSum += a;                                      // and add to sum
            }

            bufferOffset = x;
            historyIdx = 0;

            // compute the blur avera`ge with pixels from the previous pass
            for (int y = 0; y < yStop; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;  // store alpha value + shadow color

                aSum -= aHistory[historyIdx];   // substract the oldest pixel from the sum

                a = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;   // extract the new pixel ...
                aHistory[historyIdx] = a;                               // ... and store its value into history
                aSum += a;                                              // ... and add its value to the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }

            // blur the end of the column - no pixels to grab anymore
            for (int y = yStop; y < dstHeight; y++, bufferOffset += dstWidth) {

                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;

                aSum -= aHistory[historyIdx];   // substract the oldest pixel from the sum

                if (++historyIdx >= shadowSize) {
                    historyIdx -= shadowSize;
                }
            }
        }

        GraphicsUtil.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }

}
