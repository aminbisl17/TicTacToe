import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.sql.Time;
import java.awt.*;
import java.util.*;

public class TicTacToe extends JFrame implements ActionListener {

JPanel panel;
 JTextField textField;
 Random random = new Random();
 JButton[] buttons = new JButton[9];
 JButton restart;
 
  static File winWav, clickWav;
 static AudioInputStream audioStream, audioStream2; 
 static Clip clip, clip2;

 boolean player1;

  public TicTacToe() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
      /*
       * Time timer = new Time(10);
    TimerTask task = new TimerTask() {
  
      public void run() {
      
      }
    };

    timer.setTime(10);
       * 
       */
    
    // .. win audio
    winWav = new File("win.wav");
    audioStream = AudioSystem.getAudioInputStream(winWav);
    clip = AudioSystem.getClip();
    clip.open(audioStream);
    
    // .. click audio
    clickWav = new File("click.wav");
    audioStream2 = AudioSystem.getAudioInputStream(clickWav);
    clip2 = AudioSystem.getClip();
    clip2.open(audioStream2);
    
   ImageIcon icon = new ImageIcon("tictactoee.png");

    textField = new JTextField();
    textField.setBackground(Color.black);
    textField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    textField.setEditable(false);
    textField.setBounds(0, 0, 0, 80);
    textField.setHorizontalAlignment(JLabel.CENTER);
    textField.setFont(new Font("Ink Free", Font.PLAIN, 30));
    textField.setOpaque(true);
    textField.setText("Tic-Tac-Toe");
    textField.setForeground(new Color(210, 215, 211));
    textField.setFocusable(false);

    restart = new JButton("restart");
    restart.setFocusable(false);
    restart.setFont(new Font("MV Boli", Font.PLAIN, 20));
    restart.setSize(0, 80);
    restart.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    restart.setForeground(Color.black);
    restart.setBackground(Color.gray);
    restart.addActionListener(this);

    panel = new JPanel();
    panel.setPreferredSize(new Dimension(400, 400));
    panel.setBackground(Color.black);
    panel.setLayout(new GridLayout(3, 3, 1, 1));

    for (int i = 0; i < 9; i++){
      buttons[i] = new JButton();
      buttons[i].setBackground(new Color(210, 215, 211));
      buttons[i].setFocusable(false);
      buttons[i].setFont(new Font("MV Boli", Font.PLAIN, 40));
      buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
      buttons[i].setEnabled(false);
      panel.add(buttons[i]);
      buttons[i].addActionListener(this);
    }

    this.setTitle("Tic Tac Toe");
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setBounds(500, 100, 400, 400);
    this.setResizable(false);
    this.add(panel, BorderLayout.CENTER);
    this.add(textField, BorderLayout.NORTH);
    this.add(restart, BorderLayout.SOUTH);
    this.setIconImage(icon.getImage());
    this.setVisible(true);

