package GridPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ninjakl
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GridPanel extends JPanel{
    int height= 500;
    int width= 500;
    int gridSize = 20;
    int gridHoriz = ((width)/gridSize);
    int gridVert = ((height)/gridSize);
    int squareSize = height / gridSize;
    Color[][] colorArr = new Color[gridSize][gridSize];
    Color curColor = Color.RED;
    
    public GridPanel(){
        setPreferredSize(new Dimension(height,width));
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = e.getY() / squareSize;
                fillArr(x,y);
                repaint();
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
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = e.getY() / squareSize;
                fillArr(x,y);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
           }
            
        });
    }
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,height,width);
        g.setColor(Color.black);
        for(int i = 0; i <= width; i+= gridHoriz){
            g.drawLine(0,i,width,i);
        }g.drawLine(width-1, 0, width-1, height-1);
        for(int i = 0; i<= height; i+= gridVert){
            g.drawLine(i,0,i,height);
        }g.drawLine(0,height-1,width-1,height-1);
        g.setColor(curColor);
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++)
                if(colorArr[i][j] != null){
                    g.fillRect((i * squareSize) + 1,(j * squareSize) +1, squareSize -1,squareSize -1);
                }
        }
    }   
    public void fillArr(int x, int y){
        colorArr[x][y] = curColor;
    }
}