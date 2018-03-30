class location{
  int x;
  int y;
  location(int x, int y){
    this.x = x;
    this.y = y;
  }

  static public int getDistance(location l1, location l2){
    return Math.abs(l2.y - l1.y) + Math.abs(l2.x - l1.x);
  }
}