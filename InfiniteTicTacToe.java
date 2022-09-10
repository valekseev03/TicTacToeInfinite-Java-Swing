import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
import javax.swing.JButton;
import javax.swing.JFrame;
 
/*
To play compile this in a java compiler (preferably eclipse) and run. This is a tic tic toe game that can have a 30 by 30 board (couldn't do more before not seeing the pieces), you can play with an "AI" or another player. Use the console to play.
 
Sorry for any spaghetti code this was made in my sophomore year of high school, so feel free to modify it. Also feel free to view any other projects I made.
*/
 
public class InfiniteTicTacToe extends JPanel
{
    //Max 25 or 30 (starts lagging)
    int x = 30;
    //Min 3
    int z = 30;
    int score = 0;
    int Owins = 0;
    int Xwins = 0;
    int total = 0;
    
    int storedint = x;
    boolean a = false;
    int piecesplaced = 0;
    int player;
    
    
    String WinX = "";
    String WinY = "";
    JButton buttons[] = new JButton[x*x]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    
    public InfiniteTicTacToe(int xx, int y, boolean za)
    {
      //Add Way To Customize Board Size and Number to Win
     
        x = xx;
        z = y;
        a = za;
        
      setLayout(new GridLayout(x,x));
      initializebuttons(); 
      for(int i = 0; i < z; i++) {
        WinX += "X";
        WinY += "O";
      }
    }
    
