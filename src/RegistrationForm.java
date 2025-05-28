import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {
    
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
                g2.setColor(Color.BLACK); // Dark gray instead of pure black
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Smaller rounded corners
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
        namePanel.add(createTextField("First name"));
        namePanel.add(createTextField("Last name"));
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
        dobPanel.add(createComboBox(getDays()));
        dobPanel.add(createComboBox(getMonths()));
        dobPanel.add(createComboBox(getYears()));
        panel.add(dobPanel, gbc);
        
        // Reset insets for other fields
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Email
        gbc.gridy = 4;
        panel.add(createTextField("Email"), gbc);
        
        // Phone
        gbc.gridy = 5; 
        panel.add(createTextField("Phone number"), gbc);
        
        gbc.gridy = 6;
        panel.add(createPasswordField("Password"), gbc);
        
        // Confirm Password
        gbc.gridy = 7;
        panel.add(createPasswordField("Confirm password"), gbc);
        
        // Sign Up Button
        gbc.gridy = 8;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(createButton("Sign Up"), gbc);
        
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
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1, true),
            BorderFactory.createEmptyBorder(12, 24, 12, 24)
        ));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RegistrationForm.this, "Registration submitted!");
            }
        });
        
        return button;
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