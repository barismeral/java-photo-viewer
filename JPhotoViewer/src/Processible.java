import javax.swing.*;
import java.io.File;
/**
 * @author Barış MERAL
 * @since 2018.08.12
 * @version 1.0
 * @apiNote jdk10
 *
 */
public interface Processible {

 /**
  *
  * @param frame
  * @return
  */
 File openFile(JFrame frame);

 /**
  *
  * @param imageIcon
  * @param file
  */
 void getInfo(ImageIcon imageIcon,File file);

 /**
  *
  * @param filePathShown
  */
 void refreshImageList(File filePathShown);


}
