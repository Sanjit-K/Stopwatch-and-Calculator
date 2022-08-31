import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addB,subB,mulB,divB;
    JButton decb, eqB, delB, clrB, negB;
    JPanel panel;

    JButton close =  new JButton("x");

    JButton minimize = new JButton("-");

    Font myFont = new Font("Ink Free",Font.BOLD ,30);

    double num1 = 0;
    double num2 = 0;
    double result = 0;
    char operator;

    DragListener drag = new DragListener();




    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(25,25,350,70);
        textField.setEditable(false);
        textField.setBackground(new Color(166, 166, 166));

        addB = new JButton("+");
        subB = new JButton("-");
        mulB = new JButton("x");
        divB = new JButton("÷");
        decb = new JButton(".");
        eqB = new JButton("=");
        delB = new JButton("Del");
        clrB = new JButton("Clear");
        negB = new JButton("(-)");

        functionButtons[0] = addB;
        functionButtons[1] = subB;
        functionButtons[2] = mulB;
        functionButtons[3] = divB;
        functionButtons[4] = decb;
        functionButtons[5] = eqB;
        functionButtons[6] = delB;
        functionButtons[7] = clrB;
        functionButtons[8] = negB;

        for(int i = 0; i<9; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);

        }

        for(int i = 0; i<10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);

        }

        delB.setBounds(0,410,110,50);
        clrB.setBounds(400-110,410,110,50);
        negB.setBounds(145,410,110,50);

        panel=new JPanel();
        panel.setBounds(25,100,350,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addB);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subB);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulB);
        panel.add(decb);
        panel.add(numberButtons[0]);
        panel.add(eqB);
        panel.add(divB);

        close.setBounds(0,0,20,20);
        close.setOpaque(true);
        close.setFont(new Font("",Font.BOLD,10));
        close.addActionListener(this);
        close.setFocusable(false);


        minimize.setBounds(20,0,20,20);
        minimize.setOpaque(true);
        minimize.setFont(new Font("",Font.BOLD,10));
        minimize.addActionListener(this);
        minimize.setFocusable(false);

        frame.add(minimize);
        frame.add(close);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        frame.addMouseListener( drag );
        frame.addMouseMotionListener( drag );
        frame.add(panel);
        frame.add(negB);
        frame.add(textField);
        frame.add(clrB);
        frame.add(delB);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0;i<10;i++){
            if(e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == decb){
            textField.setText(textField.getText().concat("."));
        }

        if(e.getSource() == addB){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if(e.getSource() == subB){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if(e.getSource() == mulB){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if(e.getSource() == divB){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if(e.getSource() == eqB){
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }

            textField.setText(String.valueOf(result));
            num1 = result;

        }

        if(e.getSource() == clrB){
            textField.setText("");
        }

        if(e.getSource()==delB) {

            String string = textField.getText();

            textField.setText("");

            for(int i=0;i<string.length()-1;i++) {

                textField.setText(textField.getText()+string.charAt(i));

            }

        }

        if(e.getSource() == negB){
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));

        }

        if(e.getSource() == close){
            frame.dispose();
        }

        if(e.getSource() == minimize){
            frame.setState(Frame.ICONIFIED);
        }




    }
}
