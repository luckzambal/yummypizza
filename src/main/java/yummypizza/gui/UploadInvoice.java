package yummypizza.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;

import yummypizza.model.Inventory;
import yummypizza.model.Invoice;
import yummypizza.model.RawIngredients;
import yummypizza.repo.InventoryRepo;
import yummypizza.repo.InvoiceRepo;
import yummypizza.repo.ProductInInvoiceRepo;
import yummypizza.repo.RawIngredientsRepo;
import yummypizza.model.ProductInInvoice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UploadInvoice extends JDialog {
	
	private RawIngredients ingredients;
	private Invoice invoice;
	private InvoiceRepo invoices;
	private ProductInInvoice productInInvoice;
	private ProductInInvoiceRepo productsInInvoice;
	private RawIngredientsRepo rawIngredients;
	private Inventory inventory;
	private InventoryRepo inventories;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInvoiceNumber;
	private JFormattedTextField txtTotalCost;
	private JFormattedTextField txtStaffId;
	private JTextField txtProductNumber;
	private JTextField txtProductName;
	private JFormattedTextField txtUnitPrice;
	private JTextField txtUnit;
	private JFormattedTextField txtTransactionDate;
	private JCheckBox chkPaid;
	private JFormattedTextField txtQuantity;
	private JFormattedTextField txtSubtotalCost;
	private JTable tblRawIngredients;
	private JButton cancelButton;
	private JButton okButton;
	
	private RawIngredientsTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UploadInvoice dialog = new UploadInvoice();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public UploadInvoice(InventoryRepo inventories, RawIngredientsRepo rawIngredients, InvoiceRepo invoices, ProductInInvoiceRepo productsInInvoice, Inventory inventory) {
		this.inventories = inventories;
		this.rawIngredients = rawIngredients;
		this.invoices = invoices;
		this.productsInInvoice = productsInInvoice;
		this.model = new RawIngredientsTableModel();
		
		this.setModal(true);
		
		//Integer formatting for staff ID text field
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter intFormatter = new NumberFormatter(format);
		intFormatter.setValueClass(Integer.class);
		intFormatter.setMinimum(0);
		intFormatter.setMaximum(Integer.MAX_VALUE);
		
		//Decimal formatting for decimal
		DecimalFormat decimal = new DecimalFormat("0.00");
		
		//Number formatting for quantity
		NumberFormat quantityFormat = NumberFormat.getNumberInstance();
		
		//Date formatting
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		DateFormatter df = new DateFormatter(date);
		
		setBounds(100, 100, 771, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProvideInvoice = new JLabel("Provide invoice details");
		lblProvideInvoice.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblInvoiceNumber = new JLabel("Invoice Number");
		lblInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInvoiceNumber = new JTextField();
		txtInvoiceNumber.setColumns(10);
		
//		Set the date format
		txtTransactionDate = new JFormattedTextField(df);
		txtTransactionDate.setValue(new Date());
		
//		Set the total cost format
		txtTotalCost = new JFormattedTextField(decimal);
		txtTotalCost.setValue(0.00);
		txtTotalCost.setEditable(false);
		txtTotalCost.setColumns(10);
		
//		Limit staff ID to integer
		txtStaffId = new JFormattedTextField(intFormatter);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtProductNumber = new JTextField();
		txtProductNumber.setEditable(false);
		txtProductNumber.setColumns(10);
		
		txtProductName = new JTextField();
		txtProductName.setEditable(false);
		txtProductName.setColumns(10);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSubtotalCost = new JLabel("Subtotal Cost");
		lblSubtotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtUnitPrice = new JFormattedTextField(decimal);
		txtUnitPrice.setValue(0.00);
		txtUnitPrice.setEditable(false);
		txtUnitPrice.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("per");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtUnit = new JTextField();
		txtUnit.setEditable(false);
		txtUnit.setColumns(10);
		
//		Limit quantity to numbers
		txtQuantity = new JFormattedTextField(quantityFormat);
		txtQuantity.setColumns(10);
		txtQuantity.setValue(0);
		txtQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!txtQuantity.getText().isEmpty()) txtSubtotalCost.setText(decimal.format(getSubtotal(Double.valueOf(txtQuantity.getText()),Double.valueOf(txtUnitPrice.getText()))).toString());
				else {
					txtSubtotalCost.setValue(0.00);
					txtQuantity.setValue(tryParseDouble(txtQuantity.getText(),0));
				}
				
				okButton.setEnabled(enableOk());
				
			}
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(txtQuantity.getText().isEmpty()) tryParseDouble(txtQuantity.getText(),0).toString();
//			}
		});
		
