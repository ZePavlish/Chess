public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (line == toLine || column == toColumn || Math.abs(line - toLine) == Math.abs(column - toColumn)) {

            if (line == toLine) { // Двигаемся по горизонтали
                int startCol = Math.min(column, toColumn) + 1;
                int endCol = Math.max(column, toColumn);
                for (int j = startCol; j < endCol; j++) {
                    if (chessBoard.board[line][j] != null) {
                        return false; // На пути есть фигура
                    }
                }
            } else if (column == toColumn) { // Двигаемся по вертикали
                int startRow = Math.min(line, toLine) + 1;
                int endRow = Math.max(line, toLine);
                for (int i = startRow; i < endRow; i++) {
                    if (chessBoard.board[i][column] != null) {
                        return false; // На пути есть фигура
                    }
                }
            } else { // Двигаемся по диагонали
                int rowStep = (toLine > line) ? 1 : -1;
                int colStep = (toColumn > column) ? 1 : -1;
                int i = line + rowStep;
                int j = column + colStep;
                while (i != toLine && j != toColumn) {
                    if (chessBoard.board[i][j] != null) {
                        return false; // На пути есть фигура
                    }
                    i += rowStep;
                    j += colStep;
                }
            }

            return true;
        }

        return false;
    }
}
