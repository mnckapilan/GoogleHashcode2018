import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {
  static Queue<Vehicle> vehicles;
  static List<Ride> rides;
  static int F;
  static int R;
  static int T;
  static int B;

  public static void main(String[] args){
    String[] list = new String[] {
        "input/a.in", "output/a.out",
        "input/b.in", "output/b.out",
        "input/c.in", "output/c.out",
        "input/d.in", "output/d.out",
        "input/e.in", "output/e.out"
    };
    for (int i = 0; i < list.length; i += 2) {
      String[] files = new String[]{list[i],list[i+1]};
      try {
        work(files);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  public static void work(String[] args) throws FileNotFoundException {
    File file = new File(args[0]);
    Scanner scanner = new Scanner(file);
    vehicles = new PriorityQueue<>();
    rides = new ArrayList<>();

    scanner.nextInt();
    scanner.nextInt();
    F = scanner.nextInt();
    R = scanner.nextInt();
    B = scanner.nextInt();
    T = scanner.nextInt();

    for (int i = 0; i < F; i++) {
      vehicles.add(new Vehicle());
    }


    for (int i = 0; i < R; i++) {
      location startPoint = new location(scanner.nextInt(), scanner.nextInt());
      location endPoint = new location(scanner.nextInt(), scanner.nextInt());
      int earlierTime = scanner.nextInt();
      int finalTime = scanner.nextInt();
      rides.add(new Ride(i,startPoint,endPoint,earlierTime,finalTime));
    }


    while (!rides.isEmpty()) {
      Vehicle vehicle = vehicles.poll();
      double maxRate = 0;
      Ride maxRide = rides.get(0);
      for (Ride ride: rides) {
        double rate = 1.0 * ride.score(vehicle.cost(ride))
            / (vehicle.cost(ride) + ride.cost);
        if (maxRate < rate) {
          maxRate = rate;
          maxRide = ride;
        }
      }
      vehicle.add(maxRide);
      vehicles.add(vehicle);
      rides.remove(maxRide);
    }


    PrintStream out = new PrintStream(new FileOutputStream(args[1]));
    for (Vehicle v: vehicles) {
      out.println(v.toString());
    }

    System.out.println("Done " + args[1]);
  }
}
