public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        // Логика белой пешки
        if (this.color.equals("White")) {

            if (line == 1) {

                if (toLine == line + 1 && toColumn == column) {
                    return true;
                }
                if (toLine == line + 2 && toColumn == column && chessBoard.board[line + 1][column] == null) {
                    return true;
                }
            } else if (toLine == line + 1 && toColumn == column) {

                return true;
            }
        }
        // Логика черной пешки
        else if (this.color.equals("Black")) {

            if (line == 6) {

                if (toLine == line - 1 && toColumn == column) {
                    return true;
                }
                if (toLine == line - 2 && toColumn == column && chessBoard.board[line - 1][column] == null) {
                    return true;
                }
            } else if (toLine == line - 1 && toColumn == column) {

                return true;
            }
        }
        return false;
    }
}
