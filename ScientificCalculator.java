
import java.util.Stack;
import java.lang.Math;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aman
 */
public class ScientificCalculator extends javax.swing.JFrame {

    /**
     * Creates new form ScientificCalculator
     */
    public ScientificCalculator() {
        initComponents();
         jRadioButton1.setEnabled(false);
    }
     public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        if(op1=='!'||op1=='√'||op1=='^')
            return false;
        if((op1=='S'||op1=='C'||op1=='T'||op1=='s'||op1=='c'||op1=='t'||op1=='l'||op1=='L')&&(op2 !='!'))
                return false;
       
        else
            return true;
    }
    
    public double ArithmeticOperation(String expression)
    {
         
		//double π=3.14159;
                char[] tokens = expression.toCharArray();
                Stack<Double> values = new Stack<Double>();
 
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
		
                for(int i=0;i<tokens.length;++i)
					//while there is input to be read
		{
                    
			// Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;
 
            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9')||tokens[i]=='π'||tokens[i]=='.'||tokens[i]=='-'&&(!Character.isLetter(tokens[i+1]))&&(i==0||!Character.isDigit(tokens[i-2])&&tokens[i-2]!=')'))
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i]!=' ')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            
            }
 
            // Current token is an opening brace, push it to 'ops'
             else if(tokens[i]=='-'&&Character.isLetter(tokens[i+1]))
            {
                ops.push(tokens[i+1]);
                i++;
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(' && ops.peek()!='Z')
                {
                   if(ops.peek()!='U'&&ops.peek()!='Y'&&ops.peek()!='W'&&ops.peek()!='T'&&ops.peek()!='S'&&ops.peek()!='C'&&ops.peek()!='√'&&ops.peek()!='s'&&ops.peek()!='c'&&ops.peek()!='L'&&ops.peek()!='t'&&ops.peek()!='l'&&ops.peek()!='!')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    else
                       values.push(applyOp2(ops.pop(), values.pop())); 
                }
                if(ops.peek()=='Z')
                   {
                   
                    double x=values.peek();
                    x=Math.abs(x);
                    values.pop();
                    values.push(x);
                   }
                ops.pop();
            }
 
            // Current token is an operator.
            else if (tokens[i] == 'S' || tokens[i] == 'C' || tokens[i]=='T'||tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/' || tokens[i]=='√'|| tokens[i]=='L'|| tokens[i]=='^' ||tokens[i]=='s'|| tokens[i]=='c'|| tokens[i]=='t'|| tokens[i]=='l'|| tokens[i]=='!')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                {
                    if(ops.peek()!='U'&&ops.peek()!='Y'&&ops.peek()!='W'&&ops.peek()!='T'&&ops.peek()!='S'&&ops.peek()!='C'&&ops.peek()!='√'&&ops.peek()!='L'&&ops.peek()!='s'&&ops.peek()!='c'&&ops.peek()!='t'&&ops.peek()!='l'&&ops.peek()!='!')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    else
                       values.push(applyOp2(ops.pop(), values.pop()));  
                } 
 
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
            if(!values.empty())
            jTextField1.setText("Syntax Error");
             
           
            }
        
                
                
 
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
        {
            if(ops.peek()!='U'&&ops.peek()!='Y'&&ops.peek()!='W'&&ops.peek()!='T'&&ops.peek()!='S'&&ops.peek()!='C'&&ops.peek()!='√'&&ops.peek()!='L'&&ops.peek()!='s'&&ops.peek()!='c'&&ops.peek()!='t'&&ops.peek()!='l'&&ops.peek()!='!')
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            else
              values.push(applyOp2(ops.pop(), values.pop()));  
        }
 
        // Top of 'values' contains result, return it
         if(values.size()==1)
        return values.pop();
        else
         return -1;
        
    }
        
    
   
    public double applyOp(char op,double b,double a)
    {    
            
switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
           // if (b == 0)
               // throw new
               // UnsupportedOperationException("Cannot divide by zero");
            return a / b;
        case '^':
            return Math.pow(a,b);
        
        }
        return 0;
    }
      public double applyOp2(char op,double b)
    {    
            
switch (op)
        {
        case '!':
            double fact;
            if(b<0)
            {
                b=Math.abs(b);
                 fact=-1;
            }
            else
           fact=1;
           for( int i=(int) b;i>=1;i--)
               fact*=i;
           return fact;
           
        case 's':
            b=(3.14159265359*b)/180;
            return (Math.sin(b));
        case 'c':
            b=(3.14159265359*b)/180;
             return (Math.cos(b));
        case 't': 
            b=(3.14159265359*b)/180;
             return (Math.tan(b));
        case 'l': 
            
             return (Math.log(b))/2.302585;
        case '√':
             return Math.sqrt(b);
        case 'L':
            return Math.log(b);
        case 'S':
            return Math.asin(Math.toRadians(b));
        case 'C':
            return Math.acos(Math.toRadians(b));
        case 'T':
            return Math.atan(Math.toRadians(b));
        case 'W':
             b=(3.14159265359*b)/180;
            return -(Math.sin(b));
        case 'Y':
             b=(3.14159265359*b)/180;
            return -(Math.cos(b));
        case 'U':
             b=(3.14159265359*b)/180;
            return -(Math.tan(b));    
        }
        return 0;
    }
      
    public void disable()
   {
       jTextField1.setEnabled(false);
       jRadioButton1.setEnabled(true);
       jRadioButton2.setEnabled(false);
       
       jButton1.setEnabled(false);
       jButton2.setEnabled(false);
       jButton15.setEnabled(false);
       jButton3.setEnabled(false);
       jButton4.setEnabled(false);
       jButton5.setEnabled(false);
       jButton6.setEnabled(false);
       jButton7.setEnabled(false);
       jButton8.setEnabled(false);
       jButton9.setEnabled(false);
       jButton10.setEnabled(false);
       jButton11.setEnabled(false);
       jButton12.setEnabled(false);
       jButton13.setEnabled(false);
       jButton14.setEnabled(false);
       jButton16.setEnabled(false);
       jButton17.setEnabled(false);
       jButton18.setEnabled(false);
       jButton19.setEnabled(false);
       jButton20.setEnabled(false);
       jButton21.setEnabled(false);
       jButton22.setEnabled(false);
       jButton23.setEnabled(false);
       jButton24.setEnabled(false);
       jButton25.setEnabled(false);
       jButton26.setEnabled(false);
       jButton27.setEnabled(false);
       jButton28.setEnabled(false);
       jButton29.setEnabled(false);
       jButton30.setEnabled(false);
       jButton31.setEnabled(false);
       jButton32.setEnabled(false);
       jButton33.setEnabled(false);
   }
    
