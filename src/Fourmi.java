public class Fourmi {

    
public enum Direction { //Une énumération est un type de données particulier, dans lequel une variable ne peut prendre qu'un nombre restreint de valeurs. Ces valeurs sont des constantes nommées
    NORD(0, -1),
    EST(1, 0),
    SUD(0, 1),
    OUEST(-1, 0);
  
    public final int X, Y; 
  
    private Direction(int dX, int dY) {
      X = dX;
      Y = dY;
    }
  
    //retourne la direction qui est de 90 degrés vers la gauche
    //La méthode ordinal() permet de retrouver le numéro d'ordre d'un élément énuméré,
    //dans la liste de tous les éléments d'une énumération. Le premier numéro d'ordre est 0.
    public Direction gauche() {
      return Direction.values()[(this.ordinal() + 3) % 4];
    }
  
    // retourne la direction qui est de 90 degrés vers la droite
    public Direction droite() {
      return Direction.values()[(this.ordinal() + 1) % 4];
    }
  }

    public int x, y, steps;
    public Direction direction;
  
    public Fourmi(int x, int y, Direction direction) {
      this.x = x;
      this.y = y;
      this.direction = direction;
    }
  
    public boolean estDansMonde(Monde w) {
      return 0 <= x  &&  x < w.size  &&  0 <= y  &&  y < w.size;
    }
  
    public void step(Monde w) {
      Monde.Color c = w.getCellColor(x, y);
      // on change de direction selon les règles de la Fourmi De Langton
      direction = (Monde.Color.BLANC == c) ? direction.droite() : direction.gauche();
      w.setCellColor(x, y, c.inverse()); // inversion des couleurs
      x += direction.X; // mise à jours des coordonnées de la fourmi
      y += direction.Y;
      steps++; // incrémentation des pas
    }

   public String toString () {
   return String.format("Ant(%4d, %4d, %s)",x,y,direction); 
   }
  }