import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;


/**
 * @author Barış MERAL
 * @since 2018.08.12
 * @version 1.0
 * @apiNote jdk10
 * @see java.awt.Component
 *
 */


/**
 * <p>Main Class<p/>
 *
 */
public class MainFrame extends JFrame  {

    /**
     * <b>Constructor<b/>
     */
     MainFrame() {createMainFrame();}

    /**
     * <p>Fields<p/>
     */
   private JButton openButton,infoButton,refreshButton;
   private JLabel picture = new JLabel();
   private JPanel picturePanel = new JPanel();
   private ImageIcon image;
   private File fileName;
   private int arrayIndexCount = 0;
   private boolean updates=false;
   private boolean next = false;


    /**
     * @return void
     * <b>Created Main Frame<b/>
     */
   private void createMainFrame(){

        //Main Frame
       this.setSize(960,640);
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(3);
       this.setTitle("JPhoto Editor");
       this.getContentPane().setBackground(Color.gray);


            //Top Menu Panel
       JPanel menuPanel = new JPanel();
       menuPanel.setBorder(new LineBorder(Color.WHITE,4));

            //open file button
       openButton = new JButton();
       openButton.setBorderPainted(false);
       openButton.setBackground(Color.white);
       openButton.setIcon( new ImageIcon("src\\res\\op.png"));

       //file info button
       infoButton = new JButton();
       infoButton.setBorderPainted(false);
       infoButton.setBackground(Color.white);
       infoButton.setIcon(new ImageIcon("src\\res\\info.png"));

         //refresh image path button
       refreshButton = new JButton();
       refreshButton.setBorderPainted(false);
       refreshButton.setBackground(Color.white);
       refreshButton.setIcon(new ImageIcon("src\\res\\update.png"));

             // menu panel openbutton,info and refresh button container
       menuPanel.setLayout(new FlowLayout());
       menuPanel.add(openButton,FlowLayout.LEFT);
       menuPanel.add(infoButton,FlowLayout.CENTER);
       menuPanel.add(refreshButton,FlowLayout.RIGHT);

        //Botom menu panel backbutton and next button container
     JPanel bottomPanel = new JPanel(new FlowLayout());
     bottomPanel.setBorder(new LineBorder(Color.WHITE,4));

      // back button
     JButton backButton = new JButton(new ImageIcon(((new ImageIcon("src\\res\\left.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)  ));
     backButton.setBorderPainted(false);
     backButton.setBackground(Color.white);

     //next button
       JButton nextButton = new JButton(new ImageIcon(((new ImageIcon("src\\res\\right.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
       nextButton.setBorderPainted(false);
       nextButton.setBackground(Color.white);

            //added buttons
       bottomPanel.add(backButton);
       bottomPanel.add(nextButton);

            //frame added panels
       this.add(bottomPanel,BorderLayout.SOUTH);
       this.add(menuPanel,BorderLayout.NORTH);

       openButton.addActionListener(e -> { //open file button listener use lambda

          fileName =  new Process().openFile(getMainFrame());
           image = new ImageIcon(fileName.getPath());
            Image tempImage = image.getImage();
             Image imageIcon =  tempImage.getScaledInstance(picturePanel.getWidth(),picturePanel.getHeight(),Image.SCALE_DEFAULT);
              picture.setIcon(new ImageIcon(imageIcon));

       });

       infoButton.addActionListener(e -> new Process().getInfo((ImageIcon) picture.getIcon(),fileName));//info button event listener

       //refresh path button event listener
       refreshButton.addActionListener(e->{

           new Process().refreshImageList(fileName);
              updates=true;
              System.out.println(Process.fileNameArray.length);
       });

       //next button event
       nextButton.addActionListener(e->{

                  if (updates) {

                        if (arrayIndexCount == Process.fileNameArray.length)
                            arrayIndexCount=0;

                      if (Process.fileNameArray.length > arrayIndexCount) {

                          ImageIcon tempIcon = new ImageIcon(Process.filepath+"\\"+Process.fileNameArray[arrayIndexCount]);
                          Image tempImage = tempIcon.getImage();

                          ImageIcon imageIcon = new ImageIcon(tempImage.getScaledInstance(picturePanel.getWidth(),picturePanel.getHeight(),Image.SCALE_DEFAULT));

                          image = imageIcon;

                          fileName = new File(Process.filepath+"\\"+Process.fileNameArray[arrayIndexCount]);
                          arrayIndexCount++;
                          System.out.println(arrayIndexCount);
                          picture.setIcon(image);

                          next =true;
                      }

                  }
                  else
                      JOptionPane.showMessageDialog(this,"Please update");
       });

       //back button event
       backButton.addActionListener(e->{

           if (next){
               next=false;
               arrayIndexCount--;

               if (arrayIndexCount==-1)arrayIndexCount=Process.fileNameArray.length-1;
           }else {


               if (updates) {

                   if (arrayIndexCount == -1) {
                       arrayIndexCount = Process.fileNameArray.length - 1;

                   }


                   if (Process.fileNameArray.length > arrayIndexCount) {

                       ImageIcon tempIcon = new ImageIcon(Process.filepath + "\\" + Process.fileNameArray[arrayIndexCount]);
                       Image tempImage = tempIcon.getImage();

                       ImageIcon imageIcon = new ImageIcon(tempImage.getScaledInstance(picturePanel.getWidth(), picturePanel.getHeight(), Image.SCALE_DEFAULT));

                       image = imageIcon;

                       fileName = new File(Process.filepath + "\\" + Process.fileNameArray[arrayIndexCount]);
                       arrayIndexCount--;
                       System.out.println(arrayIndexCount);
                       picture.setIcon(image);

                   }

               } else
                   JOptionPane.showMessageDialog(this, "Please update");

           }
       });




       picturePanel.add(picture);
       this.add(picturePanel,BorderLayout.CENTER);

       this.setVisible(true);

   }




    public JFrame getMainFrame() {
        return this;
    }
}
