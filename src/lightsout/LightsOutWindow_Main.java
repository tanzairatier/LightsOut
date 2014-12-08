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
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tanza
 */
public class LightsOutWindow_Main extends JPanel {
    
    JButton button1, button2;
    JPanel top_panel, game_panel, bottom_panel;
    JButton[] lights_buttons;
    JButton back_button, reset_button, solve_button;
    JLabel moves_label;
    JLabel title_text;
    int num_moves;
    int[] bottom_row;
    Color light_color;
    Image on = ImageIO.read(getClass().getResource("button_on.png"));
    Image off = ImageIO.read(getClass().getResource("button_off.png"));
    
    Vector<Integer> moves_made;
    
    int rep = 0;
    int rop = 0;
    int bop = 0;
    int extra = 0;
    boolean status, status2;
    boolean buttons_clickable;
    
    public LightsOutWindow_Main() throws IOException {
        
        light_color = Color.MAGENTA;
        moves_label = new JLabel("Num Moves: 0");
        title_text = new JLabel("<html><font size='20'>Lights Out!</font></html>");
        back_button = new JButton("Back");
        reset_button = new JButton("Reset");
        solve_button = new JButton("Solve");
        lights_buttons = new JButton[25];
        
        this.setBackground(new Color(72, 209, 255));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
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
        
        game_panel = new JPanel();
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
        this.add(game_panel, c);
        game_panel.setOpaque(false);
        //game_panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        game_panel.setLayout(new GridLayout(5,5,2,2));
        
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
        bottom_panel.add(back_button);
        bottom_panel.add(reset_button);
        bottom_panel.add(solve_button);
        bottom_panel.add(moves_label);
        
        
  
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final int id = i*5+j;
                final int row = i;
                final int col = j;
                lights_buttons[id] = new JButton("");
                game_panel.add(lights_buttons[id]);
                lights_buttons[id].setPreferredSize(new Dimension(64, 64));
                //lights_buttons[id].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.PINK)); 
                lights_buttons[id].setBorder(BorderFactory.createEmptyBorder());
                lights_buttons[id].setOpaque(true);
                lights_buttons[id].setFocusable(false);
                lights_buttons[id].setBackground(light_color);
                lights_buttons[id].setIcon(new ImageIcon(on));
                
                lights_buttons[id].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        if (buttons_clickable) {
                            num_moves += 1;
                            moves_label.setText("Number of Moves: " + num_moves);
                            toggle_button(id, row, col);

                            checkForWin(0);
                        }
                    }
                });
            }
        }
        
        back_button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               LightsOutWindow.window_controller = 1;
           } 
        });
                
        reset_button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               resetStage(LightsOutWindow.game_logic.getStage());
           } 
        });
        
        solve_button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               solve();
           } 
        });
        
    }
    
    public void toggle_button(int id, int row, int col) {
        if (lights_buttons[id].getBackground().equals(new Color(238, 238, 238))) {
                            lights_buttons[id].setBackground(light_color);
                            lights_buttons[id].setIcon(new ImageIcon(on));
                        }
                        else {
                            lights_buttons[id].setBackground(null);
                            lights_buttons[id].setIcon(new ImageIcon(off));
                        }
                        if (col > 0) {
                            if (lights_buttons[id-1].getBackground().equals(new Color(238, 238, 238))) {
                                lights_buttons[id-1].setBackground(light_color);
                                lights_buttons[id-1].setIcon(new ImageIcon(on));
                            }
                            else {
                                lights_buttons[id-1].setBackground(null);
                                lights_buttons[id-1].setIcon(new ImageIcon(off));
                            }
                        }
                        if (col < 4) {
                            if (lights_buttons[id+1].getBackground().equals(new Color(238, 238, 238))) {
                                lights_buttons[id+1].setBackground(light_color);
                                lights_buttons[id+1].setIcon(new ImageIcon(on));
                            }
                            else {
                                lights_buttons[id+1].setBackground(null);
                                lights_buttons[id+1].setIcon(new ImageIcon(off));
                            }
                        }
                        if (row > 0) {
                            if (lights_buttons[id-5].getBackground().equals(new Color(238, 238, 238))) {
                                lights_buttons[id-5].setBackground(light_color);
                                lights_buttons[id-5].setIcon(new ImageIcon(on));
                            }
                            else {
                                lights_buttons[id-5].setBackground(null);
                                lights_buttons[id-5].setIcon(new ImageIcon(off));
                            }
                        }
                        if (row < 4) {
                            if (lights_buttons[id+5].getBackground().equals(new Color(238, 238, 238))) {
                                lights_buttons[id+5].setBackground(light_color);
                                lights_buttons[id+5].setIcon(new ImageIcon(on));
                            }
                            else {
                                lights_buttons[id+5].setBackground(null);
                                lights_buttons[id+5].setIcon(new ImageIcon(off));
                            }
                        }
    }
    public void newStage(int _stage) {
        resetStage(_stage);
    }
    
    public void resetStage(int _stage) {
        num_moves = 0;
        buttons_clickable = true;
        moves_label.setText("Number of Moves: " + num_moves);
        int[] stage = LightsOutWindow.game_logic.getStage(_stage);
        for (int i = 0; i < 25; i++) {
            lights_buttons[i].setIcon(new ImageIcon(off));
            lights_buttons[i].setBackground(null);
        }
        for (int i = 0; i < stage.length; i++) {
            lights_buttons[stage[i]].setIcon(new ImageIcon(on));
            lights_buttons[stage[i]].setBackground(light_color);
        }
        
    }
    
    public boolean checkForWin(int complete) {
        boolean we_won = true;
        for (int i = 0; i < 25; i++) {
            if (!lights_buttons[i].getBackground().equals(new Color(238, 238, 238))) {
                we_won = false;
            }
        }
        if (we_won && complete==0) {
            doWinnerStuff();
        }
        return we_won;
    }
    
    public void doWinnerStuff() {
        
        int moves = num_moves;
        resetStage(LightsOutWindow.game_logic.getStage());
        moves_made = new Vector<Integer>();
        status = true;
        do {
            status = chase_down();
        } while (status);
        bottom_row = get_bottom_row();
        for (int i = 0; i < bottom_row.length; i++) {
            toggle_button(bottom_row[i], 0, bottom_row[i]);
            moves_made.add(bottom_row[i]);
        }
        status = true;
        do {
            status = chase_down();
        } while (status);
        
        String z = "";
        Vector<Integer> optimal_moves = new Vector<Integer>();
        for (int i = 0; i < moves_made.size(); i++) {
            z += moves_made.elementAt(i) + ",";
            if (repeats(moves_made.elementAt(i)) == 1) optimal_moves.add(moves_made.elementAt(i));
        }
        
        System.out.println(z);
        System.out.println("Optimal Length: " + optimal_moves.size());
        
        
        
        buttons_clickable = false;
        
        if (moves <= optimal_moves.size()) {
            LightsOutWindow.game_logic.stage_completion[LightsOutWindow.game_logic.getStage()] = 2;

            int delayTime = 100;
            rep = 0;
            final javax.swing.Timer myTimer = new Timer(delayTime, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rep < 25) {
                        lights_buttons[rep].setIcon(new ImageIcon(on));
                        rep += 1;
                    }
                }
            });
            myTimer.setRepeats(true);
            myTimer.start();

            rop = 0;
            final javax.swing.Timer myTimer2 = new Timer(delayTime, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rop < 25) {
                        lights_buttons[rop].setIcon(new ImageIcon(off));
                        rop += 1;
                    }
                }
            });
            myTimer2.setInitialDelay(215);
            myTimer2.setRepeats(true);
            myTimer2.start();

            javax.swing.Timer myTimer3 = new Timer(2700, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LightsOutWindow.game_logic.setStage(LightsOutWindow.game_logic.getStage()+1);
                    newStage(LightsOutWindow.game_logic.getStage());
                    myTimer.stop();
                    myTimer2.stop();
                }
            });
            myTimer3.setInitialDelay(2700);
            myTimer3.setRepeats(false);
            myTimer3.start();
        }
        else {
            if (LightsOutWindow.game_logic.stage_completion[LightsOutWindow.game_logic.getStage()] < 2) {
                LightsOutWindow.game_logic.stage_completion[LightsOutWindow.game_logic.getStage()] = 1;
            }
            extra = moves - optimal_moves.size();
            if (extra > 25) extra = 25;
            System.out.println("Extra moves: " + extra);
            rep = 0;
            final javax.swing.Timer myTimer = new Timer(800, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < extra; i++) {
                        lights_buttons[i].setIcon(new ImageIcon(on));
                    }
                }
            });
            myTimer.setRepeats(true);
            myTimer.start();
            
            final javax.swing.Timer myTimer2 = new Timer(800, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < extra; i++) {
                        lights_buttons[i].setIcon(new ImageIcon(off));                       
                    }
                }
            });
            myTimer2.setInitialDelay(400);
            myTimer2.setRepeats(true);
            myTimer2.start();
            
            javax.swing.Timer myTimer3 = new Timer(2700, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LightsOutWindow.game_logic.setStage(LightsOutWindow.game_logic.getStage()+1);
                    newStage(LightsOutWindow.game_logic.getStage());
                    myTimer.stop();
                    myTimer2.stop();
                }
            });
            myTimer3.setInitialDelay(2700);
            myTimer3.setRepeats(false);
            myTimer3.start();
        }
        
        if (LightsOutWindow.game_logic.stage_completion[LightsOutWindow.game_logic.getStage()] == 2) {
            LightsOutWindow.window_stageselect.stage_button[LightsOutWindow.game_logic.getStage()].setIcon(new ImageIcon(LightsOutWindow.window_stageselect.perfect));
        }
        else {
            LightsOutWindow.window_stageselect.stage_button[LightsOutWindow.game_logic.getStage()].setIcon(new ImageIcon(LightsOutWindow.window_stageselect.solved));
        }

        
    }
    
    public int repeats(int item) {
        int cnt = 0;
        for (int i = 0; i < moves_made.size(); i++) {
            if (moves_made.elementAt(i) == item) cnt += 1;
        }
        return cnt;
    }
    public void solve() {
        
        buttons_clickable = false;
        moves_made = new Vector<Integer>();
        status = true;
        status2 = true;
        final javax.swing.Timer myTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status) {
                    status = chase_down();
                }
            }
        });
        myTimer.setRepeats(true);
        myTimer.start();
        
        final javax.swing.Timer myTimer2 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!status && status2) {
                    myTimer.stop();
                    bottom_row = get_bottom_row();
                    bop = 0;
                    status2 = false;
                }
                if (!status2 && bop < bottom_row.length) {
                    toggle_button(bottom_row[bop], 0, bottom_row[bop]);
                    bop += 1;
                    if (bop >= bottom_row.length) {
                        
                        if (!checkForWin(1)) {
                            status = true;
                            status2 = true;
                            myTimer.start();
                            
                        }
                        else {
                            resetStage(LightsOutWindow.game_logic.getStage());
                        }
                    }
                }
                
            }
        });
        myTimer2.setRepeats(true);
        myTimer2.start();
        
        final javax.swing.Timer myTimer3 = new Timer(100, null);
        myTimer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!status && status2) {
                    //myTimer2.stop();
                    //status = true;
                    //status2 = true;
                    //myTimer.start();
                    //resetStage(LightsOutWindow.game_logic.getStage());
                    if (checkForWin(1)) {
                        myTimer.stop();
                        myTimer2.stop();
                        myTimer3.stop();   
                        resetStage(LightsOutWindow.game_logic.getStage());
                    }
                    
                //}
            }
        });
        myTimer3.setRepeats(true);
        myTimer3.start();
    }
    public boolean chase_down() {
        //find first chaseable light
        int chaseable_id = -1;
        int row = -1;
        int col = -1;
        for (int i = 3; i >= 0; i--) {  //skip last row
            for (int j = 4; j >= 0; j--) {
                final int id = 5*i+j;
                if (lights_buttons[id].getBackground().equals(light_color)) {
                    chaseable_id = id;
                    row = i;
                    col = j;
                }
            }
        }
        
        if (chaseable_id > -1) {
            toggle_button(chaseable_id+5, row+1, col);
            moves_made.add(chaseable_id+5);
            return true;
        }
        else {
            return false;
        }
    }
    
    public int[] get_bottom_row() {
        boolean[] br = new boolean[5];
        for (int j = 0; j < 5; j++) {
            final int id = 5*4+j;
            if (lights_buttons[id].getBackground().equals(light_color)) {
                br[j] = true;
            }
            else {
                br[j] = false;
            }
        }
        int cnt = 0;
        for (int i = 0; i < br.length; i++) {
            if (br[i]) cnt += 1;
        }
        
        int[] bot;
        
        
        if (br[0] && !br[1] && !br[2] && !br[3] && br[4]) {
            bot = new int[] {0,1};
        } 
        else if (br[0] && br[1] && br[2] && !br[3] && !br[4]) {
            bot = new int[] {1};
        }
        else if (!br[0] && br[1] && !br[2] && br[3] && !br[4]) {
            bot = new int[] {0, 3};
            
        }
        else if (!br[0] && !br[1] && br[2] && br[3] && br[4]) {
            bot = new int[] {3};
            
        }
        else if (br[0] && !br[1] && br[2] && br[3] && !br[4]) {
            bot = new int[] {4};
        }
        else if (!br[0] && br[1] && br[2] && !br[3] && br[4]) {
            bot = new int[] {0};
        }
        else if (br[0] && br[1] && !br[2] && br[3] && br[4]) {
            bot = new int[] {2};
        }
        else {
            bot = new int[] {};
        }
        
        return bot;
    }
    
    public boolean replicate_bottom() {
        int who_to_press = -1;
        int row = -1;
        int col = -1;
        for (int j = 0; j < 5; j++) {
            final int id = 5*4+j;
            if (lights_buttons[id].getBackground().equals(light_color)) {
                    who_to_press = 5*0+j;
                    row = 0;
                    col = j;
            }
        }
        if (who_to_press > -1) {
            toggle_button(who_to_press, row, col);
            return false;
        }
        else {
            return true;
        }
    }
}