    Turn1();

  }
  public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    new TicTacToe();

  }
  public void actionPerformed(ActionEvent e) {

    if(e.getSource().equals(restart)){
      try {
        new TicTacToe();
      } catch (UnsupportedAudioFileException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (LineUnavailableException e1) {
        e1.printStackTrace();
      }
      this.dispose();
    }
  
    for (int i = 0; i < 9; i++){

      if (e.getSource() == buttons[i]){

        if (player1){
          if (buttons[i].getText() == ""){
            buttons[i].setBackground(new Color(207, 0, 15));
            buttons[i].setForeground(Color.white);
            buttons[i].setText("X");
            textField.setForeground(new Color(44, 130, 201));
            player1 = false;
            textField.setText("Radha e O");
            clip2.start();
          clip2.setMicrosecondPosition(5);
            check();
          }
        }

        else {
          if (buttons[i].getText() == "") {
          buttons[i].setBackground(new Color(44, 130, 201));
          buttons[i].setForeground(Color.white);
          buttons[i].setText("O");
          textField.setForeground(new Color(207, 0, 15));
          player1 = true;
          textField.setText("Radha e X");
          clip2.start();
          clip2.setMicrosecondPosition(5);
          check();
          }
        }
      }
    }
  }
  public void Turn1() {

   timer(2000);
   
   if (random.nextInt(2) == 0) {

      player1 = true;
     textField.setText("Radha e X");
     textField.setForeground(new Color(207, 0, 15));
     for (int x = 0; x < 9; x++){
      buttons[x].setEnabled(true);
     }
    }
    else {
      
    player1 = false;
    textField.setText("Radha e O");
    textField.setForeground(new Color(44, 130, 201));
    for (int x = 0; x < 9; x++){
      buttons[x].setEnabled(true);
     }
    }
  }
  public void check(){
 // check x

    if (   (buttons[0].getText() == "X") 
        && (buttons[1].getText() == "X") 
        && (buttons[2].getText() == "X"))
        {
      xWin(0, 1, 2);
    }

    if (   (buttons[3].getText() == "X") 
        && (buttons[4].getText() == "X") 
        && (buttons[5].getText() == "X"))
        {
      xWin(3, 4, 5);
    }

    if (   (buttons[6].getText() == "X") 
        && (buttons[7].getText() == "X") 
        && (buttons[8].getText() == "X"))
        {
          xWin(6, 7, 8);
    }

    if (   (buttons[0].getText() == "X") 
        && (buttons[3].getText() == "X") 
        && (buttons[6].getText() == "X"))
        {
      xWin(0, 3, 6);
    }

    if (   (buttons[1].getText() == "X") 
        && (buttons[4].getText() == "X") 
        && (buttons[7].getText() == "X"))
        {
      xWin(1, 4, 7);
    }

    if (   (buttons[2].getText() == "X") 
        && (buttons[5].getText() == "X") 
        && (buttons[8].getText() == "X"))
        {
      xWin(2, 5, 8);
    }

    if (   (buttons[0].getText() == "X") 
        && (buttons[4].getText() == "X") 
        && (buttons[8].getText() == "X"))
        {
      xWin(0, 4, 8);
    }

    if (   (buttons[2].getText() == "X") 
        && (buttons[4].getText() == "X") 
        && (buttons[6].getText() == "X"))
        {
      xWin(2, 4, 6);
    }

   // CHECK O

    if (   (buttons[0].getText() == "O") 
        && (buttons[1].getText() == "O") 
        && (buttons[2].getText() == "O"))
        {
      oWin(0, 1, 2);
    }

    if (   (buttons[3].getText() == "O") 
        && (buttons[4].getText() == "O") 
        && (buttons[5].getText() == "O"))
        {
      oWin(3, 4, 5);
    }

    if (   (buttons[6].getText() == "O") 
        && (buttons[7].getText() == "O") 
        && (buttons[8].getText() == "O"))
        {
      oWin(6, 7, 8);
    }

    if (   (buttons[0].getText() == "O") 
        && (buttons[3].getText() == "O") 
        && (buttons[6].getText() == "O"))
    {
      oWin(0, 3, 6);
    }

    if (   (buttons[1].getText() == "O") 
        && (buttons[4].getText() == "O") 
        && (buttons[7].getText() == "O"))
        {
      oWin(1, 4, 7);
    }

    if (   (buttons[2].getText() == "O") 
        && (buttons[5].getText() == "O") 
        && (buttons[8].getText() == "O"))
        {
      oWin(2, 5, 8);
    }

    if (   (buttons[0].getText() == "O") 
        && (buttons[4].getText() == "O") 
        && (buttons[8].getText() == "O"))
        {
      oWin(0, 4, 8);
    }

    if (   (buttons[2].getText() == "O") 
        && (buttons[4].getText() == "O") 
        && (buttons[6].getText() == "O"))
        {
      oWin(2, 4, 6);
    }
  }
  public void xWin(int a, int b, int c){

   buttons[a].setBackground(new Color(207, 0, 15));
   buttons[b].setBackground(new Color(207, 0, 15));
   buttons[c].setBackground(new Color(207, 0, 15));
   
   for (int i = 0; i < 9; i++){
    buttons[i].setEnabled(false);
   }
   textField.setText("X fiton!");
   textField.setForeground(new Color(207, 0, 15));
    clip.start();
    clip.setMicrosecondPosition(10);
   player1 = false;
  }

  public void oWin(int a, int b, int c){

    buttons[a].setBackground(new Color(44, 130, 201));
    buttons[b].setBackground(new Color(44, 130, 201));
    buttons[c].setBackground(new Color(44, 130, 201));
    
    for (int i = 0; i < 9; i++){
     buttons[i].setEnabled(false);
    }
    textField.setText("O fiton!");
    textField.setForeground(new Color(44, 130, 201));
    clip.start();
    clip.setMicrosecondPosition(10);
    player1 = false;
  }
  public static int timer(int i){
    try {
      Thread.sleep(i);
    }
    catch (InterruptedException e){
      e.printStackTrace();
    }
    return i;
  }
}