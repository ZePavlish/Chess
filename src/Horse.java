public class Horse extends ChessPiece {

    // Цвет
    public Horse(String color) {
        super(color);  // Вызов конструктора родительского класса для инициализации цвета
    }

    // Метод для цвета
    @Override
    public String getColor() {
        return this.color;
    }

    // Метод для проверки на ход
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, что конь не двигается на ту же клетку
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Конь ходит буквой "Г": на 2 клетки в одном направлении и на 1 клетку в другом
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}

