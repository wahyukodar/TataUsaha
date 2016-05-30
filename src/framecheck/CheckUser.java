package framecheck;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import frame.FrameUser;
import frame.FrameMain;

/**
 *
 * @author Wahyu
 */
public class CheckUser {
 private FrameMain frameMain; 
    public CheckUser(FrameMain frameMain){ 
        this.frameMain=frameMain;
        if(frameMain.getTampilJFrameUser()==false){ 
        frameMain.setObjectFrameUser(new FrameUser(frameMain)); 
        frameMain.getjDesktopPane().add(frameMain.getObjectFrameUser());
        frameMain.setTampilJFrameUser(true);
        }
        else{
            try {
                frameMain.getObjectFrameUser().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}