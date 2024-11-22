public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (line != toLine && column != toColumn) {
            return false;
        }

        if (line == toLine) { // Двигаемся по горизонтали
            int startCol = Math.min(column, toColumn) + 1;
            int endCol = Math.max(column, toColumn);
            for (int j = startCol; j < endCol; j++) {
                if (chessBoard.board[line][j] != null) { // Если на пути есть фигура, ход невозможен
                    return false;
                }
            }
        } else { // Двигаемся по вертикали
            int startRow = Math.min(line, toLine) + 1;
            int endRow = Math.max(line, toLine);
            for (int i = startRow; i < endRow; i++) {
                if (chessBoard.board[i][column] != null) { // Если на пути есть фигура, ход невозможен
                    return false;
                }
            }
        }
        return true;
    }
}
