/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framecheck;

import frame.FrameMain;
import frame.FramePembayaranSPP;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class CheckPembayaranSPP{
 private FrameMain frameMain; 
    public CheckPembayaranSPP(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFramePembayaranSPP()==false){ 
        frameMain.setObjectFramePembayaranSPP(new FramePembayaranSPP(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFramePembayaranSPP());
        frameMain.setTampilJFramePembayaranSPP(true);
        }
        else{
            try {
                frameMain.getObjectFramePembayaranSPP().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckPembayaranSPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
