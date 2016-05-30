/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package framecheck;

import frame.FrameMain;
import frame.FrameSiswa;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class CheckSiswa{
 private FrameMain frameMain; 
    public CheckSiswa(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFrameSiswa()==false){ 
        frameMain.setObjectFrameSiswa(new FrameSiswa(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFrameSiswa());
        frameMain.setTampilJFrameSiswa(true);
        }
        else{
            try {
                frameMain.getObjectFrameSiswa().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckSiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
