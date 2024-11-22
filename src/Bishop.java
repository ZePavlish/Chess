public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }

        int deltaLine = toLine > line ? 1 : -1; // Направление по строкам
        int deltaColumn = toColumn > column ? 1 : -1; // Направление по столбцам

        int i = line + deltaLine;
        int j = column + deltaColumn;

        while (i != toLine && j != toColumn) {
            if (chessBoard.board[i][j] != null) { // Если на пути есть фигура, ход невозможен
                return false;
            }
            i += deltaLine;
            j += deltaColumn;
        }

        return true;
    }
}

