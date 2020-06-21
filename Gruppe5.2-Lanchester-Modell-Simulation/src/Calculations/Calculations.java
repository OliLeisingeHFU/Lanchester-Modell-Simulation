package Calculations;

public class Calculations {
	
	public double lValue(long _g, long _h, double _r, double _s)	{
		return Math.pow((_s*_g), 2) - Math.pow((_r*_h), 2);
	}
	
	public double zeroCrossing(long _g, long _h, double _r, double _s, double _l) {
		
		if (_l > 0 || (_l == 0 && _g > _h)) {
			return Math.sqrt( (-_l)/_r );
		}	else	{
			return Math.sqrt( (-_l)/_s );
		}
	}
	
	public double gCurrent(long _g, long _h, double _r, double _s, double _t)	{
		return (-_g * ((Math.exp(Math.sqrt(_s*_r)*_t) + Math.exp(Math.sqrt(_s*_r)*_t))/2)) - ((Math.sqrt(_r/_s)*_h)*((Math.exp(Math.sqrt(_s*_r)*_t) - Math.exp(Math.sqrt(_s*_r)*_t))/2));
	}
	
	public double hCurrent(long _g, long _h, double _r, double _s, double _t)	{
		return (-_h * ((Math.exp(Math.sqrt(_s*_r)*_t) + Math.exp(Math.sqrt(_s*_r)*_t))/2)) - ((Math.sqrt(_r/_s)*_g)*((Math.exp(Math.sqrt(_s*_r)*_t) - Math.exp(Math.sqrt(_s*_r)*_t))/2));
	}
	
	public double remaining(long _g, long _h, double _r, double _s)	{
		if (lValue(_g, _h, _r, _s) > 0)	{
			return gCurrent(_g, _h, _r, _s, zeroCrossing(_g, _h, _r, _s, lValue(_g, _h, _r, _s))); 
		}	else	{
			return hCurrent(_g, _h, _r, _s, zeroCrossing(_g, _h, _r, _s, lValue(_g, _h, _r, _s))); 
		}	
	}
	
	
}
