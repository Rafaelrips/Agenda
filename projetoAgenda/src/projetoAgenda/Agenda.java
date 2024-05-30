package projetoAgenda;


import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agenda {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField telefoneField;
    private JList<Contato> contatoList;
    private DefaultListModel<Contato> contatoListModel;
    private JButton adicionarButton;
    private JButton editarButton;
    private JButton excluirButton;

    public Agenda() {
        frame = new JFrame("Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        contatoListModel = new DefaultListModel<>();
        contatoList = new JList<>(contatoListModel);
        frame.add(new JScrollPane(contatoList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        inputPanel.add(telefoneField);

        adicionarButton = new JButton("Adicionar");
        editarButton = new JButton("Editar");
        excluirButton = new JButton("Excluir");

        inputPanel.add(adicionarButton);
        inputPanel.add(editarButton);
        inputPanel.add(excluirButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarContato();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirContato();
            }
        });

        frame.setVisible(true);
    }

    private void adicionarContato() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        if (!nome.isEmpty() && !telefone.isEmpty()) {
            Contato contato = new Contato(nome, telefone);
            contatoListModel.addElement(contato);
            nomeField.setText("");
            telefoneField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Nome e Telefone não podem estar vazios.");
        }
    }

    private void editarContato() {
        int selectedIndex = contatoList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contato contato = contatoListModel.getElementAt(selectedIndex);
            String novoNome = JOptionPane.showInputDialog(frame, "Editar Nome:", contato.getNome());
            String novoTelefone = JOptionPane.showInputDialog(frame, "Editar Telefone:", contato.getTelefone());

            if (novoNome != null && novoTelefone != null && !novoNome.isEmpty() && !novoTelefone.isEmpty()) {
                contato.setNome(novoNome);
                contato.setTelefone(novoTelefone);
                contatoList.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Nome e Telefone não podem estar vazios.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um contato para editar.");
        }
    }

    private void excluirContato() {
        int selectedIndex = contatoList.getSelectedIndex();
        if (selectedIndex != -1) {
            contatoListModel.removeElementAt(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um contato para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Agenda::new);
    }
}
