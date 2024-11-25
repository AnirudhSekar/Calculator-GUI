import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Main implements ActionListener {
    private String number;
    private int num;
    private int num_index = 0;
    private int op_index = 0;
    private double[] nums = new double[100];
    private char[] operations = new char[100];
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public Main() {
        frame = new JFrame();
        // Create all buttons needed for the calculator
        JButton b1 = new JButton("1");
        b1.addActionListener(this);

        JButton b2 = new JButton("2");
        b2.addActionListener(this);
        JButton b3 = new JButton("3");
        b3.addActionListener(this);
        JButton b4 = new JButton("4");
        b4.addActionListener(this);
        JButton b5 = new JButton("5");
        b5.addActionListener(this);
        JButton b6 = new JButton("6");
        b6.addActionListener(this);
        JButton b7 = new JButton("7");
        b7.addActionListener(this);
        JButton b8 = new JButton("8");
        b8.addActionListener(this);
        JButton b9 = new JButton("9");
        b9.addActionListener(this);
        JButton b0 = new JButton("0");
        b0.addActionListener(this);
        JButton bp = new JButton("+");
        bp.addActionListener(this);
        JButton bm = new JButton("-");
        bm.addActionListener(this);
        JButton bmu = new JButton("*");
        bmu.addActionListener(this);
        JButton bd = new JButton("/");
        bd.addActionListener(this);
        JButton bc = new JButton("C");
        bc.addActionListener(this);
        JButton be = new JButton("=");
        be.addActionListener(this);


        // Label keeping track of user input
        label = new JLabel("0");
        // Create new JPanel with all buttons and the label
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 200, 200));
        panel.setLayout(new GridLayout(3, 4));
        panel.add(label);
        panel.add(bc);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(b0);
        panel.add(bp);
        panel.add(bm);
        panel.add(bmu);
        panel.add(bd);
        panel.add(be);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        String val = clicked.getText();
        char newval = val.charAt(0);
        // Handle Clearing and Entering Numbers

        // Clear
        if (val.equals("C")) {
            number = "";
            label.setText("0");
            num = 0;
            operations = new char[100];
            nums = new double[100];
            num_index = 0;
            op_index = 0;
        }
        // First number entered
        else if (label.getText().equals("0")) {
            number = val;
            label.setText(number);
            num = Integer.parseInt(clicked.getText());
        }
        // Any other number
        else if (newval != '+' && newval != '-' && newval != '*' && newval != '/' && newval != '=') {
            label.setText(label.getText() + val);
            number += val;
            num = Integer.parseInt(number);
        }
        // Handle Operations
        if (newval == '+') {

            label.setText(label.getText() + '+');
            number = "";
            nums[num_index] = num;
            num_index++;
            operations[op_index] = '+';
            op_index++;
            num = 0;
        } else if (newval == '-') {
            label.setText(label.getText() + '-');
            number = "";
            nums[num_index] = num;
            num_index++;
            operations[op_index] = '-';
            op_index++;
            num = 0;

        } else if (newval == '*') {
            label.setText(label.getText() + '*');
            number = "";
            nums[num_index] = num;
            num_index++;
            operations[op_index] = '*';
            op_index++;
            num = 0;

        } else if (newval == '/') {
            label.setText(label.getText() + '/');
            number = "";
            nums[num_index] = num;
            num_index++;
            operations[op_index] = '/';
            op_index++;
            num = 0;

        } else if (newval == '=') {
            nums[num_index] = num;
            num_index++;
            int nLength = nums.length;
            int oLength = operations.length;

            for (int i = 0; i < oLength; i++) {
                if (operations[i] == '*' || operations[i] == '/') {
                    double result = 0;
                    if (operations[i] == '*') {
                        result = nums[i] * nums[i + 1];
                    } else if (operations[i] == '/') {
                        if (nums[i + 1] == 0) {
                            label.setText("Division by zero is not allowed.");
                            return;
                        }
                        result = (double)nums[i] / nums[i + 1];
                    }

                    // Update arrays: replace nums[i] with the result, shift the rest left
                    nums[i] = result;
                    nums = shiftLeft(nums, i + 1);
                    operations = shiftLeft(operations, i);

                    // Adjust lengths
                    nLength--;
                    oLength--;

                    // Stay at the same index after shifting
                    i--;
                }
            }

            // Step 2: Handle Addition (+) and Subtraction (-)
            double result = nums[0];
            for (int i = 0; i < oLength; i++) {
                if (operations[i] == '+') {
                    result += nums[i + 1];
                } else if (operations[i] == '-') {
                    result -= nums[i + 1];
                }
            }
            /*for (int i = 0; i < op_index; i++) {
                if(i+1 < num_index) {
                    int numNext = nums[i + 1];
                    System.out.println(Arrays.toString(nums));
                    System.out.println(nums[i]);
                    System.out.println(numNext);
                    if (operations[i] == '+') {
                        res += numNext;
                    } else if (operations[i] == '-') {
                        res -= numNext;
                    } else if (operations[i] == '*') {
                        res *= numNext;
                    } else if (operations[i] == '/') {
                        res /= numNext;
                    }
                }

             */


            label.setText("" + result);
            number = "";
            num = 0;
            operations = new char[100];
            nums = new double[100];
            num_index = 0;
            op_index = 0;
        }
    }
    // Helper method to shift elements in an int array to the left
    public static double[] shiftLeft(double[] arr, int startIndex) {
        double[] newArr = new double[arr.length - 1];
        for (int i = 0; i < startIndex; i++) {
            newArr[i] = arr[i];
        }
        for (int i = startIndex; i < newArr.length; i++) {
            newArr[i] = arr[i + 1];
        }
        return newArr;
    }

    // Helper method to shift elements in a char array to the left
    public static char[] shiftLeft(char[] arr, int startIndex) {
        char[] newArr = new char[arr.length - 1];
        for (int i = 0; i < startIndex; i++) {
            newArr[i] = arr[i];
        }
        for (int i = startIndex; i < newArr.length; i++) {
            newArr[i] = arr[i + 1];
        }
        return newArr;
    }

}