package yummypizza.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import static javax.swing.JOptionPane.showMessageDialog;

import yummypizza.model.Inventory;
import yummypizza.repo.InventoryRepo;
import yummypizza.repo.InvoiceRepo;
import yummypizza.repo.ProductInInvoiceRepo;
import yummypizza.repo.RawIngredientsRepo;

public class ShowInventory extends JFrame {
	
	private InventoryRepo inventories;
	public InventoryTableModel model;
	
	private Inventory selectedInventory;
	
	private RawIngredientsRepo ingredients;
	private InvoiceRepo invoices;
	private ProductInInvoiceRepo productsInInvoice;

	private JPanel contentPane;
	public JTable tblInventory;
	
	private JLabel lblOrderCount;

	/**
	 * Create the frame.
	 */
	public ShowInventory(InventoryRepo inventories, RawIngredientsRepo ingredients, InvoiceRepo invoices, ProductInInvoiceRepo productsInInvoice) {
		
		this.inventories = inventories;
		this.ingredients = ingredients;
		this.invoices = invoices;
		this.productsInInvoice = productsInInvoice;
		this.model = new InventoryTableModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 502);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblYummyPizza = new JLabel("YUMMY PIZZA");
		lblYummyPizza.setHorizontalAlignment(SwingConstants.LEFT);
		lblYummyPizza.setVerticalAlignment(SwingConstants.TOP);
		lblYummyPizza.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblOrderCount = new JLabel("No. Orders Processed:");
		lblOrderCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCurrentTime = new JLabel("");
		lblCurrentTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generateReport();
			}
		});
		btnGenerateReport.setContentAreaFilled(false);
		btnGenerateReport.setForeground(new Color(255, 99, 71));
		btnGenerateReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateReport.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		btnGenerateReport.setBackground(Color.WHITE);
		
		JButton btnUploadInvoice = new JButton("Upload Invoice");
		btnUploadInvoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uploadInvoice(inventories, ingredients, invoices, productsInInvoice);
			}
		});
		btnUploadInvoice.setForeground(new Color(255, 99, 71));
		btnUploadInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUploadInvoice.setContentAreaFilled(false);
		btnUploadInvoice.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		btnUploadInvoice.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYummyPizza)
					.addContainerGap(503, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInventory)
					.addPreferredGap(ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
					.addComponent(btnUploadInvoice, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOrderCount)
					.addPreferredGap(ComponentPlacement.RELATED, 488, Short.MAX_VALUE)
					.addComponent(lblCurrentTime)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(494, Short.MAX_VALUE)
					.addComponent(btnGenerateReport, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYummyPizza)
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInventory)
						.addComponent(btnUploadInvoice, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrderCount)
						.addComponent(lblCurrentTime))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnGenerateReport, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		tblInventory = new JTable(model);
		tblInventory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = tblInventory.getSelectedRow();
				if(row >= 0)
					selectedInventory = model.getInventoryAtRow(row);
				else
					selectedInventory = null;
			}
			
		});
		scrollPane.setViewportView(tblInventory);
		contentPane.setLayout(gl_contentPane);
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentDate = new java.text.SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
				lblCurrentTime.setText(currentDate);
			}
		};
		new Timer(1000, taskPerformer).start();
	}
	
	private void uploadInvoice(InventoryRepo inventories, RawIngredientsRepo rawIngredients, InvoiceRepo invoices, ProductInInvoiceRepo productsInInvoice) {
		UploadInvoice upload = new UploadInvoice(inventories, rawIngredients, invoices, productsInInvoice, null);
		Inventory updatedInventory = upload.showDialog();
		
		if(updatedInventory != null) {
			tblInventory.repaint();
			model.fireTableDataChanged();
			model.refreshData();
		}
		notifyLowStock();
	}
	
	private void notifyLowStock() {
		for(Inventory inventory : inventories.findAll()) {
			if(inventory.isLowStock()) {
				showMessageDialog(null,"Some items in inventory are low in stock.");
				break;
			}
		}
	}
	
	class InventoryTableModel extends AbstractTableModel {
		
		private String[] columnNames = {"ID", "Item Name", "Is Perishable", "Quantity", "Unit", "Min. Quantity"};
		
		private List<Inventory> rows;
		
		public InventoryTableModel() {
			refreshData();
		}
		
		public void refreshData() {
			this.rows = new ArrayList<Inventory>();
			
			for(Inventory inventory: inventories.findAll()) {
				this.rows.add(inventory);
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
			Inventory inventory = rows.get(rowIndex);
			
			switch(columnIndex) {
				case 0: return inventory.getId();
				case 1: return inventory.getItemName();
				case 2: return inventory.getIsPerishable();
				case 3: return inventory.getItemQuantity();
				case 4: return inventory.getUnit();
				case 5: return inventory.getMinimumQuantity();
			}
			
			return null;
		}
		
		public Inventory getInventoryAtRow(int row) {
			return rows.get(row);
		}
		
	}
	
	private void generateReport() {
		List<Inventory> inventoryReport = new ArrayList<Inventory>();
		String currentDate = new java.text.SimpleDateFormat("ddMMyyyy").format(new Date(System.currentTimeMillis()));
		String filepath = "InventoryReport" + currentDate + ".csv";
		
		for(Inventory inventory: inventories.findAll()) {
			inventoryReport.add(inventory);
		}
//		
//		try(CSVWriter writer = new CSVWriter(new FileWriter("C:\\Inventory Report "+currentDate+".csv"))){
//			writer.writeAll(inventoryReport);
//		}
//		catch(IOException e) {
//			showMessageDialog(null,"Failed to export file");
//		}
		
		FileWriter file = null;
		
		try {
			file = new FileWriter(filepath);
			
			file.append("Product Number, Item Name, Is Perishable, Item Quantity, Unit, Minimum Quantity\n");
			for(Inventory inventory : inventoryReport) {
				file.append(inventory.getId().toString());
				file.append(",");
				file.append(inventory.getItemName());
				file.append(",");
				file.append(inventory.getIsPerishable().toString());
				file.append(",");
				file.append(inventory.getItemQuantity().toString());
				file.append(",");
				file.append(inventory.getUnit());
				file.append(",");
				file.append(inventory.getMinimumQuantity().toString());
				file.append("\n");
			}
			
			showMessageDialog(null,"File successfully created");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				file.flush();
				file.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}