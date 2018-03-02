import java.util.LinkedList;
import java.util.List;

public class Vehicle implements Comparable<Vehicle> {
  List<Ride> rides = new LinkedList<>();
  location position;
  int currentTime;

  Vehicle(){
    this.position = new location(0,0);
    this.currentTime = 0;
  }

  public void add(Ride ride){
    currentTime += location.getDistance(position, ride.startPoint);
    if(currentTime < ride.earlierTime){
      currentTime = ride.earlierTime;
    }
    currentTime += ride.cost;
    this.position = ride.endPoint;
    rides.add(ride);
  }

  @Override
  public String toString() {
    String output = "" + rides.size();
    for (Ride curr: rides) {
      output += " " + curr.id;
    }
    return output;
  }

  public int cost(Ride ride){
    int tempTime = currentTime + location.getDistance(position, ride.startPoint);
    if(tempTime < ride.earlierTime){
      tempTime = ride.earlierTime;
    }
    return tempTime;
  }

  @Override
  public int compareTo(Vehicle vehicle) {
    if (currentTime < vehicle.currentTime) {
      return -1;
    } else if (currentTime > vehicle.currentTime) {
      return 1;
    } else {
      return 0;
    }
  }
}
