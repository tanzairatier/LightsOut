/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lightsout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tanza
 */
public class LightsOutWindow_StageSelect extends JPanel {
    
    JButton button1, button2;
    
    JButton[] stage_button;
    JPanel top_panel;
    JPanel bottom_panel;
    JPanel stage_buttons_panel;
    
    JLabel bottom_text;
    JLabel title_text;
    
    Image solved, unsolved, perfect;
    final BufferedImage game_info, game_logo;
    public LightsOutWindow_StageSelect() throws IOException {
        
        
        bottom_text = new JLabel("Select a Stage");
        title_text = new JLabel("<html><font size='20'>Lights Out!</font></html>");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        solved = ImageIO.read(getClass().getResource("level_solved.png"));
        unsolved = ImageIO.read(getClass().getResource("level_unsolved.png"));
        perfect = ImageIO.read(getClass().getResource("level_perfect.png"));
        game_info = ImageIO.read(getClass().getResource("game_info.png"));
        game_logo = ImageIO.read(getClass().getResource("lights_out_logo.png"));
        top_panel = new JPanel();
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.2;
        c.weighty = 0.2;
        this.add(top_panel, c);
        top_panel.setOpaque(false);
        //top_panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        top_panel.add(title_text);
       
        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.8; c.weighty = 0.7;
        JPanel dummy = new JPanel();
        dummy.setOpaque(false);
        this.add(dummy, c);
        
        stage_buttons_panel = new JPanel();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.2;
        c.weighty = 0.7;
        this.add(stage_buttons_panel, c);
        stage_buttons_panel.setOpaque(false);
        //stage_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        stage_buttons_panel.setLayout(new GridLayout(10,5, 10, 2));
        
        c.gridx = 2; c.gridy = 1; c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.8; c.weighty = 0.7;
        JPanel dummy2 = new JPanel();
        dummy2.setOpaque(false);
        
        JButton info_button = new JButton("Info");
        info_button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               LightsOutWindow.window_controller = 3;
           } 
        });
        dummy2.add(info_button);
        this.add(dummy2, c);
        
        bottom_panel = new JPanel();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.2;
        c.weighty = 0.1;
        this.add(bottom_panel, c);
        bottom_panel.setOpaque(false);
        //bottom_panel.setBorder(BorderFactory.createLineBorder(Color.RED));
        bottom_panel.add(bottom_text);
        
        
        
        this.setBackground(new Color(72, 209, 255));
        
        stage_button = new JButton[50];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                final int stage = i*5+j;
                stage_button[stage] = new JButton(""+(stage+1));
                stage_button[stage].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        LightsOutWindow.game_logic.setStage(stage);
                        LightsOutWindow.window_main.newStage(stage);
                        LightsOutWindow.window_controller = 2;
                    }
                });
                stage_button[stage].setPreferredSize(new Dimension(64, 64));
                //stage_button[stage].setText(stage_button[stage].getText()+"!");
                stage_buttons_panel.add(stage_button[stage]);
                stage_button[stage].setIcon(new ImageIcon(unsolved));
                stage_button[stage].setBackground(new Color(72,209,255));
                stage_button[stage].setHorizontalTextPosition(JButton.CENTER);
                stage_button[stage].setVerticalTextPosition(JButton.CENTER);
                stage_button[stage].setBorder(BorderFactory.createEmptyBorder());
                stage_button[stage].setOpaque(true);
                stage_button[stage].setFocusable(false);
            }
        }
        
        
      
    }
    
    
}
