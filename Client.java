package onlinemovieticketbooking;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;
import java.io.*;

import javax.swing.border.EmptyBorder;

public class Client implements ActionListener{
    JTextField text;
    static JPanel a1;
    JScrollPane scrollPane ;
    static Box vertical=Box.createVerticalBox();
    static DataOutputStream dot;
    static JFrame f= new JFrame();
    Client()
    {
        f.setLayout(null);
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(15,23,25,25);
        p1.add(back);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile1 = new JLabel(i6);
        profile1.setBounds(50,12,50,50);
        p1.add(profile1);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(320,20,30,30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(368,20,30,30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(11, 25, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel ham = new JLabel(i15);
        ham.setBounds(410,19,13,30);
        p1.add(ham);
        
        JLabel name=new JLabel("Babu Rao");
        name.setBounds(115,25,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        p1.add(name);
        
        a1=new JPanel();
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5,75,440,570);
        f.add(scrollPane);
        
        text=new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        f.add(text);
        
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        
        JButton send=new JButton("Send");
        send.setBounds(320,655,123,40);
//        if(text.getText() != "") {
        send.addActionListener(this);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        f.add(send);
//        }
        
        
        //Hover Color Change
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                send.setBackground(Color.RED); // Change color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                send.setBackground(new Color(7, 94, 84)); // Change color back when mouse exits
            }
        });
        
        back.addMouseListener(new MouseAdapter()
        {
           @Override
           public void mouseClicked(MouseEvent e)
           {
               System.exit(0);
           }
        });
        f.setSize(450,700);
        f.setLocation(800,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	try {
    		String out =text.getText();
    		if(out.isEmpty() || out == null) {
    			return ;
    		}
        
//      	  JLabel output=new JLabel(out);
        
    		JPanel p2=formatLabel(out);
        
    		a1.setLayout(new BorderLayout());
        
    		JPanel right=new JPanel(new BorderLayout());
    		right.add(p2,BorderLayout.LINE_END);
    		vertical.add(right);
    		vertical.add(Box.createVerticalStrut(15));
    		a1.add(vertical,BorderLayout.PAGE_START);
        
    		text.setText("");
        
    		dot.writeUTF(out);
        
    		f.repaint();
    		f.invalidate();
    		f.validate();
    	}
    	catch(Exception et) {
    		et.printStackTrace();
    	}
        
    }
    public static JPanel formatLabel(String out)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output=new JLabel("<html><p style=\"width:150px\">"+ out +"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,14));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(8,15,15,40));
        panel.add(output);
        
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    
    private void sendMessage() {
        String out = text.getText().trim(); // Trim whitespace
        if (!out.isEmpty()) {
            JPanel p2 = formatLabel(out);
            vertical.add(p2); // Add the formatted message panel directly to the main panel

            // Scroll to the bottom of the scroll pane
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());

            text.setText(""); // Clear the text field
            
            vertical.revalidate();
            vertical.repaint();
        }
    }
    
    public static void main(String[] args)
    {
        Client c = new Client();
        
        try {
        	Socket s=new Socket("192.168.1.27", 6001);
        	DataInputStream din = new DataInputStream(s.getInputStream());
    		dot = new DataOutputStream(s.getOutputStream());
    		while(true) {
    			a1.setLayout(new BorderLayout());
    			String msg=din.readUTF();
    			JPanel panel=formatLabel(msg);
    			
    			JPanel left = new JPanel(new BorderLayout());
    			left.add(panel, BorderLayout.LINE_START);
    			vertical.add(left);
    			
    			vertical.add(Box.createVerticalStrut(15));
    			a1.add(vertical, BorderLayout.PAGE_START);
    			
    			f.validate();
    		}
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}