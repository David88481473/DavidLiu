public class Planet {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
		
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		double d;
		d = Math.sqrt(Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2));
		return d;
	}
	
	public double calcForceExertedBy(Planet p){
		double f;
		double G=6.67e-11;
		f = G*this.mass*p.mass/(Math.pow(this.calcDistance(p),2));
		return f;
	}
	
	public double calcForceExertedByX(Planet p){
		double fx;
		fx = this.calcForceExertedBy(p)/this.calcDistance(p)*(p.xxPos-this.xxPos);
		return fx;
	}
	public double calcForceExertedByY(Planet p){
		double fy;
		fy = this.calcForceExertedBy(p)/this.calcDistance(p)*(p.yyPos-this.yyPos);
		return fy;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double Netfx = 0;
		int length = allPlanets.length;
		int i = 0;
		while (i<=length-1){
			if(this.equals(allPlanets[i])){
			}else{
				Netfx = Netfx + this.calcForceExertedByX(allPlanets[i]);
			}
			i++;
		}
		return Netfx;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double Netfy = 0;
		int length = allPlanets.length;
		int i = 0;
		while (i<=length-1){
			if(this.equals(allPlanets[i])){
			}else{
				Netfy = Netfy + this.calcForceExertedByY(allPlanets[i]);
			}
			i++;
		}
		return Netfy;
	}
	
	public void update(double dt,double fX,double fY){
		double aX, aY, vX, vY;
		aX = fX / this.mass;
		aY = fY / this.mass;
		this.xxVel = this.xxVel + dt*aX;
		this.yyVel = this.yyVel + dt*aY;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}
	
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
	}
}
