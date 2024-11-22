public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            return true;
        }

        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        String opponentColor = this.color.equals("White") ? "Black" : "White";

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newLine = line + i;
                int newColumn = column + j;
                if (newLine >= 0 && newLine < 8 && newColumn >= 0 && newColumn < 8) {
                    ChessPiece piece = board.board[newLine][newColumn];
                    if (piece != null && piece.getColor().equals(opponentColor)) {
                        // Проверяем, может ли эта фигура атаковать короля
                        if (piece instanceof Pawn) {
                            // Пешка может атаковать по диагонали
                            if (Math.abs(i) == 1 && Math.abs(j) == 1) {
                                return true;
                            }
                        } else if (piece instanceof Rook || piece instanceof Queen) {
                            // Ладья или ферзь могут атаковать по вертикали или горизонтали
                            if (i == 0 || j == 0) {
                                return true;
                            }
                        } else if (piece instanceof Bishop || piece instanceof Queen) {
                            // Слон или ферзь могут атаковать по диагонали
                            if (Math.abs(i) == Math.abs(j)) {
                                return true;
                            }
                        } else if (piece instanceof King) {
                            // Король может атаковать все соседние клетки
                            return true;
                        }
                    }
                }
            }
        }
        return false; // Если ни одна фигура противника не может атаковать
    }
}
