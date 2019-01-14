package tdl.view.todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tdl.controller.todo.PopupMenuExample;
import tdl.model.todoelements.ToDoElementStruct;

public class addToDoElement extends JPanel implements MouseListener{
	JFrame f1;
	JCheckBox cb1;
	public JTextField txtfld1;
	JLabel Ddayl;
	public ToDoElementStruct cinputstructure;
	addToDoElement(JPanel p){
		p.add(this);
	}

	addToDoElement(JPanel p, ToDoElementStruct inputstructure, JFrame f){
		cinputstructure = inputstructure;
		f1 = f;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		/*CheckBox*/
		cb1 = new JCheckBox();
		class cb1EventHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent ae){
				JCheckBox cb = (JCheckBox) ae.getSource();
				if (cb.isSelected()) {
					inputstructure.setCheckValue(true);
					System.out.println("checked");
					//update addbutton;
				} else {
					inputstructure.setCheckValue(false);	
					System.out.println("unchecked");
					//update addbutton;
				}
			}
		}
		cb1.addActionListener(new cb1EventHandler());
		cb1.setSelected(inputstructure.getCheckValue());	
		//when checked set check boolean get boolean on another area
		/*Text Field*/
		txtfld1 = new JTextField("init text"){
			@Override
		    public Dimension getMinimumSize() {
		        return new Dimension(150, 30);
		    }

		    @Override
		    public Dimension getPreferredSize() {
		        return new Dimension(200, 30);
		    }

		    @Override
		    public Dimension getMaximumSize() {
		        return new Dimension(1000, 30);
		    }
		};
		txtfld1.setBorder(BorderFactory.createRaisedBevelBorder());
		txtfld1.setEditable(false);
		txtfld1.setText(inputstructure.getToDoText());
		/*D-day*/
		long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), inputstructure.getDDay());
		if(LocalDate.now().isBefore(inputstructure.getDDay())){
				Ddayl = new JLabel("D-"+daysBetween);//D-day 
			}else{
				Ddayl = new JLabel("D+"+daysBetween);//D-day 
			}

		//this.setBackground(Color.color[inputstructure.getPriority()]);
		this.setVisible(true);
		this.add(Ddayl);
		this.add(txtfld1);
		this.add(cb1);
		txtfld1.addMouseListener(this);
		Ddayl.addMouseListener(this);
		this.addMouseListener(this);
		p.add(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		txtfld1.setEditable(false);
	    this.cinputstructure.setToDoText(txtfld1.getText());
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
		    txtfld1.setEditable(true);
		  }
		if (e.getButton() == MouseEvent.BUTTON3){
			new PopupMenuExample(f1,e,this);
		}		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBorder(BorderFactory.createEmptyBorder());

	}	
}