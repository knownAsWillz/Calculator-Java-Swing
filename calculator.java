package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;


// BUGS TO FIX
// DECIMAL DISPLAY

// SUGGERTIONS
// ADD MORE FUNCTIONALITIES

public class calculator extends JFrame implements ActionListener {

    JTextField text = new JTextField();
    StringBuilder logic = new StringBuilder();
    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton zero = new JButton("0");
    JButton add = new JButton("+");
    JButton min = new JButton("-");
    JButton mul = new JButton("*");
    JButton div = new JButton("/");
    JButton openPar = new JButton("(");
    JButton closePar = new JButton(")");
    JButton del = new JButton("DEL");
    JButton clr = new JButton("CLR");
    JButton equal = new JButton("=");
    JButton none = new JButton();
    JButton none2 = new JButton();

    calculator() {
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        equal.setBackground(Color.BLACK);
        equal.setForeground(Color.WHITE);
        equal.addActionListener(this);
        equal.setFocusPainted(false);
        equal.setPreferredSize(new Dimension(65, 50));
        
        del.setBackground(Color.BLACK);
        del.setForeground(Color.WHITE);
        del.addActionListener(this);
        del.setFocusPainted(false);
        del.setPreferredSize(new Dimension(65, 50));
        
        clr.setBackground(Color.decode("#fabc67"));
        clr.setFocusPainted(false);
        clr.setForeground(Color.BLACK);
        clr.addActionListener(this);
        clr.setPreferredSize(new Dimension(65, 50));
        
        text.setPreferredSize(new Dimension(500, 50));
        text.setBackground(Color.WHITE);
        text.setFont(new Font("", Font.PLAIN, 20));

        // Create a panel for buttons
        JPanel panel = new JPanel(new GridLayout(4, 4, 20, 20));

        // Create buttons
        JButton[] buttons = {
            one, two, three, add,
            four, five, six, min,
            seven, eight, nine, mul,
            none, zero, none2, div
        };

        // Add buttons to the panel
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFocusPainted(false);
            buttons[i].setFont(new Font("", Font.BOLD, 20));
            buttons[i].addActionListener(this);

            if (i == 3 || i == 7 || i == 11 || i == 15) {
                buttons[i].setBackground(Color.decode("#fabc67"));
                buttons[i].setForeground(Color.BLACK);
            } else {
                buttons[i].setBackground(Color.BLACK);
                buttons[i].setForeground(Color.WHITE);
            }
            
            none.setEnabled(false);
            none2.setEnabled(false);
            panel.add(buttons[i]);
        }
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(equal);
        buttonPanel.add(del);
        buttonPanel.add(clr);

        // Create a panel for the text field and the Backspace/Clear buttons
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.add(text, BorderLayout.CENTER);
        textFieldPanel.add(buttonPanel, BorderLayout.EAST);

        // Add the panels to the frame
        this.setLayout(new BorderLayout());
        this.add(textFieldPanel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

        this.setTitle("Calculator");
        this.getRootPane().setBorder(new LineBorder(Color.WHITE, 30));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // THE ACTION LISTENER FOR BUTTONS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one) logic.append("1");
        else if(e.getSource() == two) logic.append("2");
        else if(e.getSource() == three) logic.append("3");
        else if(e.getSource() == four) logic.append("4");
        else if(e.getSource() == five) logic.append("5");
        else if(e.getSource() == six) logic.append("6");
        else if(e.getSource() == seven) logic.append("7");
        else if(e.getSource() == eight) logic.append("8");
        else if(e.getSource() == nine) logic.append("9");
        else if(e.getSource() == zero) logic.append("0");
        else if(e.getSource() == add) logic.append("+");
        else if(e.getSource() == min) logic.append("-");
        else if(e.getSource() == mul) logic.append("*");
        else if(e.getSource() == div) logic.append("/");
        else if(e.getSource() == del) {
        	if(logic.length() != 0) logic.deleteCharAt(logic.length() - 1);
        }
        else if(e.getSource() == clr) logic.setLength(0);
        else if (e.getSource() == equal) {
            String ans = calculate(logic.toString());
            logic.setLength(0);

            if (ans.matches("\\d+\\.0")) {
                ans = ans.replace(".0", "");
            }
            
            logic.append(ans);
        }


        text.setText(logic.toString());
    }
    
    
    // HERE LIES HOW THE CALCULATOR LOGIC
    public String calculate(String s) {
        Stack<Double> stack = new Stack<>();
        char sign = '+';
        double num = 0.0;
        double res = 0.0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0.0;
            }
        }
        
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return String.valueOf(res);
    }

}

