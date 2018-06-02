import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) throws Exception{
		
		//Temprorary line string
		String line = "";
		
		//Number of rows
		int R=0;
		//Number of columns
		int C=0;
		//Number of vehicles
		int F=0;
		//Number of rides
		int N=0;
		//Per-ride bonus for starting the ride on time
		int B=0;
		//The number of steps in the simulation
		int T=0;

		//File Scanner
		File file = new File(args[0]);
    		Scanner sc = new Scanner(file);
    	

		//Read first line 
		line = sc.nextLine();
		R = Integer.parseInt(line.split(" ")[0]);
		C = Integer.parseInt(line.split(" ")[1]);
		F = Integer.parseInt(line.split(" ")[2]);
		N = Integer.parseInt(line.split(" ")[3]);
		B = Integer.parseInt(line.split(" ")[4]);
		T = Integer.parseInt(line.split(" ")[5]);

        //Rides
        ArrayList<Ride> rides = new ArrayList<>(N);

        //Cars
        ArrayList<Car> cars = new ArrayList<>(F);
        for (int i = 0; i < F; i++) {
            cars.add(new Car());
        }

		//Go through the rides 
		for (int i=0;i<N;i++){
			line  = sc.nextLine();
			//Debug
//			System.out.println(line);
			int a = Integer.parseInt(line.split(" ")[0]);
			int b = Integer.parseInt(line.split(" ")[1]);
			int x = Integer.parseInt(line.split(" ")[2]);
			int y = Integer.parseInt(line.split(" ")[3]);
			int s = Integer.parseInt(line.split(" ")[4]);
			int f = Integer.parseInt(line.split(" ")[5]);
			rides.add(new Ride(i,a,b,x,y,s,f));
		}
		
		//Debug
//		System.out.println("R: "+R);
//		System.out.println("C: "+C);
//		System.out.println("F: "+F);
//		System.out.println("N: "+N);
//		System.out.println("B: "+B);
//		System.out.println("T: "+T);
//
//		System.out.println(rides);

		//GameLoop
//        strat1(cars, rides, T);
//        for (Car car: cars) {
//            System.out.print(car.completedRides.size() + " ");
//            for (Ride ride : car.completedRides) {
//                System.out.print(ride.number + " ");
//            }
//            System.out.println();
//        }

//        strat2(cars, rides, T);
//        for (Car car: cars) {
//            System.out.print(car.completedRides.size() + " ");
//            for (Ride ride : car.completedRides) {
//                System.out.print(ride.number + " ");
//            }
//            System.out.println();
//        }

        strat4(cars, rides, T);
        for (Car car: cars) {
            System.out.print(car.completedRides.size() + " ");
            for (Ride ride : car.completedRides) {
                System.out.print(ride.number + " ");
            }
            System.out.println();
        }

	}

	private static void strat1(ArrayList<Car> cars, ArrayList<Ride> rides, int T) {
	    for (Car car: cars) {
	        while (car.currentTime <= T) {
	            if (rides.isEmpty()) break;
                Ride closestRide = car.calcClosestRide(rides);

                if (closestRide.endTime < T) {
                    if (car.distanceToRideEnd(closestRide) + car.currentTime < closestRide.endTime) {
                        car.moveToNextRide(closestRide);
                        car.completeRide(closestRide);
                        rides.remove(closestRide);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private static void strat2(ArrayList<Car> cars, ArrayList<Ride> rides, int T) {
        for (Car car: cars) {
            if (rides.isEmpty()) break;
            while (car.currentTime <= T) {
                if (rides.isEmpty()) break;
                Ride closestRide = car.calcClosestRide(rides);

                if (closestRide.endTime < T) {
                    if (car.distanceToRideEnd(closestRide) + car.currentTime < closestRide.endTime) {
                        car.moveToNextRide(closestRide);
                        car.completeRide(closestRide);
                        rides.remove(closestRide);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            Random random = new Random();
            int randomInt = random.nextInt(rides.size());
            car.moveToNextRide(rides.get(randomInt));
            car.completeRide(rides.get(randomInt));
            rides.remove(rides.get(randomInt));
        }
    }

//    private static void strat3(ArrayList<Car> cars, ArrayList<Ride> rides, int T) {
//        for (Car car: cars) {
//            if (rides.isEmpty()) break;
//            while (car.currentTime <= T) {
//                if (rides.isEmpty()) break;
//                Ride closestRide = car.calcClosestRide(rides);
//
//                if (closestRide.endTime < T) {
//                    if (car.distanceToRideEnd(closestRide) + car.currentTime < closestRide.endTime) {
//                        car.moveToNextRide(closestRide);
//                        car.completeRide(closestRide);
//                        rides.remove(closestRide);
//                    } else {
//                        break;
//                    }
//                } else {
//                    break;
//                }
//            }
//            Random random = new Random();
//            int randomInt = random.nextInt(rides.size());
//            car.moveToNextRide(rides.get(randomInt));
//            car.completeRide(rides.get(randomInt));
//            rides.remove(rides.get(randomInt));
//        }
//    }

    private static void strat4(ArrayList<Car> cars, ArrayList<Ride> rides, int T) {
        for (Car car: cars) {
            if (rides.isEmpty()) break;
            while (car.currentTime <= T) {
                if (rides.isEmpty()) break;

                ArrayList<Ride> possibleRides = new ArrayList<>();

                for (Ride ride : rides) {
                    if (ride.endTime <= T) {
                        if (car.distanceToRideEnd(ride) + car.currentTime <= ride.endTime) {
                            possibleRides.add(ride);
                        }
                    }
                }

                if (possibleRides.isEmpty()) break;

                int maxDistance = 0;
                Ride maxRide = null;

                for (Ride ride : possibleRides) {
                    if (ride.distance > maxDistance) {
                        maxDistance = ride.distance;
                        maxRide = ride;
                    }
                }

                car.moveToNextRide(maxRide);
                car.completeRide(maxRide);
                rides.remove(maxRide);
            }
            Random random = new Random();
            int randomInt = random.nextInt(rides.size());
            car.moveToNextRide(rides.get(randomInt));
            car.completeRide(rides.get(randomInt));
            rides.remove(rides.get(randomInt));
        }
    }

    private static void strat5(ArrayList<Car> cars, ArrayList<Ride> rides, int T) {
        ArrayList<Ride> orderedRides = new ArrayList<>();

        while (!rides.isEmpty()) {
            int max=0;
            Ride maxRide = null;
            for (Ride ride: rides){
                if (ride.distance > max) max = ride.distance;
                maxRide = ride;
            }
            orderedRides.add(maxRide);
            rides.remove(maxRide);
        }

        int count = 0;
	    for (Car car: cars) {
            if (orderedRides.isEmpty()) break;
            while (car.currentTime <= T) {
                if (orderedRides.isEmpty()) break;
                if (count >= orderedRides.size()) break;

                car.moveToNextRide(orderedRides.get(count));
                car.completeRide(orderedRides.get(count));
                rides.remove(orderedRides.get(count));
                count++;
            }
        }
    }

}

