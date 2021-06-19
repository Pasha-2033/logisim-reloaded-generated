import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

public class sample2 {

    public sample2() {

        JTree tree = createTree();
        tree.setToggleClickCount(0);
        tree.setCellRenderer(new StateRenderer());
        tree.setCellEditor(new StateEditor());
        tree.setEditable(true);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(tree));

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private JTree createTree() {
        int children = 4;
        int grandChildren = 2;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new State("Films", false));
        DefaultMutableTreeNode node;

        String[] cat = {"Sci-Fi", "Fantasy", "Action", "Comedy"};
        String[][] films = {
            {"Star Wars", "Star Trek"},
            {"Lord of the Rings", "Conan"},
            {"Terminator", "Transformers"},
            {"Cheaper by the Doze", "Father of the Bride"}
        };
        for (int j = 0; j < children; j++) {
            node = new DefaultMutableTreeNode(new State(cat[j], false));
            root.add(node);
            for (int k = 0; k < grandChildren; k++) {
                node.add(new DefaultMutableTreeNode(new State(films[j][k], false)));
            }
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        return new JTree(model);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                new sample2();
            }
        });
    }

    public class State {

        private String text;
        private boolean selected;

        public State(String text, boolean selected) {
            this.text = text;
            this.selected = selected;
        }

        public String getText() {
            return text;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

    }

    public class StateEditor extends AbstractCellEditor implements TreeCellEditor {

        //JPanel panel;
        private JCheckBox checkBox;

        private State editorValue;

        public StateEditor() {
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
        }

        @Override
        public Object getCellEditorValue() {
            editorValue.setSelected(checkBox.isSelected());
            return editorValue;
        }

        @Override
        public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {

            System.out.println("...");

            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                State state = (State) node.getUserObject();
                editorValue = state;
                checkBox.setText(state.getText());
                checkBox.setSelected(state.isSelected());
            } else {
                checkBox.setText("??");
                checkBox.setSelected(false);
            }

            return checkBox;

        }

    }

    public class StateRenderer implements TreeCellRenderer {

        private JCheckBox checkBox;

        public StateRenderer() {
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                        boolean leaf, int row, boolean hasFocus) {

            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                State state = (State) node.getUserObject();
                checkBox.setText(state.getText());
                checkBox.setSelected(state.isSelected());
            } else {
                checkBox.setText("??");
                checkBox.setSelected(false);
            }

            if (selected) {
                checkBox.setBackground(UIManager.getColor("Tree.selectionBackground"));
                checkBox.setForeground(UIManager.getColor("Tree.selectionForeground"));
            } else {
                checkBox.setForeground(tree.getForeground());
            }

            checkBox.setOpaque(selected);

            return checkBox;
        }
    }
}