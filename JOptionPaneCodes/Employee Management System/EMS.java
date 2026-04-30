package gui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

public class EMS extends JFrame{
	JTextField txtID, txtName, txtBirth, txtAge, txtNationality, txtContact, txtEmail, txtDepartment, txtPosition;
	JComboBox<String> cmbStatus;
	JRadioButton btnMale, btnFemale;
	DefaultTableModel model;
	int i = 1;
	
	public EMS() {
		setTitle("Employee Management System");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 500);
		
		model = new DefaultTableModel(null, new String[] {"Employee ID", "Fullname", "Birth Date", "Age", "Civil Status", "Nationality", "Gender", "Contact", "Email", "Department", "Job Title / Position"});
		JTable tblRecords= new JTable(model);
		tblRecords.setEnabled(false);
		JScrollPane scrlTable = new JScrollPane(tblRecords);
		add(scrlTable).setBounds(30, 232, 730, 210);
		
		loadData();
		
		JLabel lblTitle = new JLabel("EMS Inc.");
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblTitle).setBounds(30, 20, 100, 15);
		
		JLabel lblID = new JLabel("Employee ID");
		add(lblID).setBounds(30, 50, 100, 15);
		txtID = new JTextField();
		add(txtID).setBounds(30, 70, 160, 20);
		
		JLabel lblName = new JLabel("Fullname");
		add(lblName).setBounds(30, 100, 100, 15);
		txtName = new JTextField();
		add(txtName).setBounds(30, 120, 160, 20);
		
		JLabel lblBirth = new JLabel("Date of Birth");
		add(lblBirth).setBounds(30, 150, 100, 15);
		txtBirth = new JTextField();
		add(txtBirth).setBounds(30, 170, 160, 20);
		
		JLabel lblAge = new JLabel("Age");
		add(lblAge).setBounds(220, 50, 100, 15);
		txtAge = new JTextField();
		add(txtAge).setBounds(220, 70, 160, 20);
		
		JLabel lblStatus = new JLabel("Civil Status");
		add(lblStatus).setBounds(220, 100, 100, 15);
		cmbStatus = new JComboBox<>(new String[] {"Single", "Married", "Widowed", "Seprated", "Divorced"});
		add(cmbStatus).setBounds(220, 120, 160, 20);
		
		JLabel lblNationality = new JLabel("Nationality");
		add(lblNationality).setBounds(220, 150, 100, 15);
		txtNationality = new JTextField();
		add(txtNationality).setBounds(220, 170, 160, 20);
		
		JLabel lblGender = new JLabel("Gender");
		add(lblGender).setBounds(410, 50, 100, 15);
		ButtonGroup group = new ButtonGroup();
		btnMale = new JRadioButton("Male", true);
		btnFemale = new JRadioButton("Female");
		group.add(btnMale);
		group.add(btnFemale);
		add(btnMale).setBounds(410, 70, 60, 20);
		add(btnFemale).setBounds(470, 70, 70, 20);
		
		JLabel lblContact = new JLabel("Contact Number");
		add(lblContact).setBounds(410, 100, 100, 15);
		txtContact = new JTextField();
		add(txtContact).setBounds(410, 120, 160, 20);
		
		JLabel lblEmail = new JLabel("Email");
		add(lblEmail).setBounds(410, 150, 100, 15);
		txtEmail = new JTextField();
		add(txtEmail).setBounds(410, 170, 160, 20);
		
		JLabel lblDepartment = new JLabel ("Department");
		add(lblDepartment).setBounds(600, 100, 100, 15);
		txtDepartment = new JTextField();
		add(txtDepartment).setBounds(600, 120, 160, 20);
		
		JLabel lblPosition = new JLabel("Job Title / Position");
		add(lblPosition).setBounds(600, 150, 100, 15);
		txtPosition = new JTextField();
		add(txtPosition).setBounds(600, 170, 160, 20);
		
		JButton btnAdd = new JButton("Add Employee");
		btnAdd.addActionListener(e -> addEmployee());
		add(btnAdd).setBounds(615, 200, 130, 20);
		
		setVisible(true);
	}
	
	public void loadData() {
		File data = new File("data.txt");
		if (data.exists() && data.length() > 0) {
			try (BufferedReader br = new BufferedReader(new FileReader(data))) {
				String line = "",
					id = "", name = "", birthdate = "", age = "", civilStatus = "", nationality = "", gender = "", contact = "", email = "", department = "", position = "";
				while ((line = br.readLine()) != null) {
					if (line.contains("Employee ID")) id = line.split(":")[1].trim();
					else if (line.contains("Fullname")) name = line.split(":")[1].trim();
					else if (line.contains("Date of Birth")) birthdate = line.split(":")[1].trim();
					else if (line.contains("Age")) age = line.split(":")[1].trim();
					else if (line.contains("Civil Status")) civilStatus = line.split(":")[1].trim();
					else if (line.contains("Nationality")) nationality = line.split(":")[1].trim();
					else if (line.contains("Gender")) gender = line.split(":")[1].trim();
					else if (line.contains("Contact Number")) contact = line.split(":")[1].trim();
					else if (line.contains("Email")) email = line.split(":")[1].trim();
					else if (line.contains("Department")) department = line.split(":")[1].trim();
					else if (line.contains("Job Title")) {
						position = line.split(":")[1].trim();
						model.addRow(new String[] {id, name, birthdate, age, civilStatus, nationality, gender, contact, email, department, position});
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addEmployee() {
		String id = txtID.getText(), 
			name = txtName.getText(),
			birthdate = txtBirth.getText(),
			age = txtAge.getText(),
			civilStatus = (String) cmbStatus.getSelectedItem(),
			nationality = txtNationality.getText(),
			gender = btnMale.isSelected() ? "Male" : "Female",
			contact = txtContact.getText(),
			email = txtEmail.getText(),
			department = txtDepartment.getText(),
			position = txtPosition.getText();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true))) {
			if (i == 1) bw.write("=== EMMPLOYEE MANAGEMENT SYSTEM DATA ===\n");
			if (!id.equals("")) {
				bw.write("Record " + (i++) + " :\n"
				+ "-----------------------------\n"
				+ "Employee ID: " + id
				+ "\nFullname: " + name
				+ "\nDate of Birth: " + birthdate
				+ "\nAge: " + age
				+ "\nCivil Status: " + civilStatus
				+ "\nNationality: " + nationality
				+ "\nGender: " + gender
				+ "\nContact Number: " + contact
				+ "\nEmail: " + email
				+ "\nDepartmental: " + department
				+ "\nJob Title / Position: " + position
				+ "\n---------------------------\n");
				model.addRow(new String[] {id, name, birthdate, age, civilStatus, nationality, gender, contact, email, department, position});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		clear();
		JOptionPane.showMessageDialog(null, "Record Saved!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtBirth.setText("");
		txtAge.setText("");
		cmbStatus.setSelectedIndex(0);
		txtNationality.setText("");
		btnMale.setSelected(true);
		txtContact.setText("");
		txtEmail.setText("");
		txtDepartment.setText("");
		txtPosition.setText("");
	}
	
	public static void main(String[] args) {
		new EMS();
	}
}
