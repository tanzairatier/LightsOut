/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lightsout;

/**
 *
 * @author tanza
 */
public class LightsOutGameLogic {
    
    int stage;
    int[][] stages;
    int[] stage_completion;
    
    public LightsOutGameLogic() {
        stage = 0;
        stage_completion = new int[25];
        stages = new LightsOutStages().stages;
        
    }
    
    public int[] getStage(int i) {
        return stages[i];
    }
    
    public void setStage(int _stage) {
        stage = _stage;
    }
    
    public int getStage() {
        return stage;
    }
    
}