    public void initializebuttons()
    {
        for(int i = 0; i <= x*x - 1; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].addActionListener(new buttonListener());
            
            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already           
        }
    }
    public void resetButtons()
    {
        for(int i = 0; i <= x*x - 1; i++)
        {
            buttons[i].setText("-");
        }
    }
    
    private class buttonListener implements ActionListener
    {
       
        public void actionPerformed(ActionEvent e) 
        {
            
            JButton buttonClicked = (JButton)e.getSource(); 
            if(alternate%2 == 0) {
                if(buttonClicked.getText() == "-") {
                buttonClicked.setText("X");
                piecesplaced++;
                player = 1;
                }
                            
            } else {
                if(a == true) {
                    
                int ran = (int)(Math.random()*(x*x));   
                
                if(buttons[ran].getText() == "-") {
                buttons[ran].setText("O");
                }else {
                while(buttons[ran].getText() != "-")    {
                    ran = (int)(Math.random()*(x*x));   
                }
                buttons[ran].setText("O");
                }
 
                player = 2;
                
            }else {
                if(buttonClicked.getText() == "-") {
                buttonClicked.setText("O");
                piecesplaced++;
                player = 2;
                }
                }
            }   
            
            if(piecesplaced >= x*x || checkForWin() == true )
            {   
                if(checkForWin() == true) {
                if(player == 2 & a == true) {
                JOptionPane.showConfirmDialog(null, "Computer Won!");
                JOptionPane.showConfirmDialog(null, "Final Score: " + score);
                
                score = 0;
                }else if(player == 1 & a == true){
                    
                    score += 100;
                    JOptionPane.showConfirmDialog(null, "You Won!");
                    JOptionPane.showConfirmDialog(null, "Score: " + score);
                    
                }else {
                JOptionPane.showConfirmDialog(null, "Player #" + player + " Won!"); 
            
                
                if(player == 1) {
                Xwins++;
                total+= 1;
                JOptionPane.showConfirmDialog(null, "Player #" + 1 + " Won " + Xwins + "/" + total);
                }else {
                total+= 1;
                Owins++;
                JOptionPane.showConfirmDialog(null, "Player #" + 2 + " Won " + Owins + "/" + total);
                }
               
                
                
                }
                }else {
                JOptionPane.showConfirmDialog(null, "It's A Tie!"); 
                total += 1;
                }
                
                resetButtons();
                piecesplaced = 0;
            }
                
            alternate++;
            
        }
        
        public boolean checkForWin()
        {
            
        String s = "";
            /**   Reference: the button array is arranged like this on a 3 by 3 board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
        
            //horizontal win check 
            storedint = x;
            s = "";
            for(int i = 0; i < x*x; i++) {
        
            if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                 if(i == storedint) {
                     s+= " ";
                  
                     storedint += x;
                     }
                 
                s+= buttons[i].getText();
                 
            }else
                s+= " ";
            }
            
          
            
            if(s.contains(WinX) || s.contains(WinY)){
            return true;    
            }
            s = "";
            
            
            //vertical win check 
            
            storedint = x;
            s = "";
            
           for(int j = 0; j < x; j++) { 
            for(int i = 0 + j; i < x*x; i += x) {
        
            if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                 
                s+= buttons[i].getText();
                 
            }else
                s+= " ";
            }
            
            if(s.contains(WinX) || s.contains(WinY)){
                return true;    
                }else {
                s = "";
                }
           }
           s= ""; 
       
            
           //diagonal win check [left to right]
           
           int p = 0;
           
           for(int j = 0; j < x; j++) { 
               for(int i = (j*x) + p; i < x*x; i += x + 1) {
           
               if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                 
                s+= buttons[i].getText();
                
                
                 
                
               }else {
                s+= " ";
               }
               }
               
               
           
               p = 1; 
               if(s.contains(WinX) || s.contains(WinY)){
                   return true; 
                   }else {
                   s = "";
                   p = 0;
                   }
              }
              s= "";  
          
              for(int j = 0; j < x - 3; j++) { 
                  for(int i = 1 + j; i < x*x; i += x + 1) {
              
                  if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                     
                    s+= buttons[i].getText();
               
                     
                    
                  }else {
                    s+= " ";
                  }
                  }
                  
                  if(s.contains(WinX) || s.contains(WinY)){
                      return true;  
                      }else {
                      s = "";
                      p = 0;
                      }
                 }
                  
              //diagonal win check [right to left]      
              for(int j = 0; j < x*x - 9; j++) {
                  for(int i = x*(j+1) - 1; i < x*x; i += x - 1) { 
              
                  if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                     
                    s+= buttons[i].getText();
                    
                     
                    
                  }else {
                    s+= " ";
                  }
                  }
                  
                  if(s.contains(WinX) || s.contains(WinY)){
                      return true;  
                      }else {
                      s = "";
                      p = 0;
                      }
              }
              
              //5
              for(int j = 0; j < x - 3; j++) { 
                  for(int i = x - j; i < x*x; i += x - 1) {
              
                  if((buttons[i].getText() == "X") || (buttons[i].getText() == "O")) {
                     
                    s+= buttons[i].getText();
                    
                     
                  }else
                    s+= " ";
                  }
                  
                  if(s.contains(WinX) || s.contains(WinY)){
                      return true;  
                      }else {
                      s = "";
                      }
              }
              
              
                   
            return false;
           
            
        }
    } 
    
 
 
    
    
    public static void  main(String[] args) 
    {
        
        JFrame window = new JFrame("Tic-Tac-Toe");
        String test;
        String tst;
        boolean za = true;
        boolean tsst = false;
        int x = 0;
        int y = 0 ;
       
      test = JOptionPane.showInputDialog("How Big Do You Want The Board To Be?") + "";
      
      for(int p = 0; p < 5; p++) { 
      if(test.contains(p + "")) {
        tsst = true;
       }
      }
      
      if(tsst == true) {
       x =  Integer.parseInt(test);
      }else {
        x = (int)(Math.random() * (5-2)) + 3;  
      }
      tsst = false;
      
       tst = JOptionPane.showInputDialog("How Many Do You Want To Win") + "";
       for(int q = 0; q < 5; q++) { 
              if(tst.contains(q + "")) {
                 tsst = true;  
               }
              }
       
       if(tsst == true) {
       y =  Integer.parseInt(tst);
       }else {
        y = (int)(Math.random() * (5-2)) + 3;  
      }
       
       
       if(x > 5) {
          x = 5; 
       }
       
       if(x < 3) {
     x = 3;    
       }
       if(y> x) {
           y = x;      
       }
       
       if(y < 3) {
        y = 3;   
       }
     
       
       if(JOptionPane.showInputDialog("Do You Want To Play Singleplayer (s) or Multiplayer (m)").contains("m")) {
       za = false;
       }else {
       za = true;   
       }
 
       
       
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        window.getContentPane().add(new InfiniteTicTacToe(x,y, za));
        
        window.setBounds(3000,2000,3000,3000);
        window.setVisible(true);
    }
}