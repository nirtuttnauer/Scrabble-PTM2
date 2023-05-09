package test;

import java.util.ArrayList;

public class Board {
    private static Board SingleBoard;
    private final Place[][] Places = new Place[15][15];
    private ArrayList<Word> PlacedWords = new ArrayList<>();


    //ctor
    private Board() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                Places[i][j] = new Place(initBonus(i, j), initIsWord(i, j));
            }
    }

    //inits for the ctor
    private int initBonus(int i, int j) {
        final int[][] matrix = {
                {3, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 3},
                {1, 2, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 2, 1},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2},
                {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
                {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {3, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 3},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
                {2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {1, 2, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 2, 1},
                {3, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 3}};
        return matrix[i][j];
    }

    private boolean initIsWord(int i, int j) {
        final boolean[][] mat = {
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, false, true, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, true, false, false, false},
                {false, false, false, false, true, false, false, false, false, false, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false, false, false, true, false, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, true, false, false, false},
                {false, false, true, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, false, true, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
        };

        return mat[i][j];
    }

    //getter
    public static Board getBoard() {
        if (SingleBoard == null) {
            SingleBoard = new Board();
        }
        return SingleBoard;
    }

    public boolean boardLegal(Word word) {
        int x = (word.vertical) ? word.row : word.cols;
        int xtg = (word.vertical) ? word.cols : word.row;
        if ((xtg < 0 || x < 0) || (xtg > 15 || x > 15)) {
            return false;
        }
        if (isStarNotEmpty()) {
            return x + word.getLength() < 15;
        }
        if (xtg == 7) {
            return x + word.getLength() - 1 < 15 && x <= 7 && x + word.getLength() - 1 >= 7;
        }
        return false;
    }

    public int tryPlaceWord(Word word) {
        if (boardLegal(word) && dictionaryLegal(word)) {
            word = fullWord(word);

            ArrayList<Word> newWords = filterWords(getWords(word));
            int score = getScore(newWords);
            PlaceWord(word);
            addNewWords(newWords);
            return score;
        }
        return 0;
    }

    private Word fullWord(Word word) {
        Tile[] tiles = new Tile[word.getLength()];
        for (int i = 0; i < word.getLength(); i++) {
            int x = (word.isVertical()) ? i : 0;
            int y = (word.isVertical()) ? 0 : i;
            if (word.getTiles()[i] == null) {
                tiles[i] = SingleBoard.Places[word.row + x][word.cols + y].tile;
            } else {
                tiles[i] = word.getTiles()[i];
            }
        }
        return new Word(tiles, word.row, word.cols, word.isVertical());
    }

    private ArrayList<Word> filterWords(ArrayList<Word> words) {
        words.removeIf(word -> !dictionaryLegal(word) || SingleBoard.PlacedWords.contains(word));
        return words;
    }

    private Tile[][] getTiles() {
        Tile[][] tiles = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tiles[i][j] = SingleBoard.Places[i][j].tile;
            }
        }
        return tiles;
    }

    private ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();
        newWords.add(word);
        for (int i = 0; i < word.getLength(); i++) {
            int x = (word.isVertical()) ? i : 0;
            int y = (word.isVertical()) ? 0 : i;
            int w = (word.isVertical()) ? 0 : 1;
            int z = (word.isVertical()) ? 1 : 0;
            if (SingleBoard.Places[word.row + x + w][word.cols + y + z].tile != null || SingleBoard.Places[word.row + x - w][word.cols + y - z].tile != null) {
                newWords.add(createWord(word.Tiles[i], word.row + x, word.cols + y, word.isVertical()));
            }
        }
        return newWords;
    }

    private Word createWord(Tile tile, int i, int j, boolean vertical) {
        ArrayList<Tile> tilesLL = new ArrayList<>();
        int start = (vertical) ? j : i;
        int len = 0;
        int row, cols;
        if (vertical) {
            do {
                j--;
            } while (SingleBoard.Places[i][j].tile != null);
            if (start == j) {
                tilesLL.add(tile);
                row = i;
                cols = j;
                j++;
            } else {
                j++;
                row = i;
                cols = j;
            }


            while (SingleBoard.Places[i][j].tile != null) {
                len++;
                if (start == j) {
                    tilesLL.add(tile);
                } else {
                    tilesLL.add(SingleBoard.Places[i][j].tile);
                }
                j++;
            }
            if (start == j) {
                tilesLL.add(tile);
                j++;
                len++;
            }
            while (SingleBoard.Places[i][j].tile != null) {
                len++;
                if (start == j) {
                    tilesLL.add(tile);
                } else {
                    tilesLL.add(SingleBoard.Places[i][j].tile);
                }
                j++;
            }
        } else {
            do {
                i--;
            } while (SingleBoard.Places[i][j].tile != null);
            if (start == i) {
                tilesLL.add(tile);
                row = i;
                cols = j;
                i++;
            } else {
                i++;
                row = i;
                cols = j;
            }


            while (SingleBoard.Places[i][j].tile != null) {
                len++;
                if (start == i)
                    tilesLL.add(tile);
                else {
                    tilesLL.add(SingleBoard.Places[i][j].tile);
                }
                i++;
            }
            if (start == i) {
                tilesLL.add(tile);
                i++;
                len++;
            }
            while (SingleBoard.Places[i][j].tile != null) {
                len++;
                if (start == i)
                    tilesLL.add(tile);
                else {
                    tilesLL.add(SingleBoard.Places[i][j].tile);
                }
                i++;
            }
        }

        Tile[] tiles = new Tile[len];
        for (int k = 0; k < len; k++) {
            tiles[k] = tilesLL.get(k);
        }
        return new Word(tiles, row, cols, !vertical);
    }


    private boolean isStarNotEmpty() {
        return SingleBoard.Places[7][7].tile != null;
    }

    private boolean dictionaryLegal(Word word) {
        return true;
    }

    private int getScore(ArrayList<Word> words) {
        int wordsSum = 0;
        for (Word word : words) {
            int x, y;
            int sum = 0, mul = 1;
            if (boardLegal(word)) {
                for (int i = 0; i < word.getLength(); i++) {
                    if (word.isVertical()) {
                        x = i;
                        y = 0;
                    } else {
                        x = 0;
                        y = i;
                    }
                    if (isStarNotEmpty() && word.row + x == 7 && word.cols + y == 7) {
                        sum += word.Tiles[i].getScore();
                    } else if (Places[word.row + x][word.cols + y].isWordBonus) {
                        mul *= Places[word.row + x][word.cols + y].bonus;
                        sum += word.Tiles[i].getScore();
                    } else {
                        sum += word.Tiles[i].getScore() * Places[word.row + x][word.cols + y].bonus;
                    }

                }

            }
            wordsSum += sum * mul;

        }


        return wordsSum;
    }

    private boolean isBonusFree(int i, int j) {
        return SingleBoard.Places[i][j].tile == null;
    }

    private void PlaceWord(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            if (word.getTiles()[i] != null) {
                if (word.isVertical()) {
                    SingleBoard.Places[word.row + i][word.cols].tile = word.getTiles()[i];
                } else
                    SingleBoard.Places[word.row][word.cols + i].tile = word.getTiles()[i];
            }

        }
    }

    private void addNewWords(ArrayList<Word> words) {
        SingleBoard.PlacedWords.addAll(words);
    }

    public void printBoard() {
        System.out.println("Letters:");
        System.out.println("-----------------------------------");
        this.printBoardLetters();
        System.out.println("Scores:");
        System.out.println("-----------------------------------");
        this.printBoardScores();
        System.out.println("Bonuses:");
        System.out.println("-----------------------------------");
        this.printBoardBonus();
        System.out.println("-----------------------------------");
    }

    public void printBoardLetters() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].tile.letter + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    public void printBoardScores() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].tile.getScore() + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    public void printBoardBonus() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].bonus + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    private static class Place {
        private Tile tile;
        public final int bonus;
        public final boolean isWordBonus;

        //ctor
        public Place(int bonus, boolean isWordBonus) {
            this.tile = null;
            this.bonus = bonus;
            this.isWordBonus = isWordBonus;
        }

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tile) {
            this.tile = tile;
        }

        public int getBonus() {
            return bonus;
        }

        public boolean isWordBonus() {
            return isWordBonus;
        }


    }

}
