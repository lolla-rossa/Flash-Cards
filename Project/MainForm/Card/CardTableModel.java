package MainForm.Card;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CardTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<Card> cards;

    public CardTableModel(List<Card> cards){
        this.cards = cards;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public int getColumnCount(){
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Лицевая сторона";
            case 1:
                return "Обратная сторона";
            case 2:
                return "Метка";
        }
        return "";
    }

    public int getRowCount() {
        return cards.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Card card = cards.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return card.getFirstSide();
            case 1:
                return card.getSecondSide();
            case 2:
                return card.getTag();
        }
        return "";
    }
}
