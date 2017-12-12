package net.entetrs.xenon.commons.utils;

/**
 * accumulateur de temps qui se ré-intialise à une fréquence donnée. Permet de
 * "ticker" toutes les "n" secondes, par exemples.
 * 
 * @author robin
 *
 */
public class DeltaTimeAccumulator
{
	/**
	 * accumulateur de temps
	 */
	private float accumulator = 0;
	
	/**
	 * fonction à lancer quand l'accumulateur aura atteint le tick.
	 * permet de déclencher une méthode à interval régulier.
	 */
	private Runnable runnable = null;

	/**
	 * paramétrage du générateur de tick à une certaine fréquence, exprimée en
	 * millisecondes
	 */
	private float tickFrequency;

	public DeltaTimeAccumulator(float tickFrequency)
	{
		super();
		this.tickFrequency = tickFrequency;
	}
	
	public DeltaTimeAccumulator(float tickFrequency, Runnable runnable)
	{
		this(tickFrequency);
		this.runnable = runnable;
	}

	/**
	 * ajout un écart temporel à l'accumulateur et retourne "true" si la limite
	 * est dépassée.
	 * 
	 * @param deltaTime
	 * 		écart temporelle (exprimé en secondes).
	 * @return
	 * 		true si la limite est dépassée et relance l'accumulateur à partir de zéro.
	 */
	public boolean addAndCheck(float deltaTime)
	{
		this.accumulator += deltaTime;
		if (accumulator > tickFrequency)
		{
			accumulator = 0;
			if (runnable!=null)
			{
				runnable.run();
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}
