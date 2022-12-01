package View;

import Controller.Control;
import Controller.ListarTarefas;
import Model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class TarefasView extends JFrame{

    private JPanel listaPanel;
    private JPanel base;
    private ListarTarefas listarTarefasServico;
    private JTextField textField;
    private Control control;
    private ArrayList<Tarefa> lista = new ArrayList<>();

    public TarefasView(){
        setTitle("Lista de Tarefas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,500);
        listarTarefasServico = new ListarTarefas();
        configurarJanela();
    }

    private void configurarJanela() {
        listaPanel = new JPanel();
        base = new JPanel();
        base.setLayout(new BorderLayout());
        listaPanel.setLayout(new BoxLayout(listaPanel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listaPanel);
        popularLista();

        base.add(obterHeader(), BorderLayout.NORTH);
        base.add(scrollPane, BorderLayout.CENTER);
        base.add(obterFoot(),BorderLayout.SOUTH);

        add(base);
    }

    private JPanel obterHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.add(new JLabel("Tarefas"));
        JButton adTar = new JButton("Adicionar Tarefa");
        adTar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control = new Control();
                Tarefa tarefa  = new Tarefa();
                String text = textField.getText();
                if(!Objects.equals(text, "")){
                    System.out.println(text);
                    tarefa.setDescricao(text);
                    control.inserir(tarefa);
                }
                listaPanel.removeAll();
                popularLista();
                base.repaint();
                base.revalidate();
            }
        });
        panel.add(adTar);
        textField = new JTextField(8);
        panel.add(textField);
        panel.setPreferredSize(new Dimension(290,40));
        panel.setSize(290,20);
        return panel;
    }

    private JPanel obterFoot(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton atualizar = new JButton("Atualizar");
        atualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPanel.removeAll();
                popularLista();
                base.repaint();
                base.revalidate();
            }
        });
        JButton excluir = new JButton("Excluir Tarefa");
        excluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control = new Control();
                for(Tarefa tarefa : lista){
                    control.excluir(tarefa);
                }
                listaPanel.removeAll();
                popularLista();
                base.repaint();
                base.revalidate();
            }
        });

        panel.add(atualizar);
        panel.add(excluir);
        return panel;
    }


    private void popularLista(){
        for(Tarefa tarefa : listarTarefasServico.obterTodos()){
            listaPanel.add(criarItemDaLista(tarefa));
            if(tarefa.isConcluido()){
                lista.add(tarefa);
            }
        }
    }


    private JPanel criarItemDaLista(Tarefa item){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(item.isConcluido());
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                control = new Control();
                if(checkBox.isSelected() && !item.isConcluido()){
                    try {
                        control.concluir(item);
                        lista.add(item);
                    }catch (Exception a){
                        a.printStackTrace();
                    }
                }else{
                    try {
                        control.desconcluir(item);
                        lista.remove(item);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        JLabel label = new JLabel(item.getDescricao());
        panel.add(checkBox);
        panel.add(label);
        panel.setPreferredSize(new Dimension(290,20));
        return panel;
    }

}
