/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framecheck;

import frame.FrameLaporanSPP;
import frame.FrameMain;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class CheckLaporanSPP{
 private FrameMain frameMain; 
    public CheckLaporanSPP(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFrameLaporanSPP()==false){ 
        frameMain.setObjectFrameLaporanSPP(new FrameLaporanSPP(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFrameLaporanSPP());
        frameMain.setTampilJFrameLaporanSPP(true);
        }
        else{
            try {
                frameMain.getObjectFramePembayaranSPP().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckLaporanSPP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
