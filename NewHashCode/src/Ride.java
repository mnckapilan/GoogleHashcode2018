import java.util.Arrays;
import java.util.List;

public class Ride {
  int id;
  location startPoint;
  location endPoint;
  int earlierTime;
  int latestTime;
  int finalTime;
  int cost;

  public Ride(int id, location startPoint, location endPoint, int earlierTime, int finalTime) {
    this.id = id;
    this.startPoint = startPoint;
    this.endPoint = endPoint;
    this.earlierTime = earlierTime;
    this.finalTime = finalTime;
    this.cost = location.getDistance(startPoint,endPoint);
    this.latestTime = finalTime - cost;
  }

  public int score(int t) {
    int score = cost;
    if(t <= earlierTime){
      score += Main.B;
    }
    if(t+cost > finalTime){
      score = 0;
    }
    return score;
  }

  public int nextRideDistance(Ride ride){
    return location.getDistance(endPoint,ride.startPoint);
  }



}