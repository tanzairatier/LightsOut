/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lightsout;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tanza
 */
public class LightsOutWindow extends JPanel {
    
    public static LightsOutWindow_Main window_main;
    public static LightsOutWindow_StageSelect window_stageselect;
    public static LightsOutWindow_Info window_info;
    static int window_controller;
    static LightsOutGameLogic game_logic;
    JButton button1, button2;
    
    public LightsOutWindow() throws IOException {
        this.setPreferredSize(new Dimension(500,400));
        game_logic = new LightsOutGameLogic();
        CardLayout card_manager;
        window_main = new LightsOutWindow_Main();
        window_stageselect = new LightsOutWindow_StageSelect();
        window_info = new LightsOutWindow_Info();
        this.setLayout(new CardLayout());
        this.add(window_main, "Lights Out! Extiguish the Lights!");
        this.add(window_stageselect, "Lights Out! Select a Stage");
        this.add(window_info, "Lights Out!  Info");
        
        window_controller = 1;
        repaint();
        
    }
    
    public void changeScreen(JPanel next)
    {
        for(Component c: this.getComponents())
        {
            c.setVisible(false);
            
        }
        
        JPanel content = next;
        content.requestFocus(true);
        
        content.setVisible(true);
        
        content.validate();
        content.revalidate();
        repaint();
    }
    
    
    public void paint(Graphics g)
    {
        super.paint(g);
        switch (window_controller)
        {
            case 1:
                ((CardLayout)(this.getLayout())).show(this, "Lights Out! Select a Stage");
                //changeScreen(window_stageselect);
                break;
            case 2:
                ((CardLayout)(this.getLayout())).show(this, "Lights Out! Extiguish the Lights!");
                //changeScreen(gameWindow);
                break;
            case 3:
                ((CardLayout)(this.getLayout())).show(this, "Lights Out!  Info");
                //changeScreen(gameWindow);
                break;
        }
        repaint();
    }
    
}
