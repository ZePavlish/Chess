public class ChessBoard {

    public ChessPiece[][] board = new ChessPiece[8][8]; // создаем поле для игры
    String nowPlayer;

    // Флаги для отслеживания движения королей и ладей
    boolean whiteKingMoved = false;
    boolean blackKingMoved = false;
    boolean whiteRook0Moved = false;
    boolean blackRook0Moved = false;
    boolean whiteRook7Moved = false;
    boolean blackRook7Moved = false;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Отслеживаем движения королей и ладей
                if (board[startLine][startColumn] instanceof King) {
                    if (nowPlayer.equals("White")) {
                        whiteKingMoved = true;
                    } else {
                        blackKingMoved = true;
                    }
                }
                if (board[startLine][startColumn] instanceof Rook) {
                    if (startColumn == 0) {
                        if (nowPlayer.equals("White")) {
                            whiteRook0Moved = true;
                        } else {
                            blackRook0Moved = true;
                        }
                    } else if (startColumn == 7) {
                        if (nowPlayer.equals("White")) {
                            whiteRook7Moved = true;
                        } else {
                            blackRook7Moved = true;
                        }
                    }
                }

                // Проводим перемещение
                board[endLine][endColumn] = board[startLine][startColumn];
                board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    // Метод рокировки по 0-му столбцу
    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (whiteKingMoved || whiteRook0Moved) {
                System.out.println("Белый король или ладья уже двигались.");
                return false;
            }
            if (board[0][0] == null || board[0][0] instanceof Rook == false || board[0][4] == null || board[0][4] instanceof King == false) {
                System.out.println("Невозможно сделать рокировку.");
                return false;
            }
            if (isPathClear(0, 0, 0, 3)) {
                board[0][2] = board[0][4];  // Перемещаем короля
                board[0][0] = null;  // Убираем старое положение короля
                board[0][3] = board[0][0]; // Перемещаем ладью
                board[0][0] = null;  // Убираем ладью с исходной позиции
                whiteKingMoved = true;
                whiteRook0Moved = true;
                return true;
            }
        } else {
            // Аналогичная логика для черных
            if (blackKingMoved || blackRook0Moved) {
                System.out.println("Черный король или ладья уже двигались.");
                return false;
            }
            if (board[7][0] == null || board[7][0] instanceof Rook == false || board[7][4] == null || board[7][4] instanceof King == false) {
                System.out.println("Невозможно сделать рокировку.");
                return false;
            }
            if (isPathClear(7, 0, 7, 3)) {
                board[7][2] = board[7][4];  // Перемещаем короля
                board[7][0] = null;  // Убираем старое положение короля
                board[7][3] = board[7][0]; // Перемещаем ладью
                board[7][0] = null;  // Убираем ладью с исходной позиции
                blackKingMoved = true;
                blackRook0Moved = true;
                return true;
            }
        }
        return false;
    }

    // Метод рокировки по 7-му столбцу
    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (whiteKingMoved || whiteRook7Moved) {
                System.out.println("Белый король или ладья уже двигались.");
                return false;
            }
            if (board[0][7] == null || board[0][7] instanceof Rook == false || board[0][4] == null || board[0][4] instanceof King == false) {
                System.out.println("Невозможно сделать рокировку.");
                return false;
            }
            if (isPathClear(0, 7, 0, 5)) {
                board[0][6] = board[0][4];  // Перемещаем короля
                board[0][7] = null;  // Убираем старое положение короля
                board[0][5] = board[0][7]; // Перемещаем ладью
                board[0][7] = null;  // Убираем ладью с исходной позиции
                whiteKingMoved = true;
                whiteRook7Moved = true;
                return true;
            }
        } else {
            // Аналогичная логика для черных
            if (blackKingMoved || blackRook7Moved) {
                System.out.println("Черный король или ладья уже двигались.");
                return false;
            }
            if (board[7][7] == null || board[7][7] instanceof Rook == false || board[7][4] == null || board[7][4] instanceof King == false) {
                System.out.println("Невозможно сделать рокировку.");
                return false;
            }
            if (isPathClear(7, 7, 7, 5)) {
                board[7][6] = board[7][4];  // Перемещаем короля
                board[7][7] = null;  // Убираем старое положение короля
                board[7][5] = board[7][7]; // Перемещаем ладью
                board[7][7] = null;  // Убираем ладью с исходной позиции
                blackKingMoved = true;
                blackRook7Moved = true;
                return true;
            }
        }
        return false;
    }

    // Метод проверки пути для рокировки (между королем и ладьей)
    private boolean isPathClear(int startLine, int startColumn, int endLine, int endColumn) {
        int step = (startColumn < endColumn) ? 1 : -1;
        for (int col = startColumn + step; col != endColumn; col += step) {
            if (board[startLine][col] != null) {
                return false;
            }
        }
        return true;
    }
}
