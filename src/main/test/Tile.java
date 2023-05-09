//
package test;

import java.util.Objects;

import java.util.Random;

import static java.lang.Math.abs;

public class Tile {
    public final char letter;
    public final int score;
    public final int[] Values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    //ctor
    private Tile(char letter) {
        this.letter = letter;
        this.score = getPoints(letter);

    }

    //getters
    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    public int[] getValues() {
        return Values;
    }

    int getPoints(char L) {
        int index = (int) (L - 'A');
        return Values[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {
        private static Bag SingleBag;
        private final int[] amountsMax = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        private int[] amounts = new int[26];
        private Tile[] Tiles = new Tile[26];
        //ctor
        private Bag() {
            System.arraycopy(amountsMax, 0, amounts, 0, amountsMax.length);
        }
        //getters
        public static Bag getBag() {
            if (SingleBag == null) {
                SingleBag = new Bag();
            }
            return SingleBag;
        }

        public int[] getAmountsMax() {
            return amountsMax;
        }

        public int[] getAmounts() {
            return amounts;
        }

        public void setAmounts(int[] amounts) {
            this.amounts = amounts;
        }

        public Tile[] getTiles() {
            return Tiles;
        }

        public void setTiles(Tile[] tiles) {
            Tiles = tiles;
        }

        private int size() {
            int sum = 0;
            for (int amount : amounts) {
                sum += amount;
            }
            return sum;
        }

        public int[] getQuantities() {
            int[] a = new int[26];
            System.arraycopy(amounts, 0, a, 0, amounts.length);
            return a;
        }

        public Tile getRand() {
            if (size() > 0) {
                Random rn = new Random();
                int i = abs(rn.nextInt() % 26);
                while (amounts[i % 26] == 0) {
                    i++;
                }
                char ot = (char) ('A' + (i % 26));
                return getTile(ot);
            }
            return null;
        }


        public void put(Tile tile) {
            if (amounts[tile.letter - 'A'] < amountsMax[tile.letter - 'A']) {
                for (int j = 0; j < 26; j++) {
                    if (Tiles[j] == tile) {
                        Tiles[j] = null;
                    }
                }
                this.amounts[tile.letter - 'A']++;
            }
        }

        public Tile getTile(char a) {
            if ((a >= 'A' && a <= 'Z') && amounts[a - 'A'] != 0) {
                amounts[a - 'A']--;
                Tile tile = new Tile(a);
                for (int j = 0; j < 26; j++) {
                    if (Tiles[j] == null) {
                        Tiles[j] = tile;
                        return tile;
                    }
                }
            }

            return null;
        }

    }

}




