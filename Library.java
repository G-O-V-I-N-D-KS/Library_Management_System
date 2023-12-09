package dbmsproject; 
import java.sql.*; 
import java.sql.Connection;
import javax.swing.*; 
import javax.swing.border.EmptyBorder; 
import java.awt.event.*; 
import java.util.*;
import java.util.Date; 
import java.awt.*; 
import java.text.*; 
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.TableCellRenderer; 
//Design Elements. 

class TableHeaderRenderer implements TableCellRenderer { 
    private final TableCellRenderer baseRenderer; 

    public TableHeaderRenderer(TableCellRenderer baseRenderer) { 
        this.baseRenderer = baseRenderer; 
    }

    @Override 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) { 
        JComponent c = (JComponent) baseRenderer.getTableCellRendererComponent(table, value, isSelected,hasFocus,row,column);
        c.setBorder(new EmptyBorder(2, 2, 2, 2));
        return c; 
    } 
}


//End Design Elements. 
class login implements ActionListener { 
    JFrame F; 
    JLabel labeluser,labelpwd,stu_l,stuname_l,stubrn_l; 
    JPanel panel_login,background,stud_form; 
    JTextField userField, stu_t, stuname_t, stubrn_t; 
     JPasswordField pwdField; 
    JButton login, save_login, newStudent; 
    // Border border = new LineBorder(new Color(58, 63, 156), 2, true); 
    // issue book 
    JPanel issue_book; 
    JButton issueBook, issue; 
    JLabel bstu_l, book_l, issuedate_l, duedate_l; 
    JTextField bstu_t, book_t, issuedate_t, duedate_t; 
    String issuedate_tString; 
    // end issue book 
    // new book 
    JPanel new_book; 
    JButton newBook, save_book; 
    JLabel bid_l, bname_l, pub_l, pubdate_l, categ_l, no_of_copy_l;
    JTextField bid_t, bname_t, pub_t, pubdate_t, categ_t, no_of_copy_t; 
    // end new book 
    // display label 
    int flag = 0; 
    JLabel error; 
    // end display label 
    // return book 
    JPanel return_book; 
    JButton returnBook, returnbtn; 
    JLabel sturet_l, bookret_l, return_l; 
    JTextField sturet_t, bookret_t, return_t; 
    String return_tString; 
    // end return book 
    //statistics 
    JTable j; 
    JButton statistics; 
    JPanel stati; 
    JScrollPane sp; 
    //end statistics 
    Connection conn = null;
    Statement stmt = null;ResultSet rs = null; 
    Connection temp_conn = null; 
    Statement temp_stmt = null; 
    ResultSet temp_rs = null; 
    String nameString, staffidString, loginid_staffString, pwdString; 
    login() { 
        F= new JFrame("Library Management"); 
        F.setSize(850, 450); 
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        F.setLayout(null); 
        F.setLocation(350,150); 
        F.setResizable(false); 
        F.getContentPane().setBackground(new Color(41, 42, 45)); 
        //login 
        panel_login = new JPanel();
        panel_login.setSize(500, 500);
        panel_login.setLocation(166, 70);
        panel_login.setVisible(true); 
        panel_login.setBackground(new Color(62, 64, 69)); 
        labeluser = new JLabel("Username");
        labeluser.setBounds(150, 70, 120, 25); 
        labeluser.setForeground(Color.WHITE); 
        labeluser.setFont(new Font("Century Gothic",Font.BOLD,13));
        panel_login.add(labeluser); 

        userField = new JTextField();
        userField.setBounds(150,100,200,30);
        userField.setHorizontalAlignment(JTextField.CENTER); 
        userField.setBorder(null); 
        panel_login.add(userField); 
        labelpwd = new JLabel("Password");
        labelpwd.setBounds(150, 138, 80, 25); 
        labelpwd.setForeground(Color.WHITE); 
        labelpwd.setFont(new Font("Century Gothic",Font.BOLD,13));
        panel_login.add(labelpwd); 
        pwdField = new JPasswordField();pwdField.setBounds(150,165,200,30);
        pwdField.setHorizontalAlignment(JTextField.CENTER); 
        pwdField.setBorder(null); 
        panel_login.add(pwdField); 
        login = new JButton("Login"); 
        login.addActionListener(this); 
        login.setBounds(212, 220, 75, 30); 
        login.setFocusPainted(false); 
        panel_login.add(login); 
        // background 
        background = new JPanel();
        background.setSize(850, 500);
        background.setVisible(false); 
        background.setLayout(null); 
        // background.setLocation(150, 20); 
        background.setBackground(new Color(41,42,45)); 
        panel_login.setLayout(null); 
        F.setVisible(true); 
        F.add(panel_login); 
        F.add(background); 
        // Background Panel. 
        newStudent = new JButton("New Student"); 
        newStudent.addActionListener(this); 
        newStudent.setBounds(20,20,150,30); 
        background.add(newStudent); 
        newStudent.setFocusPainted(false); 
        issueBook = new JButton("Issue Book");
        issueBook.addActionListener(this); 
        issueBook.setBounds(340,20,150,30);
        background.add(issueBook);
        issueBook.setFocusPainted(false); 
        // End Background Panel. 
        // New Student Form. 
        stud_form = new JPanel(); 
        stud_form.setSize(500,350); 
        stud_form.setLocation(166,90); 
        stud_form.setVisible(false); 
        stud_form.setLayout(null); 
        stud_form.setBackground(new Color(62,64,69)); 
        stu_l = new JLabel("Student ID"); 
        stu_l.setBounds(90,85,200,30);
        stud_form.add(stu_l); 
        stu_l.setForeground(Color.WHITE); 
        stu_l.setFont(new Font("Century Gothic",Font.BOLD,12)); 
        stu_t = new JTextField(); 
        stu_t.setBounds(190,85,200,30); 
        stu_t.setHorizontalAlignment(JTextField.CENTER); 
        stu_t.setBorder(null); 
        stud_form.add(stu_t); 
        stuname_l = new JLabel("Name"); 
        stuname_l.setBounds(112,127,200,30); 
        stud_form.add(stuname_l); 
        stuname_l.setForeground(Color.WHITE); 
        stuname_l.setFont(new Font("Century Gothic",Font.BOLD, 12)); 
        stuname_t = new JTextField(); 
        stuname_t.setBounds(190,127,200,30); 
        stuname_t.setHorizontalAlignment(JTextField.CENTER); 
        stuname_t.setBorder(null); 
        stud_form.add(stuname_t); 
        stubrn_l = new JLabel("Branch");
        stubrn_l.setBounds(108,169,200,30);
        stud_form.add(stubrn_l); 
        stubrn_l.setForeground(Color.WHITE); 
        stubrn_l.setFont(new Font("Century Gothic",Font.BOLD, 12)); 
        stubrn_t = new JTextField(); 
        stubrn_t.setBounds(190,169,200,30); 
        stubrn_t.setHorizontalAlignment(JTextField.CENTER); 
        stubrn_t.setBorder(null); 
        stud_form.add(stubrn_t); 
        save_login = new JButton("Save");
        save_login.addActionListener(this);
        save_login.setBounds(315, 211, 75, 30);
        save_login.setFocusPainted(false);
        stud_form.add(save_login); 
        background.add(stud_form); 
        // End Student Form. 
        // Issue Book. 
        issue_book = new JPanel();
        issue_book.setSize(500,350); 
        issue_book.setLocation(166,90); 
        issue_book.setLayout(null); 
        issue_book.setVisible(false); 
        issue_book.setBackground(new Color(62,64,69)); 
        bstu_l = new JLabel("Student ID"); 
        bstu_l.setBounds(90,65,200,30); 
        issue_book.add(bstu_l); 
        bstu_l.setForeground(Color.WHITE); 
        bstu_l.setFont(new Font("Century Gothic",Font.BOLD,12)); 
        bstu_t = new JTextField(); 
        bstu_t.setBounds(190,65,200,30); 
        bstu_t.setHorizontalAlignment(JTextField.CENTER); 
        issue_book.add(bstu_t); 
        bstu_t.setBorder(null); 
        book_l = new JLabel("Book ID"); 
        book_l.setBounds(103,107,200,30); 
        issue_book.add(book_l); 
        book_l.setForeground(Color.WHITE); 
        book_l.setFont(new Font("Century Gothic",Font.BOLD,12)); 
        book_t = new JTextField(); 
        book_t.setBounds(190,107,200,30); 
        issue_book.add(book_t); 
        book_t.setHorizontalAlignment(JTextField.CENTER); 
        book_t.setBorder(null); 
        issuedate_l = new JLabel("Issue Date"); 
        issuedate_l.setBounds(90,149,200,30); 
        issue_book.add(issuedate_l); 
        issuedate_l.setForeground(Color.WHITE); 
        issuedate_l.setFont(new Font("Century Gothic",Font.BOLD,12)); 
        issuedate_t = new JTextField(); 
        issuedate_t.setBounds(190,149,200,30); 
        issuedate_t.setEditable(false); 
        issuedate_t.setHorizontalAlignment(JTextField.CENTER); 
        issue_book.add(issuedate_t); 
        issuedate_t.setBorder(null); 
        duedate_l = new JLabel("Due Date"); 
        duedate_l.setBounds(93,191,200,30); 
        issue_book.add(duedate_l); 
        duedate_l.setForeground(Color.WHITE); 
        duedate_l.setFont(new Font("Century Gothic",Font.BOLD,12)); 
        duedate_t = new JTextField(); 
        duedate_t.setBounds(190,191,200,30); 
        duedate_t.setHorizontalAlignment(JTextField.CENTER); 
        issue_book.add(duedate_t); 
        duedate_t.setBorder(null); 
        issue = new JButton("Issue"); 
        issue.addActionListener(this); 
        issue.setBounds(315,233,75,30); 
        issue.setFocusPainted(false); 
        issue_book.add(issue); 
        background.add(issue_book); 
        // End Issue Book. 
        // New Book. 
        new_book = new JPanel();
        new_book.setSize(500,350);
        new_book.setLocation(166,72); 
        new_book.setVisible(false); 
        new_book.setLayout(null); 
        new_book.setBackground(new Color(62,64,69)); 
        newBook = new JButton("New Book");
        newBook.addActionListener(this);
        newBook.setBounds(180, 20, 150, 30);
        background.add(newBook);
        newBook.setFocusPainted(false);
        bid_l = new JLabel("Book ID");
        bid_l.setBounds(102, 35, 200, 30);
        new_book.add(bid_l); 
        bid_l.setForeground(Color.WHITE); 
        bid_l.setFont(new Font("Century Gothic",Font.BOLD, 12)); 
        bid_t = new JTextField();
        bid_t.setBounds(190, 35, 200, 30);
        bid_t.setHorizontalAlignment(JTextField.CENTER); 
        bid_t.setBorder(null);
        new_book.add(bid_t); 
        bname_l = new JLabel("Book Name"); 
        bname_l.setBounds(79, 77, 200, 30); 
        new_book.add(bname_l); 
        bname_l.setForeground(Color.WHITE); 
        bname_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        bname_t = new JTextField();
        bname_t.setBounds(190, 77, 200, 30);
        bname_t.setHorizontalAlignment(JTextField.CENTER); 
        bname_t.setBorder(null); 
        new_book.add(bname_t); 
        pub_l = new JLabel("Publisher");
        pub_l.setBounds(98, 119, 200, 30);
        new_book.add(pub_l); 
        pub_l.setForeground(Color.WHITE); 
        pub_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        pub_t = new JTextField();
        pub_t.setBounds(190, 119, 200, 30);
        pub_t.setHorizontalAlignment(JTextField.CENTER); 
        pub_t.setBorder(null); 
        new_book.add(pub_t); 
        pubdate_l = new JLabel("Published Date");
        pubdate_l.setBounds(63, 161, 200, 30); 
        new_book.add(pubdate_l); 
        pubdate_l.setForeground(Color.WHITE); 
        pubdate_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        pubdate_t = new JTextField();
        pubdate_t.setBounds(190, 161, 200, 30); 
        pubdate_t.setHorizontalAlignment(JTextField.CENTER); 
        pubdate_t.setBorder(null); 
        new_book.add(pubdate_t); 
        categ_l = new JLabel("Category"); 
        categ_l.setBounds(95, 203, 200, 30); 
        new_book.add(categ_l); 
        categ_l.setForeground(Color.WHITE); 
        categ_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        categ_t = new JTextField(); 
        categ_t.setBounds(190, 203, 200, 30); 
        categ_t.setHorizontalAlignment(JTextField.CENTER); 
        categ_t.setBorder(null); 
        new_book.add(categ_t); 
        no_of_copy_l = new JLabel("No. of Copies");
        no_of_copy_l.setBounds(72, 245, 200, 30); 
        new_book.add(no_of_copy_l); 
        no_of_copy_l.setForeground(Color.WHITE); 
        no_of_copy_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        no_of_copy_t = new JTextField();
        no_of_copy_t.setBounds(190, 245, 200, 30); 
        no_of_copy_t.setHorizontalAlignment(JTextField.CENTER); 
        no_of_copy_t.setBorder(null); 
        new_book.add(no_of_copy_t); 
        save_book = new JButton("Save"); 
        save_book.addActionListener(this); 
        save_book.setBounds(315, 287, 75, 30); 
        save_book.setFocusPainted(false); 
        new_book.add(save_book); 
        background.add(new_book); 
        // End New Book. 
        // display label 
        error = new JLabel("Login Unsuccessful!!!");
        error.setBounds(190, 275, 200, 30); 
        error.setForeground(Color.GRAY);
        error.setVisible(false); 
        panel_login.add(error); // end display label 
        // Return Book. 
        return_book = new JPanel(); 
        return_book.setSize(500, 350); 
        return_book.setLocation(166, 90); 
        return_book.setVisible(false); 
        return_book.setLayout(null); 
        return_book.setBackground(new Color(62, 64, 69)); 
        returnBook = new JButton("Return Book");
        returnBook.addActionListener(this);
        returnBook.setBounds(500, 20, 150, 30);
        background.add(returnBook);
        returnBook.setFocusPainted(false); 
        sturet_l = new JLabel("Student ID");
        sturet_l.setBounds(90, 85, 200, 30);
        return_book.add(sturet_l); 
        sturet_l.setForeground(Color.WHITE); 
        sturet_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        sturet_t = new JTextField(); 
        sturet_t.setBounds(190, 85, 200, 30); 
        sturet_t.setHorizontalAlignment(JTextField.CENTER); 
        sturet_t.setBorder(null); 
        return_book.add(sturet_t); 
        bookret_l = new JLabel("Book ID");
        bookret_l.setBounds(103, 127, 200, 30); 
        return_book.add(bookret_l); 
        bookret_l.setForeground(Color.WHITE); 
        bookret_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        bookret_t = new JTextField(); 
        bookret_t.setBounds(190, 127, 200, 30); 
        bookret_t.setHorizontalAlignment(JTextField.CENTER); 
        bookret_t.setBorder(null); 
        return_book.add(bookret_t); 
        return_l = new JLabel("Return Date");
        return_l.setBounds(81, 169, 200, 30);
        return_book.add(return_l); 
        return_l.setForeground(Color.WHITE); 
        return_l.setFont(new Font("Century Gothic", Font.BOLD, 12)); 
        return_t = new JTextField(); 
        return_t.setBounds(190, 169, 200, 30); 
        return_t.setEditable(false); 
        return_t.setBorder(null); 
        return_book.add(return_t); 
        return_t.setHorizontalAlignment(JTextField.CENTER); 
        returnbtn = new JButton("Return"); 
        returnbtn.addActionListener(this); 
        returnbtn.setBounds(315, 211, 75, 30); 
        returnbtn.setFocusPainted(false); 
        return_book.add(returnbtn); 
        background.add(return_book); 
        // End Return Book. 
        //Statistics. 
        stati = new JPanel(); 
        stati.setSize(790, 350); 
        stati.setLocation(20,85); 
        stati.setVisible(false); 
        stati.setLayout(null); 
        stati.setBackground(new Color(62, 64, 69)); 
        statistics = new JButton("Statistics"); 
        statistics.addActionListener(this); 
        statistics.setBounds(660, 20, 150, 30); 
        background.add(statistics); 
        background.add(stati); 
        statistics.setFocusPainted(false); 
        //End Statistics. 
        //Buttons. 
        login.setBorderPainted(false); 
        login.setContentAreaFilled(false); 
        login.setOpaque(true); 
        login.setForeground(Color.WHITE); 
        login.setBackground(new Color(58, 63, 156)); 
        login.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
        newStudent.setBorderPainted(false); 
        newStudent.setContentAreaFilled(false); 
        newStudent.setOpaque(true); 
        newStudent.setForeground(Color.WHITE); 
        newStudent.setBackground(new Color(58, 63, 156)); 
        newStudent.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
        newBook.setBorderPainted(false); 
        newBook.setContentAreaFilled(false); 
        newBook.setOpaque(true); 
        newBook.setForeground(Color.WHITE); 
        newBook.setBackground(new Color(58, 63, 156)); 
        newBook.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
        issueBook.setBorderPainted(false); 
        issueBook.setContentAreaFilled(false); 
        issueBook.setOpaque(true); 
        issueBook.setForeground(Color.WHITE); 
        issueBook.setBackground(new Color(58, 63, 156)); 
        issueBook.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
     
    returnBook.setBorderPainted(false); 
    returnBook.setContentAreaFilled(false); 
    returnBook.setOpaque(true); 
    returnBook.setForeground(Color.WHITE); 
    returnBook.setBackground(new Color(58, 63, 156)); 
    returnBook.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    statistics.setBorderPainted(false); 
    statistics.setContentAreaFilled(false); 
    statistics.setOpaque(true); 
    statistics.setForeground(Color.WHITE); 
    statistics.setBackground(new Color(58, 63, 156)); 
    statistics.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    save_login.setBorderPainted(false); 
    save_login.setContentAreaFilled(false); 
    save_login.setOpaque(true); 
    save_login.setForeground(new Color(58, 63, 156)); 
    save_login.setBackground(Color.WHITE); 
    save_login.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    save_book.setBorderPainted(false); 
    save_book.setContentAreaFilled(false); 
    save_book.setOpaque(true); 
    save_book.setForeground(new Color(58, 63, 156)); 
    save_book.setBackground(Color.WHITE); 
    save_book.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    issue.setBorderPainted(false); 
    issue.setContentAreaFilled(false); 
    issue.setOpaque(true); 
    issue.setForeground(new Color(58, 63, 156)); 
    issue.setBackground(Color.WHITE); 
    issue.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    returnbtn.setBorderPainted(false); 
    returnbtn.setContentAreaFilled(false); 
    returnbtn.setOpaque(true); 
    returnbtn.setForeground(new Color(58, 63, 156)); 
    returnbtn.setBackground(Color.WHITE); 
    returnbtn.setFont(new Font("Century Gothic", Font.BOLD, 13)); 
    //5, 65, 90 (Dark Blue) 
    //58, 63, 156 (Light Blue) 
    //62, 64, 69 (Light Grey) 
    //209, 54, 15 (Brave Orange) 
    //End Buttons. 
    //Design. 
    F.setBackground(new Color(41, 42, 45)); 
    //End Design. 
}
public void actionPerformed(ActionEvent e) { 
    // int x,col;
    // login 
    ArrayList<ArrayList<String>> x = new ArrayList<>();

    if (e.getSource() == login) { 
        flag = 0; 
        String user_ = userField.getText(); 
        String pwd = new String(pwdField.getPassword()); 
        try { 
            String userName = "root"; 
            String password = "1234"; 
            String url = "jdbc:mysql://localhost:3306/library"; 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, userName, password); 
            stmt = conn.createStatement(); 
            // Execute statements 
            stmt.execute("select * from authentication"); 
            rs = stmt.getResultSet(); 
            while (rs.next()) { 
                nameString = rs.getString("Username"); 
                pwdString = rs.getString("Password"); 
                if (pwdString.equals(pwd) && user_.equals(nameString)) { 
                    System.out.println("Login Successful!!!"); 
                    flag = 1; 
                    panel_login.setVisible(false); error.setVisible(false); background.setVisible(true); 
                    break; 
                }
                if (flag == 0) { 
                    error.setVisible(true); 
                }
            }
        } catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage()); 
        } catch (Exception b) { 
            error.setVisible(true); 
            System.err.println("Cannot connect to database server"); 
        } finally { 
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (SQLException sqlEx) { } 
                stmt = null; 
            }
            if (conn != null) { 
                try { 
                    conn.close(); 
                } catch (Exception b) { } 
                // end login 
                // book issue 
            }
        }
    }
    if (e.getSource() == issueBook) { 
        stud_form.setVisible(false); 
        issue_book.setVisible(true); 
        new_book.setVisible(false); 
        return_book.setVisible(false); 
        stati.setVisible(false); 
        issueBook.setBackground(new Color(209, 54, 15));
        newBook.setBackground(new Color(58, 63, 156));
        newStudent.setBackground(new Color(58, 63, 156));
        returnBook.setBackground(new Color(58, 63, 156));
        statistics.setBackground(new Color(58, 63, 156)); 
        // system date 
        Date date = Calendar.getInstance().getTime(); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        issuedate_tString = dateFormat.format(date);
        issuedate_t.setText(issuedate_tString); 
        // end system date 
    }
    if (e.getSource() == issue) { 
        flag = 0; 
        String bstu_tString = bstu_t.getText(); 
        String book_tString = book_t.getText(); 
        String duedate_tString = duedate_t.getText(); 
        try { 
            String userName = "root"; 
            String password = "1234"; 
            String url = "jdbc:mysql://localhost:3306/library"; 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, userName, password); stmt = conn.createStatement(); 
            stmt.execute("select * from books"); 
            rs = stmt.getResultSet(); 
            while (rs.next()) { 
                String temp_bookid = rs.getString("Book_ID"); 
                int temp_avail = Integer.parseInt(rs.getString("availability")); 
                if (book_tString.equals(temp_bookid) && temp_avail > 0) { 
                    String sql = "insert into book_issue values(" + book_tString + "," + bstu_tString +"','"  + issuedate_tString + "','" +duedate_tString + "');"; 
                    stmt.executeUpdate(sql); 
                    System.out.println("Inserted Succesfully"); 
                    sql = "update book set availability=" + (temp_avail - 1) +" where Book_ID='" + temp_bookid + "';";
                    stmt.executeUpdate(sql); 
                    bstu_t.setText(""); 
                    book_t.setText(""); 
                    issuedate_t.setText(""); 
                    duedate_t.setText(""); flag = 1; 
                    break; 
                }
                if (flag == 1) {
                    System.out.println("Book issued successfully"); 
                } else {
                    System.out.println("Book issue unsuccesful"); 
                // Execute statements 
                }
            }
        }catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage()); 
        } catch (Exception b) { 
            System.out.println("Book issue unsuccesful"); 
            System.out.print(b.getMessage()); 
            System.err.println("Cannot connect to database server"); 
        } finally { 
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (SQLException sqlEx) {
                    if (conn != null) { 
                        try { 
                            conn.close();
                        } catch (Exception b) { } 
                    } 
                } 
                issue_book.setVisible(false); 
                issueBook.setBackground(new Color(58, 63, 156)); 
                // book issue 
                // new Student
            }
        }
    }
    if (e.getSource() == newStudent) { 
        stud_form.setVisible(true); 
        issue_book.setVisible(false); 
        new_book.setVisible(false); 
        return_book.setVisible(false); 
        stati.setVisible(false); 
        newStudent.setBackground(new Color(209, 54, 15));
        newBook.setBackground(new Color(58, 63, 156));
        issueBook.setBackground(new Color(58, 63, 156));
        returnBook.setBackground(new Color(58, 63, 156));
        statistics.setBackground(new Color(58, 63, 156));
    } 
    if (e.getSource() == save_login) { 
        String stu_tString = stu_t.getText(); 
        String stuname_tString = stuname_t.getText(); 
        String stubrn_tString = stubrn_t.getText(); 
        try { 
            String userName = "root"; 
            String password = "1234"; 
            String url = "jdbc:mysql://localhost:3306/library"; 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, userName, password); 
            stmt = conn.createStatement(); 
            // Execute statements 
            String sql = "insert into student values(" + stu_tString + "," + stubrn_tString + ");"; 
            stmt.executeUpdate(sql); 
            System.out.println("Inserted Succesfully"); 
            stu_t.setText(""); 
            stuname_t.setText(""); 
            stubrn_t.setText(""); 
        } catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception b) { 
            System.out.print(b.getMessage());
            System.err.println("Cannot connect to database server"); 
        } finally { 
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (SQLException sqlEx) { } 
                stmt = null;
            }
            if (conn != null) { 
                try { 
                    conn.close(); 
                } catch (Exception b) { } 
                stud_form.setVisible(false); 
                newStudent.setBackground(new Color(58, 63, 156)); 
                // end new Student 
                // new book
            }
        }
    }
    if (e.getSource() == newBook) {
        stud_form.setVisible(false); 
        issue_book.setVisible(false); 
        new_book.setVisible(true); 
        return_book.setVisible(false); 
        stati.setVisible(false); 
        newStudent.setBackground(new Color(58, 63, 156)); 
        newBook.setBackground(new Color(209, 54, 15)); 
        issueBook.setBackground(new Color(58, 63, 156)); 
        returnBook.setBackground(new Color(58, 63, 156));
        statistics.setBackground(new Color(58, 63, 156));
    }
    if (e.getSource() == save_book) { 
        String bid_tString = bid_t.getText(); 
        String bname_tString = bname_t.getText(); 
        String pub_tString = pub_t.getText(); 
        String pubdate_tString = pubdate_t.getText(); 
        String categ_tString = categ_t.getText(); 
        String no_of_copy_tString=no_of_copy_t.getText(); 
        try { 
            String userName = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/library"; 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, userName, password); stmt = conn.createStatement(); 
            // Execute statements 
            String sql = "insert into book values(" + bid_tString +",'" + bname_tString + pub_tString + "','" + pubdate_tString +"','" + no_of_copy_tString + ");"; 
            stmt.executeUpdate(sql); 
            System.out.println("Inserted Succesfully"); 
            bid_t.setText(""); 
            bname_t.setText("");
            pub_t.setText("");
            pubdate_t.setText(""); 
            categ_t.setText("");
            no_of_copy_t.setText(""); 
        } catch (SQLException ex) { 
            System.out.println("SQLException:");
        } catch (Exception b) { 
            System.out.print(b.getMessage());
            // ex.getMessage(); 
            System.err.println("Cannot connect to database server"); 
        } finally { 
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (SQLException sqlEx) { } 
                stmt = null;
            }
            if (conn != null) {
                try { 
                    conn.close(); 
                } catch (Exception b) { }
                new_book.setVisible(false); 
                newBook.setBackground(new Color(58, 63, 156));
            }
            // end new book 
            // returnBook
        }
    }
    if (e.getSource() == returnBook) {
        stud_form.setVisible(false); 
        issue_book.setVisible(false); 
        new_book.setVisible(false); 
        return_book.setVisible(true); 
        stati.setVisible(false); 
        returnBook.setBackground(new Color(209, 54, 15));
        newStudent.setBackground(new Color(58, 63, 156));
        issueBook.setBackground(new Color(58, 63, 156));
        newBook.setBackground(new Color(58, 63, 156));
        statistics.setBackground(new Color(58, 63, 156)); 
        // system date 
        Date date = Calendar.getInstance().getTime(); 
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return_tString = dateFormat.format(date); 
        return_t.setText(return_tString); 
        // end system date 
    }
    if (e.getSource() == returnbtn) { 
        flag = 0; 
        String sturet_tString = sturet_t.getText(); 
        String bookret_tString = bookret_t.getText(); 
        String temp_bookid = "",temp_stu_id = ""; 
        try { 
            String userName = "root"; 
            String password = "1234"; 
            String url = "jdbc:mysql://localhost:3306/library"; 
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(url, userName, password); 
            stmt = conn.createStatement();
            temp_conn = DriverManager.getConnection(url, userName, password); 
            temp_stmt = temp_conn.createStatement(); 
            temp_rs = null; 
            stmt.execute("select * from book_issue"); 
            rs = stmt.getResultSet(); 
            while (rs.next()) { 
                temp_bookid = rs.getString("Book_ID"); 
                temp_stu_id = rs.getString("Student_ID"); 
                if (bookret_tString.equals(temp_bookid) && sturet_tString.equals(temp_stu_id)) { 
                    String sql = "delete from book_issue where Student_ID=" + sturet_tString + ";";
                    stmt.executeUpdate(sql); 
                    System.out.println("Deleted Succesfully"); 
                    rs.first(); 
                    stmt = null; 
                    bstu_t.setText(""); 
                    book_t.setText(""); 
                    issuedate_t.setText(""); 
                    duedate_t.setText(""); 
                    return_book.setVisible(false); 
                    flag = 1; 
                    break;   
                } 
                if (flag == 1) { 
                    System.out.println("Book returned successfully"); 
                    temp_stmt.execute("select * from books"); 
                    temp_rs = temp_stmt.getResultSet(); 
                    while (temp_rs.next()) { 
                        temp_bookid = temp_rs.getString("Book_ID");
                        if (temp_bookid.equals(bookret_tString)) 
                            break; 
                    } 
                    int temp_avail = Integer.parseInt(temp_rs.getString("availability")); 
                    String sql = "update book set availability=" + (temp_avail + 1) + " where Book_ID=" + temp_bookid + "';";
                    temp_stmt.executeUpdate(sql); 
                } else {
                    System.out.println("Book return unsuccesful"); 
                }
            
            // Execute statements 
            // Execute statements 
            stmt.execute("select * from books"); 
            rs = stmt.getResultSet(); 
            String col[]={"Book_ID","Book_name","Category","Availability"}; 
            while (rs.next()) { 
               //  String temp_bookid = rs.getString("Book_ID");
                String temp_bookname=rs.getString("Book_name");
                String category=rs.getString("Category"); 
                String avail=rs.getString("Availability"); 
                x.add(new ArrayList<String>(Arrays.asList(temp_bookid,temp_bookname,category,avail))); 
            
                for(int i = 0; i < x.size(); i++){ 
                    for(int j = 0; j <x.get(i).size(); j++){ 
                        System.out.print(x.get(i).get(j));
                    }
                }
                System.out.println(); 
            }
                String str[][]=new String[x.size()][4];
                for(int i = 0; i < x.size(); i++){
                    for(int j = 0; j <x.get(i).size(); j++){ 
                        str[i][j]=x.get(i).get(j); 
                    }}
                        j=new JTable(str,col); 
                        j.setEnabled(false); 
                        j.setBounds(25,25,700,350);
                        sp = new JScrollPane();
                        sp.setBounds(45,25,700,350); 
                        stati.add(sp); 
                        sp.getViewport().setBackground(new Color(62, 64, 69)); 
                        sp.setBorder(null); 
                        j.setBackground(new Color(62, 64, 69));
                        j.setForeground(Color.WHITE); 
                        j.setFont(new Font("Century Gothic", Font. PLAIN,11)); 
                        j.setShowGrid(false); 
                        j.getTableHeader().setReorderingAllowed(false); 
                        j.getTableHeader().setResizingAllowed(false); 
                        j.getTableHeader().setBackground(new Color(58, 63, 156)); 
                        j.getTableHeader().setForeground(Color.WHITE); 
                        j.getTableHeader().setBorder(null); 
                        TableCellRenderer baseRenderer = j.getTableHeader().getDefaultRenderer();
                        j.getTableHeader().setDefaultRenderer(new TableHeaderRenderer(baseRenderer)); 
                        j.getTableHeader().setFont(new Font("Century Gothic", Font. BOLD,12)); 
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
                        centerRenderer.setHorizontalAlignment(JLabel.CENTER); 
                        j.setRowHeight(25);
                    
                
                for(int i=0;i<4;i++) 
                    j.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); 
            }
        } catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage()); 
        } catch (Exception b) { 
            System.out.print(b.getMessage()); 
            System.err.println("Cannot connect to database server"); 
        } finally { 
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (SQLException sqlEx) { } 
                stmt = null; 
                if (conn != null) { 
                    try { 
                        conn.close(); 
                    } catch (Exception b) { } 
                } 
            }
        }
    }
}}
// end statistics 
 class Library { 
    public static void main(String args[]) { 
        new login(); 
    }
}