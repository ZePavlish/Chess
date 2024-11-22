public abstract class ChessPiece {
    // Поля
    protected String color;  // Цвет фигуры
    protected boolean check = true;  // Логическая переменная, по умолчанию true

    // Конструктор
    public ChessPiece(String color) {
        this.color = color;
    }

    // Метод для цвета фигуры
    public String getColor() {
        return this.color;
    }

    // Метод для проверки возможности движения
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    // Метод для получения символа фигуры
    public abstract String getSymbol();
}

