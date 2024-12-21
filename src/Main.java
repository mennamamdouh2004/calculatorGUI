import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(435, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

//عشان اظهر 5+ جمب بعض وانا بكتب

        Font f18 = new Font("Times New Roman", Font.BOLD, 18);    // خط الرقم ال old
        Font f28 = new Font("Times New Roman", Font.BOLD, 28);  // خط الرقم ال new


        JLabel oldValue = new JLabel("0");                 //1. بنشأه
        JLabel newValue = new JLabel("0");
        JLabel operator = new JLabel("");


        oldValue.setBounds(0, 5, 380, 25);   //2. بحدد مكانه
        newValue.setBounds(0, 30, 400, 100);
        operator.setBounds(390, 5, 15, 25);

        //عشان اخليه من اليمين للشمال الرقم
        oldValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        newValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        frame.add(oldValue);                                             // .3 بظهره
        frame.add(newValue);
        frame.add(operator);
        frame.setVisible(true);

        // اضيق الfont لل valuo
        oldValue.setFont(f18);
        newValue.setFont(f28);
        operator.setFont(f18);


        // الزراير
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

// مصفوفة لتخزين الأزرار
        JButton[] buttons = new JButton[buttonLabels.length];

        //تحديد الزراير
        int x = 10, y = 100, width = 100, height = 50;

        // إنشاء الأزرار وإضافتها إلى النافذة
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(x, y, width, height);
            buttons[i].setFont(f18);
            frame.add(buttons[i]);


            x += 105;
            if ((i + 1) % 4 == 0) {
                x = 10;
                y += 60;
            }
        }



        final double[] num1 = {0};
        final String[] currentOp = {""};



        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = button.getText();

                    if ("0123456789".contains(command)) {
                        if (newValue.getText().equals("0")) {
                            newValue.setText(command);
                        } else {
                            newValue.setText(newValue.getText() + command);
                        }
                    } else if ("/*-+".contains(command)) {
                        num1[0] = Double.parseDouble(newValue.getText());
                        currentOp[0] = command;
                        operator.setText(command);
                        oldValue.setText(newValue.getText());
                        newValue.setText("0");
                    } else if (command.equals("=")) {
                        double num2 = Double.parseDouble(newValue.getText());
                        double result = 0; // تخزين النتيجة
                        switch (currentOp[0]) {
                            case "+": result = num1[0] + num2; break;
                            case "-": result = num1[0] - num2; break;
                            case "*": result = num1[0] * num2; break;
                            case "/":
                                if (num2 != 0) {
                                    result = num1[0] / num2;
                                } else {
                                    newValue.setText("Error");
                                    return;
                                }
                                break;
                        }
                        newValue.setText(String.valueOf(result)); // عرض النتيجة
                        oldValue.setText("");
                        operator.setText("");
                    } else if (command.equals("C")) {
                        num1[0] = 0;
                        currentOp[0] = "";
                        newValue.setText("0");
                        oldValue.setText("");
                        operator.setText("");
                    }
                }
            });
        }

        frame.setVisible(true); // عرض المشروع




                                }
    }





