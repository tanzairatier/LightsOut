/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lightsout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tanza
 */
public class LightsOutWindow_Info extends JPanel {
    JButton return_button;
    JLabel info_label;
    
    public LightsOutWindow_Info() {
        return_button = new JButton("Return");
        return_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LightsOutWindow.window_controller = 1;
            }
        });
        this.add(return_button);
        info_label = new JLabel("<HTML>This game implemented by Dr. Joseph Krall (2014) <br>"
                + "             and based off of the popular handheld game <br>"
                + "             of the same name by Tiger Electronics.<br><br>"
                + "             The objective is to extinguish all the lights <br>"
                + "             by pressing adjacent lights in series.<br><br>"
                + "             A perfect solution is performed in an <br>"
                + "             optimal number of steps. <br>"
                + "             Suboptimal solutions indicate how many <br>"
                + "             steps below optimal you are via the number <br>"
                + "             of lights after you solve a puzzle.<br><br>"
                + "             Try to obtain perfect solutions for all <br>"
                + "             of the puzzles! <br><br>"
                + "             Try the Solve button for guidance and hints. <br>"
                + "             The solver does not always grant a perfect solution. <br>"
                + "             But you can perfect suboptimal solutions by removing <br>"
                + "             steps that are made more than once.</html>");
        this.add(info_label);
        this.setBackground(new Color(72, 209, 255));
        
        
        
    }
}
