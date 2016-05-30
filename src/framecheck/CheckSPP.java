/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framecheck;

import frame.FrameMain;
import frame.FrameSPP;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class CheckSPP{
 private FrameMain frameMain; 
    public CheckSPP(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFrameSPP()==false){ 
        frameMain.setObjectFrameSPP(new FrameSPP(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFrameSPP());
        frameMain.setTampilJFrameSPP(true);
        }
        else{
            try {
                frameMain.getObjectFrameSPP().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckSPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
