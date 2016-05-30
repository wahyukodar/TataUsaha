/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framecheck;

import frame.FrameKelas;
import frame.FrameMain;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class CheckKelas{
 private FrameMain frameMain; 
    public CheckKelas(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFrameKelas()==false){ 
        frameMain.setObjectFrameKelas(new FrameKelas(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFrameKelas());
        frameMain.setTampilJFrameKelas(true);
        }
        else{
            try {
                frameMain.getObjectFrameKelas().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckKelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
