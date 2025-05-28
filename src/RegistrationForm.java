import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    
    // Store references to form fields for validation
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> dayCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<String> yearCombo;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel submissionStatusLabel;
    
    public RegistrationForm() {
        setTitle("Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Create gradient from dark blue to purple
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(25, 25, 80),
                    getWidth(), getHeight(), new Color(60, 40, 120)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Left side with logo and title
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 50));
        
        JLabel logoLabel = new JLabel("logo");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 72));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("Registration Page");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalGlue());
        
        // Right side with form
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 100));
        
        JPanel formPanel = createFormPanel();
        rightPanel.add(formPanel);
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 60)); // change alpha value to adjust transparency
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
 
        
        // First Name and Last Name row
        gbc.gridy = 1;
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        namePanel.setOpaque(false);
        firstNameField = createTextField("First name");
        lastNameField = createTextField("Last name");
        namePanel.add(firstNameField);
        namePanel.add(lastNameField);
        panel.add(namePanel, gbc);
        
        // Date of Birth Label
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 5, 10); // More space above, less below
        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(dobLabel, gbc);
        
        // Date of Birth dropdowns
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 15, 10); // Normal spacing below
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dobPanel.setOpaque(false);
        dayCombo = createComboBox(getDays());
        monthCombo = createComboBox(getMonths());
        yearCombo = createComboBox(getYears());
        dobPanel.add(dayCombo);
        dobPanel.add(monthCombo);
        dobPanel.add(yearCombo);
        panel.add(dobPanel, gbc);
        
        // Reset insets for other fields
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Email
        gbc.gridy = 4;
        emailField = createTextField("Email");
        panel.add(emailField, gbc);
        
        // Phone
        gbc.gridy = 5; 
        phoneField = createTextField("Phone number");
        panel.add(phoneField, gbc);
        
        gbc.gridy = 6;
        passwordField = createPasswordField("Password");
        panel.add(passwordField, gbc);
        
        // Confirm Password
        gbc.gridy = 7;
        confirmPasswordField = createPasswordField("Confirm password");
        panel.add(confirmPasswordField, gbc);
        
        // Sign Up Button
        gbc.gridy = 8;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(createButton("Sign Up"), gbc);
        
        // Submission status label
        gbc.gridy = 9;
        gbc.insets = new Insets(10, 10, 10, 10);
        submissionStatusLabel = new JLabel("");
        submissionStatusLabel.setForeground(new Color(0, 200, 0)); // Green color for success
        submissionStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        submissionStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(submissionStatusLabel, gbc);
        
        return panel;
    }
    
    private JTextField createTextField(String placeholder) {
        JTextField field = new JTextField(15) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // No border - removed for cleaner appearance
            }
        };
        
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setOpaque(false);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setCaretColor(Color.BLACK);
        
        // Add placeholder functionality
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        
        field.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        
        return field;
    }
    
    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(15) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // No border - removed for cleaner appearance                             
            }                                                            
        };
                                                                                                                                              
        field.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        field.setOpaque(false);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setCaretColor(Color.BLACK);
        
        // Add placeholder functionality
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0); // Show placeholder text
        
        field.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('*'); // Hide password when typing
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0); // Show placeholder text
                }
            }
        });
        
        return field;
    }
    
    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<String>(items) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Create clipping area for rounded rectangle
                g2.setClip(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // No border - removed for cleaner appearance
            }
        };
        
        combo.setOpaque(false);
        combo.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        combo.setBackground(Color.WHITE);
        combo.setForeground(Color.BLACK);
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setPreferredSize(new Dimension(120, 35));
        
        // Set the renderer to have white background
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (!isSelected) {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
        
        return combo;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // No border for clean appearance
            }
        };
        
        button.setBackground(new Color(60, 40, 120)); // Purple background matching app gradient
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndSubmitForm();
            }
        });
        
        return button;
    }
    
    private void validateAndSubmitForm() {
        // Get all field values
        String firstName = getFieldValue(firstNameField, "First name");
        String lastName = getFieldValue(lastNameField, "Last name");
        String day = (String) dayCombo.getSelectedItem();
        String month = (String) monthCombo.getSelectedItem();
        String year = (String) yearCombo.getSelectedItem();
        String email = getFieldValue(emailField, "Email");
        String phone = getFieldValue(phoneField, "Phone number");
        String password = getPasswordValue(passwordField, "Password");
        String confirmPassword = getPasswordValue(confirmPasswordField, "Confirm password");
        
        // Validation checks
        if (isEmpty(firstName)) {
            showError("Please enter your first name.");
            return;
        }
        if (isEmpty(lastName)) {
            showError("Please enter your last name.");
            return;
        }
        if ("Day".equals(day) || "Month".equals(month) || "Year".equals(year)) {
            showError("Please select your complete date of birth.");
            return;
        }
        if (isEmpty(email)) {
            showError("Please enter your email address.");
            return;
        }
        if (!email.contains("@")) {
            showError("Please enter a valid email address (must contain @).");
            return;
        }
        if (isEmpty(phone)) {
            showError("Please enter your phone number.");
            return;
        }
        if (!isValidPhoneNumber(phone)) {
            showError("Phone number must contain only numbers.");
            return;
        }
        if (isEmpty(password)) {
            showError("Please enter a password.");
            return;
        }
        if (password.length() < 8) {
            showError("Password must be at least 8 characters long.");
            return;
        }
        if (isEmpty(confirmPassword)) {
            showError("Please confirm your password.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match. Please try again.");
            return;
        }
        
        // If all validations pass, show success message with submitted data
        String submittedData = "Account Successfully Created!\n\n" +
                             "Submitted Information:\n" +
                             "Name: " + firstName + " " + lastName + "\n" +
                             "Date of Birth: " + day + "/" + month + "/" + year + "\n" +
                             "Email: " + email + "\n" +
                             "Phone: " + phone + "\n" +
                             "Password: " + "*".repeat(password.length()) + " (" + password.length() + " characters)";
        
        JOptionPane.showMessageDialog(this, submittedData, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear the form after successful submission
        clearForm();
        
        // Show submission status
        submissionStatusLabel.setText("Form submitted successfully! You can register another account.");
        
        // Clear the status message after 5 seconds
        Timer timer = new Timer(5000, e -> submissionStatusLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    
    private void clearForm() {
        // Clear text fields by setting them back to placeholder text
        clearTextField(firstNameField, "First name");
        clearTextField(lastNameField, "Last name");
        clearTextField(emailField, "Email");
        clearTextField(phoneField, "Phone number");
        
        // Clear password fields
        clearPasswordField(passwordField, "Password");
        clearPasswordField(confirmPasswordField, "Confirm password");
        
        // Reset combo boxes to default selections
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
    }
    
    private void clearTextField(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
    }
    
    private void clearPasswordField(JPasswordField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0); // Show placeholder text
    }
    
    private String getFieldValue(JTextField field, String placeholder) {
        String value = field.getText();
        return placeholder.equals(value) ? "" : value;
    }
    
    private String getPasswordValue(JPasswordField field, String placeholder) {
        String value = String.valueOf(field.getPassword());
        return placeholder.equals(value) ? "" : value;
    }
    
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number contains only digits
        return phoneNumber.matches("\\d+");
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private String[] getDays() {
        String[] days = new String[32];
        days[0] = "Day";
        for (int i = 1; i <= 31; i++) {
            days[i] = String.valueOf(i);
        }
        return days;
    }
    
    private String[] getMonths() {
        return new String[]{"Month", "January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
    }
    
    private String[] getYears() {
        String[] years = new String[82];
        years[0] = "Year";
        for (int i = 1; i <= 81; i++) {
            years[i] = String.valueOf(2024 - i + 1);
        }
        return years;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new RegistrationForm().setVisible(true);
        });
    }
}