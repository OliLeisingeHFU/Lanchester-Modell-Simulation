package Calculations;


public class Calculations {
	
	public static double atanh(double x) {
		return 0.5*Math.log( (x + 1.0) / (x - 1.0));
	}
	
	public static double lValue(long _g, long _h, double _r, double _s)	{
		return (_s * _g * _g) - (_r * _h * _h);
	}
	
	public static double zeroCrossing(long _g, long _h, double _r, double _s, double _l) {
		
		if (_l > 0 || (_l == 0 && _g > _h)) {
			return Math.sqrt( (_l)/_r );
		}	else	{
			return Math.sqrt( (-_l)/_s );
		}
	}
	
	public static double gCurrent(long _g, long _h, double _r, double _s, double _t)	{
		return (_g * ((Math.exp(Math.sqrt(_s*_r)*_t) + Math.exp( - (Math.sqrt(_s*_r)*_t)))/2)) - ((_r / (Math.sqrt(_r * _s))*_h)*((Math.exp(Math.sqrt(_s*_r)*_t) - Math.exp( - (Math.sqrt(_s*_r)*_t)))/2));
	}
	
	public static double hCurrent(long _g, long _h, double _r, double _s, double _t)	{
		return (_h * ((Math.exp(Math.sqrt(_s*_r)*_t) + Math.exp( - (Math.sqrt(_s*_r)*_t)))/2)) - ((_s / (Math.sqrt(_r * _s))*_g)*((Math.exp(Math.sqrt(_s*_r)*_t) - Math.exp( - (Math.sqrt(_s*_r)*_t)))/2));
	}
	
	public static double remaining(long _g, long _h, double _r, double _s)	{
		if (lValue(_g, _h, _r, _s) > 0)	{
			return gCurrent(_g, _h, _r, _s, zeroCrossing(_g, _h, _r, _s, lValue(_g, _h, _r, _s))); 
		}	else	{
			return hCurrent(_g, _h, _r, _s, zeroCrossing(_g, _h, _r, _s, lValue(_g, _h, _r, _s))); 
		}	
	}
	
	public static double tZero(long _g, long _h, double _r, double _s)    {
        if ( _g > _h)    {
        	return atanh((Math.sqrt(_r*_s)*_g)/(_r*_h))/Math.sqrt(_r*_s);
        }
        return atanh((Math.sqrt(_r*_s)*_h)/(_s*_g))/Math.sqrt(_r*_s);
    }
		
    
    public static double terminationValue(long _g, long _h, double _r, double _s)    {
        if ( _g < _h)    {
            return Math.log(0.5 / _h)/(-Math.sqrt(_r*_s));
        }
        return Math.log(0.5 / _g)/(-Math.sqrt(_r*_s)); 
    }
	
	
}
