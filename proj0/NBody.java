public class NBody {
	public static double readRadius(String file){
		In in = new In(file);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String file){
		int i = 0;
		In in = new In(file);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[N];
		while(i < N){
			planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
			i++;
		}
		return planets;
	}
	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		double time = 0;
		while (time <= T){
		int j = 0;
		int length = planets.length;
		double[] xForces = new double[length];
		double[] yForces = new double[length];
		while (j < length){
			xForces[j] = planets[j].calcNetForceExertedByX(planets);
			yForces[j] = planets[j].calcNetForceExertedByY(planets);
			j++;
		}
		int k = 0;
		while (k < length){
			planets[k].update(dt, xForces[k], yForces[k]);
			k++;
		}
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, imageToDraw, 2*radius, 2*radius);
		int i = 0;
		while (i < length){
		    planets[i].draw();
			i++;
		}
		StdDraw.show(10);	
		time = time + dt;
	}
	}
}
