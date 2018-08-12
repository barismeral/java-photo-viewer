import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;


/**
 * @author Barış MERAL
 * @since 2018.08.12
 * @version 1.0
 * @apiNote jdk10
 * @see Processible
 *
 */

public class Process implements Processible {


  File[] fileArray ;
 public static String[] fileNameArray;
  public static   File filepath;


    /**
     *
     * @param frame
     * @return pic
     */
    @Override
    public File openFile(JFrame frame) {

        File pic = null;
        FileDialog fileDialog;

        fileDialog = new FileDialog(frame,"load Image",FileDialog.LOAD);


        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".bmp") ||
                        name.endsWith("wbmp") || name.endsWith(".gif");
            }
        });

        fileDialog.setFile("*.jpg;*.jpeg;*.bmp;*.wmbp;*.gif");
        fileDialog.setDirectory("C:\\Users\\admin\\desktop");

        fileDialog.setVisible(true);


        try{

           pic = new File(fileDialog.getDirectory() + fileDialog.getFile());

        }
        catch (Exception r){

        }

        fileArray=null;
        fileNameArray=null;

        filepath = new File(pic.getParent());

        fileArray = filepath.listFiles(new FileFilter() {

          final   FileNameExtensionFilter fileTypes = new FileNameExtensionFilter("types","jpg","jpeg","bmp","gif","wbmp");

            public boolean accept(File file) {
                return fileTypes.accept(file);
            }
        });



        fileNameArray = new String[fileArray.length+3];


           int i=0;
       for (File files: fileArray){
         if (files.isFile()){
               fileNameArray[i]=files.getName();
               i++;

          }

       }


       return pic;
    }

    /**
     *
     * @param imageIcon
     * @param file
     */
    @Override
    public void getInfo(ImageIcon imageIcon,File file) {

        if (imageIcon==null&&file==null)
            JOptionPane.showMessageDialog(null, "no pictures opened ");
        else {


            JFrame infoFrame = new JFrame("Info");
            infoFrame.setSize(250, 150);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setUndecorated(true);
            infoFrame.getContentPane().setLayout(null);


            JLabel infoLabel = new JLabel("Info This Picture");
            infoLabel.setBounds(30, 5, 100, 20);
            infoFrame.getContentPane().add(infoLabel);


            JLabel label1 = new JLabel("Widht: ");
            label1.setBounds(10, 20, 50, 30);
            infoFrame.getContentPane().add(label1);

            JLabel label2 = new JLabel(String.valueOf(imageIcon.getIconWidth()));
            label2.setBounds(70, 20, 50, 30);
            infoFrame.getContentPane().add(label2);

            JLabel label3 = new JLabel("Height: ");
            label3.setBounds(10, 40, 50, 30);
            infoFrame.getContentPane().add(label3);

            JLabel label4 = new JLabel(String.valueOf(imageIcon.getIconHeight()));
            label4.setBounds(70, 40, 50, 30);
            infoFrame.getContentPane().add(label4);

            JLabel label5 = new JLabel("Name: ");
            label5.setBounds(10, 60, 50, 30);
            infoFrame.getContentPane().add(label5);

            JLabel label6 = new JLabel(file.getName());
            label6.setBounds(60, 60, 50, 30);
            infoFrame.getContentPane().add(label6);

            JLabel label7 = new JLabel("Path: ");
            label7.setBounds(10, 80, 50, 30);
            infoFrame.getContentPane().add(label7);

            JLabel label8 = new JLabel(file.getParent());
            label8.setBounds(60, 80, 230, 30);
            infoFrame.getContentPane().add(label8);


            JButton okButton = new JButton("Ok");
            okButton.setBounds(55, 115, 100, 20);
            okButton.setBorderPainted(false);
            okButton.setBackground(Color.white);
            okButton.setBorder(new EmptyBorder(0, 0, 0, 0));
            infoFrame.getContentPane().add(okButton);
            okButton.addActionListener(e -> {

                infoFrame.dispose();
                infoFrame.setVisible(false);

            });


            infoFrame.setVisible(true);


        }

    }

    /**
     *
     * @param filePathShown
     */
    @Override
    public void refreshImageList(File filePathShown) {

        if (filePathShown == null)
            JOptionPane.showMessageDialog(null, "Please open one image..","Error",JOptionPane.ERROR_MESSAGE);
        {

            File filePath = new File(filePathShown.getParent());


            fileNameArray = null;
            fileArray = filePath.listFiles(new FileFilter() {

                private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Images files",
                        "jpg", "jpeg", "png", "gif", "wbmp", "bmp");

                public boolean accept(File file) {
                    return filter.accept(file);
                }
            });

            fileNameArray = new String[fileArray.length];


            int i = 0;
            for (File files : fileArray) {
                if (files.isFile()) {
                    fileNameArray[i] = files.getName();
                    System.out.println(fileNameArray[i]);
                    i++;

                }

            }


        }
    }
}
