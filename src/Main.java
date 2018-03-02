import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  static Vehicle[] vehicles;
  static Ride[] rides;


  public static void main(String[] args) throws FileNotFoundException {
    String[] list = new String[] {
        "input/a.in", "output/a.out",
        "input/b.in", "output/b.out",
        "input/c.in", "output/c.out",
        "input/d.in", "output/d.out",
        "input/e.in", "output/e.out"
    };
    for (int i = 0; i < list.length; i += 2) {
      String[] files = new String[]{list[i],list[i+1]};
      work(files);
    }
  }

  public static void work(String[] args) throws FileNotFoundException {
    File file = new File(args[0]);
    Scanner scanner = new Scanner(file);


    int x = scanner.nextInt();
    int y = scanner.nextInt();
    int F = scanner.nextInt();
    int R = scanner.nextInt();
    int B = scanner.nextInt();
    int T = scanner.nextInt();

    vehicles = new Vehicle[F];
    rides = new Ride[R];

    for (int i = 0; i < F; i++) {
      vehicles[i] = new Vehicle();
    }

    for (int i = 0; i < R; i++) {
      location startPoint = new location(scanner.nextInt(), scanner.nextInt());
      location endPoint = new location(scanner.nextInt(), scanner.nextInt());
      int earlierTime = scanner.nextInt();
      int finalTime = scanner.nextInt();
      rides[i] = new Ride(i,startPoint,endPoint,earlierTime,finalTime,B);
    }

    Arrays.sort(rides, (a,b) -> {
      if((a.latestTime > b.latestTime)) {
        return 1;
      }
      return -1;
    });

    for(Ride currRide: rides) {
      int minTime = Integer.MAX_VALUE;
      Vehicle minVehicle = vehicles[0];
      for (Vehicle currVehicle : vehicles) {
        int temptime = currVehicle.cost(currRide);
        if (minTime > temptime) {
          minTime = temptime;
          minVehicle = currVehicle;
        }
      }
      minVehicle.add(currRide);
    }


    PrintStream out = new PrintStream(new FileOutputStream(args[1]));
    for (int i = F-1; i >= 0; i--) {
      out.println(vehicles[i].toString());
    }

    System.out.println("Done " + args[1]);
  }
}