public void enable()
   {
       jTextField1.setEnabled(true);
       jRadioButton1.setEnabled(false);
       jRadioButton2.setEnabled(true);
       
       jButton1.setEnabled(true);
       jButton2.setEnabled(true);
       jButton15.setEnabled(true);
       jButton3.setEnabled(true);
       jButton4.setEnabled(true);
       jButton5.setEnabled(true);
       jButton6.setEnabled(true);
       jButton7.setEnabled(true);
       jButton8.setEnabled(true);
       jButton9.setEnabled(true);
       jButton10.setEnabled(true);
       jButton11.setEnabled(true);
       jButton12.setEnabled(true);
       jButton13.setEnabled(true);
       jButton14.setEnabled(true);
       jButton16.setEnabled(true);
       jButton17.setEnabled(true);
       jButton19.setEnabled(true);
       jButton18.setEnabled(true);
       jButton20.setEnabled(true);
       jButton21.setEnabled(true);
       jButton22.setEnabled(true);
       jButton23.setEnabled(true);
       jButton24.setEnabled(true);
       jButton25.setEnabled(true);
       jButton26.setEnabled(true);
       jButton27.setEnabled(true);
       jButton28.setEnabled(true);
       jButton29.setEnabled(true);
       jButton30.setEnabled(true);
       jButton32.setEnabled(true);
       jButton31.setEnabled(true);
       jButton33.setEnabled(true);
   }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(26, 20, 14));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ScientificCalculator"));
        jPanel1.setName(""); // NOI18N

        jTextField1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton1.setText("AC");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(189, 189, 174));
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(110, 96, 96));
        jButton2.setText("/");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton3.setText("*");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton6.setText("=");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton7.setText(".");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton8.setText("DEL");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton9.setText("√");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton10.setText("tan");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton11.setText("π");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(java.awt.Color.white);
        jButton12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton12.setForeground(new java.awt.Color(17, 13, 13));
        jButton12.setText("3");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(java.awt.Color.white);
        jButton13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton13.setForeground(new java.awt.Color(24, 19, 19));
        jButton13.setText("2");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(java.awt.Color.white);
        jButton14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton14.setForeground(new java.awt.Color(16, 14, 14));
        jButton14.setText("1");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton15.setText("Ln");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton16.setText("cos");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton17.setText("^");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(java.awt.Color.white);
        jButton18.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton18.setForeground(new java.awt.Color(30, 9, 9));
        jButton18.setText("6");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(java.awt.Color.white);
        jButton19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton19.setForeground(new java.awt.Color(24, 14, 14));
        jButton19.setText("5");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(java.awt.Color.white);
        jButton20.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton20.setForeground(new java.awt.Color(5, 5, 5));
        jButton20.setText("4");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton21.setText("log");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton22.setText("sin");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton23.setText("e");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(java.awt.Color.white);
        jButton24.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton24.setForeground(new java.awt.Color(14, 7, 7));
        jButton24.setText("9");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(java.awt.Color.white);
        jButton25.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton25.setForeground(new java.awt.Color(25, 25, 25));
        jButton25.setText("8");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(java.awt.Color.white);
        jButton26.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton26.setForeground(new java.awt.Color(23, 23, 23));
        jButton26.setText("7");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton27.setText("tanh");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton28.setText("cosh");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton29.setText("sinh");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(java.awt.Color.white);
        jButton30.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton30.setForeground(new java.awt.Color(22, 12, 12));
        jButton30.setText("0");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jRadioButton1.setText("ON");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jRadioButton2.setText("OFF");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton31.setText(")");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton32.setText("(");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton33.setText("!");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(jButton17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jTextField1))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 182, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton25.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        int l=jTextField1.getText().length();
        String ss=jTextField1.getText();
        String ss1="";
         if(ss.charAt(0)=='π'||ss.charAt(0)=='e')
         {
             if(ss.charAt(0)=='π')
           ss1+="3.141592653589793238462643383279";
         else 
           ss1+="2.71828";
         }
        else
            ss1=String.valueOf(ss.charAt(0));
       
        for(int i=1;i<l;i++)
        {
            
            
            
            if(Character.isDigit(ss.charAt(i))&&Character.isDigit(ss.charAt(i-1))||ss.charAt(i)=='.'||ss.charAt(i-1)=='.'||((ss.charAt(i-1)=='-' && Character.isDigit(ss.charAt(i))) && (i==1||(!Character.isDigit(ss.charAt(i-2))&&ss.charAt(i-2)!=')')))||(ss.charAt(i)=='π')||(ss.charAt(i)=='e')||(ss.charAt(i-1)=='-'&&(i==1||!Character.isDigit(ss.charAt(i-2))&&!Character.isDigit(ss.charAt(i)))))
            {
                
                 if(ss.charAt(i)=='π'||ss.charAt(i)=='e'||ss.charAt(i)=='c'||ss.charAt(i)=='s'||ss.charAt(i)=='t'||ss.charAt(i)=='(')
                   {
                       if(ss.charAt(i)=='π'||ss.charAt(i)=='e')
                       {
                           if(ss.charAt(0)=='π')
                              ss1+="3.141592653589793238462643383279";
                           else 
                              ss1+="2.71828";
                       }
                       else
                       {
                           if(ss.charAt(i)=='s'||ss.charAt(i)=='c'||ss.charAt(i)=='t'||ss.charAt(i)=='(')
                {
                    if(ss.charAt(i)=='s'||ss.charAt(i)=='c'||ss.charAt(i)=='t')
                    {
                    if(ss.charAt(i)=='s')
                    ss1+="W";
                 else if(ss.charAt(i)=='c')
                    ss1+="Y";
                 else if(ss.charAt(i)=='t')
                    ss1+="U";
                    i+=2;
                    }
                    else 
                        ss1+="Z";
                       }
                   }
                  }
                 else
                   ss1+=ss.charAt(i);
            }
            
            else
            {
                if(ss.charAt(i)=='i'||ss.charAt(i)=='a'||ss.charAt(i)=='o'||ss.charAt(i)=='I'||ss.charAt(i)=='A'||ss.charAt(i)=='O')
                {
                    if(ss.charAt(i)=='I'||ss.charAt(i)=='A'||ss.charAt(i)=='O')
                        i+=2;
                   else
                    i+=1;
                }
                else
                ss1+=" "+ss.charAt(i);
                if( ((ss.charAt(i)=='s'&&ss.charAt(i-1)!='o')||ss.charAt(i)=='c'||ss.charAt(i)=='t'||ss.charAt(i)=='l'||ss.charAt(i)=='L'||(ss.charAt(i)=='S'&&ss.charAt(i)=='O')||ss.charAt(i)=='C'||ss.charAt(i)=='T'))
                {
                    if(ss.charAt(i)=='L'||ss.charAt(i)=='S'||ss.charAt(i)=='C'||ss.charAt(i)=='T')
                    {
                        if(ss.charAt(i)=='L')
                        i+=1;
                        else
                         if(ss.charAt(i)=='S'||ss.charAt(i)=='C'||ss.charAt(i)=='T')
                        i+=3;   
                    }
                    else
                        i+=2;
                }
                   
                
            }
            
        }
         //resultc.setText(Double.toString(ArithmeticOperation(ss1)));
         String s=jTextField1.getText()+'=';
         
         jLabel1.setText(s);
        
         try{
             if(ArithmeticOperation(ss1)==-1)
               jTextField1.setText("Syntax ERROR");
             else
         jTextField1.setText(Double.toString(ArithmeticOperation(ss1)));
         }
         catch(Exception e)
         {
             jTextField1.setText("Syntax ERROR");
         }
         
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton15.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton22.getText();
        jTextField1.setText(Display);
        
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton2.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton3.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton4.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton5.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton24.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton26.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton20.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton19.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton18.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton14.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton13.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton12.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         int length= jTextField1.getText().length()-1;
        int number= jTextField1.getText().length()-1;
        String store;
        
        while(length>=0)
        {
            StringBuilder back= new StringBuilder(jTextField1.getText());
            back.deleteCharAt(number);
            store=back.toString();
            jTextField1.setText(store);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jLabel1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton7.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton27.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton9.getText();
        jTextField1.setText(Display);
       
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton21.getText();
        jTextField1.setText(Display);
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
         String Display=jTextField1.getText()+jButton30.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
         String Display=jTextField1.getText()+jButton29.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
         String Display=jTextField1.getText()+jButton28.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton16.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton11.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton17.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
         String Display=jTextField1.getText()+jButton23.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton10.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton31.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton32.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        String Display=jTextField1.getText()+jButton33.getText();
        jTextField1.setText(Display);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        enable();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
         jTextField1.setText("");
         jLabel1.setText("");
        disable();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScientificCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScientificCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScientificCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScientificCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
