import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Stopwatch implements ActionListener, KeyListener {
    JFrame frame = new JFrame();
    JButton startB = new JButton("Start");
    JButton resetB = new JButton("Reset");
    JButton close =  new JButton("x");
    JButton minimize = new JButton("-");
    JLabel timeL = new JLabel();

    DragListener drag = new DragListener();

    int ElapsedTime =  0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            ElapsedTime=ElapsedTime+1000;
            hours = (ElapsedTime/3600000);
            minutes = (ElapsedTime/60000) % 60;
            seconds = (ElapsedTime/1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeL.setText(hours_string+":"+minutes_string+":"+seconds_string);

        }

    });


    Stopwatch(){
        timeL.setText(hours_string+ ":"+minutes_string+ ":"+ seconds_string);
        timeL.setBounds(100,100,200,100);
        timeL.setFont(new Font("Andalus",Font.PLAIN,35));
        timeL.setBorder(BorderFactory.createBevelBorder(1));
        timeL.setOpaque(true);
        timeL.setHorizontalAlignment(0);

        startB.setBounds(100,200,100,50);
        startB.setFocusable(false);
        startB.addActionListener(this);

        resetB.setBounds(200,200,100,50);
        resetB.setFocusable(false);
        resetB.addActionListener(this);

        close.setBounds(90,90,20,20);
        close.setOpaque(true);
        close.setFont(new Font("",Font.BOLD,10));
        close.addActionListener(this);
        close.setFocusable(false);


        minimize.setBounds(110,90,20,20);
        minimize.setOpaque(true);
        minimize.setFont(new Font("",Font.BOLD,10));
        minimize.addActionListener(this);
        minimize.setFocusable(false);


        frame.add(minimize);
        frame.add(close);
        frame.add(timeL);
        frame.add(startB);
        frame.add(resetB);
        frame.addMouseListener( drag );
        frame.addMouseMotionListener( drag );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startB){
            start();
            if(!started){
                started=true;
                startB.setText("Stop");
                start();
            } else{
                started=false;
                startB.setText("Start");
                stop();
            }
        }

        if(e.getSource() == close){
            frame.dispose();
        }

        if(e.getSource()==resetB){
            started=false;
            startB.setText("Start");
            reset();
        }

        if(e.getSource() == minimize){
            frame.setState(Frame.ICONIFIED);
        }

    }

    void start(){
        timer.start();


    }

    void stop(){
        timer.stop();

    }

    void reset(){
        timer.stop();
        ElapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeL.setText(hours_string+ ":"+minutes_string+ ":"+ seconds_string);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

