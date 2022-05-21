package yummypizza.gui;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import yummypizza.model.Staff;
import yummypizza.repo.InventoryRepo;
import yummypizza.repo.InvoiceRepo;
import yummypizza.repo.ProductInInvoiceRepo;
import yummypizza.repo.RawIngredientsRepo;
import yummypizza.repo.StaffRepo;

public class EnterLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPassword;
	private JTextField txtStaffID;
	
	private StaffRepo staffs;
	private InventoryRepo inventories;
	private RawIngredientsRepo ingredients;
	private InvoiceRepo invoices;
	private ProductInInvoiceRepo productsInInvoice;
	
	private Integer authenticationLevel;
	private Integer counter;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			EnterLogin dialog = new EnterLogin();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EnterLogin(StaffRepo staffs, InventoryRepo inventories, RawIngredientsRepo ingredients, InvoiceRepo invoices, ProductInInvoiceRepo productsInInvoice) {
		this.staffs = staffs;
		this.inventories = inventories;
		this.ingredients = ingredients;
		this.invoices = invoices;
		this.productsInInvoice = productsInInvoice;
		
		setBounds(100, 100, 334, 198);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("Enter your login credentials");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblStaffID = new JLabel("Staff ID");
		lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPassword = new JPasswordField();
		
		txtStaffID = new JTextField();
		txtStaffID.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogin)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblStaffID))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtStaffID, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblLogin)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStaffID)
						.addComponent(txtStaffID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Integer loginCode = verifyCredentials(Integer.valueOf(txtStaffID.getText()),String.valueOf(txtPassword.getPassword()));
						
						if(loginCode == 0) {
							showMessageDialog(null, "Staff not found. Please try again.");
							return;
						}
						if(loginCode == 1) {
							showMessageDialog(null, String.format("Invalid login credentials. %d attempts remaining.", 3 - counter));
							return;
						}
						if(loginCode == 2) {
							showMessageDialog(null, "Maximum attempts made. Shutting down.");
							close();
						}
						
						if(loginCode == 3 && authenticationLevel != 1) {
							showMessageDialog(null, "No available services for you today. Login as manager.");
							return;
						}
						showMessageDialog(null,"Login successful.");
						openShowInventory();
						close();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				okButton.setForeground(new Color(255, 99, 71));
				okButton.setPreferredSize(new Dimension(65, 23));
				okButton.setMinimumSize(new Dimension(65, 23));
				okButton.setMaximumSize(new Dimension(65, 23));
				okButton.setContentAreaFilled(false);
				okButton.setBackground(Color.WHITE);
				okButton.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close();
					}
				});
				cancelButton.setContentAreaFilled(false);
				cancelButton.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setForeground(new Color(255, 99, 71));
				cancelButton.setPreferredSize(new Dimension(65, 23));
				cancelButton.setMinimumSize(new Dimension(65, 23));
				cancelButton.setMaximumSize(new Dimension(65, 23));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void close() {
		this.setVisible(false);
		this.dispose();
	}
	
	private void openShowInventory() {
		ShowInventory gui = new ShowInventory(this.inventories, this.ingredients, this.invoices, this.productsInInvoice);
		gui.pack();
		gui.setVisible(true);
	}
	
	private Integer verifyCredentials(Integer staffID, String password) {
		counter = 0;
		Integer loginCode = 0;
		
		Staff staff = this.staffs.findTopByIdStaff(staffID);
		
		if(staff == null) loginCode = 0;
		
		else if(staff != null && !password.contentEquals(staff.getPassword()) && counter < 3) {
			counter += 1;
			loginCode = 1;
		}
		else if(staff != null && staff.getPassword() != password && counter == 3) loginCode = 2;
		
		else {
			authenticationLevel = staff.getPositionId();
			loginCode = 3;
		}
		return loginCode;
	}
}
