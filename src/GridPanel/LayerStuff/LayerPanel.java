/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridPanel.LayerStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import struct.Point;

/**
 *
 * @author Alex
 */
public class LayerPanel extends javax.swing.JPanel implements LayerObserver {

    private Point currentMousePosn;
    private int selected;
    private int layerCount;
    private LayerListener ll;
    private ArrayList<Boolean> displayed;
    private int clickedLayer;

    /**
     * Creates new form LayerPanel
     */
    public LayerPanel() {
        layerCount = 0;
        initComponents();
        selected = -1;
        displayed = new ArrayList<>();
        setFocusable(true);
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                currentMousePosn = new Point(e.getX(), e.getY());
                repaint();
            }

        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                clickedLayer = selectLayer();
                if (clickedLayer != -1 && e.getX() == currentMousePosn.x && e.getY() == currentMousePosn.y) {
                    ll.alertObservers(new LayerEvent((SwingUtilities.isRightMouseButton(e)) ? LayerEvent.LAYER_DESELECTED : LayerEvent.LAYER_SELECTED, clickedLayer));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        repaint();
    }

    public void giveLL(LayerListener ll) {
        this.ll = ll;
        ll.listen(this);
    }

    public int selectLayer() {
        int layer_size = Math.min(getHeight(), getWidth() / layerCount);
        if (currentMousePosn != null) {
            int s = currentMousePosn.x / layer_size;
            if (s >= layerCount) {
                return -1;
            } else {
                return s;
            }
        } else {
            return -1;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (layerCount > 0) {
            int layer_size = Math.min(getHeight(), getWidth() / layerCount);
            for (int i = 0; i < layerCount; i++) {
                g.setColor(new Color(0, 125, 155, 200));
                g.fillRect(layer_size * i, 0, layer_size, layer_size);
                if (i == selected) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.BLACK);
                    if (displayed.get(i)) {
                        g.setColor(Color.RED);
                    }
                }
                g.drawRect(layer_size * i, 0, layer_size, layer_size);
            }
        }
        paintComponents(g);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 291, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ll.alertObservers(new LayerEvent(LayerEvent.LAYER_CREATED, -1));
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void alert(LayerEvent l) {
        if (l.type == LayerEvent.LAYER_CREATED) {
            layerCount++;
            displayed.add(true);
            selected = displayed.size() - 1;
        }
        if (l.type == LayerEvent.LAYER_SELECTED) {
            displayed.set(l.layer, true);
            selected = l.layer;
        }
        if (l.type == LayerEvent.LAYER_DESELECTED) {
            displayed.set(l.layer, false);
            if (l.layer == selected) {
                selected = -1;
                for (int i = displayed.size() - 1; i >= 0; i--) {
                    if (displayed.get(i)) {
                        selected = i;
                        break;
                    }
                }
            }
        }
        if (l.type == LayerEvent.LAYER_DELETED) {
            layerCount--;
        }
        repaint();
    }
}
