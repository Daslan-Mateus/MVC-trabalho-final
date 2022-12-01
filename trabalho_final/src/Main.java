import Model.CriarTabelas;
import View.TarefasView;

public class Main {

    public static void main(String[] args) {
        CriarTabelas.criarTabelas();
        TarefasView tarefasView = new TarefasView();
        tarefasView.setVisible(true);
    }
}
