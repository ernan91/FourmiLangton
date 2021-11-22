public class Monde {
    public static enum Color {
      BLANC, NOIR;
  
      public Color inverse() {
        return BLANC.equals(this) ? NOIR : BLANC;
      }
    }
  
    private Color[][] cells;
    public int size;
  
    public Monde(int size) {
      this.size = size;
      cells = new Color[size][size];
  
      // on initialise la couleur blanche des cellules
      for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
          cells[x][y] = Color.BLANC;
        }
      }
    }
  
    public Color getCellColor(int x, int y) {
      return cells[x][y];
    }
  
    public void setCellColor(int x, int y, Color c) {
      cells[x][y] = c;
    }
  
  }