//		Set subtotal cost format
		txtSubtotalCost = new JFormattedTextField(decimal);
		txtSubtotalCost.setEditable(false);
		txtSubtotalCost.setValue(0.00);
		txtSubtotalCost.setColumns(10);
		
		JLabel lblProvideProductDetails = new JLabel("Provide product details");
		lblProvideProductDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblPaid = new JLabel("Paid");
		lblPaid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chkPaid = new JCheckBox("");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblChooseProduct = new JLabel("Choose product");
		lblChooseProduct.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblProvideInvoice)
							.addGap(211))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPaid, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chkPaid)
							.addGap(249))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblProvideProductDetails, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addGap(189))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblSubtotalCost, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtSubtotalCost, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtQuantity, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblUnitPrice, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txtUnitPrice, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txtProductNumber, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtUnit, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
										.addComponent(txtProductName, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblStaffId, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtStaffId, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblTotalCost, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtTotalCost, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtTransactionDate, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblInvoiceNumber)
									.addGap(18)
									.addComponent(txtInvoiceNumber, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblChooseProduct, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
							.addGap(144))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblProvideInvoice)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtInvoiceNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInvoiceNumber))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTransactionDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalCost, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTotalCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStaffId, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStaffId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPaid, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkPaid))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 2, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblProvideProductDetails, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProductNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnitPrice, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUnitPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(txtUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubtotalCost, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSubtotalCost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblChooseProduct, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
		);
		
		tblRawIngredients = new JTable(model);
		tblRawIngredients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = tblRawIngredients.getSelectedRow();
				
				if (row >= 0) {
					ingredients = model.getRawIngredientsAtRow(row);
					
					txtProductNumber.setText(ingredients.getProductNumber().toString());
					txtProductName.setText(ingredients.getProductName());
					txtUnit.setText(ingredients.getUnit());
					txtUnitPrice.setText(decimal.format(ingredients.getProductCost()).toString());
				}
				else {
					ingredients = null;
					
					txtProductNumber.setText(null);
					txtProductName.setText(null);
					txtUnit.setText(null);
					txtUnitPrice.setText(null);
					txtQuantity.setText("0");
					txtSubtotalCost.setText("0.00");
					
				}
				
				cancelButton.setEnabled(enableCancel());
				okButton.setEnabled(enableOk());
			}
		});
		scrollPane.setViewportView(tblRawIngredients);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnAdd = new JButton("Add...");
			btnAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					createProductInInvoice();
					updateInventory(Integer.valueOf(txtProductNumber.getText()),Double.valueOf(txtQuantity.getText()));
					addTotalCost();
					clearProducts();
				}
			});
			btnAdd.setPreferredSize(new Dimension(65, 23));
			btnAdd.setMinimumSize(new Dimension(65, 23));
			btnAdd.setMaximumSize(new Dimension(65, 23));
			btnAdd.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
			btnAdd.setContentAreaFilled(false);
			btnAdd.setBackground(Color.WHITE);
			btnAdd.setForeground(new Color(255, 99, 71));
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonPane.add(btnAdd);
			{
				okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(!txtProductNumber.getText().isEmpty()) {
							createProductInInvoice();
							updateInventory(Integer.valueOf(txtProductNumber.getText()),Double.valueOf(txtQuantity.getText()));
							addTotalCost();
						}
						
						createInvoice();
						close();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				okButton.setForeground(new Color(255, 99, 71));
				okButton.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
				okButton.setContentAreaFilled(false);
				okButton.setBackground(Color.WHITE);
				okButton.setPreferredSize(new Dimension(65, 23));
				okButton.setMinimumSize(new Dimension(65, 23));
				okButton.setMaximumSize(new Dimension(65, 23));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close();
					}
				});
				cancelButton.setContentAreaFilled(false);
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
				cancelButton.setForeground(new Color(255, 99, 71));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setPreferredSize(new Dimension(65, 23));
				cancelButton.setMinimumSize(new Dimension(65, 23));
				cancelButton.setMaximumSize(new Dimension(65, 23));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void createInvoice() {
		this.invoice = new Invoice(
			txtInvoiceNumber.getText(),
			java.sql.Date.valueOf(txtTransactionDate.getText()),
			Double.valueOf(txtTotalCost.getText()),
			Integer.valueOf(txtStaffId.getText()),
			Boolean.valueOf(chkPaid.isSelected())
		);
		
		invoices.save(invoice);
	}
	
	private void createProductInInvoice() {
		this.productInInvoice = new ProductInInvoice(
			txtInvoiceNumber.getText(),
			Integer.valueOf(txtProductNumber.getText()),
			Double.valueOf(txtQuantity.getText()),
			Double.valueOf(txtSubtotalCost.getText())
		);
		
		productsInInvoice.save(productInInvoice);
	}
	
	public Inventory showDialog() {
		setVisible(true);
		return inventory;
	}
	
	private void addTotalCost() {
		Double totalCost =  Double.valueOf(txtTotalCost.getText());
		Double subtotalCost = Double.valueOf(txtSubtotalCost.getText());
		
		totalCost += subtotalCost;
		txtTotalCost.setText(totalCost.toString());
	}
	
	private void clearProducts() {
		txtProductNumber.setText("");
		txtProductName.setText("");
		txtUnitPrice.setText("");
		txtUnit.setText("");
		txtQuantity.setText(tryParseDouble(txtQuantity.getText(),0).toString());
		txtSubtotalCost.setText("");
	}
	
	private void close() {
	    this.setVisible(false);
	    this.dispose();
	}
	
	private boolean enableCancel() {
		if(txtProductNumber.getText().isEmpty())
			return true;
		return false;
	}
	
	private boolean enableOk() {
		if((Double.valueOf(txtQuantity.getText()) == 0 || txtQuantity.getText().isEmpty()) && !txtProductNumber.getText().isEmpty())
			return false;
		return true;
	}
	
	private Double getSubtotal(Double quantity, Double unitPrice) {
		if(quantity != null && unitPrice != null)
			return quantity*unitPrice;
		return null;
	}

	public static Double tryParseDouble(String s, double defaultValue) {
	     if (s == null) return defaultValue;

	     try {
	         return Double.parseDouble(s);
	     } catch (NumberFormatException x) {
	         return defaultValue;
	     }  
	}
	
	private void updateInventory(Integer id, Double addedQuantity) {
		ShowInventory parent = new ShowInventory(inventories, rawIngredients, invoices, productsInInvoice);
		Double originalQuantity;
		
		Inventory inventoryToUpdate = inventories.readById(id);
		
		originalQuantity = inventoryToUpdate.getItemQuantity();
		
		this.inventory = new Inventory(
			inventoryToUpdate.getId(),
			inventoryToUpdate.getItemName(),
			inventoryToUpdate.getIsPerishable(),
			originalQuantity+addedQuantity,
			inventoryToUpdate.getUnit(),
			inventoryToUpdate.getMinimumQuantity()
		);
		
		inventories.save(inventory);
		parent.tblInventory.repaint();
		parent.model.fireTableDataChanged();
		parent.model.refreshData();
	}
	
	private class RawIngredientsTableModel extends AbstractTableModel {
		private String[] columnNames = {"Product Number","Product Name", "Unit", "Unit Price"};
		
		private List<RawIngredients> rows;
		
		public RawIngredientsTableModel() {
			refreshData();
		}

		private void refreshData() {
			this.rows = new ArrayList<RawIngredients>();
			
			for (RawIngredients ingredients: rawIngredients.findAll()) {
				this.rows.add(ingredients);
			}
		}

		public int getRowCount() {
			return rows.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}
		
		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			RawIngredients ingredients = rows.get(rowIndex);
			
			switch (columnIndex) {
				case 0: return ingredients.getProductNumber();
				case 1: return ingredients.getProductName();
				case 2: return ingredients.getUnit();
				case 3: return ingredients.getProductCost();
			}
			
			return null;
		}
		
		public Class getColumnClass(int col) {
			switch (col) {
				case 0: return Integer.class;
				case 1: return String.class;
				case 2: return String.class;
				case 3: return Double.class;
			}
			
			return null;
		}
		
		public RawIngredients getRawIngredientsAtRow(int row) {
			return rows.get(row);
		}
	}
}
